fun main() {
    var palabrawin = asignarPalbra()
    var adivinacion = ""
    var palcasi = palabraCasi().iniciar(palabrawin)
    var intentos = 1
    var letrasusadas = mutableListOf<String>()
    val reproductorMidi = ReproductorMidi("pugnodollari.mid")
    println("CARGANDO ...")
    Thread.sleep(5000)

    while (intentos <= 7 ){
        println("Estas en el intento: $intentos de 7")
        print("Adivina la palabra: ")
        for (x in 0 until palabrawin.length){
                 print(palcasi[x])
        }
        println()
        println("Letras ya usadas: $letrasusadas")
        print("Escriba una letra:")
        adivinacion = readln().uppercase()
        if (adivinacion in letrasusadas.toString()){
            println("Ya usaste esa letra Prueba OTRA !!!")
            println("-------------------------------------------------------------------------------------")
            continue
        }
        letrasusadas.add(adivinacion)
        if (adivinacion in palabrawin){
            palcasi = palabraCasi().comprobarLetras(adivinacion, palcasi, palabrawin)
            if (palabrawin == palcasi) {
                println("-------------------------------------------------------------------------------------")
                println("CORRECTO GANASTE !!!")
                println("La palabra Secreta es: $palabrawin")
                break
            }
            println("-------------------------------------------------------------------------------------")
            continue
        }
        DibujoAhorcado.dibujar(intentos)
        intentos++
        println("-------------------------------------------------------------------------------------")
    }
    if (intentos == 8) {
        println("PERDISTE buena suerte a la proxima!!!")
        println("La palabra Secreta es: $palabrawin")
    }
    reproductorMidi.cerrar()
}

enum class Palabras {
    MANZANA, PLATANO, NARANJA, PERA, KIWI, HIGO, MELON, SANDIA, CALABAZA, MANGO, MELOCOTON, COCO, UVA
}

fun asignarPalbra(): String {
    var r = 1..Palabras.values().size
    var numpal = r.random()
    var paldev = ""
    var x = 0
    for(pal in Palabras.values()){
        if(x == numpal) break
        paldev = pal.toString()
        x++
    }
    return paldev
}

class palabraCasi {
    var palabra = ""
    fun iniciar(palabra: String): String{
        for (x in 0 until palabra.length){
            this.palabra = this.palabra+"_"
        }
        return this.palabra
    }
    fun comprobarLetras(letra: String, palabra: String, palwin: String): String {
        var palabranueva = ""
        var listapal = palabra.toMutableList()
        for (x in 0 until palabra.length){
            if (listapal[x].toString() == "_") {
                if (letra[0] == palwin[x]) {
                    listapal[x] = letra[0]
                }
            }
        }
        for (x in listapal){
            palabranueva = palabranueva + x
        }
        return palabranueva
    }
}