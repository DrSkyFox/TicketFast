package com.ticket.service;


import com.ticket.repr.AddressBookRepr;

public interface IAddressBookService {

    AddressBookRepr createAddressBook(AddressBookRepr addressBook);

    AddressBookRepr updateAddressBook(AddressBookRepr addressBook);



}
