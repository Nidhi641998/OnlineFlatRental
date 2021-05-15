package com.cg.onlineflatrental.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineflatrental.dao.IFlatJpaDao;
import com.cg.onlineflatrental.exception.FlatNotFoundException;
import com.cg.onlineflatrental.model.Flat;

@Service
@Transactional
public class IFlatServiceImpl implements IFlatService  {

	@Autowired
	private IFlatJpaDao iflatjpadao;
	
	@Override
	public Flat addFlat(Flat flat) {
		
		return iflatjpadao.saveAndFlush(flat);
	}

	@Override
	public Flat updateFlat(Flat flat) throws FlatNotFoundException {
		Integer flatId=flat.getFlatId();
		Flat flat1=iflatjpadao.findById(flatId).get();
		flat1.setAvialibilty(flat.getAvialibilty());
		flat1.setCost(flat.getCost());
		flat1.setFlatAdress(flat.getFlatAdress());	
		return iflatjpadao.save(flat1);
		
	}

	@Override
	public boolean deleteFlatById(Integer flatId) throws FlatNotFoundException {
		
		Flat flat=iflatjpadao.findById(flatId).get();
		iflatjpadao.deleteById(flatId);
		
		if(null==flat)
		{
			return true;
		}
		return false;
	}

	@Override
	public Flat viewFlat(Integer flatId) throws FlatNotFoundException {
		
		return iflatjpadao.findById(flatId).get();  
	}

	@Override
	public List<Flat> viewAllFlat() {
		
		return iflatjpadao.findAll();
	}

	@Override
	public List<Flat> viewAllFlatByCost(float cost, String availability) {
		// TODO Auto-generated method stub
		return null;
	}

}
