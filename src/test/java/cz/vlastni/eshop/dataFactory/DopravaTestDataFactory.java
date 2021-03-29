package cz.vlastni.eshop.dataFactory;

import cz.vlastni.eshop.entity.Doprava;
import cz.vlastni.eshop.repository.DopravaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DopravaTestDataFactory {

    @Autowired
    private DopravaRepository dopravaRepository;
    public void saveNewDoprava1() {
        Doprava deliver = new Doprava();
        deliver.setCena(500);
        dopravaRepository.save(deliver);
    }
    public void saveNewDoprava2() {
        Doprava deliver2 = new Doprava();
        deliver2.setCena(109);
        deliver2.setPopis("blabla");
        dopravaRepository.save(deliver2);
    }

    public void saveNewDoprava3() {
        Doprava deliver3 = new Doprava();
        deliver3.setCena(510);
        deliver3.setPopis("blabla3");
        dopravaRepository.save(deliver3);
    }

    public void saveDoprava(Doprava doprava) {
        if (doprava.getPopis()==null) doprava.setPopis("testDoprava");
        if (doprava.getCena()==null) doprava.setCena(99999);
        dopravaRepository.save(doprava);
    }
}
