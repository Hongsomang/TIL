### Spinner

+ 원하는 항목들을 선택 시 나열하여 선택

+ ArrayAdapter 클래스 사용-목록을 보여 주기 때문에

+ String, xml에 리스트를 추가 해 놓고 그 리스트를 불러온다

+ 스피너 생성 

   ```Spinner spinner = (Spinner) findViewById(R.id.eamil2);```

+ 어뎁터 생성

  ```ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.planets_array,android.R.layout.simple_spinner_item);```

+ 스피너와 어뎁터 연결

  ```
  spinner.setAdapter(adapter);
  ```

  ​

  ​

