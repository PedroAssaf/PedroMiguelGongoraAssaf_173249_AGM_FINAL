package com.example.listapersonagens.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {


    private final PersonagemDAO dao = new PersonagemDAO();  //Instancia da classe dao
    private ArrayAdapter<Personagem> adapter;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChangeFormulario(); //Função para mudança de formulario no inicio da aplicação
        configuraLista();   //Função para Organização da lista de personagem

    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaPersonagem(); // Função para registro e atualização da lista
    }

    private void remove(Personagem personagem) { //Metodo para remover personagem se clicar e segurar
        dao.remove(personagem);
        adapter.remove(personagem);
    }

    private void atualizaPersonagem() { // Função para registro e atualização da lista
        adapter.clear();
        adapter.addAll(dao.todos());
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { //Função para mostrar caixa de menu de remover item da lista
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_personagens_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) { //Função para obter um item da lista
        configuraMenu(item); //Função de remover para remover um item da lista
        return super.onContextItemSelected(item);

    }

    private void configuraMenu(@NonNull MenuItem item) { //Função para mostrar pergunta se deve apagar item da lista
        int itemId = item.getItemId();
        if(itemId == R.id.activity_lista_personagem_menu_remover) {
            new AlertDialog.Builder(this)
                    .setTitle("Removendo Personagem")
                    .setMessage("Tem certeza que deseja remover?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                            Personagem personagemEscolhido = adapter.getItem(menuInfo.position);
                            remove(personagemEscolhido);
                        }
                    })
                    .setNegativeButton("Não", null).show();
        }
    }


    private void configuraLista() { //Função para poder acessar os dados da lista de personagens
        ListView lista = findViewById(R.id.activity_main_list_personagem);  //Index do dao para utilização
        configuraAdapter(lista);
        configuraItemClique(lista);
        registerForContextMenu(lista);
    }


    private void configuraItemClique(ListView lista) { //Função para coletar as informações do item personagem da lista
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditar(personagemEscolhido);
            }
        });
    }


    private void abreFormularioModoEditar(Personagem personagemEscolhido) { //Função para navegar para edição do formulario
        Intent goFormulario = new Intent(this, FormularioPersonagemActivity.class);
        goFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);
        startActivity(goFormulario);
        
    }


    private void configuraAdapter(ListView lista) { //Função para configuração do adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lista.setAdapter(adapter);
    }


    private void ChangeFormulario() { //Função para navegar até o formulario
        FloatingActionButton btn = findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFormulario();
            }
        });

    }


    private void abrirFormulario() { //Função para abir o formulario
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }

}
