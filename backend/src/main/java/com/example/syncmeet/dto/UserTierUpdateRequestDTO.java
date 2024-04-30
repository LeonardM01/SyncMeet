package com.example.syncmeet.dto;

import com.example.syncmeet.model.User.TierType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing a subscription tier change
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTierUpdateRequestDTO {
    private TierType tier;
}
