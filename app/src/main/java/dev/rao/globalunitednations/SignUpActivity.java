package dev.rao.globalunitednations;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import dev.rao.globalunitednations.news.NewsActivity;

public class SignUpActivity extends AppCompatActivity {
    //Variables
    private EditText _emailText, _phoneNo, _userName;
    Button regBtn;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if (restoreLoginInfo()){
            startActivity(new Intent(SignUpActivity.this, NewsActivity.class));
        }

        progressDialog = new ProgressDialog(SignUpActivity.this);

        //Hooks to all xml elements in activity_sign_up.xm;
        _userName = (EditText) findViewById(R.id.sign_userName);
        _phoneNo = (EditText) findViewById(R.id.sign_phoneNUmber);
        _emailText = (EditText) findViewById(R.id.sign_EmailAddress);
        regBtn = (Button) findViewById(R.id.btn_signUp);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
    }

    public void login() {

       // startActivity(new Intent(getApplicationContext(), NewsActivity.class));
//        startActivity(new Intent(getApplicationContext(), UserWelcomeActivity.class));
      //  finish();
        if (!signup()) {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            return;
        }



//        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
//                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String userName = _userName.getText().toString();
        String email = _emailText.getText().toString();
        String phoneNo = _phoneNo.getText().toString();

        validatePhoneNumber(userName, phoneNo, email);
    }

    public boolean signup() {
        boolean valid = true;

        String userName = _userName.getText().toString();
        String email = _emailText.getText().toString();
        String phoneNo = _phoneNo.getText().toString();

        if (!(phoneNo.length() == 10 && Patterns.PHONE.matcher(phoneNo).matches()) ) {
            _phoneNo.setError("Enter a valid phone number");
            valid = false;
        }

        if (userName.isEmpty() || userName.length() < 3){
            _userName.setError("Enter a valid User Name");
        }else{
            _userName.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        return valid;
    }

    public void validatePhoneNumber(final String name, final String phone, final String email){
        Toast.makeText(this, "Ready", Toast.LENGTH_SHORT).show();
        final DatabaseReference dbRef;
        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("Users").child(phone).exists())){
                    HashMap<String, Object> userData = new HashMap<>();
                    userData.put("name", name);
                    userData.put("phone", phone);
                    userData.put("password", email);

                    dbRef.child("Users").child(phone).updateChildren(userData)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                        savePrefsData();
                                        Intent intent = new Intent(SignUpActivity.this, NewsActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        progressDialog.dismiss();
                                        Toast.makeText(SignUpActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
                else{
                    Toast.makeText(SignUpActivity.this, "This" + phone + "already exists", Toast.LENGTH_SHORT).show();
                    savePrefsData();
                    Intent intent = new Intent(SignUpActivity.this, NewsActivity.class);
                    startActivity(intent);
                    finish();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onSignupSuccess() {
        regBtn.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        regBtn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _userName.getText().toString();
        String mobile = _phoneNo.getText().toString();
        String email = _emailText.getText().toString();


        if (name.isEmpty() || name.length() < 3) {
            _userName.setError("at least 3 characters");
            valid = false;
        } else {
            _userName.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            _phoneNo.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _phoneNo.setError(null);
        }

        if (email.isEmpty() || email.length() < 4 || email.length() > 10) {
            _emailText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        return valid;
    }

    public boolean restoreLoginInfo() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("signupDetails",MODE_PRIVATE);
        Boolean isLoginBefore = pref.getBoolean("isLogin",false);
        return isLoginBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("signupDetails",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isLogin",true);
        editor.commit();
    }

}