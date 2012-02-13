package org.knetwork.webapp.dao;

public interface UsageDao {
    
    public void recordUsage(String email, boolean existingUser);
    
}
