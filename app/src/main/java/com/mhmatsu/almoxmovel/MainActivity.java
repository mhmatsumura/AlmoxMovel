package com.mhmatsu.almoxmovel;




import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private EditText texto;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (acessaPreferencia("endereco").equals("0")){

        	salvaPreferencia("quant0","");
        	salvaPreferencia("quant1","");
        	salvaPreferencia("quant2","");
        	salvaPreferencia("quant3","");
        	salvaPreferencia("quant4","");
        	salvaPreferencia("quant5","");
        	salvaPreferencia("quant6","");
        	salvaPreferencia("quant7","");
        	salvaPreferencia("quant8","");
        	salvaPreferencia("quant9","");
        	salvaPreferencia("registro","");
        	salvaPreferencia("placa","");
        	salvaPreferencia("veiculo","");
        	
       	 	//setContentView(R.layout.activity_main);

			cadastraEmail();

		} else{

        	Intent launchactivity= new Intent(MainActivity.this,Activity2.class);                               
          	startActivity(launchactivity); 
          	finish();

        }
        
   }
        
    private void cadastraEmail(){
    	
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
   		alertDialogBuilder.setTitle("É necessário cadastrar um endereço de email:");

   		final EditText input = new EditText(this);

   		alertDialogBuilder.setView(input);
		alertDialogBuilder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

				salvaPreferencia("endereco",input.getText().toString());

				Intent launchactivity= new Intent(MainActivity.this,Activity2.class);
				startActivity(launchactivity);
				finish();

			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
    }

    
    private void salvaPreferencia(String chave,String valor) {
    	
		SharedPreferences prefs = getSharedPreferences("preferencias_1", Context.MODE_PRIVATE);
 		Editor ed = prefs.edit();
 		ed.putString(chave,valor);
		ed.commit();
		
	}
    
    private String acessaPreferencia(String chave) {
		
	  SharedPreferences prefs = getSharedPreferences("preferencias_1", 
				Context.MODE_PRIVATE);
 	  String texto = prefs.getString(chave,"0");
		
	  return (texto);
	}
    private void textoParaTela(String s){
    	
    }
    
}     
       

    
    
    
    
    

    
    
        
    