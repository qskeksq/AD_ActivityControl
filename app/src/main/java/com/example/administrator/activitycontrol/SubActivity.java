package com.example.administrator.activitycontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.txt_value);
        button = (Button) findViewById(R.id.btn_return);

        // 이전 activity 에서 넘어온 intent 객체
        Intent intent = getIntent();

        // 값의 묶음을 꺼낸다 - 만약 putExtras 하지 않고 인텐트 넘기면 null 이 된다.
        Bundle bundle = intent.getExtras();

        // null 에서 값을 가져갔으므로 nullPointerException;
        if(bundle != null) {
            value = bundle.getString("key");
            textView.setText(value+"");
        }
        //cf) String value = getIntent.getStringExtras() 하면 예외처리를 해 줄 수 없다.

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(value);
                String temp = editText.getText().toString();
                int num2 = Integer.parseInt(temp);

                // 이 값을 setResult 에 넘겨준다.
                int result = num1+num2;

                // 결과값을 인텐트에 담아서
                Intent intent = new Intent();
                intent.putExtra("result", result);

                // setResult 에 넘겨준다. 결국은 이곳에서 인텐트를 넘겨주는군
                // 이것은 현재 액티비티에 담아둠. 이전 액티비티에 전달해 주는 것은 아니군.
                setResult(RESULT_OK, intent); // 상수에 한 번 들어가보자

                // 참고로 인텐트를 통해 액비티비간 정보를 교환하도록 설계되었음

                // 현재 앱을 종료
                finish();
            }
        });

    }
}
