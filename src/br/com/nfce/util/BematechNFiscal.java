package br.com.nfce.util;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface BematechNFiscal extends Library {

    public BematechNFiscal Instance = (BematechNFiscal) Native.loadLibrary("lib/mp2032", BematechNFiscal.class);

    public int ConfiguraModeloImpressora(int modelo);
    public int IniciaPorta(String porta);
    public int FechaPorta();
    public int FormataTX(String BufTras, int tipoletra, int italic, int sublin, int expand, int enfat);
    public int ImprimeBitmap(String bitmap, int orientacao);
    public int ImprimeBmpEspecial(String bitmap, int xScale, int yScale, int orientacao);
    public int Le_Status();
    public int VerificaPapelPresenter();
    public int BematechTX(String Texto);
    public int AcionaGuilhotina(int modo);
    public int AjustaLarguraPapel(int largura);
    public int SelecionaQualidadeImpressao(int qualidade);
}
