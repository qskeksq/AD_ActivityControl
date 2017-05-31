package com.example.administrator.activitycontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int REQ_CODE = 1;
    Button btn_sa, btn_sa_for_result;
    EditText editText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sa = (Button) findViewById(R.id.btn_startc_activity);
        btn_sa_for_result = (Button) findViewById(R.id.btn_startc_activity_for_result);
        editText = (EditText) findViewById(R.id.txt_return);

        btn_sa.setOnClickListener(this);
        btn_sa_for_result.setOnClickListener(this);

        intent = new Intent(this, SubActivity.class);

    }

    public void onClick(View view){
//        Intent intent; 이것을 전역으로 빼주는 이유는, 버튼을 클릭할 때마다 인텐트가 생성되기 때문이다.
//        하지만 계속 같은 인텐트를 사용하기 때문이다. 중복되지 않게 하기 위해 removeExtras 를 사용할 수는 있다.
        switch (view.getId()){
            // 일반 인텐트
            case R.id.btn_startc_activity:
//                intent = new Intent(this, SubActivity.class);
                intent.removeExtra("key"); //이것을 안 해주면 밑의 버튼을 클릭한 후 모든 인텐트에는 값이 계속 들어있다.
                startActivity(intent);
                break;
            // 값을 리턴받는 인텐트
            case R.id.btn_startc_activity_for_result:
//                intent = new Intent(this, SubActivity.class);
                intent.putExtra("key", editText.getText().toString());
                startActivityForResult(intent, REQ_CODE);
                break;
        }
    }

    // subActivity 를 finish 하는 순간 결과 값을 돌려받는 메소드
    // 암시적 인텐트에서 값을 전달받을 때도 사용할 수 있다!!! --> 추가 질문, 어떻게 그곳에서 값을 받아오는지?
    // 얘는 onResume 처음 시스템에서 자동으로 불러와주는 메소드임
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 받아온 결과가 RESULT_OK 일 때
        if(resultCode == RESULT_OK){
            // 내다 보낸 값이 REQ_CODE 일 때
            switch(requestCode){
                case REQ_CODE:
                    int result = data.getIntExtra("result", 0);
                    editText.setText(result+"");
                    Toast.makeText(getBaseContext(), "성공", Toast.LENGTH_SHORT).show();
            }

        }




        switch(requestCode){
            case REQ_CODE:
                if(resultCode == RESULT_OK){

                }
        }


    }
}
