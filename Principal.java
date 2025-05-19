
import java.util.Scanner;

/**
 * Esta es la clase principal, donde ejecutamos para mostrar el menú principal,
 * los datos se obtienen de la API.
 */

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);

        // Creamos la instancia de ConsultaMoneda para obtener la información de las tasas desde la API
        ConsultaMoneda consulta = new ConsultaMoneda();

        Moneda moneda;
        try{
            // Se realiza la consulta de las tasas de cambio
            moneda = consulta.consultaMoneda();
        }catch (RuntimeException ex) {
            System.out.println("Error al obtener tasas de cambio: " + ex.getMessage());
            return;
        }

        // Estas son las opciones disponibles para convertir, guardadas en un Arreglo o Array de dos dimensiones
        // El primer elemento es la moneda origen, el segundo elemento es la moneda destino
        String[][] conversiones = {
                {"USD", "ARS"},
                {"ARS", "USD"},
                {"USD", "BOB"},
                {"BOB", "USD"},
                {"USD", "COP"},
                {"COP", "USD"},
        };

        int opcion = 0;

        // Ciclo while para mostrar el menú principal
        while(opcion != 7){
            System.out.println("""
                ************************************************
                Sea bienvenido/a al Conversor de Moneda =)
                
                1) Dólar => Peso argentino
                2) Peso argentino => Dólar
                3) Dólar => Peso boliviano
                4) Peso boliviano => Dólar
                5) Dólar => Peso colombiano
                6) Peso colombiano => Dólar
                7) Salir
                Elija una opción válida:
                ************************************************
                """);
            opcion = lectura.nextInt();

            if(opcion >= 1 && opcion <= 6){
                System.out.println("Ingrese el valor que desea convertir: ");
                double valor = lectura.nextDouble();

                // Obtenemos las monedas de origen y destino de acuerdo con la opción seleccionada
                String monedaOrigen = conversiones[opcion - 1][0];
                String monedaDestino = conversiones[opcion - 1][1];

                double resultado;

                if(monedaDestino.equals("USD")){
                    // obtenemos la tasa de cambio para la moneda origen
                    double tasaCambio = moneda.conversion_rates().get(monedaOrigen);
                    resultado = valor / tasaCambio;
                }else{
                    // obtenemos la tasa de cambio para la moneda destino
                    double tasaCambio = moneda.conversion_rates().get(monedaDestino);
                    resultado = valor * tasaCambio;
                }

                // mostramos en consola el resultado
                System.out.printf("El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s]%n",
                        valor, monedaOrigen, resultado, monedaDestino);

            }else if(opcion != 7){
                System.out.println("Opción no válida.");
            }

        }
        System.out.println("Finalizando aplicación de conversor de monedas.♣");
    }
}
