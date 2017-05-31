# AD_ActivityControl
startActivityForResult-인텐트를 통한 액티비티 간 값 교환

이 함수로 Activity 를 실행하면 실행된 Activity 가 종료되면서 onActivityResult 함수를 호출해준다.
## startActivityForResult
```java
// 액티비티를 실행하는 버튼을 구분하기 위한 플래그
public static final int REQ_CODE = 1;

Intent intent = new Intent(this, SubActivity.class);
intent.putExtra("key", editText.getText().toString());
startActivityForResult(intent, REQ_CODE);
```

## setResult
호출되는 서브클래스에 작성되는 코드 
```java
// 결과값을 인텐트에 담아서
Intent intent = new Intent(); //이미 생성되어 있는 Activity를 사용하기 때문에 Context 가 필요하지 않다.
intent.putExtra("result", result);

// setResult 에 넘겨준다. 이곳에서 인텐트를 액티비티에 저장한다
// 현재 액티비티에 담아둔다. 이전 액티비티에 전달해 주는 것은 아니다.
// RESULT_OK 는 부모 Activity 에 이미 정의되어 있는 플래그값으로 된 처리가 성공적이라는 것을 의미한다.
// setResult 함수는 현재 Activity 에 Intent 를 저장하기 때문에 Context 를 따로 필요로 하지 않는다.
setResult(RESULT_OK, intent);


## onActivityResult
```java
@Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      // 받아온 결과가 RESULT_OK 일 때
      if(resultCode == RESULT_OK){
          // REQ_CODE 일 때
          switch(requestCode){
              case REQ_CODE:
              // 넘겨온 값이 없으면 0을             
                  int result = data.getIntExtra("result", 0);
                  editText.setText(result+"");
                  Toast.makeText(getBaseContext(), "성공", Toast.LENGTH_SHORT).show();
          }

      }
```
