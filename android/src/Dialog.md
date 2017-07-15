### Dialog

+ 전체 화면을 다 채우지 않고 일부 화면만 보여줌

+ AlerDialog의 구성요소

  + title-다이얼로그의 제목

    ```aDialog.setTitle("비밀번호 입력");```

  + Content-사용자에게 보여줄 내용(문자열,리스트,커스텀 레이아웃)

  + Button-버튼 3개 까지 가능

    + Positve-사용자가 허락(Ex. yes,ok)

      ```
      aDialog.setPositiveButton("확인", new DialogInterface.OnClickListener(){/*내용*/});
      ```

    + Negative-사용자가 취소(Ex. no,cancel)

      ```
      aDialog.setNegativeButton("취소", new DialogInterface.OnClickListener(){dialog.cancel();});
      ```

    + Neutral-중간 버튼

      ```
      aDialog.setNeutralButton("중강", new DialogInterface.OnClickListener(){/*내용*/});
      ```