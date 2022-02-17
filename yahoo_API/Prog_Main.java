//a cura di: Di Natale Alessandro, Niederegger Bilal, Vaccaro Arianna
import java.io.*;
class Prog_Main{
    public static void main (String args[])
    {
        Reader_Csv lettore;
        lettore=new Reader_Csv();
        lettore.Tesla();
        lettore.Apple();
        lettore.Amazon();
        lettore.Stati_Uniti();
        lettore.Meta();
    }
};