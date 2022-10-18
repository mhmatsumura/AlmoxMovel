package com.mhmatsu.almoxmovel;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.copel.mhmatsu.almoxmovel.BuildConfig;
import com.copel.mhmatsu.almoxmovel.R;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

public class Activity2 extends AppCompatActivity {

    private static final String[] GRUPOS = new String[]{" ", "Alças para alumínio", "Conectores cunha", "Medidores",
            "Ramais de alumínio", "Elos fusíveis", "Isoladores", "Dijuntores", "Porta fusíveis", "Conexões", "Cartuchos explosivos",
            "Chaves", "Alças/Laços de distribuição", "Outros", "-------------------------------------", "Ramais de cobre", "Iluminaçãço pública",
            "Alças de cobre", "Cabos"};

    private static final String[] ALCAS_PARA_ALUMINIO = new String[]{" ", "ALÇAS PARA ALUMÍNIO-Selecione...", "95604517-Alça serviço ca 16mm2",
            "95604553-Alça serviço ca 25mm2", "95604590-Alça serviço ca 35mm2"};

    private static final String[] CONECTORES_CUNHA = new String[]{" ", "CONECTORES-Selecione...", "95618838-Cunha tipo IV azul",
            "95618873-Cunha tipo III vermelho", "95618878-Cunha tipo II verde", "95618902-Cunha tipo I cinza"};


    private static final String[] MEDIDORES = new String[]{" ", "MEDIDORES-selecione...", "87504921-Medidor eletron. Monofásico 120v",
            "87503204-Medidor eletron. bifásico 120v", "87503875-Medidor eletron. trifásico 120v"};

    private static final String[] RAMAIS_DE_ALUMINIO = new String[]{" ", "RAMAIS DE ALUMÍNIO-Selecione...", "87511563-Duplex al 16mm2",
            "87511567-Triplex al 16mm2", "87509698-Triplex al 35mm2"};

    private static final String[] ELOS_FUSIVEIS = new String[]{" ", "ELOS-Selecione...", "95606310-Elo fusível 1H",
            "95611851-Elo fusível 1RH 0,5H", "95615451-Elo fusível 1RR OLHAL 0,5H", "95616342-Elo fusível 1RU OLHAL 1H"};

    private static final String[] ISOLADORES = new String[]{" ", "ISOLADORES-Selecione...", "95604733-Isolador pilar 15Kv",
            "95604707-Isolador pilar 35Kv", "95610424-Isolador bastão 15Kv"};

    private static final String[] DIJUNTORES = new String[]{" ", "DIJUNTORES-Selecione...", "87506981-Dj 1x30A ", "87506986-Dj 1x40A",
            "87507011-Dj 2x40A"};

    private static final String[] PORTA_FUSIVEIS = new String[]{" ", "PORTA FUSÍVEIS-Selecione...", "95611372-Porta fusível C 15Kv 100A cinza",
            "95611404-Porta fusível C 27Kv 100A cinza", "87511656-Lâmina para chave fusível 15Kv"};

    private static final String[] CONEXOES = new String[]{" ", "CONEXÕES-Selecione...", "95618683-Conec terminal cobre 16mm2",
            "95613896-Conec terminal 2/0 awg", "95610869-Emenda pré-formada 3x09", "95616176-Emenda pré-formada 3x10"};

    private static final String[] CARTUCHOS_EXPLOSIVOS = new String[]{" ", "CARTUCHOS-Selecione...", "95611698-Cartucho metálico azul",
            "95611724-Cartucho metálico vermelho", "95602230-Cartucho framaton azul"};

    private static final String[] CHAVES = new String[]{" ", "CHAVES-Selecione...", "95602640-Chave fusível C porcelana 15Kv 300A",
            "95602645-Chave fusível C porcelana 27Kv 300A", "95623369-LAMINA,DESLIGADORA; 15 KV;300A;NTC811250"};

    private static final String[] ALCAS_LACOS_DE_DISTRIBUICAO = new String[]{" ", "ALçAS DE DISTRIBUIçãO-Selecione...", "95604482-Alça estai 6mm",
            "95604487-Alça estai 9mm", "95610790-Alça fio aço 3x09"};

    private static final String[] OUTROS = new String[]{" ", "OUTROS-Selecione...", "87509987-Suporte para cf e para-raios",
            "95611477-Arruela quadrda zincada dn 18mm", "95619565-Porca olhal m16x2-7h zincado"};

    private static final String[] RAMAIS_DE_COBRE = new String[]{" ", "RAMAIS DE COBRE-Selecione...",
            "87502590-Duplex cu 16mm2", "87687641-Ramal de uso coletivo e subterrãneo", "87502566-Duplex cu 10mm2"};

    private static final String[] ILUMINACAO_PUBLICA = new String[]{" ", "ILUMINAÇÃO PÚBLICA-Selecione...", "95619099-Base 10A p/ rele fotoeletrico",
            "95619133-Base rl fotoel bf-60 60A c/ lumi n811324", "95618733-Braço ip tipo br-1"};

    private static final String[] ALCAS_PARA_COBRE = new String[]{" ", "ALçAS PARA COBRE-Selecione...", "95611915-Alça cobre 6mm2",
            "95618155-Alça cobre 10mm2", "95618190-Alça cobre 16mm2"};

