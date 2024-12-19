package com.project.projetoconvidados.view

import android.content.Intent
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.project.projetoconvidados.R
import com.project.projetoconvidados.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Declaração da variável para a configuração da AppBar (barra superior)
    private lateinit var appBarConfiguration: AppBarConfiguration
    // Declaração da variável de binding, que permite acessar as views do layout da Activity
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflando o layout da Activity usando ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura a toolbar como a ActionBar da Activity
        setSupportActionBar(binding.appBarMain.toolbar)

        // Configura a ação do botão de adicionar novo convidado (FAB)
        binding.appBarMain.fab.setOnClickListener { view ->
            // Quando o FAB é clicado, abre a tela de cadastro de novo convidado
            startActivity(Intent(applicationContext, GuestFormActivity::class.java))
        }

        // Configura a navegação entre os fragmentos usando o Navigation Drawer
        setupNavigation()
    }

    // Método para configurar a navegação entre os fragmentos
    private fun setupNavigation() {
        // Obtém o DrawerLayout (menu lateral) e o NavigationView (menu de navegação)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        // Cria o NavController, que gerencia a navegação entre os fragmentos
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Configura a AppBar (barra de navegação) para trabalhar com o NavController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_all_guests, R.id.nav_present, R.id.nav_absent // Destinos principais
            ), drawerLayout
        )
        // Configura a ActionBar com o NavController e a AppBarConfiguration
        setupActionBarWithNavController(navController, appBarConfiguration)
        // Configura o NavigationView para funcionar com o NavController
        navView.setupWithNavController(navController)
    }

    // Método que lida com o comportamento de navegação para cima (quando o usuário clica na seta de voltar)
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Retorna a navegação de volta ao destino anterior, ou chama o método super caso não seja possível
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // Código comentado sobre como adicionar um menu de 3 pontos à ActionBar

    // Método para inflar o menu (os itens de ação) no canto superior direito (ícone de 3 pontos)
    // Desabilitado, pois não é necessário para essa funcionalidade.
    // override fun onCreateOptionsMenu(menu: Menu): Boolean {
    //     // Infla o menu para a ActionBar
    //     menuInflater.inflate(R.menu.main, menu)
    //     return true
    // }

    // Método que lida com a interação do usuário com os itens do menu de 3 pontos
    // Caso o usuário clique em um item (como as configurações), podemos definir a ação aqui.
    // override fun onOptionsItemSelected(item: MenuItem): Boolean {
    //     if(item.itemId == R.id.action_settings) {
    //         // Aqui você pode adicionar a lógica para tratar a ação de configuração
    //     }
    //     return super.onOptionsItemSelected(item)
    // }
}
