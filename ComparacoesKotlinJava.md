<h1>Comparando trechos de código Kotlin com trechos em Java</h1>

<h3>
    Kotlin é mais conciso e tem recursos modernos como lambdas, when, e propriedades delegadas como lateinit, que permitem escrever menos código de forma mais clara e segura. <br> <br>
    Java ainda é amplamente utilizado, exige mais verbosidade e tem uma curva de desenvolvimento ligeiramente mais complexa devido à falta de algumas dessas funcionalidades sintéticas e práticas de Kotlin. <br>
</h3>

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

<h2>Tratamento de findViewById</h2>

<ul>
<li>Kotlin: O uso de findViewById pode ser simplificado ainda mais com extensões de Android, como o Kotlin Android Extensions (agora descontinuado) ou o ViewBinding.</li>
</ul>

    val buttonConvert = findViewById<Button>(R.id.bt_converter)

<ul>
<li>Java: Em Java, o desenvolvedor precisa fazer chamadas explícitas a findViewById sempre que precisar acessar um componente da interface, sendo necessário um casting quando for utilizado em versões mais antigas de Java.</li>
</ul>

    Button buttonConvert = (Button) findViewById(R.id.bt_converter);

<h2>Funções Anônimas (Lambda) e OnClickListener</h2>

<ul>
<li>Kotlin: O uso de expressões lambda em Kotlin permite definir listeners de forma mais concisa. Kotlin substitui a interface View.OnClickListener por uma função lambda que reduz o código boilerplate.</li>
</ul>

    buttonConvert.setOnClickListener {
        converter()
    }

<ul>
<li>Java: Em Java, é necessário criar uma instância anônima de OnClickListener.</li>
</ul>

    buttonConvert.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            converter();
        }
    });

<h2>when vs. switch</h2>

<ul>
<li>Kotlin: A construção when em Kotlin é mais poderosa e flexível do que o switch de Java. O when pode retornar valores e não precisa de break para evitar "fall-through".</li>
</ul>

    val currency = when(radioSelected) {
        R.id.radio_usd -> "USD"
        R.id.radio_eur -> "EUR"
        else -> return
    }

<ul>
<li>Java: O equivalente em Java usaria o switch, que é menos flexível e requer break para evitar a execução de múltiplos casos.</li>
</ul>

    String currency;
    switch (radioSelected) {
        case R.id.radio_usd:
            currency = "USD";
            break;
        case R.id.radio_eur:
            currency = "EUR";
            break;
        default:
            return;
    }

<h2>Concorrência com Thread</h2>

<ul>
<li>Kotlin: Em Kotlin, a criação de threads segue o mesmo princípio de Java. No entanto, Kotlin tem bibliotecas mais modernas como coroutines, que simplificam a programação assíncrona. Nesse exemplo, a implementação é semelhante à de Java.</li>
</ul>

    Thread {
        // Código executado na thread
    }.start()


<ul>
<li>Java: Java também cria threads de maneira explícita usando Thread ou Runnable.</li>
</ul>

    new Thread(new Runnable() {
        @Override
        public void run() {
            // Código executado na thread
        }
    }).start();


