    private static final String[] CABOS = new String[]{" ", "CABOS-Selecione...", "87511794-Cabo elet 1x2,5mm2 (m)",
            "87501299-Cabo elet pot bt cu pvc 1x4,0mm2 0,6/1Kv", "87509692-Cabo elet auto-sust duplex al 2 awg"};

    private Spinner combo = null;
    private ArrayAdapter adp, adp2;
    private EditText texto;
    private TextView textview;
    private Button button;
    private ImageButton imagebutton;
    private boolean Eh_grupo0, Eh_grupo1, Eh_grupo2, Eh_grupo3, Eh_grupo4, Eh_grupo5, Eh_grupo6, Eh_grupo7, Eh_grupo8, Eh_grupo9;
    private boolean usuarioInteragindo = false;
    private Spinner combo0, combo1, combo2, combo3, combo4, combo5, combo6, combo7, combo8, combo9;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity2);

        texto = (EditText) findViewById(R.id.data);
        texto.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        texto.requestFocus();

        combo0 = (Spinner) findViewById(R.id.item0);
        combo1 = (Spinner) findViewById(R.id.item1);
        combo2 = (Spinner) findViewById(R.id.item2);
        combo3 = (Spinner) findViewById(R.id.item3);
        combo4 = (Spinner) findViewById(R.id.item4);
        combo5 = (Spinner) findViewById(R.id.item5);
        combo6 = (Spinner) findViewById(R.id.item6);
        combo7 = (Spinner) findViewById(R.id.item7);
        combo8 = (Spinner) findViewById(R.id.item8);
        combo9 = (Spinner) findViewById(R.id.item9);

        Eh_grupo0 = true;
        Eh_grupo1 = true;
        Eh_grupo2 = true;
        Eh_grupo3 = true;
        Eh_grupo4 = true;
        Eh_grupo5 = true;
        Eh_grupo6 = true;
        Eh_grupo7 = true;
        Eh_grupo8 = true;
        Eh_grupo9 = true;

        adp = new ArrayAdapter<String>(this, R.layout.meu_spinner, GRUPOS);
        adp.setDropDownViewResource(R.layout.meu_spinner2);

        combo0 = (Spinner) findViewById(R.id.item0);
        combo0.setAdapter(adp);
        combo0.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if ((Eh_grupo0) && (usuarioInteragindo) && (position != 14) && (position != 0)) {

                    Eh_grupo0 = false;
                    selecionaGrupo(combo0, position);
                    salvaPreferencia("combo0p1", Integer.toString(position));
                    usuarioInteragindo = false;

                } else {

                    if (usuarioInteragindo) {

                        usuarioInteragindo = false;

                        if (position == 1) {

                            Eh_grupo0 = true;
                            texto = (EditText) findViewById(R.id.quantidade0);
                            texto.setText(" ");
                            combo0.setAdapter(adp);

                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        combo1 = (Spinner) findViewById(R.id.item1);
        combo1.setAdapter(adp);
        combo1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if ((Eh_grupo1) && (usuarioInteragindo) && (position != 14) && (position != 0)) {

                    Eh_grupo1 = false;
                    selecionaGrupo(combo1, position);
                    salvaPreferencia("combo1p1", Integer.toString(position));
                    usuarioInteragindo = false;

                } else {

                    if (usuarioInteragindo) {

                        usuarioInteragindo = false;

                        if (position == 1) {

                            Eh_grupo1 = true;
                            texto = (EditText) findViewById(R.id.quantidade1);
                            texto.setText(" ");
                            combo1.setAdapter(adp);

                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        combo2 = (Spinner) findViewById(R.id.item2);
        combo2.setAdapter(adp);
        combo2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if ((Eh_grupo2) && (usuarioInteragindo) && (position != 14) && (position != 0)) {

                    Eh_grupo2 = false;
                    selecionaGrupo(combo2, position);
                    salvaPreferencia("combo2p1", Integer.toString(position));
                    usuarioInteragindo = false;

                } else {
                    if (usuarioInteragindo) {

                        usuarioInteragindo = false;

                        if (position == 1) {

                            Eh_grupo2 = true;
                            texto = (EditText) findViewById(R.id.quantidade2);
                            texto.setText(" ");
                            combo2.setAdapter(adp);

                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        combo3 = (Spinner) findViewById(R.id.item3);
        combo3.setAdapter(adp);
        combo3.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if ((Eh_grupo3) && (usuarioInteragindo) && (position != 14) && (position != 0)) {

                    Eh_grupo3 = false;
                    selecionaGrupo(combo3, position);
                    salvaPreferencia("combo3p1", Integer.toString(position));
                    usuarioInteragindo = false;

                } else {
                    if (usuarioInteragindo) {

                        usuarioInteragindo = false;

                        if (position == 1) {

                            Eh_grupo3 = true;
                            texto = (EditText) findViewById(R.id.quantidade3);
                            texto.setText(" ");
                            combo3.setAdapter(adp);

                        }

                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        combo4 = (Spinner) findViewById(R.id.item4);
        combo4.setAdapter(adp);
        combo4.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if ((Eh_grupo4) && (usuarioInteragindo) && (position != 14) && (position != 0)) {

                    Eh_grupo4 = false;
                    selecionaGrupo(combo4, position);
                    salvaPreferencia("combo4p1", Integer.toString(position));
                    usuarioInteragindo = false;

                } else {
                    if (usuarioInteragindo) {

                        usuarioInteragindo = false;

                        if (position == 1) {

                            Eh_grupo4 = true;
                            texto = (EditText) findViewById(R.id.quantidade4);
                            texto.setText(" ");
                            combo4.setAdapter(adp);

                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        combo5 = (Spinner) findViewById(R.id.item5);
        combo5.setAdapter(adp);
        combo5.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if ((Eh_grupo5) && (usuarioInteragindo) && (position != 14) && (position != 0)) {

                    Eh_grupo5 = false;
                    selecionaGrupo(combo5, position);
                    salvaPreferencia("combo5p1", Integer.toString(position));
                    usuarioInteragindo = false;

                } else {
                    if (usuarioInteragindo) {

                        usuarioInteragindo = false;

                        if ((position == 1) || (position == 14)) {

                            Eh_grupo5 = true;
                            texto = (EditText) findViewById(R.id.quantidade5);
                            texto.setText(" ");
                            combo5.setAdapter(adp);

                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        combo6 = (Spinner) findViewById(R.id.item6);
        combo6.setAdapter(adp);
        combo6.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if ((Eh_grupo6) && (usuarioInteragindo) && (position != 14) && (position != 0)) {

                    Eh_grupo6 = false;
                    selecionaGrupo(combo6, position);
                    salvaPreferencia("combo6p1", Integer.toString(position));
                    usuarioInteragindo = false;

                } else {
                    if (usuarioInteragindo) {

                        usuarioInteragindo = false;

                        if (position == 1) {

                            Eh_grupo6 = true;
                            texto = (EditText) findViewById(R.id.quantidade6);
                            texto.setText(" ");
                            combo6.setAdapter(adp);

                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        combo7 = (Spinner) findViewById(R.id.item7);
        combo7.setAdapter(adp);
        combo7.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if ((Eh_grupo7) && (usuarioInteragindo) && (position != 14) && (position != 0)) {

                    Eh_grupo7 = false;
                    selecionaGrupo(combo7, position);
                    salvaPreferencia("combo7p1", Integer.toString(position));
                    usuarioInteragindo = false;

                } else {
                    if (usuarioInteragindo) {

                        usuarioInteragindo = false;

                        if (position == 1) {

                            Eh_grupo7 = true;
                            texto = (EditText) findViewById(R.id.quantidade7);
                            texto.setText(" ");
                            combo7.setAdapter(adp);

                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        combo8 = (Spinner) findViewById(R.id.item8);
        combo8.setAdapter(adp);
        combo8.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if ((Eh_grupo8) && (usuarioInteragindo) && (position != 14) && (position != 0)) {

                    Eh_grupo8 = false;
                    selecionaGrupo(combo8, position);
                    salvaPreferencia("combo8p1", Integer.toString(position));
                    usuarioInteragindo = false;

                } else {
                    if (usuarioInteragindo) {

                        usuarioInteragindo = false;

                        if (position == 1) {

                            Eh_grupo8 = true;
                            texto = (EditText) findViewById(R.id.quantidade8);
                            texto.setText(" ");
                            combo8.setAdapter(adp);

                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        combo9 = (Spinner) findViewById(R.id.item9);
        combo9.setAdapter(adp);
        combo9.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if ((Eh_grupo9) && (usuarioInteragindo) && (position != 14) && (position != 0)) {

                    Eh_grupo9 = false;
                    selecionaGrupo(combo9, position);
                    salvaPreferencia("combo9p1", Integer.toString(position));
                    usuarioInteragindo = false;

                } else {
                    if (usuarioInteragindo) {

                        usuarioInteragindo = false;

                        if (position == 1) {

                            Eh_grupo9 = true;
                            texto = (EditText) findViewById(R.id.quantidade9);
                            texto.setText(" ");
                            combo9.setAdapter(adp);

                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        ImageButton startBtn2 = (ImageButton) findViewById(R.id.gravar);
        startBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                salvaUltimo();
                Toast toast = Toast.makeText(getApplicationContext(),
                        "A requisição foi salva.", Toast.LENGTH_SHORT);
                toast.show();
            }

        });

        Button startBtn = (Button) findViewById(R.id.enviar);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                geraArquivoPdf();
                new emailAssincrono().execute(sendEmail(telaParaTexto()));
                salvaUltimo();
            }

        });

    }


    public void selecionaGrupo(Spinner c, int i) {

        String[] x = null;

        switch (i) {

            case 1:
                x = ALCAS_PARA_ALUMINIO;
                break;
            case 2:
                x = CONECTORES_CUNHA;
                break;
            case 3:
                x = MEDIDORES;
                break;
            case 4:
                x = RAMAIS_DE_ALUMINIO;
                break;
            case 5:
                x = ELOS_FUSIVEIS;
                break;
            case 6:
                x = ISOLADORES;
                break;
            case 7:
                x = DIJUNTORES;
                break;
            case 8:
                x = PORTA_FUSIVEIS;
                break;
            case 9:
                x = CONEXOES;
                break;
            case 10:
                x = CARTUCHOS_EXPLOSIVOS;
                break;
            case 11:
                x = CHAVES;
                break;
            case 12:
                x = ALCAS_LACOS_DE_DISTRIBUICAO;
                break;
            case 13:
                x = OUTROS;
                break;
            case 15:
                x = RAMAIS_DE_COBRE;
                break;
            case 16:
                x = ILUMINACAO_PUBLICA;
                break;
            case 17:
                x = ALCAS_PARA_COBRE;
                break;
            case 18:
                x = CABOS;
                break;


        }

        adp2 = new ArrayAdapter<String>(this, R.layout.meu_spinner, x);
        adp2.setDropDownViewResource(R.layout.meu_spinner2);

        c.setAdapter(adp2);
        c.performClick();


    }


    public void selecionaGrupo(Spinner c, int i, String s) {

        String[] x = null;



        switch (i) {

            case 1:
                x = ALCAS_PARA_ALUMINIO;
                break;
            case 2:
                x = CONECTORES_CUNHA;
                break;
            case 3:
                x = MEDIDORES;
                break;
            case 4:
                x = RAMAIS_DE_ALUMINIO;
                break;
            case 5:
                x = ELOS_FUSIVEIS;
                break;
            case 6:
                x = ISOLADORES;
                break;
            case 7:
                x = DIJUNTORES;
                break;
            case 8:
                x = PORTA_FUSIVEIS;
                break;
            case 9:
                x = CONEXOES;
                break;
            case 10:
                x = CARTUCHOS_EXPLOSIVOS;
                break;
            case 11:
                x = CHAVES;
                break;
            case 12:
                x = ALCAS_LACOS_DE_DISTRIBUICAO;
                break;
            case 13:
                x = OUTROS;
                break;
            case 15:
                x = RAMAIS_DE_COBRE;
                break;
            case 16:
                x = ILUMINACAO_PUBLICA;
                break;
            case 17:
                x = ALCAS_PARA_COBRE;
                break;
            case 18:
                x = CABOS;
                break;


        }

        adp2 = new ArrayAdapter<String>(this, R.layout.meu_spinner, x);
        adp2.setDropDownViewResource(R.layout.meu_spinner2);

        c.setAdapter(adp2);
        c.setSelection(Integer.parseInt(acessaPreferencia(s)), false);
    }


    protected String[][] telaParaTabela() {

        String[][] str = new String[11][4];

        for (int i = 0; i < 11; i++)
            for (int j = 0; j < 4; j++)
                str[i][j] = " ";

        if (!textoCombo(R.id.item0).equals(" ")) {

            str[0][0] = textoCombo(R.id.item0).substring(0, 8);
            str[0][1] = textoCombo(R.id.item0).substring(9, textoCombo(R.id.item0).length());
            str[0][2] = textoEditText(R.id.quantidade0);

            if (!textoCombo(R.id.item1).equals(" ")) {

                str[1][0] = textoCombo(R.id.item1).substring(0, 8);
                str[1][1] = textoCombo(R.id.item1).substring(9, textoCombo(R.id.item1).length());
                str[1][2] = textoEditText(R.id.quantidade1);

                if (!textoCombo(R.id.item2).equals(" ")) {

                    str[2][0] = textoCombo(R.id.item2).substring(0, 8);
                    str[2][1] = textoCombo(R.id.item2).substring(9, textoCombo(R.id.item2).length());
                    str[2][2] = textoEditText(R.id.quantidade2);

                    if (!textoCombo(R.id.item3).equals(" ")) {


                        str[3][0] = textoCombo(R.id.item3).substring(0, 8);
                        str[3][1] = textoCombo(R.id.item3).substring(9, textoCombo(R.id.item3).length());
                        str[3][2] = textoEditText(R.id.quantidade3);

                        if (!textoCombo(R.id.item4).equals(" ")) {


                            str[4][0] = textoCombo(R.id.item4).substring(0, 8);
                            str[4][1] = textoCombo(R.id.item4).substring(9, textoCombo(R.id.item4).length());
                            str[4][2] = textoEditText(R.id.quantidade4);

                            if (!textoCombo(R.id.item5).equals(" ")) {


                                str[5][0] = textoCombo(R.id.item5).substring(0, 8);
                                str[5][1] = textoCombo(R.id.item5).substring(9, textoCombo(R.id.item5).length());
                                str[5][2] = textoEditText(R.id.quantidade5);

                                if (!textoCombo(R.id.item6).equals(" ")) {


                                    str[6][0] = textoCombo(R.id.item6).substring(0, 8);
                                    str[6][1] = textoCombo(R.id.item6).substring(9, textoCombo(R.id.item6).length());
                                    str[6][2] = textoEditText(R.id.quantidade6);

                                    if (!textoCombo(R.id.item7).equals(" ")) {


                                        str[7][0] = textoCombo(R.id.item7).substring(0, 8);
                                        str[7][1] = textoCombo(R.id.item7).substring(9, textoCombo(R.id.item7).length());
                                        str[7][2] = textoEditText(R.id.quantidade7);

                                        if (!textoCombo(R.id.item8).equals(" ")) {


                                            str[8][0] = textoCombo(R.id.item8).substring(0, 8);
                                            str[8][1] = textoCombo(R.id.item8).substring(9, textoCombo(R.id.item8).length());
                                            str[8][2] = textoEditText(R.id.quantidade8);

                                            if (!textoCombo(R.id.item9).equals(" ")) {


                                                str[9][0] = textoCombo(R.id.item9).substring(0, 8);
                                                str[9][1] = textoCombo(R.id.item9).substring(9, textoCombo(R.id.item9).length());
                                                str[9][2] = textoEditText(R.id.quantidade9);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        str[10][0] = textoEditText(R.id.veiculo);
        str[10][1] = textoEditText(R.id.placa);
        str[10][2] = textoEditText(R.id.registro);
        str[10][3] = textoEditText(R.id.data);


        return str;
    }


    protected String telaParaTexto() {

        String s = " ";

        if (!textoCombo(R.id.item0).equals(" ")) {

            s += String.format("%-30.30s\n\n", "                    REQUISIÇÃO");


            s += String.format("%-8.8s    %-30.30s    %-3.3s\n", "CÓDIGO", "DESCRIÇãO", "QUANT.");

            s += String.format("%-8.8s    %-30.30s    %3.3s\n", textoCombo(R.id.item0).substring(0, 8),
                    textoCombo(R.id.item0).substring(9, textoCombo(R.id.item0).length()) + ": ",
                    textoEditText(R.id.quantidade0));


            if (!textoCombo(R.id.item1).equals(" ")) {

                s += String.format("%-8.8s    %-30.30s    %3.3s\n", textoCombo(R.id.item1).substring(0, 8),
                        textoCombo(R.id.item1).substring(9, textoCombo(R.id.item1).length()) + ": ",
                        textoEditText(R.id.quantidade1));

                if (!textoCombo(R.id.item2).equals(" ")) {

                    s += String.format("%-8.8s    %-30.30s    %3.3s\n", textoCombo(R.id.item2).substring(0, 8),
                            textoCombo(R.id.item2).substring(9, textoCombo(R.id.item2).length()) + ": ",
                            textoEditText(R.id.quantidade2));

                    if (!textoCombo(R.id.item3).equals(" ")) {


                        s += String.format("%-8.8s    %-30.30s    %3.3s\n", textoCombo(R.id.item3).substring(0, 8),
                                textoCombo(R.id.item3).substring(9, textoCombo(R.id.item3).length()) + ": ",
                                textoEditText(R.id.quantidade3));

                        if (!textoCombo(R.id.item4).equals(" ")) {


                            s += String.format("%-8.8s    %-30.30s    %3.3s\n", textoCombo(R.id.item4).substring(0, 8),
                                    textoCombo(R.id.item4).substring(9, textoCombo(R.id.item4).length()) + ": ",
                                    textoEditText(R.id.quantidade4));

                            if (!textoCombo(R.id.item5).equals(" ")) {


                                s += String.format("%-8.8s    %-30.30s    %3.3s\n", textoCombo(R.id.item5).substring(0, 8),
                                        textoCombo(R.id.item5).substring(9, textoCombo(R.id.item5).length()) + ": ",
                                        textoEditText(R.id.quantidade5));

                                if (!textoCombo(R.id.item6).equals(" ")) {


                                    s += String.format("%-8.8s    %-30.30s    %3.3s\n", textoCombo(R.id.item6).substring(0, 8),
                                            textoCombo(R.id.item6).substring(9, textoCombo(R.id.item6).length()) + ": ",
                                            textoEditText(R.id.quantidade6));

                                    if (!textoCombo(R.id.item7).equals(" ")) {


                                        s += String.format("%-8.8s    %-30.30s    %3.3s\n", textoCombo(R.id.item7).substring(0, 8),
                                                textoCombo(R.id.item7).substring(9, textoCombo(R.id.item7).length()) + ": ",
                                                textoEditText(R.id.quantidade7));

                                        if (!textoCombo(R.id.item8).equals(" ")) {


                                            s += String.format("%-8.8s    %-30.30s    %3.3s\n", textoCombo(R.id.item8).substring(0, 8),
                                                    textoCombo(R.id.item8).substring(9, textoCombo(R.id.item8).length()) + ": ",
                                                    textoEditText(R.id.quantidade8));

                                            if (!textoCombo(R.id.item9).equals(" ")) {


                                                s += String.format("%-8.8s    %-30.30s    %3.3s\n", textoCombo(R.id.item9).substring(0, 8),
                                                        textoCombo(R.id.item9).substring(9, textoCombo(R.id.item9).length()) + ": ",
                                                        textoEditText(R.id.quantidade9));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        s += String.format("%-15.15s\n", " ");

        s += String.format("%-15.15s    %-15.15s    %-15.15s\n\n",
                "VEÍCULO: " + textoEditText(R.id.veiculo), "PLACA: " + textoEditText(R.id.placa),
                "REGISTRO: " + textoEditText(R.id.registro));

        s += String.format("DATA: " + textoEditText(R.id.data));

        return s;

    }

    protected String textoCombo(int i) {
        combo = (Spinner) findViewById(i);
        String s = combo.getSelectedItem().toString();

        return s;

    }

    protected String textoEditText(int i) {
        texto = (EditText) findViewById(i);
        String s = texto.getText().toString();

        return s;

    }

    public void geraArquivoPdf() {

        Document document = new Document(PageSize.A4, 0f, 0f, 50f, 50f);

        try {

            String caminhoPdf = getExternalFilesDir(null) + "/Requisicao.pdf";

            PdfWriter.getInstance(document,
                    new FileOutputStream(caminhoPdf));

            document.open();

            PdfPTable table = new PdfPTable(8);

            float[] columnWidths = {100f, 10f, 96f, 100f, 100f, 100f, 100f, 100f,};

            table.setWidths(columnWidths);

            Font fonte_titulo = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Font fonte = FontFactory.getFont(FontFactory.COURIER, 11, BaseColor.BLACK);
            Font font_negrito = FontFactory.getFont(FontFactory.COURIER_BOLD, 12, BaseColor.BLACK);

            PdfPCell[][] celula = new PdfPCell[13][8];

            celula[0][0] = new PdfPCell(new Paragraph("REQUISIÇÃO", fonte_titulo));
            celula[0][0].setColspan(8);

            celula[0][0].setHorizontalAlignment(Element.ALIGN_CENTER);
            celula[1][0] = new PdfPCell(new Paragraph("CÓDIGO", font_negrito));
            celula[1][0].setColspan(2);
            celula[1][1] = new PdfPCell(new Paragraph("DESCRIÇÃO", font_negrito));
            celula[1][1].setColspan(5);
            celula[1][2] = new PdfPCell(new Paragraph("QUANT.", font_negrito));

            String[][] str2 = telaParaTabela();


            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 3; j++) {
                    celula[i + 2][j] = new PdfPCell(new Paragraph(str2[i][j], fonte));
                    if (j == 0) {
                        celula[i + 2][j].setColspan(2);
                    } else if (j == 1) {
                        celula[i + 2][j].setColspan(5);
                    } else if (j == 2) {
                        celula[i + 2][j].setHorizontalAlignment(Element.ALIGN_RIGHT);
                    }
                }
            }


            table.addCell(celula[0][0]);
            table.addCell(celula[1][0]);
            table.addCell(celula[1][1]);
            table.addCell(celula[1][2]);

            for (int i = 0; i < 10; i++)
                for (int j = 0; j < 3; j++)
                    table.addCell(celula[i + 2][j]);

            table.setSpacingAfter(10f);

            PdfPTable table2 = new PdfPTable(8);

            float[] columnWidths2 = {115f, 96f, 96f, 134f, 153f, 134f, 76f, 192f,};

            table2.setWidths(columnWidths2);

            celula[12][0] = new PdfPCell(new Paragraph("VEÍCULO", font_negrito));
            celula[12][1] = new PdfPCell(new Paragraph(str2[10][0], fonte));
            celula[12][1].setHorizontalAlignment(Element.ALIGN_CENTER);
            celula[12][2] = new PdfPCell(new Paragraph("PLACA", font_negrito));
            celula[12][3] = new PdfPCell(new Paragraph(str2[10][1], fonte));
            celula[12][3].setHorizontalAlignment(Element.ALIGN_CENTER);
            celula[12][4] = new PdfPCell(new Paragraph("REGISTRO", font_negrito));
            celula[12][5] = new PdfPCell(new Paragraph(str2[10][2], fonte));
            celula[12][5].setHorizontalAlignment(Element.ALIGN_CENTER);
            celula[12][6] = new PdfPCell(new Paragraph("DATA", font_negrito));
            celula[12][7] = new PdfPCell(new Paragraph(str2[10][3], fonte));
            celula[12][7].setHorizontalAlignment(Element.ALIGN_CENTER);

            table2.addCell(celula[12][0]);
            table2.addCell(celula[12][1]);
            table2.addCell(celula[12][2]);
            table2.addCell(celula[12][3]);
            table2.addCell(celula[12][4]);
            table2.addCell(celula[12][5]);
            table2.addCell(celula[12][6]);
            table2.addCell(celula[12][7]);

            document.add(table);
            document.add(table2);
            document.close();

        } catch (Exception e) {

        }
    }

    public void geraArquivo(String conteudoArquivo) {
        try {

            File arquivoTxt = new File(getExternalFilesDir(null) + "/AlmoxTxt.txt");

            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(arquivoTxt), "ISO-8859-1"));

            out.append(conteudoArquivo);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private class emailAssincrono extends AsyncTask<Intent, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Intent... intents) {
            Intent ei = intents[0];
            startActivity(ei);
            return null;
        }
    }


    protected Intent sendEmail(String tela) {

        Log.i("Send email", " ");

        String[] TO = {acessaPreferencia("endereco")};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "REQUISIÇÃO, PLACA: " + textoEditText(R.id.placa) + " VEÍCULO: " + textoEditText(R.id.veiculo));
        emailIntent.putExtra(Intent.EXTRA_TEXT, tela);

        String caminhoPdf = getExternalFilesDir(null) + "/Requisicao.pdf";
        File arquivoPdf = new File(caminhoPdf);
        Uri pdfURI = FileProvider.getUriForFile(Activity2.this, BuildConfig.APPLICATION_ID + ".fileprovider", arquivoPdf);
        emailIntent.putExtra(Intent.EXTRA_STREAM, pdfURI);

        return emailIntent;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.novo:
                apagaTela();
                return true;

            case R.id.email:
                cadastraEmail();
                return true;

            case R.id.ultimo:
                carregaUltimo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void salvaPreferencia(String chave, String valor) {

        SharedPreferences prefs = getSharedPreferences("preferencias_1", Context.MODE_PRIVATE);
        Editor ed = prefs.edit();
        ed.putString(chave, valor);
        ed.commit();

    }


    private String acessaPreferencia(String chave) {
        SharedPreferences prefs = getSharedPreferences("preferencias_1",
                Context.MODE_PRIVATE);
        String texto = prefs.getString(chave, "0");

        return (texto);
    }


    public void onUserInteraction() {
        super.onUserInteraction();
        usuarioInteragindo = true;
    }


    private void apagaCombo(Spinner c) {
        c.setAdapter(adp);
    }


    private void apagaTela() {

        usuarioInteragindo = false;
        apagaCombo(combo0);apagaCombo(combo1);apagaCombo(combo2);apagaCombo(combo3);apagaCombo(combo4);
        apagaCombo(combo5);apagaCombo(combo6);apagaCombo(combo7);apagaCombo(combo8);apagaCombo(combo9);

        Eh_grupo0 = true;Eh_grupo1 = true;Eh_grupo2 = true;Eh_grupo3 = true;Eh_grupo4 = true;
        Eh_grupo5 = true;Eh_grupo6 = true;Eh_grupo7 = true;Eh_grupo8 = true;Eh_grupo9 = true;

        texto = (EditText) findViewById(R.id.quantidade0);texto.setText("");
        texto = (EditText) findViewById(R.id.quantidade1);texto.setText("");
        texto = (EditText) findViewById(R.id.quantidade2);texto.setText("");
        texto = (EditText) findViewById(R.id.quantidade3);texto.setText("");
        texto = (EditText) findViewById(R.id.quantidade4);texto.setText("");
        texto = (EditText) findViewById(R.id.quantidade5);texto.setText("");
        texto = (EditText) findViewById(R.id.quantidade6);texto.setText("");
        texto = (EditText) findViewById(R.id.quantidade7);texto.setText("");
        texto = (EditText) findViewById(R.id.quantidade8);texto.setText("");
        texto = (EditText) findViewById(R.id.quantidade9);texto.setText("");
        texto = (EditText) findViewById(R.id.veiculo);texto.setText("");
        texto = (EditText) findViewById(R.id.placa);texto.setText("");
        texto = (EditText) findViewById(R.id.registro);texto.setText("");
        texto = (EditText) findViewById(R.id.data);texto.requestFocus();

    }

    private void cadastraEmail() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Activity2.this);
        alertDialogBuilder.setTitle("Cadastro de email.");

        final EditText input = new EditText(this);
        input.setText(acessaPreferencia("endereco"));

        alertDialogBuilder.setView(input);

        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                salvaPreferencia("endereco", input.getText().toString());
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void salvaUltimo() {

        salvaPreferencia("combo0p", Integer.toString(combo0.getSelectedItemPosition()));
        salvaPreferencia("combo1p", Integer.toString(combo1.getSelectedItemPosition()));
        salvaPreferencia("combo2p", Integer.toString(combo2.getSelectedItemPosition()));
        salvaPreferencia("combo3p", Integer.toString(combo3.getSelectedItemPosition()));
        salvaPreferencia("combo4p", Integer.toString(combo4.getSelectedItemPosition()));
        salvaPreferencia("combo5p", Integer.toString(combo5.getSelectedItemPosition()));
        salvaPreferencia("combo6p", Integer.toString(combo6.getSelectedItemPosition()));
        salvaPreferencia("combo7p", Integer.toString(combo7.getSelectedItemPosition()));
        salvaPreferencia("combo8p", Integer.toString(combo8.getSelectedItemPosition()));
        salvaPreferencia("combo9p", Integer.toString(combo9.getSelectedItemPosition()));

        salvaPreferencia("combo0p2", acessaPreferencia("combo0p1"));
        salvaPreferencia("combo1p2", acessaPreferencia("combo1p1"));
        salvaPreferencia("combo2p2", acessaPreferencia("combo2p1"));
        salvaPreferencia("combo3p2", acessaPreferencia("combo3p1"));
        salvaPreferencia("combo4p2", acessaPreferencia("combo4p1"));
        salvaPreferencia("combo5p2", acessaPreferencia("combo5p1"));
        salvaPreferencia("combo6p2", acessaPreferencia("combo6p1"));
        salvaPreferencia("combo7p2", acessaPreferencia("combo7p1"));
        salvaPreferencia("combo8p2", acessaPreferencia("combo8p1"));
        salvaPreferencia("combo9p2", acessaPreferencia("combo9p1"));

        texto = (EditText) findViewById(R.id.quantidade0);
        salvaPreferencia("quant0", texto.getText().toString());
        texto = (EditText) findViewById(R.id.quantidade1);
        salvaPreferencia("quant1", texto.getText().toString());
        texto = (EditText) findViewById(R.id.quantidade2);
        salvaPreferencia("quant2", texto.getText().toString());
        texto = (EditText) findViewById(R.id.quantidade3);
        salvaPreferencia("quant3", texto.getText().toString());
        texto = (EditText) findViewById(R.id.quantidade4);
        salvaPreferencia("quant4", texto.getText().toString());
        texto = (EditText) findViewById(R.id.quantidade5);
        salvaPreferencia("quant5", texto.getText().toString());
        texto = (EditText) findViewById(R.id.quantidade6);
        salvaPreferencia("quant6", texto.getText().toString());
        texto = (EditText) findViewById(R.id.quantidade7);
        salvaPreferencia("quant7", texto.getText().toString());
        texto = (EditText) findViewById(R.id.quantidade8);
        salvaPreferencia("quant8", texto.getText().toString());
        texto = (EditText) findViewById(R.id.quantidade9);
        salvaPreferencia("quant9", texto.getText().toString());

        texto = (EditText) findViewById(R.id.veiculo);
        salvaPreferencia("veiculo", texto.getText().toString());
        texto = (EditText) findViewById(R.id.placa);
        salvaPreferencia("placa", texto.getText().toString());
        texto = (EditText) findViewById(R.id.registro);
        salvaPreferencia("registro", texto.getText().toString());

    }


    private void verificaPreferencia(String s, String ss, Spinner c) {
        System.out.println("S=" + acessaPreferencia(s) + "SS=" + acessaPreferencia(ss)+"X");
        System.out.println(acessaPreferencia(s).equals("0"));
        if ((!acessaPreferencia(s).equals("0")) && (!acessaPreferencia(ss).equals("0"))) {
            System.out.println("eNTREI NO SELECIONA GRUPO DO= " + s +" "+ ss);
            selecionaGrupo(c, Integer.parseInt(acessaPreferencia(s)), ss);
        }
    }

    private boolean validaEhgrupo(String s, String ss) {
        if ((acessaPreferencia(s) != "0" && acessaPreferencia(ss) != "0")) {
            return false;
        } else {
            return true;
        }
    }


    private void carregaUltimo() {
System.out.println("ENTREI NO CARREGAR ULTIMO");
        apagaTela();

        Eh_grupo0 = validaEhgrupo("combo0p1", "combo0p");
        Eh_grupo1 = validaEhgrupo("combo1p1", "combo1p");
        Eh_grupo2 = validaEhgrupo("combo2p1", "combo2p");
        Eh_grupo3 = validaEhgrupo("combo3p1", "combo3p");
        Eh_grupo4 = validaEhgrupo("combo4p1", "combo4p");
        Eh_grupo5 = validaEhgrupo("combo5p1", "combo5p");
        Eh_grupo6 = validaEhgrupo("combo6p1", "combo6p");
        Eh_grupo7 = validaEhgrupo("combo7p1", "combo7p");
        Eh_grupo8 = validaEhgrupo("combo8p1", "combo8p");
        Eh_grupo9 = validaEhgrupo("combo9p1", "combo9p");

        verificaPreferencia("combo0p2", "combo0p", combo0);
        verificaPreferencia("combo1p2", "combo1p", combo1);
        verificaPreferencia("combo2p2", "combo2p", combo2);
        verificaPreferencia("combo3p2", "combo3p", combo3);
        verificaPreferencia("combo4p2", "combo4p", combo4);
        verificaPreferencia("combo5p2", "combo5p", combo);
        verificaPreferencia("combo6p2", "combo6p", combo6);
        verificaPreferencia("combo7p2", "combo7p", combo7);
        verificaPreferencia("combo8p2", "combo8p", combo8);
        verificaPreferencia("combo9p2", "combo9p", combo9);


        texto = (EditText) findViewById(R.id.quantidade0);
        texto.setText(acessaPreferencia("quant0"));
        texto = (EditText) findViewById(R.id.quantidade1);
        texto.setText(acessaPreferencia("quant1"));
        texto = (EditText) findViewById(R.id.quantidade2);
        texto.setText(acessaPreferencia("quant2"));
        texto = (EditText) findViewById(R.id.quantidade3);
        texto.setText(acessaPreferencia("quant3"));
        texto = (EditText) findViewById(R.id.quantidade4);
        texto.setText(acessaPreferencia("quant4"));
        texto = (EditText) findViewById(R.id.quantidade5);
        texto.setText(acessaPreferencia("quant5"));
        texto = (EditText) findViewById(R.id.quantidade6);
        texto.setText(acessaPreferencia("quant6"));
        texto = (EditText) findViewById(R.id.quantidade7);
        texto.setText(acessaPreferencia("quant7"));
        texto = (EditText) findViewById(R.id.quantidade8);
        texto.setText(acessaPreferencia("quant8"));
        texto = (EditText) findViewById(R.id.quantidade9);
        texto.setText(acessaPreferencia("quant9"));

        texto = (EditText) findViewById(R.id.veiculo);
        texto.setText(acessaPreferencia("veiculo"));
        texto = (EditText) findViewById(R.id.placa);
        texto.setText(acessaPreferencia("placa"));
        texto = (EditText) findViewById(R.id.registro);
        texto.setText(acessaPreferencia("registro"));

    }

    public float convertFromDp(int input) {
        final float scale = getResources().getDisplayMetrics().density;
        return ((input - 0.5f) / scale);
    }

}     
