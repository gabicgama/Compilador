/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

/**
 * * @author kvnet
 * @author gabyc
 * @author gabyc
 */
public class Tag {

    public static final int 
            //Palavras reservadas
            APP = 256,
            START = 257,
            STOP = 258,
            INTEGER = 259,
            REAL = 260,
            IF = 261,
            THEN = 262,
            END = 263,
            ELSE = 264,
            REPEAT = 265,
            UNTIL = 266,
            WHILE = 267,
            DO = 268,
            READ = 269,
            WRITE = 270,
            //Operadores e pontuações
            ATB = 271,  //:=
            GE = 272,   //>=
            LE = 273,   //<=
            NE = 274,   //!=
            OR = 275,   //||
            AND = 276,  //&&
            //Identificadores e literais
            IDF = 278,
            INT_CONST = 279,
            FLOAT_CONST = 280,
            LIT = 281;
            
}
