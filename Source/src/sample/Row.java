package sample;

import java.util.Random;

public class Row
{
    public void setColA(String colA) {
        this.colA = colA;
    }

    public void setColB(String colB) {
        this.colB = colB;
    }

    public void setColC(String colC) {
        this.colC = colC;
    }

    public void setColD(String colD) {
        this.colD = colD;
    }

    public void setColE(String colE) {
        this.colE = colE;
    }

    public void setColF(String colF) {
        this.colF = colF;
    }

    public void setColG(String colG) {
        this.colG = colG;
    }

    public void setColH(String colH) {
        this.colH = colH;
    }

    public void setColI(String colI) {
        this.colI = colI;
    }

    public void setColJ(String colJ) {
        this.colJ = colJ;
    }

    public void setColK(String colK) {
        this.colK = colK;
    }

    public void setColL(String colL) {
        this.colL = colL;
    }

    public void setColM(String colM) {
        this.colM = colM;
    }

    public void setColN(String colN) {
        this.colN = colN;
    }

    public void setColO(String colO) {
        this.colO = colO;
    }

    public void setColP(String colP) {
        this.colP = colP;
    }

    public void setColQ(String colQ) {
        this.colQ = colQ;
    }

    public void setColR(String colR) {
        this.colR = colR;
    }

    public void setColS(String colS) {
        this.colS = colS;
    }

    public void setColT(String colT) {
        this.colT = colT;
    }

    public void setColU(String colU) {
        this.colU = colU;
    }

    public void setColV(String colV) {
        this.colV = colV;
    }

    public void setColW(String colW) {
        this.colW = colW;
    }

    public void setColX(String colX) {
        this.colX = colX;
    }

    public void setColY(String colY) {
        this.colY = colY;
    }

    public void setColZ(String colZ) {
        this.colZ = colZ;
    }

    public String colA = "";
    public String colB = "";
    public String colC = "";
    public String colD = "";
    public String colE = "";
    public String colF = "";
    public String colG = "";
    public String colH = "";
    public String colI = "";
    public String colJ = "";
    public String colK = "";
    public String colL = "";
    public String colM = "";
    public String colN = "";
    public String colO = "";
    public String colP = "";
    public String colQ = "";
    public String colR = "";
    public String colS = "";
    public String colT = "";
    public String colU = "";
    public String colV = "";
    public String colW = "";
    public String colX = "";
    public String colY = "";
    public String colZ = "";

//    public String[] allColumns;

    // Constructor
    public Row () {
        System.out.println("Creating new row...");

    }

    public void updateColumns(String[] columnData) {
        int counter = 0;
        colA = columnData[counter++];
        colB = columnData[counter++];
        colC = columnData[counter++];
        colD = columnData[counter++];;
        colE = columnData[counter++];;
        colF = columnData[counter++];;
        colG = columnData[counter++];;
        colH = columnData[counter++];;
        colI = columnData[counter++];;
        colJ = columnData[counter++];;
        colK = columnData[counter++];;
        colL = columnData[counter++];;
        colM = columnData[counter++];;
        colN = columnData[counter++];;
        colO = columnData[counter++];;
        colP = columnData[counter++];;
        colQ = columnData[counter++];;
        colR = columnData[counter++];;
        colS = columnData[counter++];;
        colT = columnData[counter++];;
        colU = columnData[counter++];;
        colV = columnData[counter++];;
        colW = columnData[counter++];;
        colX = columnData[counter++];;
        colY = columnData[counter++];;
        colZ = columnData[counter++];;
    }

    public String[] getAllColumns()
    {
        String[] allColumns = new String[26];
        allColumns[0] = colA;
        allColumns[1] = colB;
        allColumns[2] = colC;
        allColumns[3] = colD;
        allColumns[4] = colE;
        allColumns[5] = colF;
        allColumns[6] = colG;
        allColumns[7] = colH;
        allColumns[8] = colI;
        allColumns[9] = colJ;
        allColumns[10] = colK;
        allColumns[11] = colL;
        allColumns[12] = colM;
        allColumns[13] = colN;
        allColumns[14] = colO;
        allColumns[15] = colP;
        allColumns[16] = colQ;
        allColumns[17] = colR;
        allColumns[18] = colS;
        allColumns[19] = colT;
        allColumns[20] = colU;
        allColumns[21] = colV;
        allColumns[22] = colW;
        allColumns[23] = colX;
        allColumns[24] = colY;
        allColumns[25] = colZ;

        return allColumns;
    }


    // Getters
    public String getColA() {
        return colA;
    }
    public String getColB() {
        return colB;
    }
    public String getColC() {
        return colC;
    }
    public String getColD() {
        return colD;
    }
    public String getColE() {
        return colE;
    }
    public String getColF() {
        return colF;
    }
    public String getColG() {
        return colG;
    }
    public String getColH() {
        return colH;
    }
    public String getColI() {
        return colI;
    }
    public String getColJ() {
        return colJ;
    }
    public String getColK() {
        return colK;
    }
    public String getColL() {
        return colL;
    }
    public String getColM() {
        return colM;
    }
    public String getColN() {
        return colN;
    }
    public String getColO() {
        return colO;
    }
    public String getColP() {
        return colP;
    }
    public String getColQ() {
        return colQ;
    }
    public String getColR() {
        return colR;
    }
    public String getColS() {
        return colS;
    }
    public String getColT() {
        return colT;
    }
    public String getColU() {
        return colU;
    }
    public String getColV() {
        return colV;
    }
    public String getColW() {
        return colW;
    }
    public String getColX() {
        return colX;
    }
    public String getColY() {
        return colY;
    }
    public String getColZ() {
        return colZ;
    }

}
