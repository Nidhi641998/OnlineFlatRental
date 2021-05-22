package com.cg.onlineflatrental.service;

import java.util.List;

import javax.transaction.Transactional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineflatrental.dao.ILandlordJpaDao;
import com.cg.onlineflatrental.exception.LandlordNotFoundException;
import com.cg.onlineflatrental.model.Landlord;

@Service
@Transactional
public class ILandlordServiceImpl implements ILandlordService {

	@Autowired
	private ILandlordJpaDao ilandlordjpadao;

	@Override
	public Landlord addLandlord(Landlord landlord)  {

		return ilandlordjpadao.saveAndFlush(landlord);

	}

	@Override
	public Landlord updateLandlord(Landlord landlord) throws LandlordNotFoundException {
		Integer landlordId=landlord.getLandlordId();
		Landlord landlord1=ilandlordjpadao.findById(landlordId).get();
		landlord1.setLandlordName(landlord.getLandlordName());
		landlord1.setLandlordAge(landlord.getLandlordAge());
		landlord1.setflatList(landlord.getflat());
		return ilandlordjpadao.save(landlord1);


	}

	@Override
	public  boolean deleteLandlordById(int landlordId) throws LandlordNotFoundException {
		Landlord landlord1=ilandlordjpadao.findById(landlordId).get();
		ilandlordjpadao.deleteById(landlordId);

		if(null==landlord1)
		{
			return true;

		}
		return false;
	}

	@Override
	public Landlord  viewLandlordById(int landlordId) throws LandlordNotFoundException {

		return ilandlordjpadao.findById(landlordId).get();
	}

	@Override
	public List<Landlord>viewAllLandlord() {
		return ilandlordjpadao.findAll();
	}

}