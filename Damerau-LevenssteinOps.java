package AA;

public class P100 {
    
    public static int argMin(int swap, int insert, int delete){
        if(swap <= insert && swap <= delete){
            return 0;
        }

        if(insert < swap && insert <= delete){
            return 1;
        }

        if(delete < swap && delete < insert){
            return 2;
        }
        
        return 0;
    }

    
    public static String bestSolution(String[] data) throws IllegalArgumentException {
        String source = data[0];
        String target = data[1];
        int sourceLength = source.length();
        int targetLength = target.length();
        String result = "";

        
        if (sourceLength == 0){
            for(int i = 0; i < targetLength; i++){
                result += "i" + target.charAt(i);
            }
            return result;
        } 
        if (targetLength == 0){
            for(int i = 0; i < sourceLength; i++){
                result += "b" + source.charAt(i);
            }
            return result;
        } 
        
        int[][] dist = new int[sourceLength + 1][targetLength + 1];
        String[][] operations = new String[sourceLength][targetLength];

        for (int i = 0; i < sourceLength + 1; i++) {
            dist[i][0] = i;
        }
        
        for (int j = 0; j < targetLength + 1; j++) {
            dist[0][j] = j;
        }
        
        for (int i = 1; i < sourceLength + 1; i++) {
            for (int j = 1; j < targetLength + 1; j++) {
                int cost = source.charAt(i - 1) == target.charAt(j - 1) ? 0 : 1;
                int insert = dist[i][j - 1] + 1;
                int delete = dist[i - 1][j] + 1;
                int swap = dist[i - 1][j - 1] + cost;
                int index = 0;
                
                dist[i][j] = Math.min(Math.min(insert, delete),swap);
                index = argMin(swap,insert,delete);

                if(index == 0){
                    operations[i-1][j-1] = "s" + source.charAt(i-1) + target.charAt(j-1);
                }else if(index == 1){
                    operations[i-1][j-1] = "i" + target.charAt(j-1);
                }else{
                    operations[i-1][j-1] = "b" + source.charAt(i-1);
                }
                


                //Calcula si es menor el coste de trasposicion que el obtenido anteriormente
                if (i > 1 && j > 1 && source.charAt(i - 1) == target.charAt(j - 2) && source.charAt(i - 2) == target.charAt(j - 1) && (target.charAt(j-1) != source.charAt(i-1) && target.charAt(j-2) != source.charAt(i-2))) {
                    if(dist[i - 2][j - 2] + cost <= dist[i][j]){
                        operations[i-1][j-1] = "w" + source.charAt(i-2) + source.charAt(i-1) + target.charAt(j-2) + target.charAt(j-1);
                    }
                    dist[i][j] = Math.min(dist[i][j], dist[i - 2][j - 2] + cost);
                }
            }
            
        }

        //System.out.println("MATRIZ DE DISTANCIAS");
        //System.out.println("--------------------");
        //for(int x = 0; x < source.length()+1; x++){
        //    for(int y = 0; y < target.length()+1; y++){
        //        System.out.print(dist[x][y] + "\t");
        //    }
        //    System.out.println();
        //}
        //
        //System.out.println("MATRIZ DE OPERACIONES");
        //System.out.println("--------------------");
        //for(int x = 0; x < source.length(); x++){
        //    for(int y = 0; y < target.length(); y++){
        //        System.out.print(operations[x][y] + "\t");
        //    }
        //    System.out.println();
        //}

        
        int i = source.length()-1;
        int j = target.length()-1;

        if(sourceLength == targetLength){
            int contador = targetLength;
            while(contador > 0){
                result = operations[i][j] + result;
                if(operations[i][j].charAt(0) == 's'){
                    i--;
                    j--;
                    contador--;
                }
                else if(operations[i][j].charAt(0) == 'i'){
                    j--;
                    contador--;
                }
                else if(operations[i][j].charAt(0) == 'b'){
                    i--;
                    contador--;
                }
                else{
                    i -= 2;
                    j -= 2;
                    contador -= 2;
                }
            }
        }
        else if(sourceLength > targetLength){
            int contador = sourceLength;
            while(contador > 0){
                if(j > -1){
                    result = operations[i][j] + result;
                    if(operations[i][j].charAt(0) == 's'){
                        i--;
                        j--;
                        contador--;
                    }
                    else if(operations[i][j].charAt(0) == 'i'){
                        j--;
                        contador--;
                    }
                    else if(operations[i][j].charAt(0) == 'b'){
                        i--;
                        contador--;
                    }
                    else{
                        i -= 2;
                        j -= 2;
                        contador -= 2;
                    }
                }
                else{
                    String conversion = "b" + operations[i][0].charAt(1);
                    result = conversion + result;
                    i--;
                    contador--;
                }
            }
        }
        else{
            int contador = targetLength;
            while(contador > 0){
                if(i > -1){
                    result = operations[i][j] + result;
                    if(operations[i][j].charAt(0) == 's'){
                        i--;
                        j--;
                        contador--;
                    }
                    else if(operations[i][j].charAt(0) == 'i'){
                        j--;
                        contador--;
                    }
                    else if(operations[i][j].charAt(0) == 'b'){
                        i--;
                        contador--;
                    }
                    else{
                        i -= 2;
                        j -= 2;
                        contador -= 2;
                    }
                }
                else{
                    String conversion = "i" + operations[0][j].charAt(2);
                    result = conversion + result;
                    j--;
                    contador--;
                }
            }
        }

        //System.out.println("DISTANCIA DE EDICION : " + dist[sourceLength][targetLength]);
        return result;
    }

    
    //public static void main(String[] args){
    //    String[] data = {"3356711",
    //                     "3365711"};
    //    System.out.println("CADENA RESULTADO: " + bestSolution(data));
    //}
}