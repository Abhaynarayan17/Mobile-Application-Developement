package com.example.agecalcdpd;
import androidx.appcompat.app.AppCompatActivity; import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent; import android.os.Bundle; import android.view.View; import android.widget.Button;
import android.widget.DatePicker; import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity { EditText name, num, date;
Button btn; int diff;

@SuppressLint("MissingInflatedId") @Override
protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main); name = findViewById(R.id.nameBox); num = findViewById(R.id.aadharBox);
date = findViewById(R.id.dateBox);
date.setOnClickListener(new View.OnClickListener() { @Override
public void onClick(View v) {
Calendar c = Calendar.getInstance(); int pYear = c.get(Calendar.YEAR);
int pMonth = c.get(Calendar.MONTH); int pDate = c.get(Calendar.DATE);
 
DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_DeviceDefault_DialogWhenLarge, new
DatePickerDialog.OnDateSetListener() { @Override
public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) { date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
diff = pYear - year;
}
},pYear, pMonth, pDate); dialog.show();
}
});
}

public void checkEligibility(View v) {
String s_name = name.getText().toString(); String s_num = num.getText().toString();
Intent in = new Intent(getApplicationContext(), ResultActivity.class); in.putExtra("NAME",s_name);
in.putExtra("AADHAR",s_num); in.putExtra("AGE",diff); startActivity(in);
}
}

activity_result.xml:

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
xmlns:android="http://schemas.android.com/apk/res/android" xmlns:app="http://schemas.android.com/apk/res-auto" xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent" android:layout_height="match_parent" tools:context=".ResultActivity">

<TextView
android:id="@+id/result"
android:layout_width="wrap_content" android:layout_height="wrap_content" android:gravity="center_horizontal" android:textStyle="bold"
app:layout_constraintBottom_toBottomOf="parent" app:layout_constraintEnd_toEndOf="parent" app:layout_constraintStart_toStartOf="parent" app:layout_constraintTop_toTopOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
ResultActivity.java:
package com.example.agecalcdpd;
 
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent; import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity { TextView res;
@Override
protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
setContentView(R.layout.activity_result); res = findViewById(R.id.result);
Intent intt = getIntent();
String r_name = intt.getStringExtra("NAME"); String r_aadhar = intt.getStringExtra("AADHAR"); int age = intt.getIntExtra("AGE",0);
if(age>=18)
res.setText("Name: "+r_name+"\nAadhar Number: "+r_aadhar+"\n You are eligible to vote"); else
res.setText("Name: "+r_name+"\nAadhar Number: "+r_aadhar+"\n You are not eligible to vote");
}
}
