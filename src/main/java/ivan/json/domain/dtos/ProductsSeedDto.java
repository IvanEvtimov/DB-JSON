package ivan.json.domain.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductsSeedDto {
    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    public ProductsSeedDto() {
    }

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, message = "Name must be at least 3 symbols long")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be positive")
    @DecimalMin("0.01")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
