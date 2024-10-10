<h1>Comparando trechos de código Kotlin com trechos em Java</h1>

<h2>Estrutura Geral</h2>

    class MainActivity : AppCompatActivity() {
        // Código Kotlin
    }

    public class MainActivity extends AppCompatActivity {
        // Código Java
    }

<h2>lateinit vs. Inicialização no Construtor</h2>

<ul>

<li>Kotlin: O modificador lateinit permite inicializar uma variável não nula posteriormente.Em Kotlin,
 isso evita a necessidade de inicializar uma variável diretamente no construtor.
</li>
<li>Java: Em Java, variáveis devem ser inicializadas imediatamente,
 seja no construtor ou no próprio método onCreate, caso contrário, a variável seria null.</li>
</ul>

    private lateinit var result: TextView

    Em Java, precisaríamos de algo assim no método onCreate:

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        result = findViewById(R.id.text_result);
    }
