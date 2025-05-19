import java.util.Map;

/**
 * Este es un Record que representa a una moneda.
 * Se utiliza un registro para almacenar el código de la moneda base
 * y un Map con las tasas de cambio a otras monedas.
 *
 */

public record Moneda(String base_code,
                     Map<String, Double> conversion_rates) {
}
