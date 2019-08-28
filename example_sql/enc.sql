
GRANT EXECUTE on DBMS_CRYPTO to public;

SET SERVEROUTPUT ON
    

CREATE OR REPLACE PACKAGE BODY system.CRYPTO_AES256

IS

/******************************************************************************

  암호화

 ******************************************************************************/

 FUNCTION ENC_AES ( input_string IN VARCHAR2
 ) RETURN VARCHAR2
 IS

   encrypted_raw      RAW (2000);             -- 암호화된 RAW타입 데이터
   key_bytes_raw      RAW (32);               -- 암호화 KEY (32RAW => 32Byte => 256bit)
   encryption_type    PLS_INTEGER :=          -- 암호화 알고리즘 선언

                            DBMS_CRYPTO.ENCRYPT_AES256

                          + DBMS_CRYPTO.CHAIN_CBC

                          + DBMS_CRYPTO.PAD_PKCS5;

    BEGIN

      

        key_bytes_raw :=UTL_I18N.STRING_TO_RAW('12345678901234567890123456789012', 'AL32UTF8');
        encrypted_raw := DBMS_CRYPTO.ENCRYPT
            (
                src => UTL_I18N.STRING_TO_RAW (input_string,  'AL32UTF8'),
                typ => encryption_type,
                key => key_bytes_raw

            );

      

        -- 에러 방지를 위해 base64_encode로 인코딩 처리..
        -- ORA-06502: PL/SQL: numeric or value error: hex to raw conversion error
        RETURN UTL_RAW.CAST_TO_VARCHAR2(UTL_ENCODE.BASE64_ENCODE(encrypted_raw));

       

    END ENC_AES;

 

 

/******************************************************************************

  복호화

 ******************************************************************************/

 FUNCTION DEC_AES (  encrypted_raw IN VARCHAR2

 ) RETURN VARCHAR2

IS

 

   output_string      VARCHAR2 (200);         -- 복호화된 문자열

   decrypted_raw      RAW (2000);             -- 복호화된 raw타입 데이터

   key_bytes_raw      RAW (32);               -- 256bit 암호화 key

   encryption_type    PLS_INTEGER :=          -- 복호화 알고리즘 선언

                            DBMS_CRYPTO.ENCRYPT_AES256

                          + DBMS_CRYPTO.CHAIN_CBC

                          + DBMS_CRYPTO.PAD_PKCS5;

    BEGIN

   

        key_bytes_raw :=UTL_I18N.STRING_TO_RAW('12345678901234567890123456789012', 'AL32UTF8');

        decrypted_raw := DBMS_CRYPTO.DECRYPT

            (

                -- 에러 방지를 위해 base64_decode로 인코딩 처리..

                -- ORA-06502: PL/SQL: numeric or value error: hex to raw conversion error

                src =>UTL_ENCODE.BASE64_DECODE(UTL_RAW.CAST_TO_RAW(encrypted_raw)),

                typ => encryption_type,

                key => key_bytes_raw

            );

       output_string := UTL_I18N.RAW_TO_CHAR (decrypted_raw, 'AL32UTF8');

    

       RETURN output_string;

      

    END DEC_AES;                       

 

END CRYPTO_AES256;

/



/***************************************************************************
  암호화
 ******************************************************************************/
 FUNCTION ENC_AES ( input_string IN VARCHAR2
 ) RETURN VARCHAR2;

/******************************************************************************
  복호화
 ******************************************************************************/
 FUNCTION DEC_AES (  encrypted_raw IN VARCHAR2
 ) RETURN VARCHAR2;                       

END CRYPTO_AES256;
------------------------------------------------------------------------------------------

    
--    DECLARE
--      input_string  VARCHAR2 (200) := 'The Oracle';  -- 입력 VARCHAR2 데이터
--      input_raw     RAW(128);                        -- 입력 RAW 데이터
--
--      encrypted_raw RAW (2000); -- 암호화 데이터
--
--      key_string VARCHAR2(8) := 'secret';  -- MAC 함수에서 사용할 비밀 키
--      raw_key RAW(128) := UTL_RAW.CAST_TO_RAW(CONVERT(key_string,'AL32UTF8','US7ASCII')); -- 비밀키를 RAW 타입으로 변환
--
--    BEGIN
--      -- VARCHAR2를 RAW 타입으로 변환
--      input_raw := UTL_I18N.STRING_TO_RAW (input_string, 'AL32UTF8');
--
--      DBMS_OUTPUT.PUT_LINE('----------- HASH 함수 -------------');
--    encrypted_raw := DBMS_CRYPTO.HASH( src => input_raw,
--                                         typ => DBMS_CRYPTO.HASH_SH1);
--
--      DBMS_OUTPUT.PUT_LINE('입력 문자열의 해시값 : ' || RAWTOHEX(encrypted_raw));
--
--      DBMS_OUTPUT.PUT_LINE('----------- MAC 함수 -------------');
--      encrypted_raw := DBMS_CRYPTO.MAC( src => input_raw,
--                                        typ => DBMS_CRYPTO.HMAC_MD5,
--                                        key => raw_key);
--
--      DBMS_OUTPUT.PUT_LINE('MAC 값 : ' || RAWTOHEX(encrypted_raw));
--    END;