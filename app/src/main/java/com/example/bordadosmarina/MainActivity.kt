package com.example.bordadosmarina
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bordadosmarina.ui.theme.BordadosMarinaTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BordadosMarinaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BordadosMarina(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun BordadosMarina(modifier: Modifier = Modifier) {
    //estado da qtd
    var quantidade by remember { mutableIntStateOf(1) }

    val precoUnitario = 19.90
    val total = quantidade * precoUnitario

    Column( //colunm organiza elementos verticalmente
        modifier = modifier //o moodifier vai definir tamanho e espacamento
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Cabecalho()
        Spacer(modifier = Modifier.height(24.dp))
        Informacoes()
        Spacer(modifier = Modifier.height(24.dp))
        OfertaDoDia()
        Spacer(modifier = Modifier.height(24.dp))
        ControleQuantidade(
            quantidade = quantidade,
            diminuir = {
                if (quantidade > 1) {
                    quantidade--
                }
            },
            aumentar = {
                quantidade++
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Total: R$ %.2f".format(total))
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { }) {
            Text(text = "♡ ENCOMENDAR")
        }
    }
}
@Composable
fun Cabecalho() {
    Text(text = "BORDADOS MARINA")
    Text(text = "Artesanato com cuidado e criatividade")
}
@Composable
fun Informacoes() {
    // row organiza os elementos horizontalmente
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "♡ Avaliação: 5/5")
        Text(text = "⏱ Entrega: 2 dias úteis")
    }
}

@Composable
fun OfertaDoDia() {
    Text(text = "Oferta do dia:")
    Text(text = "- Passarinho chibi")
    Text(text = "Preço: R$ 19,90")
}

@Composable
fun ControleQuantidade(
    quantidade: Int,
    diminuir: () -> Unit,
    aumentar: () -> Unit
) {
    //row organiza botoes e a qtd
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(onClick = { diminuir() }) {
            Text(text = "-")
        }

        Text(text = "Quantidade: $quantidade")

        Button(onClick = { aumentar() }) {
            Text(text = "+")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BordadosMarinaPreview() {
    BordadosMarina()
}