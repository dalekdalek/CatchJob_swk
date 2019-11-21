package com.example.catchjob_swk;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {


    private DatePickerDialog.OnDateSetListener callbackMethod; // 생년월일 날짜선택을 위한 데이트피커 필드
    private TextView birthday; // 데이터피커에서 선택한 날짜를 양식(YYYY년 MM월 DD일)에 맞춰 보여줄 텍스트뷰 필드
    private FirebaseAuth mAuth; // Firebase 로그인 인증을 위한 필드
    private int Year; // 생년
    private int Month; // 월
    private int Day; // 일을 다른 메소드에 전달하기 위한 필드 (임시저장용)

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign); // activity_sign 레이아웃을 띄움

        birthday = findViewById(R.id.tview_birthday);

        mAuth = FirebaseAuth.getInstance();

        InitializeListener();

        findViewById(R.id.btn_createUser).setOnClickListener(onClickListener); // 어떤 버튼이든 눌리면 버튼관리 메소드 호출

    }

    View.OnClickListener onClickListener = new View.OnClickListener() { // 버튼관리 메소드? 인스턴스?
        @Override
        public void onClick(View view) {

            switch(view.getId()) {

                case R.id.btn_createUser :
                    ViewToast("계정을 생성하고 있습니다.");
                    SignUp(); // btn_createUser가 눌리면 SignUp 메소드 호출
                    break;

                case R.id.btn_createCancel :



            }
        }
    };

    public void InitializeListener() { // 데이트 피커 호출용 메소드, 건드릴거 ㄴㄴ
        callbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                birthday.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
                Year = year;
                Month = monthOfYear;
                Day = dayOfMonth;
            }
        };
    }

    public void OnClickHandler(View view) { // 데이트 피커 호출용 메소드2, 건드릴거 ㄴㄴ
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2019, 5, 24);
        dialog.show();
    }




    private void SignUp() { // SignUp 메소드 : Firebase-Authentication에 인증하기 위한 용도

        final String TAG = "SignUp"; // 메소드 태그 : 로그확인 용임

        final String Email = ((EditText)findViewById(R.id.edit_email)).getText().toString(); // 입력받은 이메일을 받아와 String 타입으로 변환
        final String Password = ((EditText)findViewById(R.id.edit_pw)).getText().toString(); // 입력받은 비밀번호를 받아와 String 타입으로 변환
        final String Name = ((EditText)findViewById(R.id.edit_name)).getText().toString(); // 입력받은 이름 가져옴
        final String Phone = ((EditText)findViewById(R.id.edit_phone)).getText().toString(); // 입력받은 연락처 가져옴

        final FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser(); // 현재 유저정보 가저옴(이 상태에선 이미 Firebase Authentication에 이메일과 비밀번호로 인증된 상태이므로)
        final FirebaseFirestore DB = FirebaseFirestore.getInstance(); // 데이터 베이스 인스턴스 선언

        if(!Email.contains("@") || !Email.contains(".")) {
            ViewToast("아이디(이메일)형식이 올바르지 않습니다.");
        }
        else if (Password.length() < 6) {
            ViewToast("비밀번호는 6자 이상이어야 합니다.");
        }
        else if(Name.length() == 0 || Phone.length() == 0 || Year == 0 || Month == 0 || Day == 0) {
            ViewToast("입력한 정보를 확인하시오.");
        }
        else {
            mAuth.createUserWithEmailAndPassword(Email, Password) // firebase 인증 메소드, 건드릴거 ㄴㄴ
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete (@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) { // 이메일과 비밀번호가 잘 등록되면
                                Log.d(TAG, "SignUp:success"); // SignUp 성공 로그를 띄우고
                                UserAccount userAccount = new UserAccount(Email, Name, Phone, Year, Month, Day); // UserAccount 인스턴스 선언 후 초기화

                                if(isIndividual()) // 개인계정이 체크되었다면
                                {
                                    DB.collection("IndividualUser").document(User.getUid()).set(userAccount) // DB의 IndividualUser Collection에 등록
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    ViewToast("개인 회원가입에 성공하였습니다.");
                                                    Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                                                    startActivity(intent);
                                                    Log.d(TAG, "DocumentSnapshot successfully written!");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w(TAG, "Error writing document", e);
                                                }
                                            });
                                }
                                else // 그렇지 않다면(= 법인계정이 체크되었다면)
                                {
                                    DB.collection("CorporationUser").document(User.getUid()).set(userAccount)// DB의 CorporationUser Collection에 등록
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    ViewToast("법인 회원가입에 성공하였습니다.");
                                                    Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                                                    startActivity(intent);
                                                    Log.d(TAG, "DocumentSnapshot successfully written!");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w(TAG, "Error writing document", e);
                                                }
                                            });
                                }
                            } else { // 실패하면
                                Log.w(TAG, "SignUp:failure", task.getException()); // SignIp 실패 로그와 실패 이유를 띄움
                                ViewToast("중복된 이메일입니다.");
                            }

                        }
                    });
        }

    }


    public void ViewToast(String message) { //toast 띄우는 메소드, 편리한 사용을 위해 만듦
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public boolean isIndividual() {
        RadioButton Individual = findViewById(R.id.radiobtn_individual); // 개인계정 라디오 버튼 가져옴,


        if(Individual.isChecked()) { // 개인계정에 체크되면,
            return true; // true 리턴
        }
        else { // 그렇지 않다면 ( 법인 계정이 체크되면 )
            return false; // false 리턴
        }
    }


}

