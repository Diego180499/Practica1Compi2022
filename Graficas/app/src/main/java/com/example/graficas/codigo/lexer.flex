package codigo;
import static codigo.Tokens.*;
import java.util.ArrayList;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    public String lexeme;
    private int linea;
    private int columna;
    private static ArrayList<String> errores = new ArrayList<>();
    
    public void agregarError(String error){
        errores.add(error);
    }

    public static ArrayList<String> getErroresLexicos(){
           return errores;
    }
%}
%%
"," {lexeme=yytext();return Coma;}
{espacio} {/*Ignore*/}
"#".* {/*Ignore*/}
(Def|def) {lexeme=yytext();return DEF;}
(Barras) {lexeme=yytext();return BARRAS;}
(Pie) {lexeme=yytext();return PIE;}
(titulo) {lexeme=yytext();return Titulo;}
(ejex) {lexeme=yytext();return ejeX;}
(ejey) {lexeme=yytext();return ejeY;}
(etiquetas) {lexeme=yytext();return Etiqueta;}
(Ejecutar) {lexeme=yytext();return Ejecutar;}
(extra) {lexeme=yytext();return Extra;}
(total) {lexeme=yytext();return Total;}
(valores) {lexeme=yytext();return Valores;}
(unir) {lexeme=yytext();return Unir;}
(tipo) {lexeme=yytext();return Tipo;}
(Cantidad)  {lexeme=yytext();return Cantidad;}
(Porcentaje) {lexeme=yytext();return Porcentaje;}
(Definir) {lexeme=yytext();return DEFINIR;}
"=" {lexeme=yytext();return Igual;}
"+" {lexeme=yytext();return Suma;}
"-" {lexeme=yytext();return Resta;}
"*" {lexeme=yytext();return Multiplicacion;}
"/" {lexeme=yytext();return Division;}
"(" {lexeme=yytext();return Parentesis_a;}
")" {lexeme=yytext();return Parentesis_c;}
"{" {lexeme=yytext();return Llaves_a;}
"}" {lexeme=yytext();return Llaves_c;}
";" {lexeme=yytext();return P_coma;}
"[" {lexeme=yytext();return Corchete_a;}
"]" {lexeme=yytext();return Corchete_c;}
":" {lexeme=yytext();return Dos_p;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
{D}+\.{D}+     {lexeme=yytext(); return Decimal;}
\"({L}({L}|{D})*)\" {lexeme=yytext(); return Nombre;}
 . {lexeme=yytext(); linea = yyline +1; columna = yycolumn+1; agregarError(lexeme+"   "+linea+"   "+columna); return ERROR;}