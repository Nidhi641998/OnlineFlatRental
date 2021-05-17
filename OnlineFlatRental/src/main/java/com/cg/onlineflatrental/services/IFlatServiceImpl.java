package com.cg.onlineflatrental.services;

import java.util.List;
import java.util.Optional;

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
	
	String flatIdNotAvailable="flat with given id was not found";
	@Override
	public Flat addFlat(Flat flat) {
		
		return iflatjpadao.saveAndFlush(flat);
	}

	@Override
	public Flat updateFlat(Flat flat) throws FlatNotFoundException {
		Integer flatId=flat.getFlatId();
		Flat flat1=iflatjpadao.findById(flatId).get();
		flat1.setAvailability(flat.getAvailability());
		flat1.setCost(flat.getCost());
		flat1.setFlatAddress(flat.getFlatAddress());	
		return iflatjpadao.save(flat1);
		
	}

	@Override
	public boolean deleteFlatById(Integer flatId) throws FlatNotFoundException {
		
		Flat flat=iflatjpadao.findById(flatId).get();
		if(flat==null)
		{
			 throw new FlatNotFoundException(flatIdNotAvailable);
			
		}
		else {
		iflatjpadao.deleteById(flatId);
		
		return true;
		}
	}

	@Override
	public Flat viewFlat(Integer flatId) throws FlatNotFoundException {
		
		Optional<Flat> optional=iflatjpadao.findById(flatId);  
		if(optional.isPresent())
		{
			Flat flat=optional.get();
			return flat;
		}
		else
		{
			throw new FlatNotFoundException(flatIdNotAvailable);

		}
		
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
