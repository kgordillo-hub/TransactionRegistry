package co.agro.blockchain.TransactionRegistry.controllersImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.agro.blockchain.TransactionRegistry.controllers.KeyGeneratorController;
import co.agro.blockchain.TransactionRegistry.model.KeyPairDto;
import co.agro.blockchain.TransactionRegistry.service.BigchainDbUtilService;

@RestController
public class KeyGeneratorControllerImpl implements KeyGeneratorController{

	@Autowired
	BigchainDbUtilService bigchainDbUtilService;
	
	@Override
	@RequestMapping(value = "/keygen", method = RequestMethod.GET, produces = "application/json")
	public KeyPairDto generateKeyPair() {
		return bigchainDbUtilService.generateKeyPair();
	}

}
