package com.example.syncmeet.dto;

import com.example.syncmeet.model.User.TierType;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Tier is required")
    private TierType tier;
}
