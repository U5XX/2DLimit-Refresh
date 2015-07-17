package se.tothelimit.android;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import se.tothelimit.Input.Buffer;


/**
 * The Bluetooth handler for 2DLimit.
 * A modified version of the one we got from Andreas Göransson.
 *
 * @author Simon Cedergren
 * @author Fredrik Andersson
 * @author Andreas Göransson
 */
public class BluetoothHandler implements CheckBluetoothInteface {

    private final String tag = "2DLimit | BTHandler";

    private MainActivity main;
    private BluetoothAdapter mBluetoothAdapter;
    private ConnectThread mConnectThread;
    public ConnectedThread mConnectedThread;
    private final UUID SPP_UUID = UUID
            .fromString("00001101-0000-1000-8000-00805F9B34FB"); // UUID of the BT-module.
    private final static int REQUEST_ENABLE_BT = 335566;
    private BluetoothHandler btHandler;
    private boolean isConnected = false;
    private int reconnectionAttempts = 0;

    public BluetoothHandler(MainActivity main) {
        this.main = main;
        this.btHandler = this;
    }

    /**
     * Checks for Bluetooth on device and informs
     * user if device hasn't any Bluetooth module.
     */
    public void checkForBluetooth() {
        if (hasBluetoothSupport()) {
            ensureBluetoothEnabled();
        } else {
            showToast("Device does not support BlueTooth");
        }
    }

    /**
     * Shows a toast with the given string.
     *
     * @param message - the string to show in the toast.
     */
    private void showToast(final String message) {
        main.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(main.getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Checks if device has Bluetooth capabilities.
     *
     * @return true if device has Bluetooth capabilites.
     */
    private boolean hasBluetoothSupport() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return (mBluetoothAdapter != null);
    }

    /**
     * Sends an intent that asks user to
     * turn on the Bluetooth radio if it's
     * disabled.
     */
    private void ensureBluetoothEnabled() {
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            main.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            connectToBluetoothDevice("00:06:66:64:42:97"); // Hard coded MAC of the BT-device.
            Log.i(tag, "Connected to device!");
        }
    }

    /**
     * Tries to connect to a Bluetooth device with the given MAC-address.
     *
     * @param mac - the MAC-address to connect to.
     */
    private void connectToBluetoothDevice(String mac) {
        if (BluetoothAdapter.checkBluetoothAddress(mac)) {
            BluetoothDevice mBluetoothDevice = mBluetoothAdapter
                    .getRemoteDevice(mac);
            mConnectThread = new ConnectThread(mBluetoothDevice);
            mConnectThread.start();
        }
    }

    /**
     * Gently shuts down the threads.
     */
    public synchronized void stop() {
        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }
        isConnected = false;
    }

    /**
     * Class that handles the connection attempt.
     */
    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            BluetoothSocket tmp = null;
            mmDevice = device;
            try {
                tmp = device.createRfcommSocketToServiceRecord(SPP_UUID);
            } catch (IOException e) {
                Log.e(tag, "Unable to create RFCommSocket");
            }
            mmSocket = tmp;
        }

        /**
         * Tries to connect to the Bluetooth device with the given UUID.
         */
        public void run() {
            try {
                Log.i(tag, "Reconnection attempt #" + reconnectionAttempts);
                reconnectionAttempts++;
                mmSocket.connect();
                showToast("Connected to controller");
            } catch (IOException connectException) {
                Log.e(tag, "Unable to connect socket");

                RetryConnectionDialog dialog = new RetryConnectionDialog();
                dialog.setContext(main);
                dialog.setListener(btHandler);
                dialog.show(main.getFragmentManager(), dialog.getTag());

                try {
                    mmSocket.close();
                } catch (IOException closeException) {

                }

                return;
            }

            // When a connection has been set up successfully, start ConnectedThread and
            // set isConnected to true.
            mConnectedThread = new ConnectedThread(mmSocket);
            mConnectedThread.start();
            isConnected = true;
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {

            }
        }
    }

    class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(tag, "Unable to get streams");
            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;

            Log.i(tag, "ConnectedThread running...");
        }

        /**
         * Listens for incomming data and writes it to the
         * static buffer.
         */
        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);
                    if (bytes > 0) {
                        Log.i(tag, " ConnectedThread got value: " + buffer[0]);
                        Buffer.write(buffer[0]);
                    }

                } catch (IOException e) {
                    Log.e(tag, "IOException in ConnectedThread");
                    break;
                }
            }
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * Should be called from RetryConnectionDialog when
     * user taps the Retry-button if not already connected.
     */
    @Override
    public void checkBluetoothInteface() {
        if (!isConnected) {
            checkForBluetooth();
        }
    }
}
