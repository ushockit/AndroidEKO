package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.myapplication.dialogs.MyAlertDialog;
import com.example.myapplication.dialogs.SignInDialogFragment;
import com.example.myapplication.dialogs.ViewItemsDialogFragment;
import com.example.myapplication.viewmodels.SignInViewModel;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements SignInDialogFragment.SignInListener {

    Button btnShowDlg, btnSignIn, btnShowDlgItems;
    LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowDlg = findViewById(R.id.btnShowDlg);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnShowDlgItems = findViewById(R.id.btnShowDlgItems);
        root = findViewById(R.id.root);

        btnShowDlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAlertDialog alertDlg = new MyAlertDialog();
                alertDlg.show(getSupportFragmentManager(), "MyAlertDialog");
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInDialogFragment signInDlg = new SignInDialogFragment();
                signInDlg.show(getSupportFragmentManager(), "SignInDialog");
            }
        });

        btnShowDlgItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewItemsDialogFragment dlg = new ViewItemsDialogFragment();
                dlg.show(getSupportFragmentManager(), "ViewItemsDialog");
            }
        });
    }

    @Override
    public void onDialogSignInClick(DialogFragment dialog, SignInViewModel model) {
        Snackbar.make(root, model.toString(), Snackbar.LENGTH_LONG).show();
    }
}