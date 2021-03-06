package es.ulpgc.eite.da.basicquizlab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CheatActivity extends AppCompatActivity {

  public static final String TAG = "Quiz.CheatActivity";

  //private boolean comprobar;

  public final static String EXTRA_ANSWER = "EXTRA_ANSWER";
  public final static String EXTRA_CHEATED = "EXTRA_CHEATED";

  public static final String KEY_ANSWER = "KEY_ANSWER";
  public static final String KEY_botones_yes = "KEY_yes";
  public static final String KEY_botones_no = "KEY_no";
  public static final String KEY_pulsado = "KEY_pulsado";

  private Button noButton, yesButton;
  private TextView answerText;

  private int currentAnswer;
  private boolean answerCheated;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cheat);

    getSupportActionBar().setTitle(R.string.cheat_title);



    initLayoutData();

    linkLayoutComponents();
    //recuperamos estado
    if (savedInstanceState != null){
      String aux  = savedInstanceState.getString(KEY_ANSWER);
      answerText.setText(aux);

      yesButton.setEnabled(savedInstanceState.getBoolean(KEY_botones_yes));
      noButton.setEnabled(savedInstanceState.getBoolean(KEY_botones_no));
      answerCheated = savedInstanceState.getBoolean(KEY_pulsado);

    }
    enableLayoutButtons();


    /*if (savedInstanceState != null){
      String aux  = savedInstanceState.getString(KEY_ANSWER);
      answerText.setText(aux);

      yesButton.setEnabled(savedInstanceState.getBoolean(KEY_botones_yes));
      noButton.setEnabled(savedInstanceState.getBoolean(KEY_botones_no));
      answerCheated = savedInstanceState.getBoolean(KEY_pulsado);

    }*/
  }

  private void initLayoutData() {
    currentAnswer = getIntent().getExtras().getInt(EXTRA_ANSWER);
  }

  private void linkLayoutComponents() {
    noButton = findViewById(R.id.noButton);
    yesButton = findViewById(R.id.yesButton);

    answerText = findViewById(R.id.answerText);
  }

  private void enableLayoutButtons() {

    noButton.setOnClickListener(v -> onNoButtonClicked());
    yesButton.setOnClickListener(v -> onYesButtonClicked());
  }

  private void returnCheatedStatus() {
    Log.d(TAG, "returnCheatedStatus()");
    Log.d(TAG, "answerCheated: " + answerCheated);

    Intent intent = new Intent();
    intent.putExtra(EXTRA_CHEATED, answerCheated);
    setResult(RESULT_OK, intent);

    finish();
  }

  @Override
  public void onBackPressed() {
    Log.d(TAG, "onBackPressed()");

    returnCheatedStatus();
  }


  private void onYesButtonClicked() {
    yesButton.setEnabled(false);
    noButton.setEnabled(false);
    answerCheated = true;

    if(currentAnswer == 0) {
      answerText.setText(R.string.false_text);
    } else {
      answerText.setText(R.string.true_text);

    }
  }

  private void onNoButtonClicked() {
    yesButton.setEnabled(false);
    noButton.setEnabled(false);

    returnCheatedStatus();
  }
//implementamos guardado
  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(KEY_ANSWER,answerText.getText().toString());
    outState.putBoolean(KEY_botones_yes,yesButton.isEnabled());
    outState.putBoolean(KEY_botones_no,noButton.isEnabled());
    outState.putBoolean(KEY_pulsado,answerCheated);


 }
}
