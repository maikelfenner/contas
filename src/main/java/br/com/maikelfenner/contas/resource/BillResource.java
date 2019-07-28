package br.com.maikelfenner.contas.resource;

import br.com.maikelfenner.contas.converter.BillEntityToDTO;
import br.com.maikelfenner.contas.dto.BillDTO;
import br.com.maikelfenner.contas.model.Bill;
import br.com.maikelfenner.contas.model.ErrorWrapper;
import br.com.maikelfenner.contas.service.BillService;
import br.com.maikelfenner.contas.service.exception.InvalidFieldException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/bill")
public class BillResource {

    private BillService billService;

    @Autowired
    public BillResource(BillService billService) {
        this.billService = billService;
    }

    @ApiOperation(value = "Listing all bills")
    @GetMapping
    public List<BillDTO> list() {
        BillEntityToDTO converter = new BillEntityToDTO();
        List<Bill> bills = billService.findAll();

        return bills.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Adding new bill ", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Bill created"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<BillDTO> add(@RequestBody HashMap<String, String> request) throws InvalidFieldException {
        String name = request.get("name");
        Double value = Double.valueOf(request.get("value"));
        LocalDate dueDate = LocalDate.parse(request.get("dueDate"));
        LocalDate paymentDate = LocalDate.parse(request.get("paymentDate"));

        BillDTO bill = new BillDTO(name, value, dueDate, paymentDate);

        BillDTO savedBill = billService.save(bill);

        return new ResponseEntity<>(savedBill, HttpStatus.CREATED);
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<ErrorWrapper> handleInvalidFieldException(InvalidFieldException e) {
        return new ResponseEntity<>(new ErrorWrapper(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
