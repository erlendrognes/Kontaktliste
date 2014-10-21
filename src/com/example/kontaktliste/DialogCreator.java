/*package com.example.kontaktliste;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

@SuppressLint("ValidFragment")
public class DialogCreator extends DialogFragment {

	private ContactDetails cd;
	private Main mActivity;
	private Contact contact;

	public DialogCreator(Contact contact, ContactDetails activity) {
		cLActivity = activity;
		this.contact = contact;
	}

	public DialogCreator(Main activity) {
		mActivity = activity;
	}

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		if (cd != null) {
			String firstname = contact.getFirstname();
			return new AlertDialog.Builder(getActivity())
					.setTitle(getString(R.string.confirmDelete))
					.setMessage("Do you want to delete " + firstname + "?")
					.setPositiveButton(getString(R.string.yes),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int whichButton) {
									cd.delCon(contact.getId());

								}
							})
					.setNegativeButton(getString(R.string.no),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int whichButton) {

								}
							}).create();
		}

		if (mActivity != null) {
			return new AlertDialog.Builder(getActivity())
					.setTitle(getString(R.string.about_title))
					.setMessage(getString(R.string.about_body))
					.setPositiveButton(getString(R.string.ok),null).create();
		}

		return null;

	}

}
*/