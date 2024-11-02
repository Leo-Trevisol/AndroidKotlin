
class ModulosActivity : AppCompatActivity() {
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modulos)
        val mainColor = Global.getInstance().defaultColor
        val supportActionBar: ActionBar = getSupportActionBar()
        supportActionBar.setDisplayHomeAsUpEnabled(true)

        getWindow().setStatusBarColor(mainColor)
        supportActionBar.setBackgroundDrawable(ColorDrawable(mainColor))
        supportActionBar.setTitle("Módulos")

        showModulos()
    }

    private fun showModulos() {
        val modulosFragment: ModulosFragment = ModulosFragment()

        val ft: FragmentTransaction = getSupportFragmentManager().beginTransaction()

        ft.replace(R.id.container, modulosFragment)

        ft.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent: Intent = Intent(getApplicationContext(), LoginActivity::class.java)
        finish() // Fecha a Activity atual
        startActivity(intent) // Inicia a Activity novamente
    }
}