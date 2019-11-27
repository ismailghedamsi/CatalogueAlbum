package com.example.tpfinal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class SignInFragment extends DialogFragment {
    PasswordAuthentificationInterface callback;
    FragmentSwitchable callback2;
    private static int currentView = 0;
    EditText passwordEdit ;
    EditText emailEdit ;
    private static final String KEY_EMAIL= "email_key";
    private static final String KEY_PASSWORD = "password_key";

    public interface FragmentSwitchable{
        public void onSwitchToSignUp();
    }

    public interface PasswordAuthentificationInterface{
        public void onAuthneticate(String email,String password);
    }

    public void setPasswordAuthentificationInterface(PasswordAuthentificationInterface callback) {
        this.callback = callback;
    }

    public static SignInFragment newInstance(int title) {
        SignInFragment frag = new SignInFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_password,null);
        passwordEdit = v.findViewById(R.id.input_password);
        emailEdit = v.findViewById(R.id.input_email);


        Button btn = v.findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onAuthneticate(passwordEdit.getText().toString(),emailEdit.getText().toString());
            }
        });
        Button link_sign_up = v.findViewById(R.id.link_signup);

        link_sign_up.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                callback2.onSwitchToSignUp();
            }
        });

        Button login = v.findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Test",Toast.LENGTH_LONG).show();
                callback.onAuthneticate(emailEdit.getText().toString(),passwordEdit.getText().toString());
            }
        });
        return new AlertDialog.Builder(getActivity()).setView(v).create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (PasswordAuthentificationInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement PasswordAuthentificationInterface");
        }

        try {
            callback2 = (FragmentSwitchable) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FragmentSwitchable");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_EMAIL,emailEdit.getText().toString());
        outState.putString(KEY_PASSWORD,passwordEdit.getText().toString());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_password,null);
        passwordEdit = v.findViewById(R.id.input_password);
        emailEdit = v.findViewById(R.id.input_email);

        String savedEmail = "a";
        String savedPassword = "a";
        if (savedInstanceState != null) {
            savedEmail = savedInstanceState.getString(KEY_EMAIL);
            savedPassword = savedInstanceState.getString(KEY_PASSWORD);
            Log.d(SignInFragment.class.getSimpleName(),"aaa"+savedEmail);


        }

        passwordEdit.setText(savedPassword);
        emailEdit.setText(savedEmail);
    }

}