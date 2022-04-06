package com.company.UzCard;

import com.company.UzCard.entity.CardEntity;
import com.company.UzCard.entity.ProfileEntity;
import com.company.UzCard.service.CardService;
import com.company.UzCard.service.ProfileService;
import com.company.UzCard.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
class UzCardApplicationTests {
@Autowired
private ProfileService profileService;
@Autowired
private CardService cardService;
@Autowired
private TransactionService transactionService;

	@Test
	void contextLoads() {

		/*ProfileEntity profile = new ProfileEntity();
		profile.setName("Vali");
		profile.setSurname("Valiev");
		profile.setMiddleName("Valievich");
		profile.setCreatedDate(LocalDateTime.now());
		profile.setBirthDate(LocalDate.of(2002, 1, 2));
		profileService.create(profile);*/


//		CardEntity entity = new CardEntity();
//		entity.setNumber("8600123456781234");
//		entity.setBalance(0l);
//		entity.setCreatedDate(LocalDateTime.now());
//		entity.setExc_Date("10/27");
//		entity.setProfile(profileService.get(1));

//		cardService.create("8600123412344321", "12/12", 2);

//		cardService.increaseBalance("8600123412344321", 1000_00l);

//		transactionService.makeTransaction("8600123412344321", "8600123412341234", 400_00L);

	}

}
