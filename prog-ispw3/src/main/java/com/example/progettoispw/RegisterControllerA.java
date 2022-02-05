package com.example.progettoispw;

import java.io.IOException;

public class RegisterControllerA {
    private FileInterDAO filedao;

    public void register(LogBean log) throws MyException {
        RegisterDAO dao=RegisterDAO.getInstance();
        dao.registerdao(log);
        filedao=FileInterDAO.getInstance();
    }

    public void initFile(LogBean log) throws IOException, ClassNotFoundException {
        Login login=Convert.convertBeanToEntity(log);
        filedao.writeLog(login);
    }
}
