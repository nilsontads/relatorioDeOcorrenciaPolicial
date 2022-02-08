/*
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License
 * 
 * By exercising the Licensed Rights (defined below), You accept and agree to be bound by
 * the terms and conditions of this Creative Commons Attribution-NonCommercial-NoDerivatives
 * 4.0 International Public License ("Public License"). To the extent this Public License
 * may be interpreted as a contract, You are granted the Licensed Rights in consideration of
 * Your acceptance of these terms and conditions, and the Licensor grants You such rights in
 * consideration of benefits the Licensor receives from making the Licensed Material
 * available under these terms and conditions.
 */
package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author boss
 */
public class DateUtil {

    public static Calendar preparaDataInicio(Calendar data) {
        if (data != null) { //Esse teste é porque as vezes vem data nula do componente
            data.set(Calendar.HOUR_OF_DAY, 00);
            data.set(Calendar.MINUTE, 00);
            data.set(Calendar.SECOND, 00);
            return data;
        }
        return null;
    }

    public static Calendar setaHoraCalendar(Calendar data, Integer h, Integer m, Integer s) {
        if (data != null) {
            data.set(Calendar.HOUR_OF_DAY, h);
            data.set(Calendar.MINUTE, m);
            data.set(Calendar.SECOND, s);
            return data;
        }
        return null;
    }

    public static Calendar preparaDataFinal(Calendar data) {
        if (data != null) {
            data.set(Calendar.HOUR_OF_DAY, 23);
            data.set(Calendar.MINUTE, 59);
            data.set(Calendar.SECOND, 59);
            return data;
        }
        return null;
    }

    public static Date formataData(String data) throws ParseException {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dataFormat.parse(data);
    }

    public static Date formataDataHora(String data) throws ParseException {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        return dataFormat.parse(data);
    }

    public static Calendar formataDataCalendar(String data) throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(data));
            return cal;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date formataHora(String data) throws ParseException {
        SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm");
        return dataFormat.parse(data);
    }

    public static String formataData(Date data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dataFormat.format(data);
    }

    public static String formataHora(Date data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm");
        return dataFormat.format(data);
    }

    public static String formataData(Calendar data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dataFormat.format(data.getTime());
    }

    public static String formataDataHora(Calendar data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        return (dataFormat.format(data.getTime()));
    }

    public static String formataDataHHmmSS(Calendar data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        return (dataFormat.format(data.getTime()));
    }

    public static String formataHora(Calendar data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm");
        return dataFormat.format(data.getTime());
    }

    public static int quantidadeDias(Date inicial, Date finall) {
        int diffDays = (int) ((inicial.getTime() - finall.getTime()) / (24 * 60 * 60 * 1000));  // 7
        return diffDays;
    }

    public static String quantidadeHoraMinutoSegundo(Calendar inicial, Calendar finall) {
        int difMili = (int) ((finall.getTimeInMillis() - inicial.getTimeInMillis()) / 1000);
        int diffHor = (int) difMili / (int) 3600;
        int diffMin = (int) (difMili % 3600) / (int) 60;
        int diffSeg = (difMili % 3600) % 60;
        return diffHor + ":" + diffMin + ":" + diffSeg;
    }

    public static String getDataDia() {
        Date date = new Date();
        DateFormat formatData = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return formatData.format(date);
    }

    public static String getHoraDia() {
        Date date = new Date();
        DateFormat formatData = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        return formatData.format(date);
    }

    public static Boolean verificarDataMaiorDataAtual(Calendar data) {
        Date date = new Date();
        Calendar hoje = Calendar.getInstance();
        hoje.setTime(date);

        long dataHoje = hoje.getTimeInMillis();
        long dataTeste = data.getTimeInMillis();

        if (dataTeste > dataHoje) {
            return true;
        }

        return false;
    }

    public static Boolean verificarDataMaior(Calendar data1, Calendar data2) {
        long _data1 = data1.getTimeInMillis();
        long _data2 = data2.getTimeInMillis();
        if (_data1 > _data2) {
            return true;
        }
        return false;
    }
}
