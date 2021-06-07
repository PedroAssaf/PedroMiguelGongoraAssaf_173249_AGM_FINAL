package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;


public class FormularioPersonagemActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagem"; // Titulo na barra top
    private static final String TITULO_NOVO_PERSONAGEM = "Novo Personagem"; // Titulo na barra top
    private EditText nomePersonagem;        // Obter nome do personagem
    private EditText alturaPersonagem;      // Altura do personagem
    private EditText nascimentoPersonagem;  // Nascimento do personagem
    private EditText telefonePersonagem;    // Telefone do personagem
    private EditText enderecoPersonagem;    // Endereço do personagem
    private EditText cepPersonagem;         // CEP do personagem
    private EditText rgPersonagem;          // RG do personagem
    private EditText generoPersonagem;      // Genero do personagem
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //Função para checar(icone) salvando os dados do personagem
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //Metodo para clicar no check e salvar os dados do personagem
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_personagem_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        inicializacaoCampos(); //Construção/Organização do formulario do personagem

        carregaPersonagem();//Função do formulario utilizando informações do personagem

        configuraBotao(); //Função do evento de click do botão para salvar

    }

    private void carregaPersonagem() { //Utilizar as informações para popular a lista personagem
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PERSONAGEM)) {
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencherCampos();
        } else {
            setTitle(TITULO_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
    }

    private void preencherCampos() { //Função para relacionar as todas as informações de nome, altura e nascimento para serem setados na lista
        nomePersonagem.setText(personagem.getNome());
        alturaPersonagem.setText(personagem.getAltura());
        nascimentoPersonagem.setText(personagem.getNascimento());
        telefonePersonagem.setText(personagem.getTelefone());
        enderecoPersonagem.setText(personagem.getEndereco());
        cepPersonagem.setText(personagem.getCep());
        rgPersonagem.setText(personagem.getRg());
        generoPersonagem.setText(personagem.getGenero());
    }

    private void configuraBotao() { // Função para salvar o conteudo na lista de personagem (Botão)
        Button btnSalvar = findViewById(R.id.button);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizaFormulario();
            }
        });
    }

    private void finalizaFormulario() { // Função para salvar/finalizar conteudo de um item na lista de personagem
        preencherPersonagem();
        if (personagem.IdValido()) {
            dao.edita(personagem);
        } else {
            dao.salva(personagem);
        }
        finish();
    }

    private void inicializacaoCampos() {
        nomePersonagem = findViewById(R.id.textInputNome); // Entrada de nome do personagem (variavel)

        alturaPersonagem = findViewById(R.id.editTextAltura);  // Entrada de altura do personagem (variavel)

        nascimentoPersonagem = findViewById(R.id.editTextDate); // Entrada de nascimento do personagem (variavel)

        telefonePersonagem = findViewById(R.id.editTextTelefone); // Entrada de Telefone do personagem (variavel)

        enderecoPersonagem = findViewById(R.id.editTextEndereco); // Entrada de Endereço do personagem (variavel)

        cepPersonagem = findViewById(R.id.editTextCep); // Entrada de CEP do personagem (variavel)

        rgPersonagem = findViewById(R.id.editTextRg); // Entrada de RG do personagem (variavel)

        generoPersonagem = findViewById(R.id.editTextGenero); // Entrada de Genero do personagem (variavel)

        SimpleMaskFormatter smfAltura = new SimpleMaskFormatter("N,NN"); //Mascara para o entrada altura
        MaskTextWatcher mtwAltura = new MaskTextWatcher(alturaPersonagem, smfAltura);
        alturaPersonagem.addTextChangedListener(mtwAltura);

        SimpleMaskFormatter smfNascimento = new SimpleMaskFormatter("NN/NN/NNNN"); //Mascara para o entrada nascimento
        MaskTextWatcher mtwNascimento = new MaskTextWatcher(nascimentoPersonagem, smfNascimento);
        nascimentoPersonagem.addTextChangedListener(mtwNascimento);

        SimpleMaskFormatter smfTelefone = new SimpleMaskFormatter("(NN)NNNNN-NNNN"); //Mascara para o entrada telefone
        MaskTextWatcher mtwTelefone = new MaskTextWatcher(telefonePersonagem, smfTelefone);
        telefonePersonagem.addTextChangedListener(mtwTelefone);

        SimpleMaskFormatter smfCep = new SimpleMaskFormatter("NNNNN-NNN"); //Mascara para o entrada Cep
        MaskTextWatcher mtwCep = new MaskTextWatcher(cepPersonagem, smfCep);
        cepPersonagem.addTextChangedListener(mtwCep);

        SimpleMaskFormatter smfRg = new SimpleMaskFormatter("NN.NNN.NNN-N"); //Mascara para o entrada RG
        MaskTextWatcher mtwRg = new MaskTextWatcher(rgPersonagem, smfRg);
        rgPersonagem.addTextChangedListener(mtwRg);

    }

    private void preencherPersonagem() { //Função para converter os valores das entradas para novas strings (variaveis)
        String nome = nomePersonagem.getText().toString();
        String altura = alturaPersonagem.getText().toString();
        String nascimento = nascimentoPersonagem.getText().toString();
        String telefone = telefonePersonagem.getText().toString();
        String endereco = enderecoPersonagem.getText().toString();
        String cep = cepPersonagem.getText().toString();
        String rg = rgPersonagem.getText().toString();
        String genero = generoPersonagem.getText().toString();

        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
        personagem.setTelefone(telefone);
        personagem.setEndereco(endereco);
        personagem.setCep(cep);
        personagem.setRg(rg);
        personagem.setGenero(genero);
    }

}