package com.cg.onlineflatrental.service;

import java.util.List;

import com.cg.onlineflatrental.exception.TenantNotFoundException;
import com.cg.onlineflatrental.model.Tenant;


public interface ITenantService {

	public Tenant addTenant(Tenant tenant)throws TenantNotFoundException ;

	public List<Tenant> viewAllTenants();

	public Tenant viewTenantById(int tenantId)throws TenantNotFoundException;

	public Tenant updateTenant(Tenant tenant)throws TenantNotFoundException;

	public Boolean deleteTenant(int tenantId)throws TenantNotFoundException;

	public Tenant validateTenat(int tenantId);
}


