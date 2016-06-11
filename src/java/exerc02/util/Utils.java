package exerc02.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author Anderson
 */
public class Utils {

    // Converter valor para o formato de exibi��o
    public static String converterValor(Double value, int decimais) throws ParseException {
        Locale locale = new Locale("pt", "BR");
        String formato = "";
        switch (decimais) {
            case 0:
                formato = "###,###,##0";
                break;
            case 1:
                formato = "###,###,##0.0";
                break;
            case 2:
                formato = "###,###,##0.00";
                break;
            case 3:
                formato = "###,###,##0.000";
                break;
            case 4:
                formato = "###,###,##0.0000";
                break;
            case 5:
                formato = "###,###,##0.00000";
                break;
            default:
                formato = "###,###,##0.00";
                break;
        }
        DecimalFormat formatodecimal = formato == null ? new DecimalFormat("###,###,##0.00") : new DecimalFormat(formato);
        formatodecimal.setDecimalFormatSymbols(new DecimalFormatSymbols(locale));
        formatodecimal.setGroupingSize(3);
        formatodecimal.setGroupingUsed(true);
        String resultado = formatodecimal.format(value);
        return resultado;
    }

}
