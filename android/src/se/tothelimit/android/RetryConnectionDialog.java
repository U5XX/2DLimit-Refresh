package se.tothelimit.android;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;

/**
 * A dialog that asks user to try to reconnect to the Arduino.
 * @author Simon Cedergren <simon@tuxflux.se>
 *
 */
public class RetryConnectionDialog extends DialogFragment {
	private Builder builder;
	private Context context;
	private View view;
	private CheckBluetoothInteface listener;
	
	/**
	 * Sets context for the dialog.
	 * @param context - the context of the dialog.
	 */
	public void setContext(Context context) {
		this.context = context;
	}
	
	/**
	 * Sets the listener of the dialog.
	 * @param listener - the listener to the dialog.
	 */
	public void setListener(CheckBluetoothInteface listener){
		this.listener = listener;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		builder = new AlertDialog.Builder(context);
		view = new View(context);
		builder.setTitle("Unable to connect to controller").setMessage("Do you want to retry?").setPositiveButton("Retry", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Interface to BluetoothHandler that triggers a reconnect attempt.
				listener.checkBluetoothInteface();
			}
		});
		
		builder.setNegativeButton("Cancel", new 
				OnClickListener(){

			@Override
			public void onClick(DialogInterface diag, int arg1) {
				diag.dismiss();
			}
		});
		
		return builder.create();
	}
}
