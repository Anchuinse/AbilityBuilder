package com.creator.anchuinse.abilitybuilder.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.creator.anchuinse.abilitybuilder.R;

/**
 * Created by Matt on 6/23/18.
 */

public class PowersetNameDialog extends AppCompatDialogFragment {
    private EditText editTextInputName;
    private PowersetNameDialog.PowersetNameDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_powerset_name,null);

        builder.setView(view)
                .setTitle("Change Name")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String input_name = editTextInputName.getText().toString();
                        listener.applyText(input_name);
                    }
                });

        editTextInputName = view.findViewById(R.id.input_powerset_name);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (PowersetNameDialog.PowersetNameDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement listener");
        }
    }

    public interface PowersetNameDialogListener{
        void applyText(String input_name);
    }
}
