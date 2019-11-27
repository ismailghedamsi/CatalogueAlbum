package com.example.tpfinal;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tpfinal.domain.RegisterUser;
import com.example.tpfinal.model.SignUpModel;


public class SignUpFragment extends DialogFragment {
    SignUpFragment.FragmentSwitchable callback;
    Button registerButton;
    TextView firstName;
    TextView lastName;
    TextView email;
    TextView password;
    public interface FragmentSwitchable{
        public void onSwitchToSignIn();
    }

    public void setPasswordFragmentSwitchable(SignUpFragment.FragmentSwitchable callback) {
        this.callback = callback;
    }

    public static SignUpFragment newInstance(int title) {
        SignUpFragment frag = new SignUpFragment();
        frag.setCancelable(false);
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_sign_up,null);
        registerButton = v.findViewById(R.id.btn_register);
        firstName = v.findViewById(R.id.input_first_name);
        lastName = v.findViewById(R.id.input_last_name);
        email = v.findViewById(R.id.input_email_sign_up);
        password = v.findViewById(R.id.input_password_sign_up);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"register",Toast.LENGTH_LONG).show();
                RegisterUser user = new RegisterUser(firstName.getText().toString(),lastName.getText().toString(),email.getText().toString(),password.getText().toString());
                SignUpModel.register(getContext(),user);
            }
        });

        Button link_sign_in = v.findViewById(R.id.link_sign_in);
        link_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSwitchToSignIn();
            }
        });

        return new AlertDialog.Builder(getActivity()).setView(v).setCancelable(false).create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            callback = (SignUpFragment.FragmentSwitchable) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FragmentSwitchable");
        }

    }


}
