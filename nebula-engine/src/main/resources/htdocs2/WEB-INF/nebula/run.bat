
ECHO @Package("Assets Management") type  Asset{ >> Asset.nebula
ECHO Org;>> Asset.nebula
ECHO User;>> Asset.nebula
ECHO Asset_Group;>> Asset.nebula
ECHO !ID;>> Asset.nebula
ECHO Assetdepreciationdate Date;>> Asset.nebula
ECHO Assetdisposaldate Date;>> Asset.nebula
ECHO Assetservicedate Date;>> Asset.nebula
ECHO Bpartnersr Bpartner;>> Asset.nebula
ECHO Bpartner;>> Asset.nebula
ECHO Bpartner_Location;>> Asset.nebula
ECHO Location;>> Asset.nebula
ECHO Project;>> Asset.nebula
ECHO Created Timestamp;>> Asset.nebula
ECHO Createdby Date;>> Asset.nebula
ECHO Description;>> Asset.nebula
ECHO Guaranteedate Date;>> Asset.nebula
ECHO Help;>> Asset.nebula
ECHO Isactive YesNo;>> Asset.nebula
ECHO Isdepreciated YesNo;>> Asset.nebula
ECHO Isdisposed YesNo;>> Asset.nebula
ECHO Isfullydepreciated YesNo;>> Asset.nebula
ECHO Isinposession YesNo;>> Asset.nebula
ECHO Isowned YesNo;>> Asset.nebula
ECHO Istrialphase YesNo;>> Asset.nebula
ECHO Lastmaintenancedate Date;>> Asset.nebula
ECHO Lastmaintenancenote Note;>> Asset.nebula
ECHO Lastmaintenanceunit Unit;>> Asset.nebula
ECHO Leaseterminationdate Date;>> Asset.nebula
ECHO Lease_Bpartner Bpartner;>> Asset.nebula
ECHO Lifeuseunits Number;>> Asset.nebula
ECHO Locationcomment Comment;>> Asset.nebula
ECHO LOT String;>> Asset.nebula
ECHO Attributesetinstance;>> Asset.nebula
ECHO Inoutline;>> Asset.nebula
ECHO Locator;>> Asset.nebula
ECHO Product;>> Asset.nebula
ECHO !Name;>> Asset.nebula
ECHO Nextmaintenencedate Date;>> Asset.nebula
ECHO Nextmaintenenceunit Unit;>> Asset.nebula
ECHO Processing YesNo;>> Asset.nebula
ECHO Qty Quantity;>> Asset.nebula
ECHO SerNo Code;>> Asset.nebula
ECHO Systemstatus YesNo;>> Asset.nebula
ECHO Updated Timestamp;>> Asset.nebula
ECHO Updatedby Date;>> Asset.nebula
ECHO Uselifemonths Number;>> Asset.nebula
ECHO Uselifeyears Count;>> Asset.nebula
ECHO Useunits Number;>> Asset.nebula
ECHO Value String;>> Asset.nebula
ECHO Versionno Code;>> Asset.nebula
ECHO };>> Asset.nebula 
 ECHO @Package("Assets Management") type  Asset_Delivery{ >> Asset_Delivery.nebula
ECHO Org;>> Asset_Delivery.nebula
ECHO User;>> Asset_Delivery.nebula
ECHO !ID;>> Asset_Delivery.nebula
ECHO Asset;>> Asset_Delivery.nebula
ECHO Created Timestamp;>> Asset_Delivery.nebula
ECHO Createdby Date;>> Asset_Delivery.nebula
ECHO Deliveryconfirmation Description;>> Asset_Delivery.nebula
ECHO Description;>> Asset_Delivery.nebula
ECHO Email;>> Asset_Delivery.nebula
ECHO Help;>> Asset_Delivery.nebula
ECHO Isactive YesNo;>> Asset_Delivery.nebula
ECHO LOT String;>> Asset_Delivery.nebula
ECHO Inoutline;>> Asset_Delivery.nebula
ECHO Productdownload;>> Asset_Delivery.nebula
ECHO Messageid ID;>> Asset_Delivery.nebula
ECHO Movementdate Date;>> Asset_Delivery.nebula
ECHO Referrer String;>> Asset_Delivery.nebula
ECHO Remote_Addr URL;>> Asset_Delivery.nebula
ECHO Remote_Host Host;>> Asset_Delivery.nebula
ECHO SerNo Code;>> Asset_Delivery.nebula
ECHO URL;>> Asset_Delivery.nebula
ECHO Updated Timestamp;>> Asset_Delivery.nebula
ECHO Updatedby Date;>> Asset_Delivery.nebula
ECHO Versionno Code;>> Asset_Delivery.nebula
ECHO };>> Asset_Delivery.nebula 
 ECHO @Package("Assets Management") type  Asset_Group{ >> Asset_Group.nebula
ECHO Org;>> Asset_Group.nebula
ECHO !ID;>> Asset_Group.nebula
ECHO Created Timestamp;>> Asset_Group.nebula
ECHO Createdby Date;>> Asset_Group.nebula
ECHO Description;>> Asset_Group.nebula
ECHO Help;>> Asset_Group.nebula
ECHO Isactive YesNo;>> Asset_Group.nebula
ECHO Iscreateasactive YesNo;>> Asset_Group.nebula
ECHO Isdepreciated YesNo;>> Asset_Group.nebula
ECHO Isoneassetperuom YesNo;>> Asset_Group.nebula
ECHO Isowned YesNo;>> Asset_Group.nebula
ECHO Istrackissues YesNo;>> Asset_Group.nebula
ECHO !Name;>> Asset_Group.nebula
ECHO Supportlevel YesNo;>> Asset_Group.nebula
ECHO Updated Timestamp;>> Asset_Group.nebula
ECHO Updatedby Date;>> Asset_Group.nebula
ECHO };>> Asset_Group.nebula 
 ECHO @Package("Assets Management") type  Asset_Retirement{ >> Asset_Retirement.nebula
ECHO Org;>> Asset_Retirement.nebula
ECHO Asset;>> Asset_Retirement.nebula
ECHO !ID;>> Asset_Retirement.nebula
ECHO Assetmarketvalueamt Amount;>> Asset_Retirement.nebula
ECHO Assetvalueamt Amount;>> Asset_Retirement.nebula
ECHO Invoiceline;>> Asset_Retirement.nebula
ECHO Created Timestamp;>> Asset_Retirement.nebula
ECHO Createdby Date;>> Asset_Retirement.nebula
ECHO Isactive YesNo;>> Asset_Retirement.nebula
ECHO Updated Timestamp;>> Asset_Retirement.nebula
ECHO Updatedby Date;>> Asset_Retirement.nebula
ECHO };>> Asset_Retirement.nebula 
 ECHO @Package("Assets Management") type  Registration{ >> Registration.nebula
ECHO Org;>> Registration.nebula
ECHO User;>> Registration.nebula
ECHO Asset;>> Registration.nebula
ECHO !ID;>> Registration.nebula
ECHO Assetservicedate Date;>> Registration.nebula
ECHO Bpartner;>> Registration.nebula
ECHO Created Timestamp;>> Registration.nebula
ECHO Createdby Date;>> Registration.nebula
ECHO Description;>> Registration.nebula
ECHO Help;>> Registration.nebula
ECHO Isactive YesNo;>> Registration.nebula
ECHO Isallowpublish YesNo;>> Registration.nebula
ECHO Isinproduction YesNo;>> Registration.nebula
ECHO Isregistered YesNo;>> Registration.nebula
ECHO Product;>> Registration.nebula
ECHO !Name;>> Registration.nebula
ECHO Note;>> Registration.nebula
ECHO Processing YesNo;>> Registration.nebula
ECHO Remote_Addr URL;>> Registration.nebula
ECHO Remote_Host Host;>> Registration.nebula
ECHO Updated Timestamp;>> Registration.nebula
ECHO Updatedby Date;>> Registration.nebula
ECHO };>> Registration.nebula 
 ECHO @Package("Assets Management") type  Registrationattribute{ >> Registrationattribute.nebula
ECHO Org;>> Registrationattribute.nebula
ECHO Reference;>> Registrationattribute.nebula
ECHO Reference_Value Reference;>> Registrationattribute.nebula
ECHO !ID;>> Registrationattribute.nebula
ECHO Columnname Name;>> Registrationattribute.nebula
ECHO Created Timestamp;>> Registrationattribute.nebula
ECHO Createdby Date;>> Registrationattribute.nebula
ECHO Description;>> Registrationattribute.nebula
ECHO Isactive YesNo;>> Registrationattribute.nebula
ECHO Isselfservice YesNo;>> Registrationattribute.nebula
ECHO !Name;>> Registrationattribute.nebula
ECHO SeqNo;>> Registrationattribute.nebula
ECHO Updated Timestamp;>> Registrationattribute.nebula
ECHO Updatedby Date;>> Registrationattribute.nebula
ECHO };>> Registrationattribute.nebula 
 ECHO @Package("Assets Management") type  Registrationproduct{ >> Registrationproduct.nebula
ECHO Org;>> Registrationproduct.nebula
ECHO !Registrationattribute;>> Registrationproduct.nebula
ECHO Created Timestamp;>> Registrationproduct.nebula
ECHO Createdby Date;>> Registrationproduct.nebula
ECHO Description;>> Registrationproduct.nebula
ECHO Isactive YesNo;>> Registrationproduct.nebula
ECHO !Product;>> Registrationproduct.nebula
ECHO Updated Timestamp;>> Registrationproduct.nebula
ECHO Updatedby Date;>> Registrationproduct.nebula
ECHO };>> Registrationproduct.nebula 
 ECHO @Package("Assets Management") type  Registrationvalue{ >> Registrationvalue.nebula
ECHO Org;>> Registrationvalue.nebula
ECHO !Registrationattribute;>> Registrationvalue.nebula
ECHO !Registration;>> Registrationvalue.nebula
ECHO Created Timestamp;>> Registrationvalue.nebula
ECHO Createdby Date;>> Registrationvalue.nebula
ECHO Description;>> Registrationvalue.nebula
ECHO Isactive YesNo;>> Registrationvalue.nebula
ECHO !Name;>> Registrationvalue.nebula
ECHO Updated Timestamp;>> Registrationvalue.nebula
ECHO Updatedby Date;>> Registrationvalue.nebula
ECHO };>> Registrationvalue.nebula 
 ECHO @Package("Application Dictionary") type  Accesslog{ >> Accesslog.nebula
ECHO Client;>> Accesslog.nebula
ECHO Column;>> Accesslog.nebula
ECHO Org;>> Accesslog.nebula
ECHO Table;>> Accesslog.nebula
ECHO User;>> Accesslog.nebula
ECHO Lead;>> Accesslog.nebula
ECHO Created Timestamp;>> Accesslog.nebula
ECHO Createdby Date;>> Accesslog.nebula
ECHO Description;>> Accesslog.nebula
ECHO Isactive YesNo;>> Accesslog.nebula

ECHO Remote_Addr URL;>> Accesslog.nebula
ECHO Remote_Host Host;>> Accesslog.nebula
ECHO Reply Note;>> Accesslog.nebula
ECHO Textmsg MSG;>> Accesslog.nebula
ECHO Updated Timestamp;>> Accesslog.nebula
ECHO Updatedby Date;>> Accesslog.nebula
ECHO };>> Accesslog.nebula 
 ECHO @Package("Application Dictionary") type  Alert{ >> Alert.nebula
ECHO !ID;>> Alert.nebula
ECHO Client;>> Alert.nebula
ECHO Org;>> Alert.nebula
ECHO Alertmessage MSG;>> Alert.nebula
ECHO Alertsubject Subject;>> Alert.nebula
ECHO Created Timestamp;>> Alert.nebula
ECHO Createdby Date;>> Alert.nebula
ECHO Description;>> Alert.nebula
ECHO Enforceclientsecurity YesNo;>> Alert.nebula
ECHO Enforcerolesecurity YesNo;>> Alert.nebula
ECHO Help;>> Alert.nebula
ECHO Isactive YesNo;>> Alert.nebula
ECHO Isvalid YesNo;>> Alert.nebula
ECHO !Name;>> Alert.nebula
ECHO Updated Timestamp;>> Alert.nebula
ECHO Updatedby Date;>> Alert.nebula
ECHO };>> Alert.nebula 
 ECHO @Package("Application Dictionary") type  Alertprocessor{ >> Alertprocessor.nebula
ECHO Client;>> Alertprocessor.nebula
ECHO Org;>> Alertprocessor.nebula
ECHO Schedule;>> Alertprocessor.nebula
ECHO Created Timestamp;>> Alertprocessor.nebula
ECHO Createdby Date;>> Alertprocessor.nebula
ECHO Datelastrun Date;>> Alertprocessor.nebula
ECHO Datenextrun Date;>> Alertprocessor.nebula
ECHO Description;>> Alertprocessor.nebula
ECHO Frequency;>> Alertprocessor.nebula
ECHO Frequencytype YesNo;>> Alertprocessor.nebula
ECHO Isactive YesNo;>> Alertprocessor.nebula
ECHO Keeplogdays Count;>> Alertprocessor.nebula
ECHO !Name;>> Alertprocessor.nebula
ECHO Processing YesNo;>> Alertprocessor.nebula
ECHO Supervisor User;>> Alertprocessor.nebula
ECHO Updated Timestamp;>> Alertprocessor.nebula
ECHO Updatedby Date;>> Alertprocessor.nebula
ECHO };>> Alertprocessor.nebula 
 ECHO @Package("Application Dictionary") type  Alertprocessorlog{ >> Alertprocessorlog.nebula
ECHO Alertprocessor;>> Alertprocessorlog.nebula
ECHO Client;>> Alertprocessorlog.nebula
ECHO Org;>> Alertprocessorlog.nebula
ECHO Binarydata Note;>> Alertprocessorlog.nebula
ECHO Created Timestamp;>> Alertprocessorlog.nebula
ECHO Createdby Date;>> Alertprocessorlog.nebula
ECHO Description;>> Alertprocessorlog.nebula
ECHO Isactive YesNo;>> Alertprocessorlog.nebula
ECHO Iserror YesNo;>> Alertprocessorlog.nebula
ECHO Reference String;>> Alertprocessorlog.nebula
ECHO Summary Note;>> Alertprocessorlog.nebula
ECHO Textmsg MSG;>> Alertprocessorlog.nebula
ECHO Updated Timestamp;>> Alertprocessorlog.nebula
ECHO Updatedby Date;>> Alertprocessorlog.nebula
ECHO };>> Alertprocessorlog.nebula 
 ECHO @Package("Application Dictionary") type  Alertrecipient{ >> Alertrecipient.nebula
ECHO Alert;>> Alertrecipient.nebula
ECHO Client;>> Alertrecipient.nebula
ECHO Org;>> Alertrecipient.nebula
ECHO Role;>> Alertrecipient.nebula
ECHO User;>> Alertrecipient.nebula
ECHO Created Timestamp;>> Alertrecipient.nebula
ECHO Createdby Date;>> Alertrecipient.nebula
ECHO Isactive YesNo;>> Alertrecipient.nebula
ECHO Updated Timestamp;>> Alertrecipient.nebula
ECHO Updatedby Date;>> Alertrecipient.nebula
ECHO };>> Alertrecipient.nebula 
 ECHO @Package("Application Dictionary") type  Alertrule{ >> Alertrule.nebula
ECHO Alert;>> Alertrule.nebula
ECHO Client;>> Alertrule.nebula
ECHO Org;>> Alertrule.nebula
ECHO Table;>> Alertrule.nebula
ECHO Created Timestamp;>> Alertrule.nebula
ECHO Createdby Date;>> Alertrule.nebula
ECHO ErrorMsg MSG;>> Alertrule.nebula
ECHO Fromclause Note;>> Alertrule.nebula
ECHO Isactive YesNo;>> Alertrule.nebula
ECHO Isvalid YesNo;>> Alertrule.nebula
ECHO !Name;>> Alertrule.nebula
ECHO Otherclause Note;>> Alertrule.nebula
ECHO Postprocessing YesNo;>> Alertrule.nebula
ECHO Preprocessing YesNo;>> Alertrule.nebula
ECHO Selectclause Note;>> Alertrule.nebula
ECHO Updated Timestamp;>> Alertrule.nebula
ECHO Updatedby Date;>> Alertrule.nebula
ECHO Whereclause Note;>> Alertrule.nebula
ECHO };>> Alertrule.nebula 
 ECHO @Package("Application Dictionary") type  Archive{ >> Archive.nebula
ECHO Client;>> Archive.nebula
ECHO Org;>> Archive.nebula
ECHO Process;>> Archive.nebula
ECHO Table;>> Archive.nebula
ECHO Binarydata Note;>> Archive.nebula
ECHO Bpartner;>> Archive.nebula
ECHO Created Timestamp;>> Archive.nebula
ECHO Createdby Date;>> Archive.nebula
ECHO Description;>> Archive.nebula
ECHO Help;>> Archive.nebula
ECHO Isactive YesNo;>> Archive.nebula
ECHO Isreport YesNo;>> Archive.nebula
ECHO !Name;>> Archive.nebula

ECHO Updated Timestamp;>> Archive.nebula
ECHO Updatedby Date;>> Archive.nebula
ECHO };>> Archive.nebula 
 ECHO @Package("Application Dictionary") type  Assigncriteria{ >> Assigncriteria.nebula
ECHO Assigntarget;>> Assigncriteria.nebula
ECHO Client;>> Assigncriteria.nebula
ECHO Org;>> Assigncriteria.nebula
ECHO Sourcecolumn Column;>> Assigncriteria.nebula
ECHO Andor YesNo;>> Assigncriteria.nebula
ECHO Created Timestamp;>> Assigncriteria.nebula
ECHO Createdby Date;>> Assigncriteria.nebula
ECHO Description;>> Assigncriteria.nebula
ECHO Isactive YesNo;>> Assigncriteria.nebula
ECHO Operation Attr;>> Assigncriteria.nebula

ECHO SeqNo;>> Assigncriteria.nebula
ECHO Updated Timestamp;>> Assigncriteria.nebula
ECHO Updatedby Date;>> Assigncriteria.nebula
ECHO Valuestring String;>> Assigncriteria.nebula
ECHO };>> Assigncriteria.nebula 
 ECHO @Package("Application Dictionary") type  Assignlog{ >> Assignlog.nebula
ECHO Assigntarget;>> Assignlog.nebula
ECHO Client;>> Assignlog.nebula
ECHO Org;>> Assignlog.nebula
ECHO Created Timestamp;>> Assignlog.nebula
ECHO Createdby Date;>> Assignlog.nebula
ECHO Help;>> Assignlog.nebula
ECHO Isactive YesNo;>> Assignlog.nebula

ECHO Updated Timestamp;>> Assignlog.nebula
ECHO Updatedby Date;>> Assignlog.nebula
ECHO };>> Assignlog.nebula 
 ECHO @Package("Application Dictionary") type  Assignset{ >> Assignset.nebula
ECHO Client;>> Assignset.nebula
ECHO Org;>> Assignset.nebula
ECHO Table;>> Assignset.nebula
ECHO Autoassignrule YesNo;>> Assignset.nebula
ECHO Created Timestamp;>> Assignset.nebula
ECHO Createdby Date;>> Assignset.nebula
ECHO Description;>> Assignset.nebula
ECHO Help;>> Assignset.nebula
ECHO Isactive YesNo;>> Assignset.nebula
ECHO !Name;>> Assignset.nebula
ECHO Updated Timestamp;>> Assignset.nebula
ECHO Updatedby Date;>> Assignset.nebula
ECHO };>> Assignset.nebula 
 ECHO @Package("Application Dictionary") type  Assigntarget{ >> Assigntarget.nebula
ECHO !ID;>> Assigntarget.nebula
ECHO Client;>> Assigntarget.nebula
ECHO Org;>> Assigntarget.nebula
ECHO Targetcolumn Column;>> Assigntarget.nebula
ECHO Assignrule YesNo;>> Assigntarget.nebula
ECHO Created Timestamp;>> Assigntarget.nebula
ECHO Createdby Date;>> Assigntarget.nebula
ECHO Description;>> Assigntarget.nebula
ECHO Isactive YesNo;>> Assigntarget.nebula

ECHO SeqNo;>> Assigntarget.nebula
ECHO Updated Timestamp;>> Assigntarget.nebula
ECHO Updatedby Date;>> Assigntarget.nebula
ECHO Valuesql String;>> Assigntarget.nebula
ECHO Valuestring String;>> Assigntarget.nebula
ECHO };>> Assigntarget.nebula 
 ECHO @Package("Application Dictionary") type  Attachment{ >> Attachment.nebula
ECHO Client;>> Attachment.nebula
ECHO Org;>> Attachment.nebula
ECHO Table;>> Attachment.nebula
ECHO Binarydata Note;>> Attachment.nebula
ECHO Created Timestamp;>> Attachment.nebula
ECHO Createdby Date;>> Attachment.nebula
ECHO Isactive YesNo;>> Attachment.nebula

ECHO Textmsg MSG;>> Attachment.nebula
ECHO Title String;>> Attachment.nebula
ECHO Updated Timestamp;>> Attachment.nebula
ECHO Updatedby Date;>> Attachment.nebula
ECHO };>> Attachment.nebula 
 ECHO @Package("Application Dictionary") type  Attachmentnote{ >> Attachmentnote.nebula
ECHO Attachment;>> Attachmentnote.nebula
ECHO Client;>> Attachmentnote.nebula
ECHO Org;>> Attachmentnote.nebula
ECHO User;>> Attachmentnote.nebula
ECHO Created Timestamp;>> Attachmentnote.nebula
ECHO Createdby Date;>> Attachmentnote.nebula
ECHO Isactive YesNo;>> Attachmentnote.nebula
ECHO Textmsg MSG;>> Attachmentnote.nebula
ECHO Title String;>> Attachmentnote.nebula
ECHO Updated Timestamp;>> Attachmentnote.nebula
ECHO Updatedby Date;>> Attachmentnote.nebula
ECHO };>> Attachmentnote.nebula 
 ECHO @Package("Application Dictionary") type  Attribute{ >> Attribute.nebula
ECHO Client;>> Attribute.nebula
ECHO Org;>> Attribute.nebula
ECHO Reference;>> Attribute.nebula
ECHO Reference_Value Reference;>> Attribute.nebula
ECHO Table;>> Attribute.nebula
ECHO Val_Rule;>> Attribute.nebula
ECHO Callout String;>> Attribute.nebula
ECHO Created Timestamp;>> Attribute.nebula
ECHO Createdby Date;>> Attribute.nebula
ECHO Defaultvalue Note;>> Attribute.nebula
ECHO Description;>> Attribute.nebula
ECHO Displaylength Length;>> Attribute.nebula
ECHO Displaylogic Note;>> Attribute.nebula
ECHO Fieldlength Length;>> Attribute.nebula
ECHO Help;>> Attribute.nebula
ECHO Isactive YesNo;>> Attribute.nebula
ECHO Isencrypted YesNo;>> Attribute.nebula
ECHO Isfieldonly YesNo;>> Attribute.nebula
ECHO Isheading YesNo;>> Attribute.nebula
ECHO Ismandatory YesNo;>> Attribute.nebula
ECHO Isreadonly YesNo;>> Attribute.nebula
ECHO Issameline YesNo;>> Attribute.nebula
ECHO Isupdateable YesNo;>> Attribute.nebula
ECHO !Name;>> Attribute.nebula
ECHO SeqNo;>> Attribute.nebula
ECHO Updated Timestamp;>> Attribute.nebula
ECHO Updatedby Date;>> Attribute.nebula
ECHO Vformat String;>> Attribute.nebula
ECHO Valuemax String;>> Attribute.nebula
ECHO Valuemin String;>> Attribute.nebula
ECHO };>> Attribute.nebula 
 ECHO @Package("Application Dictionary") type  Attribute_Value{ >> Attribute_Value.nebula

ECHO V_Date Date;>> Attribute_Value.nebula
ECHO V_Number Number;>> Attribute_Value.nebula
ECHO V_String Note;>> Attribute_Value.nebula
ECHO };>> Attribute_Value.nebula 
 ECHO @Package("Application Dictionary") type  Bview{ >> Bview.nebula
ECHO Ad_Bview_type YesNo;>> Bview.nebula
ECHO Client;>> Bview.nebula
ECHO Org;>> Bview.nebula
ECHO Created Timestamp;>> Bview.nebula
ECHO Createdby Date;>> Bview.nebula
ECHO Description;>> Bview.nebula
ECHO Entitytype Attr;>> Bview.nebula
ECHO Isactive YesNo;>> Bview.nebula
ECHO !Name;>> Bview.nebula
ECHO Updated Timestamp;>> Bview.nebula
ECHO Updatedby Date;>> Bview.nebula
ECHO };>> Bview.nebula 
 ECHO @Package("Application Dictionary") type  Bview_Access{ >> Bview_Access.nebula
ECHO Bview;>> Bview_Access.nebula
ECHO Client;>> Bview_Access.nebula
ECHO Org;>> Bview_Access.nebula
ECHO Role;>> Bview_Access.nebula
ECHO Created Timestamp;>> Bview_Access.nebula
ECHO Createdby Date;>> Bview_Access.nebula
ECHO Entitytype Attr;>> Bview_Access.nebula
ECHO Isactive YesNo;>> Bview_Access.nebula
ECHO Isreadwrite YesNo;>> Bview_Access.nebula
ECHO Updated Timestamp;>> Bview_Access.nebula
ECHO Updatedby Date;>> Bview_Access.nebula
ECHO };>> Bview_Access.nebula 
 ECHO @Package("Application Dictionary") type  Bview_Field{ >> Bview_Field.nebula
ECHO Bview;>> Bview_Field.nebula
ECHO Client;>> Bview_Field.nebula
ECHO Org;>> Bview_Field.nebula
ECHO Created Timestamp;>> Bview_Field.nebula
ECHO Createdby Date;>> Bview_Field.nebula
ECHO Description;>> Bview_Field.nebula
ECHO Entitytype Attr;>> Bview_Field.nebula
ECHO Isactive YesNo;>> Bview_Field.nebula
ECHO Isidentifier YesNo;>> Bview_Field.nebula
ECHO Isprinted YesNo;>> Bview_Field.nebula
ECHO !Name;>> Bview_Field.nebula
ECHO SeqNo;>> Bview_Field.nebula
ECHO Sortno SeqNo;>> Bview_Field.nebula
ECHO Updated Timestamp;>> Bview_Field.nebula
ECHO Updatedby Date;>> Bview_Field.nebula
ECHO };>> Bview_Field.nebula 
 ECHO @Package("Application Dictionary") type  Bview_Reportlayer{ >> Bview_Reportlayer.nebula
ECHO !ID;>> Bview_Reportlayer.nebula
ECHO Client;>> Bview_Reportlayer.nebula
ECHO Org;>> Bview_Reportlayer.nebula
ECHO Createprocess YesNo;>> Bview_Reportlayer.nebula
ECHO Created Timestamp;>> Bview_Reportlayer.nebula
ECHO Createdby Date;>> Bview_Reportlayer.nebula
ECHO Db_View_name Name;>> Bview_Reportlayer.nebula
ECHO Entitytype Attr;>> Bview_Reportlayer.nebula
ECHO Isactive YesNo;>> Bview_Reportlayer.nebula
ECHO !Name;>> Bview_Reportlayer.nebula
ECHO Publish YesNo;>> Bview_Reportlayer.nebula
ECHO Updated Timestamp;>> Bview_Reportlayer.nebula
ECHO Updatedby Date;>> Bview_Reportlayer.nebula
ECHO };>> Bview_Reportlayer.nebula 
 ECHO @Package("Application Dictionary") type  Bview_Reportlayer_map{ >> Bview_Reportlayer_map.nebula
ECHO Bview_Reportlayer;>> Bview_Reportlayer_map.nebula
ECHO !ID;>> Bview_Reportlayer_map.nebula
ECHO Client;>> Bview_Reportlayer_map.nebula
ECHO Org;>> Bview_Reportlayer_map.nebula
ECHO Columnname Name;>> Bview_Reportlayer_map.nebula
ECHO Created Timestamp;>> Bview_Reportlayer_map.nebula
ECHO Createdby Date;>> Bview_Reportlayer_map.nebula
ECHO Entitytype Attr;>> Bview_Reportlayer_map.nebula
ECHO Isactive YesNo;>> Bview_Reportlayer_map.nebula
ECHO Isidentifier YesNo;>> Bview_Reportlayer_map.nebula
ECHO !Name;>> Bview_Reportlayer_map.nebula
ECHO Updated Timestamp;>> Bview_Reportlayer_map.nebula
ECHO Updatedby Date;>> Bview_Reportlayer_map.nebula
ECHO };>> Bview_Reportlayer_map.nebula 
 ECHO @Package("Application Dictionary") type  Bview_Source{ >> Bview_Source.nebula
ECHO !ID;>> Bview_Source.nebula
ECHO Client;>> Bview_Source.nebula
ECHO Org;>> Bview_Source.nebula
ECHO Process;>> Bview_Source.nebula
ECHO Reportview;>> Bview_Source.nebula
ECHO Table;>> Bview_Source.nebula
ECHO Created Timestamp;>> Bview_Source.nebula
ECHO Createdby Date;>> Bview_Source.nebula
ECHO Description;>> Bview_Source.nebula
ECHO Entitytype Attr;>> Bview_Source.nebula
ECHO Importfields YesNo;>> Bview_Source.nebula
ECHO Isactive YesNo;>> Bview_Source.nebula
ECHO Isdefault YesNo;>> Bview_Source.nebula
ECHO !Name;>> Bview_Source.nebula
ECHO SeqNo;>> Bview_Source.nebula
ECHO Sourcetype YesNo;>> Bview_Source.nebula
ECHO Tablename Name;>> Bview_Source.nebula
ECHO Updated Timestamp;>> Bview_Source.nebula
ECHO Updatedby Date;>> Bview_Source.nebula
ECHO };>> Bview_Source.nebula 
 ECHO @Package("Application Dictionary") type  Bview_Source_mapping{ >> Bview_Source_mapping.nebula
ECHO Bview_Source_field Column;>> Bview_Source_mapping.nebula
ECHO Bview_Source;>> Bview_Source_mapping.nebula
ECHO !ID;>> Bview_Source_mapping.nebula
ECHO Client;>> Bview_Source_mapping.nebula
ECHO Org;>> Bview_Source_mapping.nebula
ECHO Created Timestamp;>> Bview_Source_mapping.nebula
ECHO Createdby Date;>> Bview_Source_mapping.nebula
ECHO Description;>> Bview_Source_mapping.nebula
ECHO Entitytype Attr;>> Bview_Source_mapping.nebula
ECHO Isactive YesNo;>> Bview_Source_mapping.nebula
ECHO !Name;>> Bview_Source_mapping.nebula
ECHO Updated Timestamp;>> Bview_Source_mapping.nebula
ECHO Updatedby Date;>> Bview_Source_mapping.nebula
ECHO };>> Bview_Source_mapping.nebula 
 ECHO @Package("Application Dictionary") type  Changelog{ >> Changelog.nebula
ECHO Client;>> Changelog.nebula
ECHO Column;>> Changelog.nebula
ECHO Org;>> Changelog.nebula
ECHO Role;>> Changelog.nebula
ECHO Session;>> Changelog.nebula
ECHO Table;>> Changelog.nebula
ECHO Changelogtype YesNo;>> Changelog.nebula
ECHO Created Timestamp;>> Changelog.nebula
ECHO Createdby Date;>> Changelog.nebula
ECHO Description;>> Changelog.nebula
ECHO Isactive YesNo;>> Changelog.nebula
ECHO Iscustomization YesNo;>> Changelog.nebula
ECHO Newvalue Note;>> Changelog.nebula
ECHO Oldvalue Note;>> Changelog.nebula


ECHO Redo YesNo;>> Changelog.nebula
ECHO Trxname Name;>> Changelog.nebula
ECHO Undo YesNo;>> Changelog.nebula
ECHO Updated Timestamp;>> Changelog.nebula
ECHO Updatedby Date;>> Changelog.nebula
ECHO };>> Changelog.nebula 
 ECHO @Package("Application Dictionary") type  Client{ >> Client.nebula
ECHO Ad_Language Language;>> Client.nebula
ECHO Org;>> Client.nebula
ECHO Autoarchive YesNo;>> Client.nebula
ECHO Created Timestamp;>> Client.nebula
ECHO Createdby Date;>> Client.nebula
ECHO Description;>> Client.nebula
ECHO Documentdir String;>> Client.nebula
ECHO Emailtest YesNo;>> Client.nebula
ECHO Isactive YesNo;>> Client.nebula
ECHO Iscostimmediate YesNo;>> Client.nebula
ECHO Ismultilingualdocument YesNo;>> Client.nebula
ECHO Ispostimmediate YesNo;>> Client.nebula
ECHO Isserveremail YesNo;>> Client.nebula
ECHO Issmtpauthorization YesNo;>> Client.nebula
ECHO Issmtptls YesNo;>> Client.nebula
ECHO Isusebetafunctions YesNo;>> Client.nebula
ECHO Ldapquery String;>> Client.nebula
ECHO Mmpolicy YesNo;>> Client.nebula
ECHO Modelvalidationclasses String;>> Client.nebula
ECHO !Name;>> Client.nebula
ECHO Requestemail Email;>> Client.nebula
ECHO Requestfolder String;>> Client.nebula
ECHO Requestuser String;>> Client.nebula
ECHO Requestuserpw String;>> Client.nebula
ECHO Smtphost Host;>> Client.nebula
ECHO Smtpport Number;>> Client.nebula
ECHO Updated Timestamp;>> Client.nebula
ECHO Updatedby Date;>> Client.nebula
ECHO Value String;>> Client.nebula
ECHO };>> Client.nebula 
 ECHO @Package("Application Dictionary") type  Clientinfo{ >> Clientinfo.nebula
ECHO Org;>> Clientinfo.nebula
ECHO Tree_Activity Tree;>> Clientinfo.nebula
ECHO Tree_Bpartner Tree;>> Clientinfo.nebula
ECHO Tree_Campaign Tree;>> Clientinfo.nebula
ECHO Tree_Menu Tree;>> Clientinfo.nebula
ECHO Tree_Org Tree;>> Clientinfo.nebula
ECHO Tree_Product Tree;>> Clientinfo.nebula
ECHO Tree_Project Tree;>> Clientinfo.nebula
ECHO Tree_Salesregion Tree;>> Clientinfo.nebula
ECHO Bankverificationclass Name;>> Clientinfo.nebula
ECHO Acctschema1 Acctschema;>> Clientinfo.nebula
ECHO Bpartnercashtrx Bpartner;>> Clientinfo.nebula
ECHO Calendar;>> Clientinfo.nebula
ECHO Uom_Length Uom;>> Clientinfo.nebula
ECHO Uom_Time Uom;>> Clientinfo.nebula
ECHO Uom_Volume Uom;>> Clientinfo.nebula
ECHO Uom_Weight Uom;>> Clientinfo.nebula
ECHO Created Timestamp;>> Clientinfo.nebula
ECHO Createdby Date;>> Clientinfo.nebula
ECHO Isactive YesNo;>> Clientinfo.nebula
ECHO Isdiscountlineamt YesNo;>> Clientinfo.nebula
ECHO Keeplogdays Count;>> Clientinfo.nebula
ECHO Productfreight Product;>> Clientinfo.nebula
ECHO Matchrequirementi YesNo;>> Clientinfo.nebula
ECHO Matchrequirementr YesNo;>> Clientinfo.nebula
ECHO Pricingengineclass String;>> Clientinfo.nebula
ECHO Requesttype;>> Clientinfo.nebula
ECHO Updated Timestamp;>> Clientinfo.nebula
ECHO Updatedby Date;>> Clientinfo.nebula
ECHO };>> Clientinfo.nebula 
 ECHO @Package("Application Dictionary") type  Clientshare{ >> Clientshare.nebula
ECHO Client;>> Clientshare.nebula
ECHO Org;>> Clientshare.nebula
ECHO Table;>> Clientshare.nebula
ECHO Created Timestamp;>> Clientshare.nebula
ECHO Createdby Date;>> Clientshare.nebula
ECHO Description;>> Clientshare.nebula
ECHO Isactive YesNo;>> Clientshare.nebula
ECHO !Name;>> Clientshare.nebula
ECHO Sharetype YesNo;>> Clientshare.nebula
ECHO Updated Timestamp;>> Clientshare.nebula
ECHO Updatedby Date;>> Clientshare.nebula
ECHO };>> Clientshare.nebula 
 ECHO @Package("Application Dictionary") type  Color{ >> Color.nebula
ECHO !ID;>> Color.nebula
ECHO Image;>> Color.nebula
ECHO Org;>> Color.nebula
ECHO Alpha Number;>> Color.nebula
ECHO Alpha_1 Number;>> Color.nebula
ECHO Blue Number;>> Color.nebula
ECHO Blue_1 Number;>> Color.nebula
ECHO Colortype YesNo;>> Color.nebula
ECHO Created Timestamp;>> Color.nebula
ECHO Createdby Date;>> Color.nebula
ECHO Green Number;>> Color.nebula
ECHO Green_1 Number;>> Color.nebula
ECHO Imagealpha Number;>> Color.nebula
ECHO Isactive YesNo;>> Color.nebula
ECHO Isdefault YesNo;>> Color.nebula
ECHO Linedistance Number;>> Color.nebula
ECHO Linewidth Width;>> Color.nebula
ECHO !Name;>> Color.nebula
ECHO Red Number;>> Color.nebula
ECHO Red_1 Number;>> Color.nebula
ECHO Repeatdistance Number;>> Color.nebula
ECHO Startpoint YesNo;>> Color.nebula
ECHO Updated Timestamp;>> Color.nebula
ECHO Updatedby Date;>> Color.nebula
ECHO };>> Color.nebula 
 ECHO @Package("Application Dictionary") type  Column{ >> Column.nebula
ECHO !ID;>> Column.nebula
ECHO Element;>> Column.nebula
ECHO Org;>> Column.nebula
ECHO Process;>> Column.nebula
ECHO Reference;>> Column.nebula
ECHO Reference_Value Reference;>> Column.nebula
ECHO Table;>> Column.nebula
ECHO Val_Rule;>> Column.nebula
ECHO Callout String;>> Column.nebula
ECHO Columnname Name;>> Column.nebula
ECHO Columnsql Note;>> Column.nebula
ECHO Constrainttype YesNo;>> Column.nebula
ECHO Created Timestamp;>> Column.nebula
ECHO Createdby Date;>> Column.nebula
ECHO Defaultvalue Note;>> Column.nebula
ECHO Description;>> Column.nebula
ECHO Entitytype Attr;>> Column.nebula
ECHO Fieldlength Length;>> Column.nebula
ECHO Help;>> Column.nebula
ECHO Isactive YesNo;>> Column.nebula
ECHO Isalwaysupdateable YesNo;>> Column.nebula
ECHO Iscallout YesNo;>> Column.nebula
ECHO Isencrypted YesNo;>> Column.nebula
ECHO Isidentifier YesNo;>> Column.nebula
ECHO Iskey YesNo;>> Column.nebula
ECHO Ismandatory YesNo;>> Column.nebula
ECHO Ismandatoryui YesNo;>> Column.nebula
ECHO Isparent YesNo;>> Column.nebula
ECHO Isrecursivefk YesNo;>> Column.nebula
ECHO Isselectioncolumn YesNo;>> Column.nebula
ECHO Issummarycolumn YesNo;>> Column.nebula
ECHO Issyncdatabase YesNo;>> Column.nebula
ECHO Istranslated YesNo;>> Column.nebula
ECHO Isupdateable YesNo;>> Column.nebula
ECHO Mandatorylogic Note;>> Column.nebula
ECHO !Name;>> Column.nebula
ECHO Readonlylogic Note;>> Column.nebula
ECHO Selectionseqno SeqNo;>> Column.nebula
ECHO SeqNo;>> Column.nebula
ECHO Summaryseqno SeqNo;>> Column.nebula
ECHO Tableuid ID;>> Column.nebula
ECHO Updated Timestamp;>> Column.nebula
ECHO Updatedby Date;>> Column.nebula
ECHO Vformat String;>> Column.nebula
ECHO Valuemax String;>> Column.nebula
ECHO Valuemin String;>> Column.nebula
ECHO Version Number;>> Column.nebula
ECHO };>> Column.nebula 
 ECHO @Package("Application Dictionary") type  Column_Access{ >> Column_Access.nebula
ECHO !Column;>> Column_Access.nebula
ECHO Org;>> Column_Access.nebula
ECHO !Role;>> Column_Access.nebula
ECHO Table;>> Column_Access.nebula
ECHO Created Timestamp;>> Column_Access.nebula
ECHO Createdby Date;>> Column_Access.nebula
ECHO Isactive YesNo;>> Column_Access.nebula
ECHO Isexclude YesNo;>> Column_Access.nebula
ECHO Isreadonly YesNo;>> Column_Access.nebula
ECHO Updated Timestamp;>> Column_Access.nebula
ECHO Updatedby Date;>> Column_Access.nebula
ECHO };>> Column_Access.nebula 
 ECHO @Package("Application Dictionary") type  Column_Trl{ >> Column_Trl.nebula
ECHO !Column;>> Column_Trl.nebula
ECHO !Ad_Language Language;>> Column_Trl.nebula
ECHO Org;>> Column_Trl.nebula
ECHO Created Timestamp;>> Column_Trl.nebula
ECHO Createdby Date;>> Column_Trl.nebula
ECHO Isactive YesNo;>> Column_Trl.nebula
ECHO Istranslated YesNo;>> Column_Trl.nebula
ECHO !Name;>> Column_Trl.nebula
ECHO Updated Timestamp;>> Column_Trl.nebula
ECHO Updatedby Date;>> Column_Trl.nebula
ECHO };>> Column_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Componentcreate{ >> Componentcreate.nebula
ECHO !ID;>> Componentcreate.nebula
ECHO Componentreg;>> Componentcreate.nebula
ECHO Org;>> Componentcreate.nebula
ECHO User;>> Componentcreate.nebula
ECHO Created Timestamp;>> Componentcreate.nebula
ECHO Createdby Date;>> Componentcreate.nebula
ECHO Description;>> Componentcreate.nebula
ECHO Email;>> Componentcreate.nebula
ECHO Isactive YesNo;>> Componentcreate.nebula
ECHO Referrer String;>> Componentcreate.nebula
ECHO Remote_Addr URL;>> Componentcreate.nebula
ECHO Remote_Host Host;>> Componentcreate.nebula
ECHO Responsetext Description;>> Componentcreate.nebula
ECHO Updated Timestamp;>> Componentcreate.nebula
ECHO Updatedby Date;>> Componentcreate.nebula
ECHO };>> Componentcreate.nebula 
 ECHO @Package("Application Dictionary") type  Componentreg{ >> Componentreg.nebula
ECHO !ID;>> Componentreg.nebula
ECHO Org;>> Componentreg.nebula
ECHO User;>> Componentreg.nebula
ECHO Approvalcomment Comment;>> Componentreg.nebula
ECHO Binarydata Note;>> Componentreg.nebula
ECHO Bpartner;>> Componentreg.nebula
ECHO Componentname Name;>> Componentreg.nebula
ECHO Componenttype YesNo;>> Componentreg.nebula
ECHO Created Timestamp;>> Componentreg.nebula
ECHO Createdby Date;>> Componentreg.nebula
ECHO Description;>> Componentreg.nebula
ECHO Distributiontype YesNo;>> Componentreg.nebula
ECHO Documentationtext Note;>> Componentreg.nebula
ECHO Help;>> Componentreg.nebula
ECHO Isactive YesNo;>> Componentreg.nebula
ECHO Isapproved YesNo;>> Componentreg.nebula
ECHO Ispublished YesNo;>> Componentreg.nebula
ECHO Pricelist_Version;>> Componentreg.nebula
ECHO Product_Category;>> Componentreg.nebula
ECHO Product;>> Componentreg.nebula
ECHO !Name;>> Componentreg.nebula
ECHO Pricestd Price;>> Componentreg.nebula
ECHO Processing YesNo;>> Componentreg.nebula
ECHO Requirecompiereversion String;>> Componentreg.nebula
ECHO Requirecomponentversion String;>> Componentreg.nebula
ECHO Suggestedprice Price;>> Componentreg.nebula
ECHO Trialphasedays Count;>> Componentreg.nebula
ECHO Updated Timestamp;>> Componentreg.nebula
ECHO Updatedby Date;>> Componentreg.nebula
ECHO Version Number;>> Componentreg.nebula
ECHO };>> Componentreg.nebula 
 ECHO @Package("Application Dictionary") type  Componentregupdate{ >> Componentregupdate.nebula
ECHO !ID;>> Componentregupdate.nebula
ECHO Componentreg;>> Componentregupdate.nebula
ECHO Org;>> Componentregupdate.nebula
ECHO User;>> Componentregupdate.nebula
ECHO Approvalcomment Comment;>> Componentregupdate.nebula
ECHO Binarydata Note;>> Componentregupdate.nebula
ECHO Created Timestamp;>> Componentregupdate.nebula
ECHO Createdby Date;>> Componentregupdate.nebula
ECHO Description;>> Componentregupdate.nebula
ECHO Documentationtext Note;>> Componentregupdate.nebula
ECHO Email;>> Componentregupdate.nebula
ECHO Help;>> Componentregupdate.nebula
ECHO Isactive YesNo;>> Componentregupdate.nebula
ECHO Isapproved YesNo;>> Componentregupdate.nebula
ECHO Ispublished YesNo;>> Componentregupdate.nebula
ECHO !Name;>> Componentregupdate.nebula
ECHO Pricestd Price;>> Componentregupdate.nebula
ECHO Referrer String;>> Componentregupdate.nebula
ECHO Remote_Addr URL;>> Componentregupdate.nebula
ECHO Remote_Host Host;>> Componentregupdate.nebula
ECHO Reply Note;>> Componentregupdate.nebula
ECHO Requirecompiereversion String;>> Componentregupdate.nebula
ECHO Requirecomponentversion String;>> Componentregupdate.nebula
ECHO Suggestedprice Price;>> Componentregupdate.nebula
ECHO Updated Timestamp;>> Componentregupdate.nebula
ECHO Updatedby Date;>> Componentregupdate.nebula
ECHO Version Number;>> Componentregupdate.nebula
ECHO };>> Componentregupdate.nebula 
 ECHO @Package("Application Dictionary") type  Componentuse{ >> Componentuse.nebula
ECHO Componentreg;>> Componentuse.nebula
ECHO !ID;>> Componentuse.nebula
ECHO Org;>> Componentuse.nebula
ECHO User;>> Componentuse.nebula
ECHO Created Timestamp;>> Componentuse.nebula
ECHO Createdby Date;>> Componentuse.nebula
ECHO Description;>> Componentuse.nebula
ECHO Email;>> Componentuse.nebula
ECHO Isactive YesNo;>> Componentuse.nebula
ECHO Referrer String;>> Componentuse.nebula
ECHO Remote_Addr URL;>> Componentuse.nebula
ECHO Remote_Host Host;>> Componentuse.nebula
ECHO Responsetext Description;>> Componentuse.nebula
ECHO Updated Timestamp;>> Componentuse.nebula
ECHO Updatedby Date;>> Componentuse.nebula
ECHO };>> Componentuse.nebula 
 ECHO @Package("Application Dictionary") type  Ctxarea{ >> Ctxarea.nebula
ECHO !ID;>> Ctxarea.nebula
ECHO Org;>> Ctxarea.nebula
ECHO Created Timestamp;>> Ctxarea.nebula
ECHO Createdby Date;>> Ctxarea.nebula
ECHO Description;>> Ctxarea.nebula
ECHO Entitytype Attr;>> Ctxarea.nebula
ECHO Help;>> Ctxarea.nebula
ECHO Isactive YesNo;>> Ctxarea.nebula
ECHO Issotrx YesNo;>> Ctxarea.nebula
ECHO !Name;>> Ctxarea.nebula
ECHO Updated Timestamp;>> Ctxarea.nebula
ECHO Updatedby Date;>> Ctxarea.nebula
ECHO };>> Ctxarea.nebula 
 ECHO @Package("Application Dictionary") type  Datamigration{ >> Datamigration.nebula
ECHO Client;>> Datamigration.nebula
ECHO !ID;>> Datamigration.nebula
ECHO Org;>> Datamigration.nebula
ECHO Created Timestamp;>> Datamigration.nebula
ECHO Createdby Date;>> Datamigration.nebula
ECHO Datamigrationtype YesNo;>> Datamigration.nebula
ECHO Description;>> Datamigration.nebula
ECHO Entitytype Attr;>> Datamigration.nebula
ECHO Help;>> Datamigration.nebula
ECHO Isactive YesNo;>> Datamigration.nebula
ECHO !Name;>> Datamigration.nebula
ECHO Processing YesNo;>> Datamigration.nebula
ECHO Updated Timestamp;>> Datamigration.nebula
ECHO Updatedby Date;>> Datamigration.nebula
ECHO };>> Datamigration.nebula 
 ECHO @Package("Application Dictionary") type  Datamigrationentry{ >> Datamigrationentry.nebula
ECHO !ID;>> Datamigrationentry.nebula
ECHO Datamigration;>> Datamigrationentry.nebula
ECHO Org;>> Datamigrationentry.nebula
ECHO Table;>> Datamigrationentry.nebula
ECHO Created Timestamp;>> Datamigrationentry.nebula
ECHO Createdby Date;>> Datamigrationentry.nebula
ECHO Datamigrationentrytype YesNo;>> Datamigrationentry.nebula
ECHO Description;>> Datamigrationentry.nebula
ECHO Isactive YesNo;>> Datamigrationentry.nebula

ECHO Updated Timestamp;>> Datamigrationentry.nebula
ECHO Updatedby Date;>> Datamigrationentry.nebula
ECHO Whereclause Note;>> Datamigrationentry.nebula
ECHO };>> Datamigrationentry.nebula 
 ECHO @Package("Application Dictionary") type  Datamigrationpreview{ >> Datamigrationpreview.nebula
ECHO !ID;>> Datamigrationpreview.nebula
ECHO Datamigration;>> Datamigrationpreview.nebula
ECHO Org;>> Datamigrationpreview.nebula
ECHO Table;>> Datamigrationpreview.nebula
ECHO Created Timestamp;>> Datamigrationpreview.nebula
ECHO Createdby Date;>> Datamigrationpreview.nebula
ECHO Description;>> Datamigrationpreview.nebula
ECHO Info Description;>> Datamigrationpreview.nebula
ECHO Isactive YesNo;>> Datamigrationpreview.nebula
ECHO Levelno SeqNo;>> Datamigrationpreview.nebula

ECHO Tableuid ID;>> Datamigrationpreview.nebula
ECHO Updated Timestamp;>> Datamigrationpreview.nebula
ECHO Updatedby Date;>> Datamigrationpreview.nebula
ECHO };>> Datamigrationpreview.nebula 
 ECHO @Package("Application Dictionary") type  Desktop{ >> Desktop.nebula
ECHO Color;>> Desktop.nebula
ECHO !ID;>> Desktop.nebula
ECHO Image;>> Desktop.nebula
ECHO Org;>> Desktop.nebula
ECHO Created Timestamp;>> Desktop.nebula
ECHO Createdby Date;>> Desktop.nebula
ECHO Description;>> Desktop.nebula
ECHO Help;>> Desktop.nebula
ECHO Isactive YesNo;>> Desktop.nebula
ECHO !Name;>> Desktop.nebula
ECHO Updated Timestamp;>> Desktop.nebula
ECHO Updatedby Date;>> Desktop.nebula
ECHO };>> Desktop.nebula 
 ECHO @Package("Application Dictionary") type  Desktop_Trl{ >> Desktop_Trl.nebula
ECHO !Desktop;>> Desktop_Trl.nebula
ECHO !Ad_Language Language;>> Desktop_Trl.nebula
ECHO Org;>> Desktop_Trl.nebula
ECHO Created Timestamp;>> Desktop_Trl.nebula
ECHO Createdby Date;>> Desktop_Trl.nebula
ECHO Description;>> Desktop_Trl.nebula
ECHO Help;>> Desktop_Trl.nebula
ECHO Isactive YesNo;>> Desktop_Trl.nebula
ECHO Istranslated YesNo;>> Desktop_Trl.nebula
ECHO !Name;>> Desktop_Trl.nebula
ECHO Updated Timestamp;>> Desktop_Trl.nebula
ECHO Updatedby Date;>> Desktop_Trl.nebula
ECHO };>> Desktop_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Desktopworkbench{ >> Desktopworkbench.nebula
ECHO !ID;>> Desktopworkbench.nebula
ECHO Desktop;>> Desktopworkbench.nebula
ECHO Org;>> Desktopworkbench.nebula
ECHO Workbench;>> Desktopworkbench.nebula
ECHO Created Timestamp;>> Desktopworkbench.nebula
ECHO Createdby Date;>> Desktopworkbench.nebula
ECHO Isactive YesNo;>> Desktopworkbench.nebula
ECHO SeqNo;>> Desktopworkbench.nebula
ECHO Updated Timestamp;>> Desktopworkbench.nebula
ECHO Updatedby Date;>> Desktopworkbench.nebula
ECHO };>> Desktopworkbench.nebula 
 ECHO @Package("Application Dictionary") type  Element{ >> Element.nebula
ECHO !ID;>> Element.nebula
ECHO Org;>> Element.nebula
ECHO Reference;>> Element.nebula
ECHO Reference_Value Reference;>> Element.nebula
ECHO Val_Rule;>> Element.nebula
ECHO Columnname Name;>> Element.nebula
ECHO Created Timestamp;>> Element.nebula
ECHO Createdby Date;>> Element.nebula
ECHO Dbdatatype YesNo;>> Element.nebula
ECHO Description;>> Element.nebula
ECHO Entitytype Attr;>> Element.nebula
ECHO Fieldlength Length;>> Element.nebula
ECHO Help;>> Element.nebula
ECHO Isactive YesNo;>> Element.nebula
ECHO !Name;>> Element.nebula
ECHO Po_Description Description;>> Element.nebula
ECHO Po_Help Note;>> Element.nebula
ECHO !Po_Name Name;>> Element.nebula
ECHO Po_Printname Name;>> Element.nebula
ECHO Printname Name;>> Element.nebula
ECHO Updated Timestamp;>> Element.nebula
ECHO Updatedby Date;>> Element.nebula
ECHO };>> Element.nebula 
 ECHO @Package("Application Dictionary") type  Element_Trl{ >> Element_Trl.nebula
ECHO !Element;>> Element_Trl.nebula
ECHO !Ad_Language Language;>> Element_Trl.nebula
ECHO Org;>> Element_Trl.nebula
ECHO Created Timestamp;>> Element_Trl.nebula
ECHO Createdby Date;>> Element_Trl.nebula
ECHO Description;>> Element_Trl.nebula
ECHO Help;>> Element_Trl.nebula
ECHO Isactive YesNo;>> Element_Trl.nebula
ECHO Istranslated YesNo;>> Element_Trl.nebula
ECHO !Name;>> Element_Trl.nebula
ECHO Po_Description Description;>> Element_Trl.nebula
ECHO Po_Help Note;>> Element_Trl.nebula
ECHO !Po_Name Name;>> Element_Trl.nebula
ECHO Po_Printname Name;>> Element_Trl.nebula
ECHO Printname Name;>> Element_Trl.nebula
ECHO Updated Timestamp;>> Element_Trl.nebula
ECHO Updatedby Date;>> Element_Trl.nebula
ECHO };>> Element_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Elementctx{ >> Elementctx.nebula
ECHO Ctxarea;>> Elementctx.nebula
ECHO !ID;>> Elementctx.nebula
ECHO Element;>> Elementctx.nebula
ECHO Org;>> Elementctx.nebula
ECHO Created Timestamp;>> Elementctx.nebula
ECHO Createdby Date;>> Elementctx.nebula
ECHO Description;>> Elementctx.nebula
ECHO Entitytype Attr;>> Elementctx.nebula
ECHO Help;>> Elementctx.nebula
ECHO Isactive YesNo;>> Elementctx.nebula
ECHO !Name;>> Elementctx.nebula
ECHO Printname Name;>> Elementctx.nebula
ECHO Updated Timestamp;>> Elementctx.nebula
ECHO Updatedby Date;>> Elementctx.nebula
ECHO };>> Elementctx.nebula 
 ECHO @Package("Application Dictionary") type  Elementctx_Trl{ >> Elementctx_Trl.nebula
ECHO !Elementctx;>> Elementctx_Trl.nebula
ECHO !Ad_Language Language;>> Elementctx_Trl.nebula
ECHO Org;>> Elementctx_Trl.nebula
ECHO Created Timestamp;>> Elementctx_Trl.nebula
ECHO Createdby Date;>> Elementctx_Trl.nebula
ECHO Description;>> Elementctx_Trl.nebula
ECHO Help;>> Elementctx_Trl.nebula
ECHO Isactive YesNo;>> Elementctx_Trl.nebula
ECHO Istranslated YesNo;>> Elementctx_Trl.nebula
ECHO !Name;>> Elementctx_Trl.nebula
ECHO Printname Name;>> Elementctx_Trl.nebula
ECHO Updated Timestamp;>> Elementctx_Trl.nebula
ECHO Updatedby Date;>> Elementctx_Trl.nebula
ECHO };>> Elementctx_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Entitytype{ >> Entitytype.nebula
ECHO ID;>> Entitytype.nebula
ECHO Org;>> Entitytype.nebula
ECHO Binarydata Note;>> Entitytype.nebula
ECHO Classpath String;>> Entitytype.nebula
ECHO Createcomponent YesNo;>> Entitytype.nebula
ECHO Created Timestamp;>> Entitytype.nebula
ECHO Createdby Date;>> Entitytype.nebula
ECHO Description;>> Entitytype.nebula
ECHO Documentationtext Note;>> Entitytype.nebula
ECHO !Entitytype Attr;>> Entitytype.nebula
ECHO Help;>> Entitytype.nebula
ECHO Isactive YesNo;>> Entitytype.nebula
ECHO Isregistered YesNo;>> Entitytype.nebula
ECHO Licensetext Note;>> Entitytype.nebula
ECHO Modelpackage String;>> Entitytype.nebula
ECHO Modelvalidationclasses String;>> Entitytype.nebula
ECHO !Name;>> Entitytype.nebula
ECHO Processing YesNo;>> Entitytype.nebula

ECHO Relativeloadno SeqNo;>> Entitytype.nebula
ECHO Requirecompiereversion String;>> Entitytype.nebula
ECHO Requirecomponentversion String;>> Entitytype.nebula
ECHO Summary Note;>> Entitytype.nebula
ECHO Updated Timestamp;>> Entitytype.nebula
ECHO Updatedby Date;>> Entitytype.nebula
ECHO Version Number;>> Entitytype.nebula
ECHO };>> Entitytype.nebula 
 ECHO @Package("Application Dictionary") type  Error{ >> Error.nebula
ECHO !ID;>> Error.nebula
ECHO Ad_Language Language;>> Error.nebula
ECHO Org;>> Error.nebula
ECHO Code Note;>> Error.nebula
ECHO Created Timestamp;>> Error.nebula
ECHO Createdby Date;>> Error.nebula
ECHO Isactive YesNo;>> Error.nebula
ECHO !Name;>> Error.nebula
ECHO Updated Timestamp;>> Error.nebula
ECHO Updatedby Date;>> Error.nebula
ECHO };>> Error.nebula 
 ECHO @Package("Application Dictionary") type  Eul_Setup{ >> Eul_Setup.nebula
ECHO !ID;>> Eul_Setup.nebula
ECHO Org;>> Eul_Setup.nebula
ECHO Createeuluser YesNo;>> Eul_Setup.nebula
ECHO Created Timestamp;>> Eul_Setup.nebula
ECHO Createdby Date;>> Eul_Setup.nebula
ECHO Description;>> Eul_Setup.nebula
ECHO Entitytype Attr;>> Eul_Setup.nebula
ECHO Isactive YesNo;>> Eul_Setup.nebula
ECHO !Name;>> Eul_Setup.nebula
ECHO Updated Timestamp;>> Eul_Setup.nebula
ECHO Updatedby Date;>> Eul_Setup.nebula
ECHO };>> Eul_Setup.nebula 
 ECHO @Package("Application Dictionary") type  Eul_User{ >> Eul_User.nebula
ECHO !ID;>> Eul_User.nebula
ECHO Org;>> Eul_User.nebula
ECHO Role;>> Eul_User.nebula
ECHO User;>> Eul_User.nebula
ECHO Created Timestamp;>> Eul_User.nebula
ECHO Createdby Date;>> Eul_User.nebula
ECHO Isactive YesNo;>> Eul_User.nebula
ECHO !Name;>> Eul_User.nebula
ECHO Password String;>> Eul_User.nebula
ECHO Updated Timestamp;>> Eul_User.nebula
ECHO Updatedby Date;>> Eul_User.nebula
ECHO };>> Eul_User.nebula 
 ECHO @Package("Application Dictionary") type  Field{ >> Field.nebula
ECHO Column;>> Field.nebula
ECHO Fieldgroup;>> Field.nebula
ECHO !ID;>> Field.nebula
ECHO Org;>> Field.nebula
ECHO Reference;>> Field.nebula
ECHO Tab;>> Field.nebula
ECHO Created Timestamp;>> Field.nebula
ECHO Createdby Date;>> Field.nebula
ECHO Defaultvalue Note;>> Field.nebula
ECHO Description;>> Field.nebula
ECHO Displaylength Length;>> Field.nebula
ECHO Displaylogic Note;>> Field.nebula
ECHO Entitytype Attr;>> Field.nebula
ECHO Help;>> Field.nebula
ECHO Isactive YesNo;>> Field.nebula
ECHO Iscentrallymaintained YesNo;>> Field.nebula
ECHO Isdefaultfocus YesNo;>> Field.nebula
ECHO Isdisplayed YesNo;>> Field.nebula
ECHO Isencrypted YesNo;>> Field.nebula
ECHO Isfieldonly YesNo;>> Field.nebula
ECHO Isheading YesNo;>> Field.nebula
ECHO Ismandatoryui YesNo;>> Field.nebula
ECHO Isreadonly YesNo;>> Field.nebula
ECHO Issameline YesNo;>> Field.nebula
ECHO Mrseqno SeqNo;>> Field.nebula
ECHO Maxheight Height;>> Field.nebula
ECHO Maxwidth Width;>> Field.nebula
ECHO !Name;>> Field.nebula
ECHO Obscuretype Attr;>> Field.nebula
ECHO SeqNo;>> Field.nebula
ECHO Sortno SeqNo;>> Field.nebula
ECHO Updated Timestamp;>> Field.nebula
ECHO Updatedby Date;>> Field.nebula
ECHO };>> Field.nebula 
 ECHO @Package("Application Dictionary") type  Field_Trl{ >> Field_Trl.nebula
ECHO !Field;>> Field_Trl.nebula
ECHO !Ad_Language Language;>> Field_Trl.nebula
ECHO Org;>> Field_Trl.nebula
ECHO Created Timestamp;>> Field_Trl.nebula
ECHO Createdby Date;>> Field_Trl.nebula
ECHO Description;>> Field_Trl.nebula
ECHO Help;>> Field_Trl.nebula
ECHO Isactive YesNo;>> Field_Trl.nebula
ECHO Istranslated YesNo;>> Field_Trl.nebula
ECHO !Name;>> Field_Trl.nebula
ECHO Updated Timestamp;>> Field_Trl.nebula
ECHO Updatedby Date;>> Field_Trl.nebula
ECHO };>> Field_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Fieldgroup{ >> Fieldgroup.nebula
ECHO !ID;>> Fieldgroup.nebula
ECHO Org;>> Fieldgroup.nebula
ECHO Created Timestamp;>> Fieldgroup.nebula
ECHO Createdby Date;>> Fieldgroup.nebula
ECHO Entitytype Attr;>> Fieldgroup.nebula
ECHO Isactive YesNo;>> Fieldgroup.nebula
ECHO !Name;>> Fieldgroup.nebula
ECHO Updated Timestamp;>> Fieldgroup.nebula
ECHO Updatedby Date;>> Fieldgroup.nebula
ECHO };>> Fieldgroup.nebula 
 ECHO @Package("Application Dictionary") type  Fieldgroup_Trl{ >> Fieldgroup_Trl.nebula
ECHO !Fieldgroup;>> Fieldgroup_Trl.nebula
ECHO !Ad_Language Language;>> Fieldgroup_Trl.nebula
ECHO Org;>> Fieldgroup_Trl.nebula
ECHO Created Timestamp;>> Fieldgroup_Trl.nebula
ECHO Createdby Date;>> Fieldgroup_Trl.nebula
ECHO Isactive YesNo;>> Fieldgroup_Trl.nebula
ECHO Istranslated YesNo;>> Fieldgroup_Trl.nebula
ECHO !Name;>> Fieldgroup_Trl.nebula
ECHO Updated Timestamp;>> Fieldgroup_Trl.nebula
ECHO Updatedby Date;>> Fieldgroup_Trl.nebula
ECHO };>> Fieldgroup_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Find{ >> Find.nebula
ECHO Column;>> Find.nebula
ECHO !ID;>> Find.nebula
ECHO Org;>> Find.nebula
ECHO Andor YesNo;>> Find.nebula
ECHO Created Timestamp;>> Find.nebula
ECHO Createdby Date;>> Find.nebula
ECHO ID;>> Find.nebula
ECHO Isactive YesNo;>> Find.nebula
ECHO Operation Attr;>> Find.nebula
ECHO Updated Timestamp;>> Find.nebula
ECHO Updatedby Date;>> Find.nebula
ECHO Value String;>> Find.nebula
ECHO Value2 String;>> Find.nebula
ECHO };>> Find.nebula 
 ECHO @Package("Application Dictionary") type  Form{ >> Form.nebula
ECHO Ctxarea;>> Form.nebula
ECHO !ID;>> Form.nebula
ECHO Org;>> Form.nebula
ECHO Accesslevel YesNo;>> Form.nebula
ECHO Classname Name;>> Form.nebula
ECHO Created Timestamp;>> Form.nebula
ECHO Createdby Date;>> Form.nebula
ECHO Description;>> Form.nebula
ECHO Entitytype Attr;>> Form.nebula
ECHO Help;>> Form.nebula
ECHO Isactive YesNo;>> Form.nebula
ECHO Isbetafunctionality YesNo;>> Form.nebula
ECHO Jspurl URL;>> Form.nebula
ECHO !Name;>> Form.nebula
ECHO Updated Timestamp;>> Form.nebula
ECHO Updatedby Date;>> Form.nebula
ECHO Webclassname Name;>> Form.nebula
ECHO };>> Form.nebula 
 ECHO @Package("Application Dictionary") type  Form_Access{ >> Form_Access.nebula
ECHO !Form;>> Form_Access.nebula
ECHO Org;>> Form_Access.nebula
ECHO !Role;>> Form_Access.nebula
ECHO Created Timestamp;>> Form_Access.nebula
ECHO Createdby Date;>> Form_Access.nebula
ECHO Isactive YesNo;>> Form_Access.nebula
ECHO Isreadwrite YesNo;>> Form_Access.nebula
ECHO Updated Timestamp;>> Form_Access.nebula
ECHO Updatedby Date;>> Form_Access.nebula
ECHO };>> Form_Access.nebula 
 ECHO @Package("Application Dictionary") type  Form_Trl{ >> Form_Trl.nebula
ECHO !Form;>> Form_Trl.nebula
ECHO !Ad_Language Language;>> Form_Trl.nebula
ECHO Org;>> Form_Trl.nebula
ECHO Created Timestamp;>> Form_Trl.nebula
ECHO Createdby Date;>> Form_Trl.nebula
ECHO Description;>> Form_Trl.nebula
ECHO Help;>> Form_Trl.nebula
ECHO Isactive YesNo;>> Form_Trl.nebula
ECHO Istranslated YesNo;>> Form_Trl.nebula
ECHO !Name;>> Form_Trl.nebula
ECHO Updated Timestamp;>> Form_Trl.nebula
ECHO Updatedby Date;>> Form_Trl.nebula
ECHO };>> Form_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Formshortcut{ >> Formshortcut.nebula
ECHO !ID;>> Formshortcut.nebula
ECHO Form;>> Formshortcut.nebula
ECHO Org;>> Formshortcut.nebula
ECHO Created Timestamp;>> Formshortcut.nebula
ECHO Createdby Date;>> Formshortcut.nebula
ECHO Description;>> Formshortcut.nebula
ECHO Help;>> Formshortcut.nebula
ECHO Isactive YesNo;>> Formshortcut.nebula
ECHO !Name;>> Formshortcut.nebula
ECHO Updated Timestamp;>> Formshortcut.nebula
ECHO Updatedby Date;>> Formshortcut.nebula
ECHO Value String;>> Formshortcut.nebula
ECHO };>> Formshortcut.nebula 
 ECHO @Package("Application Dictionary") type  Image{ >> Image.nebula
ECHO !ID;>> Image.nebula
ECHO Org;>> Image.nebula
ECHO Binarydata Note;>> Image.nebula
ECHO Created Timestamp;>> Image.nebula
ECHO Createdby Date;>> Image.nebula
ECHO Description;>> Image.nebula
ECHO Entitytype Attr;>> Image.nebula
ECHO Imageurl URL;>> Image.nebula
ECHO Isactive YesNo;>> Image.nebula
ECHO !Name;>> Image.nebula
ECHO Updated Timestamp;>> Image.nebula
ECHO Updatedby Date;>> Image.nebula
ECHO };>> Image.nebula 
 ECHO @Package("Application Dictionary") type  Impformat{ >> Impformat.nebula
ECHO !ID;>> Impformat.nebula
ECHO Org;>> Impformat.nebula
ECHO Table;>> Impformat.nebula
ECHO Created Timestamp;>> Impformat.nebula
ECHO Createdby Date;>> Impformat.nebula
ECHO Description;>> Impformat.nebula
ECHO Formattype YesNo;>> Impformat.nebula
ECHO Isactive YesNo;>> Impformat.nebula
ECHO !Name;>> Impformat.nebula
ECHO Processing YesNo;>> Impformat.nebula
ECHO Updated Timestamp;>> Impformat.nebula
ECHO Updatedby Date;>> Impformat.nebula
ECHO };>> Impformat.nebula 
 ECHO @Package("Application Dictionary") type  Impformat_Row{ >> Impformat_Row.nebula
ECHO Column;>> Impformat_Row.nebula
ECHO Impformat;>> Impformat_Row.nebula
ECHO !ID;>> Impformat_Row.nebula
ECHO Org;>> Impformat_Row.nebula
ECHO Callout String;>> Impformat_Row.nebula
ECHO Constantvalue String;>> Impformat_Row.nebula
ECHO Created Timestamp;>> Impformat_Row.nebula
ECHO Createdby Date;>> Impformat_Row.nebula
ECHO Dataformat String;>> Impformat_Row.nebula
ECHO Datatype YesNo;>> Impformat_Row.nebula
ECHO Decimalpoint Long;>> Impformat_Row.nebula
ECHO Divideby100 YesNo;>> Impformat_Row.nebula
ECHO Endno SeqNo;>> Impformat_Row.nebula
ECHO Isactive YesNo;>> Impformat_Row.nebula
ECHO !Name;>> Impformat_Row.nebula
ECHO Script Note;>> Impformat_Row.nebula
ECHO SeqNo;>> Impformat_Row.nebula
ECHO Startno SeqNo;>> Impformat_Row.nebula
ECHO Updated Timestamp;>> Impformat_Row.nebula
ECHO Updatedby Date;>> Impformat_Row.nebula
ECHO };>> Impformat_Row.nebula 
 ECHO @Package("Application Dictionary") type  Indexcolumn{ >> Indexcolumn.nebula
ECHO Column;>> Indexcolumn.nebula
ECHO !ID;>> Indexcolumn.nebula
ECHO Org;>> Indexcolumn.nebula
ECHO Tableindex;>> Indexcolumn.nebula
ECHO Columnsql Note;>> Indexcolumn.nebula
ECHO Created Timestamp;>> Indexcolumn.nebula
ECHO Createdby Date;>> Indexcolumn.nebula
ECHO Entitytype Attr;>> Indexcolumn.nebula
ECHO Isactive YesNo;>> Indexcolumn.nebula
ECHO SeqNo;>> Indexcolumn.nebula
ECHO Updated Timestamp;>> Indexcolumn.nebula
ECHO Updatedby Date;>> Indexcolumn.nebula
ECHO };>> Indexcolumn.nebula 
 ECHO @Package("Application Dictionary") type  Infocolumn{ >> Infocolumn.nebula
ECHO Element;>> Infocolumn.nebula
ECHO !ID;>> Infocolumn.nebula
ECHO Infowindow;>> Infocolumn.nebula
ECHO Org;>> Infocolumn.nebula
ECHO Reference;>> Infocolumn.nebula
ECHO Created Timestamp;>> Infocolumn.nebula
ECHO Createdby Date;>> Infocolumn.nebula
ECHO Description;>> Infocolumn.nebula
ECHO Entitytype Attr;>> Infocolumn.nebula
ECHO Help;>> Infocolumn.nebula
ECHO Isactive YesNo;>> Infocolumn.nebula
ECHO Iscentrallymaintained YesNo;>> Infocolumn.nebula
ECHO Isdisplayed YesNo;>> Infocolumn.nebula
ECHO Isidentifier YesNo;>> Infocolumn.nebula
ECHO Iskey YesNo;>> Infocolumn.nebula
ECHO Isquerycriteria YesNo;>> Infocolumn.nebula
ECHO Isrange YesNo;>> Infocolumn.nebula
ECHO !Name;>> Infocolumn.nebula
ECHO Selectclause Note;>> Infocolumn.nebula
ECHO SeqNo;>> Infocolumn.nebula
ECHO Updated Timestamp;>> Infocolumn.nebula
ECHO Updatedby Date;>> Infocolumn.nebula
ECHO };>> Infocolumn.nebula 
 ECHO @Package("Application Dictionary") type  Infocolumn_Trl{ >> Infocolumn_Trl.nebula
ECHO !Infocolumn;>> Infocolumn_Trl.nebula
ECHO !Ad_Language Language;>> Infocolumn_Trl.nebula
ECHO Org;>> Infocolumn_Trl.nebula
ECHO Created Timestamp;>> Infocolumn_Trl.nebula
ECHO Createdby Date;>> Infocolumn_Trl.nebula
ECHO Description;>> Infocolumn_Trl.nebula
ECHO Help;>> Infocolumn_Trl.nebula
ECHO Isactive YesNo;>> Infocolumn_Trl.nebula
ECHO Istranslated YesNo;>> Infocolumn_Trl.nebula
ECHO !Name;>> Infocolumn_Trl.nebula
ECHO Updated Timestamp;>> Infocolumn_Trl.nebula
ECHO Updatedby Date;>> Infocolumn_Trl.nebula
ECHO };>> Infocolumn_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Infowindow{ >> Infowindow.nebula
ECHO !ID;>> Infowindow.nebula
ECHO Org;>> Infowindow.nebula
ECHO Table;>> Infowindow.nebula
ECHO Autocompfromclause Note;>> Infowindow.nebula
ECHO Created Timestamp;>> Infowindow.nebula
ECHO Createdby Date;>> Infowindow.nebula
ECHO Description;>> Infowindow.nebula
ECHO Entitytype Attr;>> Infowindow.nebula
ECHO Fromclause Note;>> Infowindow.nebula
ECHO Help;>> Infowindow.nebula
ECHO Isactive YesNo;>> Infowindow.nebula
ECHO Iscustomdefault YesNo;>> Infowindow.nebula
ECHO !Name;>> Infowindow.nebula
ECHO Otherclause Note;>> Infowindow.nebula
ECHO Processing YesNo;>> Infowindow.nebula
ECHO Updated Timestamp;>> Infowindow.nebula
ECHO Updatedby Date;>> Infowindow.nebula
ECHO };>> Infowindow.nebula 
 ECHO @Package("Application Dictionary") type  Infowindow_Trl{ >> Infowindow_Trl.nebula
ECHO !Infowindow;>> Infowindow_Trl.nebula
ECHO !Ad_Language Language;>> Infowindow_Trl.nebula
ECHO Org;>> Infowindow_Trl.nebula
ECHO Created Timestamp;>> Infowindow_Trl.nebula
ECHO Createdby Date;>> Infowindow_Trl.nebula
ECHO Description;>> Infowindow_Trl.nebula
ECHO Help;>> Infowindow_Trl.nebula
ECHO Isactive YesNo;>> Infowindow_Trl.nebula
ECHO Istranslated YesNo;>> Infowindow_Trl.nebula
ECHO !Name;>> Infowindow_Trl.nebula
ECHO Updated Timestamp;>> Infowindow_Trl.nebula
ECHO Updatedby Date;>> Infowindow_Trl.nebula
ECHO };>> Infowindow_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Issue{ >> Issue.nebula
ECHO Form;>> Issue.nebula
ECHO !ID;>> Issue.nebula
ECHO Org;>> Issue.nebula
ECHO Process;>> Issue.nebula
ECHO Window;>> Issue.nebula
ECHO Asset;>> Issue.nebula
ECHO Comments Note;>> Issue.nebula
ECHO Created Timestamp;>> Issue.nebula
ECHO Createdby Date;>> Issue.nebula
ECHO Dbaddress String;>> Issue.nebula
ECHO Databaseinfo Description;>> Issue.nebula
ECHO Errortrace Note;>> Issue.nebula
ECHO Isactive YesNo;>> Issue.nebula
ECHO Isreproducible YesNo;>> Issue.nebula
ECHO Isvanillasystem YesNo;>> Issue.nebula
ECHO Issuesource YesNo;>> Issue.nebula
ECHO Issuesummary Note;>> Issue.nebula
ECHO Javainfo Description;>> Issue.nebula
ECHO Lineno SeqNo;>> Issue.nebula
ECHO Local_Host Host;>> Issue.nebula
ECHO Loggername Name;>> Issue.nebula
ECHO !Name;>> Issue.nebula
ECHO Operatingsysteminfo Description;>> Issue.nebula
ECHO Processed YesNo;>> Issue.nebula
ECHO Processing YesNo;>> Issue.nebula
ECHO Profileinfo Description;>> Issue.nebula
ECHO Issueknown;>> Issue.nebula
ECHO Issueproject;>> Issue.nebula
ECHO Issuesystem;>> Issue.nebula
ECHO Issueuser;>> Issue.nebula
ECHO Request;>> Issue.nebula

ECHO Releaseno Code;>> Issue.nebula
ECHO Releasetag String;>> Issue.nebula
ECHO Remote_Addr URL;>> Issue.nebula
ECHO Remote_Host Host;>> Issue.nebula
ECHO Requestdocumentno SeqNo;>> Issue.nebula
ECHO Responsetext Description;>> Issue.nebula
ECHO Sourceclassname Name;>> Issue.nebula
ECHO Sourcemethodname Name;>> Issue.nebula
ECHO Stacktrace Note;>> Issue.nebula
ECHO Statisticsinfo String;>> Issue.nebula
ECHO Supportemail Email;>> Issue.nebula
ECHO Systemstatus YesNo;>> Issue.nebula
ECHO Updated Timestamp;>> Issue.nebula
ECHO Updatedby Date;>> Issue.nebula
ECHO Username Name;>> Issue.nebula
ECHO Version Number;>> Issue.nebula
ECHO };>> Issue.nebula 
 ECHO @Package("Application Dictionary") type  Labelprinter{ >> Labelprinter.nebula
ECHO !ID;>> Labelprinter.nebula
ECHO Org;>> Labelprinter.nebula
ECHO Created Timestamp;>> Labelprinter.nebula
ECHO Createdby Date;>> Labelprinter.nebula
ECHO Description;>> Labelprinter.nebula
ECHO Isactive YesNo;>> Labelprinter.nebula
ECHO !Name;>> Labelprinter.nebula
ECHO Updated Timestamp;>> Labelprinter.nebula
ECHO Updatedby Date;>> Labelprinter.nebula
ECHO };>> Labelprinter.nebula 
 ECHO @Package("Application Dictionary") type  Labelprinterfunction{ >> Labelprinterfunction.nebula
ECHO !ID;>> Labelprinterfunction.nebula
ECHO Labelprinter;>> Labelprinterfunction.nebula
ECHO Org;>> Labelprinterfunction.nebula
ECHO Created Timestamp;>> Labelprinterfunction.nebula
ECHO Createdby Date;>> Labelprinterfunction.nebula
ECHO Description;>> Labelprinterfunction.nebula
ECHO Functionprefix String;>> Labelprinterfunction.nebula
ECHO Functionsuffix String;>> Labelprinterfunction.nebula
ECHO Isactive YesNo;>> Labelprinterfunction.nebula
ECHO Isxyposition YesNo;>> Labelprinterfunction.nebula
ECHO !Name;>> Labelprinterfunction.nebula
ECHO Updated Timestamp;>> Labelprinterfunction.nebula
ECHO Updatedby Date;>> Labelprinterfunction.nebula
ECHO Xyseparator String;>> Labelprinterfunction.nebula
ECHO };>> Labelprinterfunction.nebula 
 ECHO @Package("Application Dictionary") type  Language{ >> Language.nebula
ECHO !Ad_Language Language;>> Language.nebula
ECHO ID;>> Language.nebula
ECHO Org;>> Language.nebula
ECHO Countrycode Attr;>> Language.nebula
ECHO Created Timestamp;>> Language.nebula
ECHO Createdby Date;>> Language.nebula
ECHO Datepattern String;>> Language.nebula
ECHO Isactive YesNo;>> Language.nebula
ECHO Isbaselanguage YesNo;>> Language.nebula
ECHO Isdecimalpoint YesNo;>> Language.nebula
ECHO Issystemlanguage YesNo;>> Language.nebula
ECHO Languageiso String;>> Language.nebula
ECHO !Name;>> Language.nebula
ECHO Processing YesNo;>> Language.nebula
ECHO Timepattern String;>> Language.nebula
ECHO Updated Timestamp;>> Language.nebula
ECHO Updatedby Date;>> Language.nebula
ECHO };>> Language.nebula 
 ECHO @Package("Application Dictionary") type  Ldapaccess{ >> Ldapaccess.nebula
ECHO !ID;>> Ldapaccess.nebula
ECHO Ldapprocessor;>> Ldapaccess.nebula
ECHO Org;>> Ldapaccess.nebula
ECHO User;>> Ldapaccess.nebula
ECHO Asset;>> Ldapaccess.nebula
ECHO Created Timestamp;>> Ldapaccess.nebula
ECHO Createdby Date;>> Ldapaccess.nebula
ECHO Description;>> Ldapaccess.nebula
ECHO Isactive YesNo;>> Ldapaccess.nebula
ECHO Iserror YesNo;>> Ldapaccess.nebula
ECHO Interestarea;>> Ldapaccess.nebula
ECHO Remote_Addr URL;>> Ldapaccess.nebula
ECHO Remote_Host Host;>> Ldapaccess.nebula
ECHO Summary Note;>> Ldapaccess.nebula
ECHO Updated Timestamp;>> Ldapaccess.nebula
ECHO Updatedby Date;>> Ldapaccess.nebula
ECHO };>> Ldapaccess.nebula 
 ECHO @Package("Application Dictionary") type  Ldapprocessor{ >> Ldapprocessor.nebula
ECHO !ID;>> Ldapprocessor.nebula
ECHO Org;>> Ldapprocessor.nebula
ECHO Created Timestamp;>> Ldapprocessor.nebula
ECHO Createdby Date;>> Ldapprocessor.nebula
ECHO Datelastrun Date;>> Ldapprocessor.nebula
ECHO Datenextrun Date;>> Ldapprocessor.nebula
ECHO Description;>> Ldapprocessor.nebula
ECHO Isactive YesNo;>> Ldapprocessor.nebula
ECHO Keeplogdays Count;>> Ldapprocessor.nebula
ECHO Ldapport Number;>> Ldapprocessor.nebula
ECHO !Name;>> Ldapprocessor.nebula
ECHO Processing YesNo;>> Ldapprocessor.nebula
ECHO Supervisor User;>> Ldapprocessor.nebula
ECHO Updated Timestamp;>> Ldapprocessor.nebula
ECHO Updatedby Date;>> Ldapprocessor.nebula
ECHO };>> Ldapprocessor.nebula 
 ECHO @Package("Application Dictionary") type  Ldapprocessorlog{ >> Ldapprocessorlog.nebula
ECHO !ID;>> Ldapprocessorlog.nebula
ECHO Ldapprocessor;>> Ldapprocessorlog.nebula
ECHO Org;>> Ldapprocessorlog.nebula
ECHO Binarydata Note;>> Ldapprocessorlog.nebula
ECHO Created Timestamp;>> Ldapprocessorlog.nebula
ECHO Createdby Date;>> Ldapprocessorlog.nebula
ECHO Description;>> Ldapprocessorlog.nebula
ECHO Isactive YesNo;>> Ldapprocessorlog.nebula
ECHO Iserror YesNo;>> Ldapprocessorlog.nebula
ECHO Reference String;>> Ldapprocessorlog.nebula
ECHO Summary Note;>> Ldapprocessorlog.nebula
ECHO Textmsg MSG;>> Ldapprocessorlog.nebula
ECHO Updated Timestamp;>> Ldapprocessorlog.nebula
ECHO Updatedby Date;>> Ldapprocessorlog.nebula
ECHO };>> Ldapprocessorlog.nebula 
 ECHO @Package("Application Dictionary") type  Loginmsg{ >> Loginmsg.nebula
ECHO !ID;>> Loginmsg.nebula
ECHO Org;>> Loginmsg.nebula
ECHO Classname Name;>> Loginmsg.nebula
ECHO Created Timestamp;>> Loginmsg.nebula
ECHO Createdby Date;>> Loginmsg.nebula
ECHO Description;>> Loginmsg.nebula
ECHO Isactive YesNo;>> Loginmsg.nebula
ECHO Loginmsgfrequency YesNo;>> Loginmsg.nebula
ECHO Loginmsgtype YesNo;>> Loginmsg.nebula
ECHO !Name;>> Loginmsg.nebula
ECHO Textmsg MSG;>> Loginmsg.nebula
ECHO Updated Timestamp;>> Loginmsg.nebula
ECHO Updatedby Date;>> Loginmsg.nebula
ECHO Validfrom Date;>> Loginmsg.nebula
ECHO Validto Date;>> Loginmsg.nebula
ECHO Whereclause Note;>> Loginmsg.nebula
ECHO };>> Loginmsg.nebula 
 ECHO @Package("Application Dictionary") type  Loginmsglog{ >> Loginmsglog.nebula
ECHO !ID;>> Loginmsglog.nebula
ECHO Loginmsg;>> Loginmsglog.nebula
ECHO Org;>> Loginmsglog.nebula
ECHO Session;>> Loginmsglog.nebula
ECHO User;>> Loginmsglog.nebula
ECHO Created Timestamp;>> Loginmsglog.nebula
ECHO Createdby Date;>> Loginmsglog.nebula
ECHO Description;>> Loginmsglog.nebula
ECHO Isactive YesNo;>> Loginmsglog.nebula
ECHO Isuseraccepted YesNo;>> Loginmsglog.nebula
ECHO Updated Timestamp;>> Loginmsglog.nebula
ECHO Updatedby Date;>> Loginmsglog.nebula
ECHO };>> Loginmsglog.nebula 
 ECHO @Package("Application Dictionary") type  Menu{ >> Menu.nebula
ECHO Form;>> Menu.nebula
ECHO !ID;>> Menu.nebula
ECHO Org;>> Menu.nebula
ECHO Process;>> Menu.nebula
ECHO Task;>> Menu.nebula
ECHO Window;>> Menu.nebula
ECHO Workbench;>> Menu.nebula
ECHO Workflow;>> Menu.nebula
ECHO Action YesNo;>> Menu.nebula
ECHO Created Timestamp;>> Menu.nebula
ECHO Createdby Date;>> Menu.nebula
ECHO Description;>> Menu.nebula
ECHO Entitytype Attr;>> Menu.nebula
ECHO Isactive YesNo;>> Menu.nebula
ECHO Isreadonly YesNo;>> Menu.nebula
ECHO Issummary YesNo;>> Menu.nebula
ECHO !Name;>> Menu.nebula
ECHO Updated Timestamp;>> Menu.nebula
ECHO Updatedby Date;>> Menu.nebula
ECHO };>> Menu.nebula 
 ECHO @Package("Application Dictionary") type  Menu_Trl{ >> Menu_Trl.nebula
ECHO !Ad_Language Language;>> Menu_Trl.nebula
ECHO !Menu;>> Menu_Trl.nebula
ECHO Org;>> Menu_Trl.nebula
ECHO Created Timestamp;>> Menu_Trl.nebula
ECHO Createdby Date;>> Menu_Trl.nebula
ECHO Description;>> Menu_Trl.nebula
ECHO Isactive YesNo;>> Menu_Trl.nebula
ECHO Istranslated YesNo;>> Menu_Trl.nebula
ECHO !Name;>> Menu_Trl.nebula
ECHO Updated Timestamp;>> Menu_Trl.nebula
ECHO Updatedby Date;>> Menu_Trl.nebula
ECHO };>> Menu_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Message{ >> Message.nebula
ECHO !ID;>> Message.nebula
ECHO Org;>> Message.nebula
ECHO Created Timestamp;>> Message.nebula
ECHO Createdby Date;>> Message.nebula
ECHO Entitytype Attr;>> Message.nebula
ECHO Isactive YesNo;>> Message.nebula
ECHO Msgtext Note;>> Message.nebula
ECHO Msgtip Note;>> Message.nebula
ECHO Msgtype YesNo;>> Message.nebula
ECHO Updated Timestamp;>> Message.nebula
ECHO Updatedby Date;>> Message.nebula
ECHO Value String;>> Message.nebula
ECHO };>> Message.nebula 
 ECHO @Package("Application Dictionary") type  Message_Trl{ >> Message_Trl.nebula
ECHO !Ad_Language Language;>> Message_Trl.nebula
ECHO !Message;>> Message_Trl.nebula
ECHO Org;>> Message_Trl.nebula
ECHO Created Timestamp;>> Message_Trl.nebula
ECHO Createdby Date;>> Message_Trl.nebula
ECHO Isactive YesNo;>> Message_Trl.nebula
ECHO Istranslated YesNo;>> Message_Trl.nebula
ECHO Msgtext Note;>> Message_Trl.nebula
ECHO Msgtip Note;>> Message_Trl.nebula
ECHO Updated Timestamp;>> Message_Trl.nebula
ECHO Updatedby Date;>> Message_Trl.nebula
ECHO };>> Message_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Migrationstep{ >> Migrationstep.nebula
ECHO !ID;>> Migrationstep.nebula
ECHO Org;>> Migrationstep.nebula
ECHO Version;>> Migrationstep.nebula
ECHO Classname Name;>> Migrationstep.nebula
ECHO Code Note;>> Migrationstep.nebula
ECHO Created Timestamp;>> Migrationstep.nebula
ECHO Createdby Date;>> Migrationstep.nebula
ECHO Dbtype YesNo;>> Migrationstep.nebula
ECHO Description;>> Migrationstep.nebula
ECHO Help;>> Migrationstep.nebula
ECHO Isactive YesNo;>> Migrationstep.nebula
ECHO Isoktofail YesNo;>> Migrationstep.nebula
ECHO !Name;>> Migrationstep.nebula
ECHO SeqNo;>> Migrationstep.nebula
ECHO Timingtype YesNo;>> Migrationstep.nebula
ECHO Type YesNo;>> Migrationstep.nebula
ECHO Updated Timestamp;>> Migrationstep.nebula
ECHO Updatedby Date;>> Migrationstep.nebula
ECHO };>> Migrationstep.nebula 
 ECHO @Package("Application Dictionary") type  Modification{ >> Modification.nebula
ECHO !ID;>> Modification.nebula
ECHO Org;>> Modification.nebula
ECHO Version;>> Modification.nebula
ECHO Created Timestamp;>> Modification.nebula
ECHO Createdby Date;>> Modification.nebula
ECHO Description;>> Modification.nebula
ECHO Help;>> Modification.nebula
ECHO Isactive YesNo;>> Modification.nebula
ECHO Modificationtype YesNo;>> Modification.nebula
ECHO !Name;>> Modification.nebula
ECHO SeqNo;>> Modification.nebula
ECHO Updated Timestamp;>> Modification.nebula
ECHO Updatedby Date;>> Modification.nebula
ECHO };>> Modification.nebula 
 ECHO @Package("Application Dictionary") type  Note{ >> Note.nebula
ECHO Message;>> Note.nebula
ECHO !ID;>> Note.nebula
ECHO Org;>> Note.nebula
ECHO Table;>> Note.nebula
ECHO User;>> Note.nebula
ECHO Wf_Activity;>> Note.nebula
ECHO Created Timestamp;>> Note.nebula
ECHO Createdby Date;>> Note.nebula
ECHO Description;>> Note.nebula
ECHO Isactive YesNo;>> Note.nebula
ECHO Processed YesNo;>> Note.nebula
ECHO Processing YesNo;>> Note.nebula

ECHO Reference String;>> Note.nebula
ECHO Textmsg MSG;>> Note.nebula
ECHO Updated Timestamp;>> Note.nebula
ECHO Updatedby Date;>> Note.nebula
ECHO };>> Note.nebula 
 ECHO @Package("Application Dictionary") type  Org{ >> Org.nebula
ECHO !ID;>> Org.nebula
ECHO Created Timestamp;>> Org.nebula
ECHO Createdby Date;>> Org.nebula
ECHO Description;>> Org.nebula
ECHO Isactive YesNo;>> Org.nebula
ECHO Issummary YesNo;>> Org.nebula
ECHO Isvalid YesNo;>> Org.nebula
ECHO !Name;>> Org.nebula
ECHO Updated Timestamp;>> Org.nebula
ECHO Updatedby Date;>> Org.nebula
ECHO Value String;>> Org.nebula
ECHO };>> Org.nebula 
 ECHO @Package("Application Dictionary") type  Orginfo{ >> Orginfo.nebula
ECHO Orgtype;>> Orginfo.nebula
ECHO !Org;>> Orginfo.nebula
ECHO Calendar;>> Orginfo.nebula
ECHO Location;>> Orginfo.nebula
ECHO Created Timestamp;>> Orginfo.nebula
ECHO Createdby Date;>> Orginfo.nebula
ECHO DUNS;>> Orginfo.nebula
ECHO Isactive YesNo;>> Orginfo.nebula
ECHO Warehouse;>> Orginfo.nebula
ECHO Parent_Org Org;>> Orginfo.nebula
ECHO Supervisor User;>> Orginfo.nebula
ECHO Taxid ID;>> Orginfo.nebula
ECHO Updated Timestamp;>> Orginfo.nebula
ECHO Updatedby Date;>> Orginfo.nebula
ECHO };>> Orginfo.nebula 
 ECHO @Package("Application Dictionary") type  Orgtype{ >> Orgtype.nebula
ECHO !ID;>> Orgtype.nebula
ECHO Org;>> Orgtype.nebula
ECHO Printcolor;>> Orgtype.nebula
ECHO Created Timestamp;>> Orgtype.nebula
ECHO Createdby Date;>> Orgtype.nebula
ECHO Description;>> Orgtype.nebula
ECHO Isactive YesNo;>> Orgtype.nebula
ECHO Isbalancing YesNo;>> Orgtype.nebula
ECHO Islegalentity YesNo;>> Orgtype.nebula
ECHO !Name;>> Orgtype.nebula
ECHO Updated Timestamp;>> Orgtype.nebula
ECHO Updatedby Date;>> Orgtype.nebula
ECHO };>> Orgtype.nebula 
 ECHO @Package("Application Dictionary") type  Pinstance{ >> Pinstance.nebula
ECHO Org;>> Pinstance.nebula
ECHO !ID;>> Pinstance.nebula
ECHO Process;>> Pinstance.nebula
ECHO Role;>> Pinstance.nebula
ECHO Session;>> Pinstance.nebula
ECHO User;>> Pinstance.nebula
ECHO Created Timestamp;>> Pinstance.nebula
ECHO Createdby Date;>> Pinstance.nebula
ECHO ErrorMsg MSG;>> Pinstance.nebula
ECHO Isactive YesNo;>> Pinstance.nebula
ECHO Isprocessing YesNo;>> Pinstance.nebula

ECHO Result Number;>> Pinstance.nebula
ECHO Updated Timestamp;>> Pinstance.nebula
ECHO Updatedby Date;>> Pinstance.nebula
ECHO };>> Pinstance.nebula 
 ECHO @Package("Application Dictionary") type  Pinstance_Log{ >> Pinstance_Log.nebula

ECHO P_Date Date;>> Pinstance_Log.nebula

ECHO P_Msg MSG;>> Pinstance_Log.nebula
ECHO P_Number Number;>> Pinstance_Log.nebula
ECHO };>> Pinstance_Log.nebula 
 ECHO @Package("Application Dictionary") type  Pinstance_Para{ >> Pinstance_Para.nebula
ECHO Org;>> Pinstance_Para.nebula
ECHO !Pinstance;>> Pinstance_Para.nebula
ECHO Created Timestamp;>> Pinstance_Para.nebula
ECHO Createdby Date;>> Pinstance_Para.nebula
ECHO Info Description;>> Pinstance_Para.nebula
ECHO Info_To String;>> Pinstance_Para.nebula
ECHO Isactive YesNo;>> Pinstance_Para.nebula
ECHO P_Date Date;>> Pinstance_Para.nebula
ECHO P_Date_to Date;>> Pinstance_Para.nebula
ECHO P_Number Number;>> Pinstance_Para.nebula
ECHO P_Number_to Number;>> Pinstance_Para.nebula
ECHO P_String String;>> Pinstance_Para.nebula
ECHO P_String_to String;>> Pinstance_Para.nebula
ECHO Parametername Name;>> Pinstance_Para.nebula
ECHO !SeqNo;>> Pinstance_Para.nebula
ECHO Updated Timestamp;>> Pinstance_Para.nebula
ECHO Updatedby Date;>> Pinstance_Para.nebula
ECHO };>> Pinstance_Para.nebula 
 ECHO @Package("Application Dictionary") type  Preference{ >> Preference.nebula
ECHO Org;>> Preference.nebula
ECHO !ID;>> Preference.nebula
ECHO User;>> Preference.nebula
ECHO Window;>> Preference.nebula
ECHO Attribute String;>> Preference.nebula
ECHO Created Timestamp;>> Preference.nebula
ECHO Createdby Date;>> Preference.nebula
ECHO Isactive YesNo;>> Preference.nebula
ECHO Updated Timestamp;>> Preference.nebula
ECHO Updatedby Date;>> Preference.nebula
ECHO Value String;>> Preference.nebula
ECHO };>> Preference.nebula 
 ECHO @Package("Application Dictionary") type  Printcolor{ >> Printcolor.nebula
ECHO Org;>> Printcolor.nebula
ECHO !ID;>> Printcolor.nebula
ECHO Code Note;>> Printcolor.nebula
ECHO Created Timestamp;>> Printcolor.nebula
ECHO Createdby Date;>> Printcolor.nebula
ECHO Isactive YesNo;>> Printcolor.nebula
ECHO Isdefault YesNo;>> Printcolor.nebula
ECHO !Name;>> Printcolor.nebula
ECHO Updated Timestamp;>> Printcolor.nebula
ECHO Updatedby Date;>> Printcolor.nebula
ECHO };>> Printcolor.nebula 
 ECHO @Package("Application Dictionary") type  Printfont{ >> Printfont.nebula
ECHO Org;>> Printfont.nebula
ECHO !ID;>> Printfont.nebula
ECHO Code Note;>> Printfont.nebula
ECHO Created Timestamp;>> Printfont.nebula
ECHO Createdby Date;>> Printfont.nebula
ECHO Isactive YesNo;>> Printfont.nebula
ECHO Isdefault YesNo;>> Printfont.nebula
ECHO !Name;>> Printfont.nebula
ECHO Updated Timestamp;>> Printfont.nebula
ECHO Updatedby Date;>> Printfont.nebula
ECHO };>> Printfont.nebula 
 ECHO @Package("Application Dictionary") type  Printform{ >> Printform.nebula
ECHO Org;>> Printform.nebula
ECHO !ID;>> Printform.nebula
ECHO Created Timestamp;>> Printform.nebula
ECHO Createdby Date;>> Printform.nebula
ECHO Description;>> Printform.nebula
ECHO Inventory_Mailtext Mailtext;>> Printform.nebula
ECHO Inventory_Printformat Printformat;>> Printform.nebula
ECHO Invoice_Mailtext Mailtext;>> Printform.nebula
ECHO Invoice_Printformat Printformat;>> Printform.nebula
ECHO Isactive YesNo;>> Printform.nebula
ECHO Movement_Mailtext Mailtext;>> Printform.nebula
ECHO Movement_Printformat Printformat;>> Printform.nebula
ECHO !Name;>> Printform.nebula
ECHO Order_Mailtext Mailtext;>> Printform.nebula
ECHO Order_Printformat Printformat;>> Printform.nebula
ECHO Pck_Clutlist_printformat Printformat;>> Printform.nebula
ECHO Pck_Ordtlist_printformat Printformat;>> Printform.nebula
ECHO Project_Mailtext Mailtext;>> Printform.nebula
ECHO Project_Printformat Printformat;>> Printform.nebula
ECHO Put_Tlist_printformat Printformat;>> Printform.nebula
ECHO Remittance_Mailtext Mailtext;>> Printform.nebula
ECHO Remittance_Printformat Printformat;>> Printform.nebula
ECHO Rpl_Tlist_printformat Printformat;>> Printform.nebula
ECHO Shipment_Mailtext Mailtext;>> Printform.nebula
ECHO Shipment_Printformat Printformat;>> Printform.nebula
ECHO Updated Timestamp;>> Printform.nebula
ECHO Updatedby Date;>> Printform.nebula
ECHO Workorder_Printformat Printformat;>> Printform.nebula
ECHO };>> Printform.nebula 
 ECHO @Package("Application Dictionary") type  Printformat{ >> Printformat.nebula
ECHO Client;>> Printformat.nebula
ECHO Org;>> Printformat.nebula
ECHO Printcolor;>> Printformat.nebula
ECHO Printfont;>> Printformat.nebula
ECHO !ID;>> Printformat.nebula
ECHO Printpaper;>> Printformat.nebula
ECHO Printtableformat;>> Printformat.nebula
ECHO Reportview;>> Printformat.nebula
ECHO Table;>> Printformat.nebula
ECHO Createcopy YesNo;>> Printformat.nebula
ECHO Created Timestamp;>> Printformat.nebula
ECHO Createdby Date;>> Printformat.nebula
ECHO Description;>> Printformat.nebula
ECHO Footermargin Long;>> Printformat.nebula
ECHO Headermargin Long;>> Printformat.nebula
ECHO Isactive YesNo;>> Printformat.nebula
ECHO Isdefault YesNo;>> Printformat.nebula
ECHO Isform YesNo;>> Printformat.nebula
ECHO Isstandardheaderfooter YesNo;>> Printformat.nebula
ECHO Issuppressdupgroupby YesNo;>> Printformat.nebula
ECHO Istablebased YesNo;>> Printformat.nebula
ECHO Istotalsonly YesNo;>> Printformat.nebula
ECHO !Name;>> Printformat.nebula
ECHO Printername Name;>> Printformat.nebula
ECHO Updated Timestamp;>> Printformat.nebula
ECHO Updatedby Date;>> Printformat.nebula
ECHO };>> Printformat.nebula 
 ECHO @Package("Application Dictionary") type  Printformatitem{ >> Printformatitem.nebula
ECHO Client;>> Printformatitem.nebula
ECHO Column;>> Printformatitem.nebula
ECHO Fieldgroup;>> Printformatitem.nebula
ECHO Org;>> Printformatitem.nebula
ECHO Printcolor;>> Printformatitem.nebula
ECHO Printfont;>> Printformatitem.nebula
ECHO Printformatchild Printformat;>> Printformatitem.nebula
ECHO !ID;>> Printformatitem.nebula
ECHO Printformat;>> Printformatitem.nebula
ECHO Printgraph;>> Printformatitem.nebula
ECHO Arcdiameter Number;>> Printformatitem.nebula
ECHO Barcodetype Attr;>> Printformatitem.nebula
ECHO Belowcolumn Number;>> Printformatitem.nebula
ECHO Created Timestamp;>> Printformatitem.nebula
ECHO Createdby Date;>> Printformatitem.nebula
ECHO Fieldalignmenttype YesNo;>> Printformatitem.nebula
ECHO Imageisattached YesNo;>> Printformatitem.nebula
ECHO Imageurl URL;>> Printformatitem.nebula
ECHO Isactive YesNo;>> Printformatitem.nebula
ECHO Isascending YesNo;>> Printformatitem.nebula
ECHO Isaveraged YesNo;>> Printformatitem.nebula
ECHO Iscentrallymaintained YesNo;>> Printformatitem.nebula
ECHO Iscounted YesNo;>> Printformatitem.nebula
ECHO Isdeviationcalc YesNo;>> Printformatitem.nebula
ECHO Isfilledrectangle YesNo;>> Printformatitem.nebula
ECHO Isfixedwidth YesNo;>> Printformatitem.nebula
ECHO Isgroupby YesNo;>> Printformatitem.nebula
ECHO Isheightoneline YesNo;>> Printformatitem.nebula
ECHO Isimagefield YesNo;>> Printformatitem.nebula
ECHO Ismaxcalc YesNo;>> Printformatitem.nebula
ECHO Ismincalc YesNo;>> Printformatitem.nebula
ECHO Isnextline YesNo;>> Printformatitem.nebula
ECHO Isnextpage YesNo;>> Printformatitem.nebula
ECHO Isorderby YesNo;>> Printformatitem.nebula
ECHO Ispagebreak YesNo;>> Printformatitem.nebula
ECHO Isprinted YesNo;>> Printformatitem.nebula
ECHO Isrelativeposition YesNo;>> Printformatitem.nebula
ECHO Isrunningtotal YesNo;>> Printformatitem.nebula
ECHO Issetnlposition YesNo;>> Printformatitem.nebula
ECHO Issummarized YesNo;>> Printformatitem.nebula
ECHO Issuppressnull YesNo;>> Printformatitem.nebula
ECHO Isvariancecalc YesNo;>> Printformatitem.nebula
ECHO Linealignmenttype YesNo;>> Printformatitem.nebula
ECHO Linewidth Width;>> Printformatitem.nebula
ECHO Maxheight Height;>> Printformatitem.nebula
ECHO Maxwidth Width;>> Printformatitem.nebula
ECHO !Name;>> Printformatitem.nebula
ECHO Printareatype YesNo;>> Printformatitem.nebula
ECHO Printformattype YesNo;>> Printformatitem.nebula
ECHO Printname Name;>> Printformatitem.nebula
ECHO Printnamesuffix String;>> Printformatitem.nebula
ECHO Runningtotallines Number;>> Printformatitem.nebula
ECHO SeqNo;>> Printformatitem.nebula
ECHO Shapetype YesNo;>> Printformatitem.nebula
ECHO Sortno SeqNo;>> Printformatitem.nebula
ECHO Updated Timestamp;>> Printformatitem.nebula
ECHO Updatedby Date;>> Printformatitem.nebula
ECHO Xposition Long;>> Printformatitem.nebula
ECHO Xspace Number;>> Printformatitem.nebula
ECHO Yposition Long;>> Printformatitem.nebula
ECHO Yspace Number;>> Printformatitem.nebula
ECHO };>> Printformatitem.nebula 
 ECHO @Package("Application Dictionary") type  Printformatitem_Trl{ >> Printformatitem_Trl.nebula
ECHO !Ad_Language Language;>> Printformatitem_Trl.nebula
ECHO Org;>> Printformatitem_Trl.nebula
ECHO !Printformatitem;>> Printformatitem_Trl.nebula
ECHO Created Timestamp;>> Printformatitem_Trl.nebula
ECHO Createdby Date;>> Printformatitem_Trl.nebula
ECHO Isactive YesNo;>> Printformatitem_Trl.nebula
ECHO Istranslated YesNo;>> Printformatitem_Trl.nebula
ECHO Printname Name;>> Printformatitem_Trl.nebula
ECHO Printnamesuffix String;>> Printformatitem_Trl.nebula
ECHO Updated Timestamp;>> Printformatitem_Trl.nebula
ECHO Updatedby Date;>> Printformatitem_Trl.nebula
ECHO };>> Printformatitem_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Printgraph{ >> Printgraph.nebula
ECHO Org;>> Printgraph.nebula
ECHO Printformat;>> Printgraph.nebula
ECHO !ID;>> Printgraph.nebula
ECHO Created Timestamp;>> Printgraph.nebula
ECHO Createdby Date;>> Printgraph.nebula
ECHO Data1_Printformatitem Printformatitem;>> Printgraph.nebula
ECHO Data2_Printformatitem Printformatitem;>> Printgraph.nebula
ECHO Data3_Printformatitem Printformatitem;>> Printgraph.nebula
ECHO Data4_Printformatitem Printformatitem;>> Printgraph.nebula
ECHO Data_Printformatitem Printformatitem;>> Printgraph.nebula
ECHO Description;>> Printgraph.nebula
ECHO Description_Printformatitem Printformatitem;>> Printgraph.nebula
ECHO Graphtype YesNo;>> Printgraph.nebula
ECHO Isactive YesNo;>> Printgraph.nebula
ECHO !Name;>> Printgraph.nebula
ECHO Updated Timestamp;>> Printgraph.nebula
ECHO Updatedby Date;>> Printgraph.nebula
ECHO };>> Printgraph.nebula 
 ECHO @Package("Application Dictionary") type  Printlabel{ >> Printlabel.nebula
ECHO Labelprinter;>> Printlabel.nebula
ECHO Org;>> Printlabel.nebula
ECHO !ID;>> Printlabel.nebula
ECHO Table;>> Printlabel.nebula
ECHO Created Timestamp;>> Printlabel.nebula
ECHO Createdby Date;>> Printlabel.nebula
ECHO Description;>> Printlabel.nebula
ECHO Isactive YesNo;>> Printlabel.nebula
ECHO Islandscape YesNo;>> Printlabel.nebula
ECHO Labelheight Height;>> Printlabel.nebula
ECHO Labelwidth Width;>> Printlabel.nebula
ECHO !Name;>> Printlabel.nebula
ECHO Printername Name;>> Printlabel.nebula
ECHO Updated Timestamp;>> Printlabel.nebula
ECHO Updatedby Date;>> Printlabel.nebula
ECHO };>> Printlabel.nebula 
 ECHO @Package("Application Dictionary") type  Printlabelline{ >> Printlabelline.nebula
ECHO Column;>> Printlabelline.nebula
ECHO Labelprinterfunction;>> Printlabelline.nebula
ECHO Org;>> Printlabelline.nebula
ECHO !ID;>> Printlabelline.nebula
ECHO Printlabel;>> Printlabelline.nebula
ECHO Created Timestamp;>> Printlabelline.nebula
ECHO Createdby Date;>> Printlabelline.nebula
ECHO Isactive YesNo;>> Printlabelline.nebula
ECHO Labelformattype YesNo;>> Printlabelline.nebula
ECHO !Name;>> Printlabelline.nebula
ECHO Printname Name;>> Printlabelline.nebula
ECHO SeqNo;>> Printlabelline.nebula
ECHO Updated Timestamp;>> Printlabelline.nebula
ECHO Updatedby Date;>> Printlabelline.nebula
ECHO Xposition Long;>> Printlabelline.nebula
ECHO Yposition Long;>> Printlabelline.nebula
ECHO };>> Printlabelline.nebula 
 ECHO @Package("Application Dictionary") type  Printlabelline_Trl{ >> Printlabelline_Trl.nebula
ECHO !Ad_Language Language;>> Printlabelline_Trl.nebula
ECHO Org;>> Printlabelline_Trl.nebula
ECHO !Printlabelline;>> Printlabelline_Trl.nebula
ECHO Created Timestamp;>> Printlabelline_Trl.nebula
ECHO Createdby Date;>> Printlabelline_Trl.nebula
ECHO Isactive YesNo;>> Printlabelline_Trl.nebula
ECHO Istranslated YesNo;>> Printlabelline_Trl.nebula
ECHO Printname Name;>> Printlabelline_Trl.nebula
ECHO Updated Timestamp;>> Printlabelline_Trl.nebula
ECHO Updatedby Date;>> Printlabelline_Trl.nebula
ECHO };>> Printlabelline_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Printpaper{ >> Printpaper.nebula
ECHO Org;>> Printpaper.nebula
ECHO !ID;>> Printpaper.nebula
ECHO Code Note;>> Printpaper.nebula
ECHO Created Timestamp;>> Printpaper.nebula
ECHO Createdby Date;>> Printpaper.nebula
ECHO Description;>> Printpaper.nebula
ECHO Dimensionunits YesNo;>> Printpaper.nebula
ECHO Isactive YesNo;>> Printpaper.nebula
ECHO Isdefault YesNo;>> Printpaper.nebula
ECHO Islandscape YesNo;>> Printpaper.nebula
ECHO Marginbottom Long;>> Printpaper.nebula
ECHO Marginleft Long;>> Printpaper.nebula
ECHO Marginright Long;>> Printpaper.nebula
ECHO Margintop Long;>> Printpaper.nebula
ECHO !Name;>> Printpaper.nebula
ECHO Processing YesNo;>> Printpaper.nebula
ECHO Sizex Long;>> Printpaper.nebula
ECHO Sizey Long;>> Printpaper.nebula
ECHO Updated Timestamp;>> Printpaper.nebula
ECHO Updatedby Date;>> Printpaper.nebula
ECHO };>> Printpaper.nebula 
 ECHO @Package("Application Dictionary") type  Printtableformat{ >> Printtableformat.nebula
ECHO Org;>> Printtableformat.nebula
ECHO !ID;>> Printtableformat.nebula
ECHO Created Timestamp;>> Printtableformat.nebula
ECHO Createdby Date;>> Printtableformat.nebula
ECHO Description;>> Printtableformat.nebula
ECHO Footercenter String;>> Printtableformat.nebula
ECHO Footerleft String;>> Printtableformat.nebula
ECHO Footerright String;>> Printtableformat.nebula
ECHO Functbg_Printcolor Printcolor;>> Printtableformat.nebula
ECHO Functfg_Printcolor Printcolor;>> Printtableformat.nebula
ECHO Funct_Printfont Printfont;>> Printtableformat.nebula
ECHO Hdrline_Printcolor Printcolor;>> Printtableformat.nebula
ECHO Hdrstroke Number;>> Printtableformat.nebula
ECHO Hdrstroketype YesNo;>> Printtableformat.nebula
ECHO Hdrtextbg_Printcolor Printcolor;>> Printtableformat.nebula
ECHO Hdrtextfg_Printcolor Printcolor;>> Printtableformat.nebula
ECHO Hdr_Printfont Printfont;>> Printtableformat.nebula
ECHO Headercenter String;>> Printtableformat.nebula
ECHO Headerleft String;>> Printtableformat.nebula
ECHO Headerright String;>> Printtableformat.nebula
ECHO Imageisattached YesNo;>> Printtableformat.nebula
ECHO Imageurl URL;>> Printtableformat.nebula
ECHO Isactive YesNo;>> Printtableformat.nebula
ECHO Isdefault YesNo;>> Printtableformat.nebula
ECHO Ispaintboundarylines YesNo;>> Printtableformat.nebula
ECHO Ispainthlines YesNo;>> Printtableformat.nebula
ECHO Ispaintheaderlines YesNo;>> Printtableformat.nebula
ECHO Ispaintvlines YesNo;>> Printtableformat.nebula
ECHO Isprintfunctionsymbols YesNo;>> Printtableformat.nebula
ECHO Linestroke Number;>> Printtableformat.nebula
ECHO Linestroketype YesNo;>> Printtableformat.nebula
ECHO Line_Printcolor Printcolor;>> Printtableformat.nebula
ECHO !Name;>> Printtableformat.nebula
ECHO Updated Timestamp;>> Printtableformat.nebula
ECHO Updatedby Date;>> Printtableformat.nebula
ECHO };>> Printtableformat.nebula 
 ECHO @Package("Application Dictionary") type  Private_Access{ >> Private_Access.nebula
ECHO Org;>> Private_Access.nebula
ECHO !Table;>> Private_Access.nebula
ECHO !User;>> Private_Access.nebula
ECHO Created Timestamp;>> Private_Access.nebula
ECHO Createdby Date;>> Private_Access.nebula
ECHO Isactive YesNo;>> Private_Access.nebula

ECHO Updated Timestamp;>> Private_Access.nebula
ECHO Updatedby Date;>> Private_Access.nebula
ECHO };>> Private_Access.nebula 
 ECHO @Package("Application Dictionary") type  Process{ >> Process.nebula
ECHO Client;>> Process.nebula
ECHO Ctxarea;>> Process.nebula
ECHO Org;>> Process.nebula
ECHO Printformat;>> Process.nebula
ECHO !ID;>> Process.nebula
ECHO Reporttemplate;>> Process.nebula
ECHO Reportview;>> Process.nebula
ECHO Workflow;>> Process.nebula
ECHO Accesslevel YesNo;>> Process.nebula
ECHO Addmenu YesNo;>> Process.nebula
ECHO Classname Name;>> Process.nebula
ECHO Created Timestamp;>> Process.nebula
ECHO Createdby Date;>> Process.nebula
ECHO Description;>> Process.nebula
ECHO Entitytype Attr;>> Process.nebula
ECHO Help;>> Process.nebula
ECHO Isactive YesNo;>> Process.nebula
ECHO Isbetafunctionality YesNo;>> Process.nebula
ECHO Isdirectprint YesNo;>> Process.nebula
ECHO Isexternal YesNo;>> Process.nebula
ECHO Isreport YesNo;>> Process.nebula
ECHO Isserverprocess YesNo;>> Process.nebula
ECHO !Name;>> Process.nebula
ECHO Procedurename Name;>> Process.nebula
ECHO Statistic_Count Count;>> Process.nebula
ECHO Statistic_Seconds Number;>> Process.nebula
ECHO Updated Timestamp;>> Process.nebula
ECHO Updatedby Date;>> Process.nebula
ECHO Value String;>> Process.nebula
ECHO Workflowvalue String;>> Process.nebula
ECHO };>> Process.nebula 
 ECHO @Package("Application Dictionary") type  Process_Access{ >> Process_Access.nebula
ECHO Org;>> Process_Access.nebula
ECHO !Process;>> Process_Access.nebula
ECHO !Role;>> Process_Access.nebula
ECHO Created Timestamp;>> Process_Access.nebula
ECHO Createdby Date;>> Process_Access.nebula
ECHO Isactive YesNo;>> Process_Access.nebula
ECHO Isreadwrite YesNo;>> Process_Access.nebula
ECHO Updated Timestamp;>> Process_Access.nebula
ECHO Updatedby Date;>> Process_Access.nebula
ECHO };>> Process_Access.nebula 
 ECHO @Package("Application Dictionary") type  Process_Para{ >> Process_Para.nebula
ECHO Client;>> Process_Para.nebula
ECHO Element;>> Process_Para.nebula
ECHO Org;>> Process_Para.nebula
ECHO Process;>> Process_Para.nebula
ECHO !ID;>> Process_Para.nebula
ECHO Reference;>> Process_Para.nebula
ECHO Reference_Value Reference;>> Process_Para.nebula
ECHO Val_Rule;>> Process_Para.nebula
ECHO Columnname Name;>> Process_Para.nebula
ECHO Created Timestamp;>> Process_Para.nebula
ECHO Createdby Date;>> Process_Para.nebula
ECHO Defaultvalue Note;>> Process_Para.nebula
ECHO Defaultvalue2 String;>> Process_Para.nebula
ECHO Description;>> Process_Para.nebula
ECHO Entitytype Attr;>> Process_Para.nebula
ECHO Fieldlength Length;>> Process_Para.nebula
ECHO Help;>> Process_Para.nebula
ECHO Isactive YesNo;>> Process_Para.nebula
ECHO Iscentrallymaintained YesNo;>> Process_Para.nebula
ECHO Isdisplayed YesNo;>> Process_Para.nebula
ECHO Ismandatory YesNo;>> Process_Para.nebula
ECHO Isrange YesNo;>> Process_Para.nebula
ECHO !Name;>> Process_Para.nebula
ECHO SeqNo;>> Process_Para.nebula
ECHO Updated Timestamp;>> Process_Para.nebula
ECHO Updatedby Date;>> Process_Para.nebula
ECHO Vformat String;>> Process_Para.nebula
ECHO Valuemax String;>> Process_Para.nebula
ECHO Valuemin String;>> Process_Para.nebula
ECHO };>> Process_Para.nebula 
 ECHO @Package("Application Dictionary") type  Process_Para_trl{ >> Process_Para_trl.nebula
ECHO !Ad_Language Language;>> Process_Para_trl.nebula
ECHO Org;>> Process_Para_trl.nebula
ECHO !Process_Para;>> Process_Para_trl.nebula
ECHO Created Timestamp;>> Process_Para_trl.nebula
ECHO Createdby Date;>> Process_Para_trl.nebula
ECHO Description;>> Process_Para_trl.nebula
ECHO Help;>> Process_Para_trl.nebula
ECHO Isactive YesNo;>> Process_Para_trl.nebula
ECHO Istranslated YesNo;>> Process_Para_trl.nebula
ECHO !Name;>> Process_Para_trl.nebula
ECHO Updated Timestamp;>> Process_Para_trl.nebula
ECHO Updatedby Date;>> Process_Para_trl.nebula
ECHO };>> Process_Para_trl.nebula 
 ECHO @Package("Application Dictionary") type  Process_Trl{ >> Process_Trl.nebula
ECHO !Ad_Language Language;>> Process_Trl.nebula
ECHO Org;>> Process_Trl.nebula
ECHO !Process;>> Process_Trl.nebula
ECHO Created Timestamp;>> Process_Trl.nebula
ECHO Createdby Date;>> Process_Trl.nebula
ECHO Description;>> Process_Trl.nebula
ECHO Help;>> Process_Trl.nebula
ECHO Isactive YesNo;>> Process_Trl.nebula
ECHO Istranslated YesNo;>> Process_Trl.nebula
ECHO !Name;>> Process_Trl.nebula
ECHO Updated Timestamp;>> Process_Trl.nebula
ECHO Updatedby Date;>> Process_Trl.nebula
ECHO };>> Process_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Querylog{ >> Querylog.nebula
ECHO Org;>> Querylog.nebula
ECHO !ID;>> Querylog.nebula
ECHO Role;>> Querylog.nebula
ECHO Session;>> Querylog.nebula
ECHO Table;>> Querylog.nebula
ECHO Created Timestamp;>> Querylog.nebula
ECHO Createdby Date;>> Querylog.nebula
ECHO Isactive YesNo;>> Querylog.nebula
ECHO Parameter String;>> Querylog.nebula
ECHO Recordcount Count;>> Querylog.nebula
ECHO Updated Timestamp;>> Querylog.nebula
ECHO Updatedby Date;>> Querylog.nebula
ECHO Whereclause Note;>> Querylog.nebula
ECHO };>> Querylog.nebula 
 

ECHO Role;>> Recentitem.nebula

ECHO Table;>> Recentitem.nebula
ECHO User;>> Recentitem.nebula
ECHO Window;>> Recentitem.nebula
ECHO Created Timestamp;>> Recentitem.nebula
ECHO Createdby Date;>> Recentitem.nebula
ECHO Isactive YesNo;>> Recentitem.nebula
ECHO !Name;>> Recentitem.nebula

ECHO Updated Timestamp;>> Recentitem.nebula
ECHO Updatedby Date;>> Recentitem.nebula
ECHO };>> Recentitem.nebula 
 ECHO @Package("Application Dictionary") type  Record_Access{ >> Record_Access.nebula
ECHO Org;>> Record_Access.nebula
ECHO !Role;>> Record_Access.nebula
ECHO !Table;>> Record_Access.nebula
ECHO Created Timestamp;>> Record_Access.nebula
ECHO Createdby Date;>> Record_Access.nebula
ECHO Isactive YesNo;>> Record_Access.nebula
ECHO Isdependententities YesNo;>> Record_Access.nebula
ECHO Isexclude YesNo;>> Record_Access.nebula
ECHO Isreadonly YesNo;>> Record_Access.nebula

ECHO Updated Timestamp;>> Record_Access.nebula
ECHO Updatedby Date;>> Record_Access.nebula
ECHO };>> Record_Access.nebula 
 ECHO @Package("Application Dictionary") type  Ref_List{ >> Ref_List.nebula
ECHO Org;>> Ref_List.nebula
ECHO !ID;>> Ref_List.nebula
ECHO Reference;>> Ref_List.nebula
ECHO Created Timestamp;>> Ref_List.nebula
ECHO Createdby Date;>> Ref_List.nebula
ECHO Description;>> Ref_List.nebula
ECHO Entitytype Attr;>> Ref_List.nebula
ECHO Isactive YesNo;>> Ref_List.nebula
ECHO !Name;>> Ref_List.nebula
ECHO Updated Timestamp;>> Ref_List.nebula
ECHO Updatedby Date;>> Ref_List.nebula
ECHO Validfrom Date;>> Ref_List.nebula
ECHO Validto Date;>> Ref_List.nebula
ECHO Value String;>> Ref_List.nebula
ECHO };>> Ref_List.nebula 
 ECHO @Package("Application Dictionary") type  Ref_List_trl{ >> Ref_List_trl.nebula
ECHO !Ad_Language Language;>> Ref_List_trl.nebula
ECHO Org;>> Ref_List_trl.nebula
ECHO !Ref_List;>> Ref_List_trl.nebula
ECHO Created Timestamp;>> Ref_List_trl.nebula
ECHO Createdby Date;>> Ref_List_trl.nebula
ECHO Description;>> Ref_List_trl.nebula
ECHO Isactive YesNo;>> Ref_List_trl.nebula
ECHO Istranslated YesNo;>> Ref_List_trl.nebula
ECHO !Name;>> Ref_List_trl.nebula
ECHO Updated Timestamp;>> Ref_List_trl.nebula
ECHO Updatedby Date;>> Ref_List_trl.nebula
ECHO };>> Ref_List_trl.nebula 
 ECHO @Package("Application Dictionary") type  Ref_Table{ >> Ref_Table.nebula
ECHO Org;>> Ref_Table.nebula
ECHO !Reference;>> Ref_Table.nebula
ECHO Table;>> Ref_Table.nebula
ECHO Column_Display Column;>> Ref_Table.nebula
ECHO Column_Key Column;>> Ref_Table.nebula
ECHO Created Timestamp;>> Ref_Table.nebula
ECHO Createdby Date;>> Ref_Table.nebula
ECHO Entitytype Attr;>> Ref_Table.nebula
ECHO Isactive YesNo;>> Ref_Table.nebula
ECHO Isdisplayidentifiers YesNo;>> Ref_Table.nebula
ECHO Isvaluedisplayed YesNo;>> Ref_Table.nebula
ECHO Orderbyclause Note;>> Ref_Table.nebula
ECHO Updated Timestamp;>> Ref_Table.nebula
ECHO Updatedby Date;>> Ref_Table.nebula
ECHO Whereclause Note;>> Ref_Table.nebula
ECHO };>> Ref_Table.nebula 
 ECHO @Package("Application Dictionary") type  Reference{ >> Reference.nebula
ECHO Org;>> Reference.nebula
ECHO !ID;>> Reference.nebula
ECHO Created Timestamp;>> Reference.nebula
ECHO Createdby Date;>> Reference.nebula
ECHO Description;>> Reference.nebula
ECHO Entitytype Attr;>> Reference.nebula
ECHO Help;>> Reference.nebula
ECHO Isactive YesNo;>> Reference.nebula
ECHO !Name;>> Reference.nebula
ECHO Updated Timestamp;>> Reference.nebula
ECHO Updatedby Date;>> Reference.nebula
ECHO Vformat String;>> Reference.nebula
ECHO Validationtype YesNo;>> Reference.nebula
ECHO };>> Reference.nebula 
 ECHO @Package("Application Dictionary") type  Reference_Trl{ >> Reference_Trl.nebula
ECHO !Ad_Language Language;>> Reference_Trl.nebula
ECHO Org;>> Reference_Trl.nebula
ECHO !Reference;>> Reference_Trl.nebula
ECHO Created Timestamp;>> Reference_Trl.nebula
ECHO Createdby Date;>> Reference_Trl.nebula
ECHO Description;>> Reference_Trl.nebula
ECHO Help;>> Reference_Trl.nebula
ECHO Isactive YesNo;>> Reference_Trl.nebula
ECHO Istranslated YesNo;>> Reference_Trl.nebula
ECHO !Name;>> Reference_Trl.nebula
ECHO Updated Timestamp;>> Reference_Trl.nebula
ECHO Updatedby Date;>> Reference_Trl.nebula
ECHO };>> Reference_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Registration{ >> Registration.nebula
ECHO Org;>> Registration.nebula
ECHO !ID;>> Registration.nebula
ECHO Country;>> Registration.nebula
ECHO Currency;>> Registration.nebula
ECHO Location;>> Registration.nebula
ECHO Company Name;>> Registration.nebula
ECHO Created Timestamp;>> Registration.nebula
ECHO Createdby Date;>> Registration.nebula
ECHO Description;>> Registration.nebula
ECHO Email;>> Registration.nebula
ECHO Employeerange Attr;>> Registration.nebula
ECHO Firstname Name;>> Registration.nebula
ECHO Industry Attr;>> Registration.nebula
ECHO Industryinfo Description;>> Registration.nebula
ECHO Isactive YesNo;>> Registration.nebula
ECHO Isallowpublish YesNo;>> Registration.nebula
ECHO Isallowstatistics YesNo;>> Registration.nebula
ECHO Isinproduction YesNo;>> Registration.nebula
ECHO Isregistered YesNo;>> Registration.nebula
ECHO Lastname Name;>> Registration.nebula
ECHO Numberemployees Count;>> Registration.nebula
ECHO Optin YesNo;>> Registration.nebula
ECHO Phone PhoneNumber;>> Registration.nebula
ECHO Platforminfo Description;>> Registration.nebula
ECHO Processing YesNo;>> Registration.nebula

ECHO Remote_Addr URL;>> Registration.nebula
ECHO Remote_Host Host;>> Registration.nebula
ECHO Salesvolume Number;>> Registration.nebula
ECHO Startproductiondate Date;>> Registration.nebula
ECHO URL;>> Registration.nebula
ECHO Updated Timestamp;>> Registration.nebula
ECHO Updatedby Date;>> Registration.nebula
ECHO };>> Registration.nebula 
 ECHO @Package("Application Dictionary") type  Replication{ >> Replication.nebula
ECHO Org;>> Replication.nebula
ECHO Replicationstrategy;>> Replication.nebula
ECHO !ID;>> Replication.nebula
ECHO Created Timestamp;>> Replication.nebula
ECHO Createdby Date;>> Replication.nebula
ECHO Datelastrun Date;>> Replication.nebula
ECHO Description;>> Replication.nebula
ECHO Help;>> Replication.nebula
ECHO Hostaddress String;>> Replication.nebula
ECHO Hostport Long;>> Replication.nebula
ECHO Idrangeend Number;>> Replication.nebula
ECHO Idrangestart Number;>> Replication.nebula
ECHO Isactive YesNo;>> Replication.nebula
ECHO Isrmioverhttp YesNo;>> Replication.nebula
ECHO !Name;>> Replication.nebula
ECHO Prefix String;>> Replication.nebula
ECHO Processing YesNo;>> Replication.nebula
ECHO Remote_Client Client;>> Replication.nebula
ECHO Remote_Org Org;>> Replication.nebula
ECHO Suffix String;>> Replication.nebula
ECHO Updated Timestamp;>> Replication.nebula
ECHO Updatedby Date;>> Replication.nebula
ECHO };>> Replication.nebula 
 ECHO @Package("Application Dictionary") type  Replication_Log{ >> Replication_Log.nebula
ECHO Org;>> Replication_Log.nebula
ECHO Replicationtable;>> Replication_Log.nebula
ECHO !ID;>> Replication_Log.nebula
ECHO Replication_Run;>> Replication_Log.nebula
ECHO Created Timestamp;>> Replication_Log.nebula
ECHO Createdby Date;>> Replication_Log.nebula
ECHO Isactive YesNo;>> Replication_Log.nebula
ECHO Isreplicated YesNo;>> Replication_Log.nebula
ECHO P_Msg MSG;>> Replication_Log.nebula
ECHO Updated Timestamp;>> Replication_Log.nebula
ECHO Updatedby Date;>> Replication_Log.nebula
ECHO };>> Replication_Log.nebula 
 ECHO @Package("Application Dictionary") type  Replication_Run{ >> Replication_Run.nebula
ECHO Org;>> Replication_Run.nebula
ECHO Replication;>> Replication_Run.nebula
ECHO !ID;>> Replication_Run.nebula
ECHO Created Timestamp;>> Replication_Run.nebula
ECHO Createdby Date;>> Replication_Run.nebula
ECHO Description;>> Replication_Run.nebula
ECHO Isactive YesNo;>> Replication_Run.nebula
ECHO Isreplicated YesNo;>> Replication_Run.nebula
ECHO !Name;>> Replication_Run.nebula
ECHO Updated Timestamp;>> Replication_Run.nebula
ECHO Updatedby Date;>> Replication_Run.nebula
ECHO };>> Replication_Run.nebula 
 ECHO @Package("Application Dictionary") type  Replicationstrategy{ >> Replicationstrategy.nebula
ECHO Org;>> Replicationstrategy.nebula
ECHO !ID;>> Replicationstrategy.nebula
ECHO Created Timestamp;>> Replicationstrategy.nebula
ECHO Createdby Date;>> Replicationstrategy.nebula
ECHO Description;>> Replicationstrategy.nebula
ECHO Entitytype Attr;>> Replicationstrategy.nebula
ECHO Help;>> Replicationstrategy.nebula
ECHO Isactive YesNo;>> Replicationstrategy.nebula
ECHO !Name;>> Replicationstrategy.nebula
ECHO Updated Timestamp;>> Replicationstrategy.nebula
ECHO Updatedby Date;>> Replicationstrategy.nebula
ECHO };>> Replicationstrategy.nebula 
 ECHO @Package("Application Dictionary") type  Replicationtable{ >> Replicationtable.nebula
ECHO Org;>> Replicationtable.nebula
ECHO Replicationstrategy;>> Replicationtable.nebula
ECHO !ID;>> Replicationtable.nebula
ECHO Table;>> Replicationtable.nebula
ECHO Created Timestamp;>> Replicationtable.nebula
ECHO Createdby Date;>> Replicationtable.nebula
ECHO Entitytype Attr;>> Replicationtable.nebula
ECHO Isactive YesNo;>> Replicationtable.nebula
ECHO Replicationtype YesNo;>> Replicationtable.nebula
ECHO Updated Timestamp;>> Replicationtable.nebula
ECHO Updatedby Date;>> Replicationtable.nebula
ECHO };>> Replicationtable.nebula 
 ECHO @Package("Application Dictionary") type  Reporttemplate{ >> Reporttemplate.nebula
ECHO Client;>> Reporttemplate.nebula
ECHO Org;>> Reporttemplate.nebula
ECHO !ID;>> Reporttemplate.nebula
ECHO Ad_Reporttemplate_type YesNo;>> Reporttemplate.nebula
ECHO Created Timestamp;>> Reporttemplate.nebula
ECHO Createdby Date;>> Reporttemplate.nebula
ECHO Description;>> Reporttemplate.nebula
ECHO Entitytype Attr;>> Reporttemplate.nebula
ECHO Isactive YesNo;>> Reporttemplate.nebula
ECHO !Name;>> Reporttemplate.nebula
ECHO SeqNo;>> Reporttemplate.nebula
ECHO Updated Timestamp;>> Reporttemplate.nebula
ECHO Updatedby Date;>> Reporttemplate.nebula
ECHO };>> Reporttemplate.nebula 
 ECHO @Package("Application Dictionary") type  Reporttemplate_Relation{ >> Reporttemplate_Relation.nebula
ECHO Org;>> Reporttemplate_Relation.nebula
ECHO Reporttemplate;>> Reporttemplate_Relation.nebula
ECHO Reporttemplate_Related Reporttemplate;>> Reporttemplate_Relation.nebula
ECHO !ID;>> Reporttemplate_Relation.nebula
ECHO Created Timestamp;>> Reporttemplate_Relation.nebula
ECHO Createdby Date;>> Reporttemplate_Relation.nebula
ECHO Description;>> Reporttemplate_Relation.nebula
ECHO Entitytype Attr;>> Reporttemplate_Relation.nebula
ECHO Isactive YesNo;>> Reporttemplate_Relation.nebula
ECHO !Name;>> Reporttemplate_Relation.nebula
ECHO SeqNo;>> Reporttemplate_Relation.nebula
ECHO Updated Timestamp;>> Reporttemplate_Relation.nebula
ECHO Updatedby Date;>> Reporttemplate_Relation.nebula
ECHO };>> Reporttemplate_Relation.nebula 
 ECHO @Package("Application Dictionary") type  Reportview{ >> Reportview.nebula
ECHO Org;>> Reportview.nebula
ECHO !ID;>> Reportview.nebula
ECHO Table;>> Reportview.nebula
ECHO Created Timestamp;>> Reportview.nebula
ECHO Createdby Date;>> Reportview.nebula
ECHO Description;>> Reportview.nebula
ECHO Entitytype Attr;>> Reportview.nebula
ECHO Isactive YesNo;>> Reportview.nebula
ECHO !Name;>> Reportview.nebula
ECHO Orderbyclause Note;>> Reportview.nebula
ECHO Updated Timestamp;>> Reportview.nebula
ECHO Updatedby Date;>> Reportview.nebula
ECHO Whereclause Note;>> Reportview.nebula
ECHO };>> Reportview.nebula 
 ECHO @Package("Application Dictionary") type  Reportview_Col{ >> Reportview_Col.nebula
ECHO Column;>> Reportview_Col.nebula
ECHO Org;>> Reportview_Col.nebula
ECHO !ID;>> Reportview_Col.nebula
ECHO Reportview;>> Reportview_Col.nebula
ECHO Created Timestamp;>> Reportview_Col.nebula
ECHO Createdby Date;>> Reportview_Col.nebula
ECHO Functioncolumn Name;>> Reportview_Col.nebula
ECHO Isactive YesNo;>> Reportview_Col.nebula
ECHO Isgroupfunction YesNo;>> Reportview_Col.nebula
ECHO Updated Timestamp;>> Reportview_Col.nebula
ECHO Updatedby Date;>> Reportview_Col.nebula
ECHO };>> Reportview_Col.nebula 
 ECHO @Package("Application Dictionary") type  Role{ >> Role.nebula
ECHO Org;>> Role.nebula
ECHO !ID;>> Role.nebula
ECHO Tree_Menu Tree;>> Role.nebula
ECHO Tree_Org Tree;>> Role.nebula
ECHO Amtapproval Amount;>> Role.nebula
ECHO Currency;>> Role.nebula
ECHO Confirmqueryrecords Count;>> Role.nebula
ECHO Connectionprofile YesNo;>> Role.nebula
ECHO Created Timestamp;>> Role.nebula
ECHO Createdby Date;>> Role.nebula
ECHO Description;>> Role.nebula
ECHO Displayclientorg YesNo;>> Role.nebula
ECHO Isaccessallorgs YesNo;>> Role.nebula
ECHO Isactive YesNo;>> Role.nebula
ECHO Isadministrator YesNo;>> Role.nebula
ECHO Iscanapproveowndoc YesNo;>> Role.nebula
ECHO Iscanexport YesNo;>> Role.nebula
ECHO Iscanreport YesNo;>> Role.nebula
ECHO Ischangelog YesNo;>> Role.nebula
ECHO Ismanual YesNo;>> Role.nebula
ECHO Ispersonalaccess YesNo;>> Role.nebula
ECHO Ispersonallock YesNo;>> Role.nebula
ECHO Isshowacct YesNo;>> Role.nebula
ECHO Isusebprestrictions YesNo;>> Role.nebula
ECHO Isuseuserorgaccess YesNo;>> Role.nebula
ECHO Maxqueryrecords Count;>> Role.nebula
ECHO !Name;>> Role.nebula
ECHO Overridereturnpolicy YesNo;>> Role.nebula
ECHO Overwritepricelimit YesNo;>> Role.nebula
ECHO Preferencetype YesNo;>> Role.nebula
ECHO Supervisor User;>> Role.nebula
ECHO Updated Timestamp;>> Role.nebula
ECHO Updatedby Date;>> Role.nebula
ECHO Userlevel Long;>> Role.nebula
ECHO Winuserdeflevel YesNo;>> Role.nebula
ECHO };>> Role.nebula 
 ECHO @Package("Application Dictionary") type  Role_Orgaccess{ >> Role_Orgaccess.nebula
ECHO !Org;>> Role_Orgaccess.nebula
ECHO !Role;>> Role_Orgaccess.nebula
ECHO Created Timestamp;>> Role_Orgaccess.nebula
ECHO Createdby Date;>> Role_Orgaccess.nebula
ECHO Isactive YesNo;>> Role_Orgaccess.nebula
ECHO Isreadonly YesNo;>> Role_Orgaccess.nebula
ECHO Updated Timestamp;>> Role_Orgaccess.nebula
ECHO Updatedby Date;>> Role_Orgaccess.nebula
ECHO };>> Role_Orgaccess.nebula 
 ECHO @Package("Application Dictionary") type  Schedule{ >> Schedule.nebula
ECHO Org;>> Schedule.nebula
ECHO !ID;>> Schedule.nebula
ECHO Created Timestamp;>> Schedule.nebula
ECHO Createdby Date;>> Schedule.nebula
ECHO Description;>> Schedule.nebula
ECHO Frequency;>> Schedule.nebula
ECHO Frequencytype YesNo;>> Schedule.nebula
ECHO Help;>> Schedule.nebula
ECHO Isactive YesNo;>> Schedule.nebula
ECHO Monthday Count;>> Schedule.nebula
ECHO !Name;>> Schedule.nebula
ECHO Onfriday YesNo;>> Schedule.nebula
ECHO Onmonday YesNo;>> Schedule.nebula
ECHO Onsaturday YesNo;>> Schedule.nebula
ECHO Onsunday YesNo;>> Schedule.nebula
ECHO Onthursday YesNo;>> Schedule.nebula
ECHO Ontuesday YesNo;>> Schedule.nebula
ECHO Onwednesday YesNo;>> Schedule.nebula
ECHO Runonlyonip String;>> Schedule.nebula
ECHO Runonlyspecifiedtime YesNo;>> Schedule.nebula
ECHO Runonlyspecifiedtolmin Number;>> Schedule.nebula
ECHO Schedulehour Count;>> Schedule.nebula
ECHO Scheduleminute Number;>> Schedule.nebula
ECHO Scheduletype YesNo;>> Schedule.nebula
ECHO Updated Timestamp;>> Schedule.nebula
ECHO Updatedby Date;>> Schedule.nebula
ECHO Weekday YesNo;>> Schedule.nebula
ECHO };>> Schedule.nebula 
 ECHO @Package("Application Dictionary") type  Scheduler{ >> Scheduler.nebula
ECHO Org;>> Scheduler.nebula
ECHO Process;>> Scheduler.nebula
ECHO Schedule;>> Scheduler.nebula
ECHO !ID;>> Scheduler.nebula
ECHO Created Timestamp;>> Scheduler.nebula
ECHO Createdby Date;>> Scheduler.nebula
ECHO Datelastrun Date;>> Scheduler.nebula
ECHO Datenextrun Date;>> Scheduler.nebula
ECHO Description;>> Scheduler.nebula
ECHO Frequency;>> Scheduler.nebula
ECHO Frequencytype YesNo;>> Scheduler.nebula
ECHO Isactive YesNo;>> Scheduler.nebula
ECHO Keeplogdays Count;>> Scheduler.nebula
ECHO !Name;>> Scheduler.nebula
ECHO Processing YesNo;>> Scheduler.nebula
ECHO Supervisor User;>> Scheduler.nebula
ECHO Updated Timestamp;>> Scheduler.nebula
ECHO Updatedby Date;>> Scheduler.nebula
ECHO };>> Scheduler.nebula 
 ECHO @Package("Application Dictionary") type  Scheduler_Para{ >> Scheduler_Para.nebula
ECHO Org;>> Scheduler_Para.nebula
ECHO !Process_Para;>> Scheduler_Para.nebula
ECHO !Scheduler;>> Scheduler_Para.nebula
ECHO Created Timestamp;>> Scheduler_Para.nebula
ECHO Createdby Date;>> Scheduler_Para.nebula
ECHO Description;>> Scheduler_Para.nebula
ECHO Isactive YesNo;>> Scheduler_Para.nebula
ECHO Parameterdefault String;>> Scheduler_Para.nebula
ECHO Updated Timestamp;>> Scheduler_Para.nebula
ECHO Updatedby Date;>> Scheduler_Para.nebula
ECHO };>> Scheduler_Para.nebula 
 ECHO @Package("Application Dictionary") type  Schedulerlog{ >> Schedulerlog.nebula
ECHO Org;>> Schedulerlog.nebula
ECHO !ID;>> Schedulerlog.nebula
ECHO Scheduler;>> Schedulerlog.nebula
ECHO Binarydata Note;>> Schedulerlog.nebula
ECHO Created Timestamp;>> Schedulerlog.nebula
ECHO Createdby Date;>> Schedulerlog.nebula
ECHO Description;>> Schedulerlog.nebula
ECHO Isactive YesNo;>> Schedulerlog.nebula
ECHO Iserror YesNo;>> Schedulerlog.nebula
ECHO Reference String;>> Schedulerlog.nebula
ECHO Summary Note;>> Schedulerlog.nebula
ECHO Textmsg MSG;>> Schedulerlog.nebula
ECHO Updated Timestamp;>> Schedulerlog.nebula
ECHO Updatedby Date;>> Schedulerlog.nebula
ECHO };>> Schedulerlog.nebula 
 ECHO @Package("Application Dictionary") type  Schedulerrecipient{ >> Schedulerrecipient.nebula
ECHO Org;>> Schedulerrecipient.nebula
ECHO Role;>> Schedulerrecipient.nebula
ECHO !ID;>> Schedulerrecipient.nebula
ECHO Scheduler;>> Schedulerrecipient.nebula
ECHO User;>> Schedulerrecipient.nebula
ECHO Created Timestamp;>> Schedulerrecipient.nebula
ECHO Createdby Date;>> Schedulerrecipient.nebula
ECHO Isactive YesNo;>> Schedulerrecipient.nebula
ECHO Updated Timestamp;>> Schedulerrecipient.nebula
ECHO Updatedby Date;>> Schedulerrecipient.nebula
ECHO };>> Schedulerrecipient.nebula 
 ECHO @Package("Application Dictionary") type  Sequence{ >> Sequence.nebula
ECHO Org;>> Sequence.nebula
ECHO !ID;>> Sequence.nebula
ECHO Created Timestamp;>> Sequence.nebula
ECHO Createdby Date;>> Sequence.nebula
ECHO Currentnext Number;>> Sequence.nebula
ECHO Currentnextsys Number;>> Sequence.nebula
ECHO Description;>> Sequence.nebula
ECHO Incrementno SeqNo;>> Sequence.nebula
ECHO Isactive YesNo;>> Sequence.nebula
ECHO Isaudited YesNo;>> Sequence.nebula
ECHO Isautosequence YesNo;>> Sequence.nebula
ECHO Istableid YesNo;>> Sequence.nebula
ECHO !Name;>> Sequence.nebula
ECHO Prefix String;>> Sequence.nebula
ECHO Startnewyear YesNo;>> Sequence.nebula
ECHO Startno SeqNo;>> Sequence.nebula
ECHO Suffix String;>> Sequence.nebula
ECHO Updated Timestamp;>> Sequence.nebula
ECHO Updatedby Date;>> Sequence.nebula
ECHO Vformat String;>> Sequence.nebula
ECHO };>> Sequence.nebula 
 ECHO @Package("Application Dictionary") type  Sequence_Audit{ >> Sequence_Audit.nebula
ECHO Org;>> Sequence_Audit.nebula
ECHO !Sequence;>> Sequence_Audit.nebula
ECHO !Table;>> Sequence_Audit.nebula
ECHO Created Timestamp;>> Sequence_Audit.nebula
ECHO Createdby Date;>> Sequence_Audit.nebula
ECHO Documentno SeqNo;>> Sequence_Audit.nebula
ECHO Isactive YesNo;>> Sequence_Audit.nebula

ECHO Updated Timestamp;>> Sequence_Audit.nebula
ECHO Updatedby Date;>> Sequence_Audit.nebula
ECHO };>> Sequence_Audit.nebula 
 ECHO @Package("Application Dictionary") type  Sequence_No{ >> Sequence_No.nebula
ECHO Org;>> Sequence_No.nebula
ECHO !Sequence;>> Sequence_No.nebula
ECHO !Calendaryear String;>> Sequence_No.nebula
ECHO Created Timestamp;>> Sequence_No.nebula
ECHO Createdby Date;>> Sequence_No.nebula
ECHO Currentnext Number;>> Sequence_No.nebula
ECHO Isactive YesNo;>> Sequence_No.nebula
ECHO Updated Timestamp;>> Sequence_No.nebula
ECHO Updatedby Date;>> Sequence_No.nebula
ECHO };>> Sequence_No.nebula 
 ECHO @Package("Application Dictionary") type  Session{ >> Session.nebula
ECHO Org;>> Session.nebula
ECHO Role;>> Session.nebula
ECHO !ID;>> Session.nebula
ECHO Created Timestamp;>> Session.nebula
ECHO Createdby Date;>> Session.nebula
ECHO Description;>> Session.nebula
ECHO Isactive YesNo;>> Session.nebula
ECHO Lastactivitytime Date;>> Session.nebula
ECHO Lastactivitytype Attr;>> Session.nebula
ECHO Processed YesNo;>> Session.nebula
ECHO Processing YesNo;>> Session.nebula
ECHO Remote_Addr URL;>> Session.nebula
ECHO Remote_Host Host;>> Session.nebula
ECHO Sessiontype YesNo;>> Session.nebula
ECHO Updated Timestamp;>> Session.nebula
ECHO Updatedby Date;>> Session.nebula
ECHO Websession String;>> Session.nebula
ECHO };>> Session.nebula 
 ECHO @Package("Application Dictionary") type  System{ >> System.nebula
ECHO Org;>> System.nebula
ECHO !ID;>> System.nebula
ECHO Created Timestamp;>> System.nebula
ECHO Createdby Date;>> System.nebula
ECHO Customprefix String;>> System.nebula
ECHO Dbaddress String;>> System.nebula
ECHO Dbinstance String;>> System.nebula
ECHO Description;>> System.nebula
ECHO Encryptionkey String;>> System.nebula
ECHO Idrangeend Number;>> System.nebula
ECHO Idrangestart Number;>> System.nebula
ECHO Info Description;>> System.nebula
ECHO Isactive YesNo;>> System.nebula
ECHO Isallowstatistics YesNo;>> System.nebula
ECHO Isautoerrorreport YesNo;>> System.nebula
ECHO Isjustmigrated YesNo;>> System.nebula
ECHO Ldapdomain String;>> System.nebula
ECHO Ldaphost Host;>> System.nebula
ECHO !Name;>> System.nebula
ECHO Noprocessors Number;>> System.nebula
ECHO Oldname Name;>> System.nebula
ECHO Password String;>> System.nebula
ECHO Processing YesNo;>> System.nebula
ECHO Profileinfo Description;>> System.nebula

ECHO Releaseno Code;>> System.nebula
ECHO Replicationtype YesNo;>> System.nebula
ECHO Statisticsinfo String;>> System.nebula
ECHO Summary Note;>> System.nebula
ECHO Supportemail Email;>> System.nebula
ECHO Supportexpdate Date;>> System.nebula
ECHO Supportlevel YesNo;>> System.nebula
ECHO Supportunits Number;>> System.nebula
ECHO Systemstatus YesNo;>> System.nebula
ECHO Updated Timestamp;>> System.nebula
ECHO Updatedby Date;>> System.nebula
ECHO Username Name;>> System.nebula
ECHO Version Number;>> System.nebula
ECHO };>> System.nebula 
 ECHO @Package("Application Dictionary") type  Tab{ >> Tab.nebula
ECHO Columnsortorder Column;>> Tab.nebula
ECHO Columnsortyesno Column;>> Tab.nebula
ECHO Column;>> Tab.nebula
ECHO Ctxarea;>> Tab.nebula
ECHO Image;>> Tab.nebula
ECHO Org;>> Tab.nebula
ECHO Process;>> Tab.nebula
ECHO !ID;>> Tab.nebula
ECHO Table;>> Tab.nebula
ECHO Window;>> Tab.nebula
ECHO Commitwarning Note;>> Tab.nebula
ECHO Created Timestamp;>> Tab.nebula
ECHO Createdby Date;>> Tab.nebula
ECHO Description;>> Tab.nebula
ECHO Displaylogic Note;>> Tab.nebula
ECHO Entitytype Attr;>> Tab.nebula
ECHO Hastree YesNo;>> Tab.nebula
ECHO Help;>> Tab.nebula
ECHO Importfields YesNo;>> Tab.nebula
ECHO Included_Tab Tab;>> Tab.nebula
ECHO Isactive YesNo;>> Tab.nebula
ECHO Isadvancedtab YesNo;>> Tab.nebula
ECHO Isdisplayed YesNo;>> Tab.nebula
ECHO Isinfotab YesNo;>> Tab.nebula
ECHO Isinsertrecord YesNo;>> Tab.nebula
ECHO Isreadonly YesNo;>> Tab.nebula
ECHO Issinglerow YesNo;>> Tab.nebula
ECHO Issorttab YesNo;>> Tab.nebula
ECHO Istranslationtab YesNo;>> Tab.nebula
ECHO !Name;>> Tab.nebula
ECHO Orderbyclause Note;>> Tab.nebula
ECHO Processing YesNo;>> Tab.nebula
ECHO Readonlylogic Note;>> Tab.nebula
ECHO Referenced_Tab Tab;>> Tab.nebula
ECHO SeqNo;>> Tab.nebula
ECHO Tablevel Long;>> Tab.nebula
ECHO Updated Timestamp;>> Tab.nebula
ECHO Updatedby Date;>> Tab.nebula
ECHO Whereclause Note;>> Tab.nebula
ECHO };>> Tab.nebula 
 ECHO @Package("Application Dictionary") type  Tab_Trl{ >> Tab_Trl.nebula
ECHO !Ad_Language Language;>> Tab_Trl.nebula
ECHO Org;>> Tab_Trl.nebula
ECHO !Tab;>> Tab_Trl.nebula
ECHO Commitwarning Note;>> Tab_Trl.nebula
ECHO Created Timestamp;>> Tab_Trl.nebula
ECHO Createdby Date;>> Tab_Trl.nebula
ECHO Description;>> Tab_Trl.nebula
ECHO Help;>> Tab_Trl.nebula
ECHO Isactive YesNo;>> Tab_Trl.nebula
ECHO Istranslated YesNo;>> Tab_Trl.nebula
ECHO !Name;>> Tab_Trl.nebula
ECHO Updated Timestamp;>> Tab_Trl.nebula
ECHO Updatedby Date;>> Tab_Trl.nebula
ECHO };>> Tab_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Table{ >> Table.nebula
ECHO Org;>> Table.nebula
ECHO !ID;>> Table.nebula
ECHO Val_Rule;>> Table.nebula
ECHO Window;>> Table.nebula
ECHO Accesslevel YesNo;>> Table.nebula
ECHO Base_Table Table;>> Table.nebula
ECHO Created Timestamp;>> Table.nebula
ECHO Createdby Date;>> Table.nebula
ECHO Datecolumn Column;>> Table.nebula
ECHO Description;>> Table.nebula
ECHO Entitytype Attr;>> Table.nebula
ECHO Exportview YesNo;>> Table.nebula
ECHO Help;>> Table.nebula
ECHO Importtable YesNo;>> Table.nebula
ECHO Importview YesNo;>> Table.nebula
ECHO Isactive YesNo;>> Table.nebula
ECHO Ischangelog YesNo;>> Table.nebula
ECHO Isdeleteable YesNo;>> Table.nebula
ECHO Ishighvolume YesNo;>> Table.nebula
ECHO Isreportingtable YesNo;>> Table.nebula
ECHO Issecurityenabled YesNo;>> Table.nebula
ECHO Isview YesNo;>> Table.nebula
ECHO Loadseq Number;>> Table.nebula
ECHO !Name;>> Table.nebula
ECHO Po_Window Window;>> Table.nebula
ECHO Referenced_Table Table;>> Table.nebula
ECHO Replicationtype YesNo;>> Table.nebula
ECHO Subtabletype YesNo;>> Table.nebula
ECHO Tablename Name;>> Table.nebula
ECHO Tabletrxtype YesNo;>> Table.nebula
ECHO Updated Timestamp;>> Table.nebula
ECHO Updatedby Date;>> Table.nebula
ECHO };>> Table.nebula 
 ECHO @Package("Application Dictionary") type  Table_Access{ >> Table_Access.nebula
ECHO Org;>> Table_Access.nebula
ECHO !Role;>> Table_Access.nebula
ECHO !Table;>> Table_Access.nebula
ECHO !Accesstyperule YesNo;>> Table_Access.nebula
ECHO Created Timestamp;>> Table_Access.nebula
ECHO Createdby Date;>> Table_Access.nebula
ECHO Isactive YesNo;>> Table_Access.nebula
ECHO Iscanexport YesNo;>> Table_Access.nebula
ECHO Iscanreport YesNo;>> Table_Access.nebula
ECHO Isexclude YesNo;>> Table_Access.nebula
ECHO Isreadonly YesNo;>> Table_Access.nebula
ECHO Updated Timestamp;>> Table_Access.nebula
ECHO Updatedby Date;>> Table_Access.nebula
ECHO };>> Table_Access.nebula 
 ECHO @Package("Application Dictionary") type  Table_Trl{ >> Table_Trl.nebula
ECHO !Ad_Language Language;>> Table_Trl.nebula
ECHO Org;>> Table_Trl.nebula
ECHO !Table;>> Table_Trl.nebula
ECHO Created Timestamp;>> Table_Trl.nebula
ECHO Createdby Date;>> Table_Trl.nebula
ECHO Isactive YesNo;>> Table_Trl.nebula
ECHO Istranslated YesNo;>> Table_Trl.nebula
ECHO !Name;>> Table_Trl.nebula
ECHO Updated Timestamp;>> Table_Trl.nebula
ECHO Updatedby Date;>> Table_Trl.nebula
ECHO };>> Table_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Tableindex{ >> Tableindex.nebula
ECHO Org;>> Tableindex.nebula
ECHO !ID;>> Tableindex.nebula
ECHO Table;>> Tableindex.nebula
ECHO Created Timestamp;>> Tableindex.nebula
ECHO Createdby Date;>> Tableindex.nebula
ECHO Description;>> Tableindex.nebula
ECHO Entitytype Attr;>> Tableindex.nebula
ECHO Help;>> Tableindex.nebula
ECHO Isactive YesNo;>> Tableindex.nebula
ECHO Iscreateconstraint YesNo;>> Tableindex.nebula
ECHO Isunique YesNo;>> Tableindex.nebula
ECHO !Name;>> Tableindex.nebula
ECHO Processing YesNo;>> Tableindex.nebula
ECHO Updated Timestamp;>> Tableindex.nebula
ECHO Updatedby Date;>> Tableindex.nebula
ECHO };>> Tableindex.nebula 
 ECHO @Package("Application Dictionary") type  Task{ >> Task.nebula
ECHO Org;>> Task.nebula
ECHO !ID;>> Task.nebula
ECHO Accesslevel YesNo;>> Task.nebula
ECHO Created Timestamp;>> Task.nebula
ECHO Createdby Date;>> Task.nebula
ECHO Description;>> Task.nebula
ECHO Entitytype Attr;>> Task.nebula
ECHO Help;>> Task.nebula
ECHO Isactive YesNo;>> Task.nebula
ECHO Isserverprocess YesNo;>> Task.nebula
ECHO !Name;>> Task.nebula
ECHO Os_Command Note;>> Task.nebula
ECHO Updated Timestamp;>> Task.nebula
ECHO Updatedby Date;>> Task.nebula
ECHO };>> Task.nebula 
 ECHO @Package("Application Dictionary") type  Task_Access{ >> Task_Access.nebula
ECHO Org;>> Task_Access.nebula
ECHO !Role;>> Task_Access.nebula
ECHO !Task;>> Task_Access.nebula
ECHO Created Timestamp;>> Task_Access.nebula
ECHO Createdby Date;>> Task_Access.nebula
ECHO Isactive YesNo;>> Task_Access.nebula
ECHO Isreadwrite YesNo;>> Task_Access.nebula
ECHO Updated Timestamp;>> Task_Access.nebula
ECHO Updatedby Date;>> Task_Access.nebula
ECHO };>> Task_Access.nebula 
 ECHO @Package("Application Dictionary") type  Task_Trl{ >> Task_Trl.nebula
ECHO !Ad_Language Language;>> Task_Trl.nebula
ECHO Org;>> Task_Trl.nebula
ECHO !Task;>> Task_Trl.nebula
ECHO Created Timestamp;>> Task_Trl.nebula
ECHO Createdby Date;>> Task_Trl.nebula
ECHO Description;>> Task_Trl.nebula
ECHO Help;>> Task_Trl.nebula
ECHO Isactive YesNo;>> Task_Trl.nebula
ECHO Istranslated YesNo;>> Task_Trl.nebula
ECHO !Name;>> Task_Trl.nebula
ECHO Updated Timestamp;>> Task_Trl.nebula
ECHO Updatedby Date;>> Task_Trl.nebula
ECHO };>> Task_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Taskinstance{ >> Taskinstance.nebula
ECHO Org;>> Taskinstance.nebula
ECHO !ID;>> Taskinstance.nebula
ECHO Task;>> Taskinstance.nebula
ECHO Created Timestamp;>> Taskinstance.nebula
ECHO Createdby Date;>> Taskinstance.nebula
ECHO Isactive YesNo;>> Taskinstance.nebula
ECHO Updated Timestamp;>> Taskinstance.nebula
ECHO Updatedby Date;>> Taskinstance.nebula
ECHO };>> Taskinstance.nebula 
 ECHO @Package("Application Dictionary") type  Tree{ >> Tree.nebula
ECHO Org;>> Tree.nebula
ECHO Table;>> Tree.nebula
ECHO !ID;>> Tree.nebula
ECHO Created Timestamp;>> Tree.nebula
ECHO Createdby Date;>> Tree.nebula
ECHO Description;>> Tree.nebula
ECHO Isactive YesNo;>> Tree.nebula
ECHO Isallnodes YesNo;>> Tree.nebula
ECHO Isdefault YesNo;>> Tree.nebula
ECHO !Name;>> Tree.nebula
ECHO Processing YesNo;>> Tree.nebula
ECHO Treetype Attr;>> Tree.nebula
ECHO Updated Timestamp;>> Tree.nebula
ECHO Updatedby Date;>> Tree.nebula
ECHO };>> Tree.nebula 
 ECHO @Package("Application Dictionary") type  Treebar{ >> Treebar.nebula
ECHO Org;>> Treebar.nebula
ECHO !Tree;>> Treebar.nebula
ECHO !User;>> Treebar.nebula
ECHO Created Timestamp;>> Treebar.nebula
ECHO Createdby Date;>> Treebar.nebula
ECHO Isactive YesNo;>> Treebar.nebula

ECHO Updated Timestamp;>> Treebar.nebula
ECHO Updatedby Date;>> Treebar.nebula
ECHO };>> Treebar.nebula 
 ECHO @Package("Application Dictionary") type  Treenode{ >> Treenode.nebula
ECHO Org;>> Treenode.nebula
ECHO !Tree;>> Treenode.nebula
ECHO Created Timestamp;>> Treenode.nebula
ECHO Createdby Date;>> Treenode.nebula
ECHO Isactive YesNo;>> Treenode.nebula


ECHO SeqNo;>> Treenode.nebula
ECHO Updated Timestamp;>> Treenode.nebula
ECHO Updatedby Date;>> Treenode.nebula
ECHO };>> Treenode.nebula 
 ECHO @Package("Application Dictionary") type  Treenodebp{ >> Treenodebp.nebula
ECHO Org;>> Treenodebp.nebula
ECHO !Tree;>> Treenodebp.nebula
ECHO Created Timestamp;>> Treenodebp.nebula
ECHO Createdby Date;>> Treenodebp.nebula
ECHO Isactive YesNo;>> Treenodebp.nebula


ECHO SeqNo;>> Treenodebp.nebula
ECHO Updated Timestamp;>> Treenodebp.nebula
ECHO Updatedby Date;>> Treenodebp.nebula
ECHO };>> Treenodebp.nebula 
 ECHO @Package("Application Dictionary") type  Treenodecmc{ >> Treenodecmc.nebula
ECHO Org;>> Treenodecmc.nebula
ECHO !Tree;>> Treenodecmc.nebula
ECHO Created Timestamp;>> Treenodecmc.nebula
ECHO Createdby Date;>> Treenodecmc.nebula
ECHO Isactive YesNo;>> Treenodecmc.nebula


ECHO SeqNo;>> Treenodecmc.nebula
ECHO Updated Timestamp;>> Treenodecmc.nebula
ECHO Updatedby Date;>> Treenodecmc.nebula
ECHO };>> Treenodecmc.nebula 
 ECHO @Package("Application Dictionary") type  Treenodecmm{ >> Treenodecmm.nebula
ECHO Org;>> Treenodecmm.nebula
ECHO !Tree;>> Treenodecmm.nebula
ECHO Created Timestamp;>> Treenodecmm.nebula
ECHO Createdby Date;>> Treenodecmm.nebula
ECHO Isactive YesNo;>> Treenodecmm.nebula


ECHO SeqNo;>> Treenodecmm.nebula
ECHO Updated Timestamp;>> Treenodecmm.nebula
ECHO Updatedby Date;>> Treenodecmm.nebula
ECHO };>> Treenodecmm.nebula 
 ECHO @Package("Application Dictionary") type  Treenodecms{ >> Treenodecms.nebula
ECHO Org;>> Treenodecms.nebula
ECHO !Tree;>> Treenodecms.nebula
ECHO Created Timestamp;>> Treenodecms.nebula
ECHO Createdby Date;>> Treenodecms.nebula
ECHO Isactive YesNo;>> Treenodecms.nebula


ECHO SeqNo;>> Treenodecms.nebula
ECHO Updated Timestamp;>> Treenodecms.nebula
ECHO Updatedby Date;>> Treenodecms.nebula
ECHO };>> Treenodecms.nebula 
 ECHO @Package("Application Dictionary") type  Treenodecmt{ >> Treenodecmt.nebula
ECHO Org;>> Treenodecmt.nebula
ECHO !Tree;>> Treenodecmt.nebula
ECHO Created Timestamp;>> Treenodecmt.nebula
ECHO Createdby Date;>> Treenodecmt.nebula
ECHO Isactive YesNo;>> Treenodecmt.nebula


ECHO SeqNo;>> Treenodecmt.nebula
ECHO Updated Timestamp;>> Treenodecmt.nebula
ECHO Updatedby Date;>> Treenodecmt.nebula
ECHO };>> Treenodecmt.nebula 
 ECHO @Package("Application Dictionary") type  Treenodemm{ >> Treenodemm.nebula
ECHO Org;>> Treenodemm.nebula
ECHO !Tree;>> Treenodemm.nebula
ECHO Created Timestamp;>> Treenodemm.nebula
ECHO Createdby Date;>> Treenodemm.nebula
ECHO Isactive YesNo;>> Treenodemm.nebula


ECHO SeqNo;>> Treenodemm.nebula
ECHO Updated Timestamp;>> Treenodemm.nebula
ECHO Updatedby Date;>> Treenodemm.nebula
ECHO };>> Treenodemm.nebula 
 ECHO @Package("Application Dictionary") type  Treenodepr{ >> Treenodepr.nebula
ECHO Org;>> Treenodepr.nebula
ECHO !Tree;>> Treenodepr.nebula
ECHO Created Timestamp;>> Treenodepr.nebula
ECHO Createdby Date;>> Treenodepr.nebula
ECHO Isactive YesNo;>> Treenodepr.nebula


ECHO SeqNo;>> Treenodepr.nebula
ECHO Updated Timestamp;>> Treenodepr.nebula
ECHO Updatedby Date;>> Treenodepr.nebula
ECHO };>> Treenodepr.nebula 
 ECHO @Package("Application Dictionary") type  Treenodeu1{ >> Treenodeu1.nebula
ECHO Org;>> Treenodeu1.nebula
ECHO !Tree;>> Treenodeu1.nebula
ECHO Created Timestamp;>> Treenodeu1.nebula
ECHO Createdby Date;>> Treenodeu1.nebula
ECHO Isactive YesNo;>> Treenodeu1.nebula


ECHO SeqNo;>> Treenodeu1.nebula
ECHO Updated Timestamp;>> Treenodeu1.nebula
ECHO Updatedby Date;>> Treenodeu1.nebula
ECHO };>> Treenodeu1.nebula 
 ECHO @Package("Application Dictionary") type  Treenodeu2{ >> Treenodeu2.nebula
ECHO Org;>> Treenodeu2.nebula
ECHO !Tree;>> Treenodeu2.nebula
ECHO Created Timestamp;>> Treenodeu2.nebula
ECHO Createdby Date;>> Treenodeu2.nebula
ECHO Isactive YesNo;>> Treenodeu2.nebula


ECHO SeqNo;>> Treenodeu2.nebula
ECHO Updated Timestamp;>> Treenodeu2.nebula
ECHO Updatedby Date;>> Treenodeu2.nebula
ECHO };>> Treenodeu2.nebula 
 ECHO @Package("Application Dictionary") type  Treenodeu3{ >> Treenodeu3.nebula
ECHO Org;>> Treenodeu3.nebula
ECHO !Tree;>> Treenodeu3.nebula
ECHO Created Timestamp;>> Treenodeu3.nebula
ECHO Createdby Date;>> Treenodeu3.nebula
ECHO Isactive YesNo;>> Treenodeu3.nebula


ECHO SeqNo;>> Treenodeu3.nebula
ECHO Updated Timestamp;>> Treenodeu3.nebula
ECHO Updatedby Date;>> Treenodeu3.nebula
ECHO };>> Treenodeu3.nebula 
 ECHO @Package("Application Dictionary") type  Treenodeu4{ >> Treenodeu4.nebula
ECHO Org;>> Treenodeu4.nebula
ECHO !Tree;>> Treenodeu4.nebula
ECHO Created Timestamp;>> Treenodeu4.nebula
ECHO Createdby Date;>> Treenodeu4.nebula
ECHO Isactive YesNo;>> Treenodeu4.nebula


ECHO SeqNo;>> Treenodeu4.nebula
ECHO Updated Timestamp;>> Treenodeu4.nebula
ECHO Updatedby Date;>> Treenodeu4.nebula
ECHO };>> Treenodeu4.nebula 
 ECHO @Package("Application Dictionary") type  User{ >> User.nebula
ECHO Orgtrx Org;>> User.nebula
ECHO Org;>> User.nebula
ECHO Tree_Menufavorite Tree;>> User.nebula
ECHO Tree_Menunew Tree;>> User.nebula
ECHO !ID;>> User.nebula
ECHO Birthday;>> User.nebula
ECHO Bouncedinfo Description;>> User.nebula
ECHO Bpartner;>> User.nebula
ECHO Bpartner_Location;>> User.nebula
ECHO Greeting;>> User.nebula
ECHO Job;>> User.nebula
ECHO Comments Note;>> User.nebula
ECHO Connectionprofile YesNo;>> User.nebula
ECHO Created Timestamp;>> User.nebula
ECHO Createdby Date;>> User.nebula
ECHO Description;>> User.nebula
ECHO Email;>> User.nebula
ECHO Emailuser Email;>> User.nebula
ECHO Emailuserpw String;>> User.nebula
ECHO Emailverify String;>> User.nebula
ECHO Emailverifydate Date;>> User.nebula
ECHO Fax;>> User.nebula
ECHO Isactive YesNo;>> User.nebula
ECHO Isemailbounced YesNo;>> User.nebula
ECHO Isfullbpaccess YesNo;>> User.nebula
ECHO Ldapuser Name;>> User.nebula
ECHO Lastcontact Date;>> User.nebula
ECHO Lastregistrationreminder Date;>> User.nebula
ECHO Lastresult String;>> User.nebula
ECHO !Name;>> User.nebula
ECHO Notificationtype YesNo;>> User.nebula
ECHO Password String;>> User.nebula
ECHO Phone PhoneNumber;>> User.nebula
ECHO Phone2 PhoneNumber;>> User.nebula
ECHO Processing YesNo;>> User.nebula
ECHO Supervisor User;>> User.nebula
ECHO Title String;>> User.nebula
ECHO Updated Timestamp;>> User.nebula
ECHO Updatedby Date;>> User.nebula
ECHO Value String;>> User.nebula
ECHO };>> User.nebula 
 ECHO @Package("Application Dictionary") type  User_Orgaccess{ >> User_Orgaccess.nebula
ECHO !Org;>> User_Orgaccess.nebula
ECHO !User;>> User_Orgaccess.nebula
ECHO Created Timestamp;>> User_Orgaccess.nebula
ECHO Createdby Date;>> User_Orgaccess.nebula
ECHO Isactive YesNo;>> User_Orgaccess.nebula
ECHO Isreadonly YesNo;>> User_Orgaccess.nebula
ECHO Updated Timestamp;>> User_Orgaccess.nebula
ECHO Updatedby Date;>> User_Orgaccess.nebula
ECHO };>> User_Orgaccess.nebula 
 ECHO @Package("Application Dictionary") type  User_Roles{ >> User_Roles.nebula
ECHO Org;>> User_Roles.nebula
ECHO !Role;>> User_Roles.nebula
ECHO !User;>> User_Roles.nebula
ECHO Created Timestamp;>> User_Roles.nebula
ECHO Createdby Date;>> User_Roles.nebula
ECHO Isactive YesNo;>> User_Roles.nebula
ECHO Updated Timestamp;>> User_Roles.nebula
ECHO Updatedby Date;>> User_Roles.nebula
ECHO };>> User_Roles.nebula 
 ECHO @Package("Application Dictionary") type  User_Substitute{ >> User_Substitute.nebula
ECHO Org;>> User_Substitute.nebula
ECHO User;>> User_Substitute.nebula
ECHO !ID;>> User_Substitute.nebula
ECHO Created Timestamp;>> User_Substitute.nebula
ECHO Createdby Date;>> User_Substitute.nebula
ECHO Description;>> User_Substitute.nebula
ECHO Isactive YesNo;>> User_Substitute.nebula
ECHO !Name;>> User_Substitute.nebula
ECHO Substitute User;>> User_Substitute.nebula
ECHO Updated Timestamp;>> User_Substitute.nebula
ECHO Updatedby Date;>> User_Substitute.nebula
ECHO Validfrom Date;>> User_Substitute.nebula
ECHO Validto Date;>> User_Substitute.nebula
ECHO };>> User_Substitute.nebula 
 ECHO @Package("Application Dictionary") type  Userbpaccess{ >> Userbpaccess.nebula
ECHO Org;>> Userbpaccess.nebula
ECHO !ID;>> Userbpaccess.nebula
ECHO User;>> Userbpaccess.nebula
ECHO Bpaccesstype YesNo;>> Userbpaccess.nebula
ECHO Created Timestamp;>> Userbpaccess.nebula
ECHO Createdby Date;>> Userbpaccess.nebula
ECHO Docbasetype Attr;>> Userbpaccess.nebula
ECHO Isactive YesNo;>> Userbpaccess.nebula
ECHO Requesttype;>> Userbpaccess.nebula
ECHO Updated Timestamp;>> Userbpaccess.nebula
ECHO Updatedby Date;>> Userbpaccess.nebula
ECHO };>> Userbpaccess.nebula 
 ECHO @Package("Application Dictionary") type  Userdef_Field{ >> Userdef_Field.nebula
ECHO Field;>> Userdef_Field.nebula
ECHO Org;>> Userdef_Field.nebula
ECHO !ID;>> Userdef_Field.nebula
ECHO Userdef_Tab;>> Userdef_Field.nebula
ECHO Created Timestamp;>> Userdef_Field.nebula
ECHO Createdby Date;>> Userdef_Field.nebula
ECHO Defaultvalue Note;>> Userdef_Field.nebula
ECHO Description;>> Userdef_Field.nebula
ECHO Displaylength Length;>> Userdef_Field.nebula
ECHO Displaylogic Note;>> Userdef_Field.nebula
ECHO Help;>> Userdef_Field.nebula
ECHO Isactive YesNo;>> Userdef_Field.nebula
ECHO Isdisplayed YesNo;>> Userdef_Field.nebula
ECHO Ismandatoryui YesNo;>> Userdef_Field.nebula
ECHO Isreadonly YesNo;>> Userdef_Field.nebula
ECHO Issameline YesNo;>> Userdef_Field.nebula
ECHO Isselectioncolumn YesNo;>> Userdef_Field.nebula
ECHO Issummarycolumn YesNo;>> Userdef_Field.nebula
ECHO Isupdateable YesNo;>> Userdef_Field.nebula
ECHO Mrseqno SeqNo;>> Userdef_Field.nebula
ECHO !Name;>> Userdef_Field.nebula
ECHO Selectionseqno SeqNo;>> Userdef_Field.nebula
ECHO SeqNo;>> Userdef_Field.nebula
ECHO Sortno SeqNo;>> Userdef_Field.nebula
ECHO Summaryseqno SeqNo;>> Userdef_Field.nebula
ECHO Updated Timestamp;>> Userdef_Field.nebula
ECHO Updatedby Date;>> Userdef_Field.nebula
ECHO Valuemax String;>> Userdef_Field.nebula
ECHO Valuemin String;>> Userdef_Field.nebula
ECHO };>> Userdef_Field.nebula 
 ECHO @Package("Application Dictionary") type  Userdef_Tab{ >> Userdef_Tab.nebula
ECHO Org;>> Userdef_Tab.nebula
ECHO Tab;>> Userdef_Tab.nebula
ECHO !ID;>> Userdef_Tab.nebula
ECHO Userdef_Win;>> Userdef_Tab.nebula
ECHO Created Timestamp;>> Userdef_Tab.nebula
ECHO Createdby Date;>> Userdef_Tab.nebula
ECHO Description;>> Userdef_Tab.nebula
ECHO Displaylogic Note;>> Userdef_Tab.nebula
ECHO Help;>> Userdef_Tab.nebula
ECHO Isactive YesNo;>> Userdef_Tab.nebula
ECHO Isdisplayed YesNo;>> Userdef_Tab.nebula
ECHO Isinsertrecord YesNo;>> Userdef_Tab.nebula
ECHO Ismultirowonly YesNo;>> Userdef_Tab.nebula
ECHO Isreadonly YesNo;>> Userdef_Tab.nebula
ECHO Issinglerow YesNo;>> Userdef_Tab.nebula
ECHO !Name;>> Userdef_Tab.nebula
ECHO Readonlylogic Note;>> Userdef_Tab.nebula
ECHO SeqNo;>> Userdef_Tab.nebula
ECHO Updated Timestamp;>> Userdef_Tab.nebula
ECHO Updatedby Date;>> Userdef_Tab.nebula
ECHO };>> Userdef_Tab.nebula 
 ECHO @Package("Application Dictionary") type  Userdef_Win{ >> Userdef_Win.nebula
ECHO Ad_Language Language;>> Userdef_Win.nebula
ECHO Org;>> Userdef_Win.nebula
ECHO Role;>> Userdef_Win.nebula
ECHO !ID;>> Userdef_Win.nebula
ECHO User;>> Userdef_Win.nebula
ECHO Window;>> Userdef_Win.nebula
ECHO Created Timestamp;>> Userdef_Win.nebula
ECHO Createdby Date;>> Userdef_Win.nebula
ECHO Customizationname Name;>> Userdef_Win.nebula
ECHO Description;>> Userdef_Win.nebula
ECHO Entitytype Attr;>> Userdef_Win.nebula
ECHO Help;>> Userdef_Win.nebula
ECHO Isactive YesNo;>> Userdef_Win.nebula
ECHO Isdefault YesNo;>> Userdef_Win.nebula
ECHO Isreadonly YesNo;>> Userdef_Win.nebula
ECHO Issystemdefault YesNo;>> Userdef_Win.nebula
ECHO Isuserupdateable YesNo;>> Userdef_Win.nebula
ECHO !Name;>> Userdef_Win.nebula
ECHO Updated Timestamp;>> Userdef_Win.nebula
ECHO Updatedby Date;>> Userdef_Win.nebula
ECHO Winheight Height;>> Userdef_Win.nebula
ECHO Winwidth Width;>> Userdef_Win.nebula
ECHO };>> Userdef_Win.nebula 
 ECHO @Package("Application Dictionary") type  Usermail{ >> Usermail.nebula
ECHO Org;>> Usermail.nebula
ECHO !ID;>> Usermail.nebula
ECHO User;>> Usermail.nebula
ECHO Created Timestamp;>> Usermail.nebula
ECHO Createdby Date;>> Usermail.nebula
ECHO Deliveryconfirmation Description;>> Usermail.nebula
ECHO Isactive YesNo;>> Usermail.nebula
ECHO Isdelivered YesNo;>> Usermail.nebula
ECHO Mailtext Note;>> Usermail.nebula
ECHO Messageid ID;>> Usermail.nebula
ECHO Mailtext;>> Usermail.nebula
ECHO Subject;>> Usermail.nebula
ECHO Updated Timestamp;>> Usermail.nebula
ECHO Updatedby Date;>> Usermail.nebula
ECHO Mailmsg;>> Usermail.nebula
ECHO };>> Usermail.nebula 
 ECHO @Package("Application Dictionary") type  Userpreference{ >> Userpreference.nebula
ECHO Org;>> Userpreference.nebula


ECHO !ID;>> Userpreference.nebula
ECHO User;>> Userpreference.nebula
ECHO Created Timestamp;>> Userpreference.nebula
ECHO Createdby Date;>> Userpreference.nebula
ECHO Isactive YesNo;>> Userpreference.nebula
ECHO Isautocommit YesNo;>> Userpreference.nebula
ECHO Isshowacct YesNo;>> Userpreference.nebula
ECHO Isshowadvanced YesNo;>> Userpreference.nebula
ECHO Isshowtrl YesNo;>> Userpreference.nebula

ECHO Printername Name;>> Userpreference.nebula
ECHO Tablerows Number;>> Userpreference.nebula
ECHO Uitheme String;>> Userpreference.nebula
ECHO Updated Timestamp;>> Userpreference.nebula
ECHO Updatedby Date;>> Userpreference.nebula
ECHO };>> Userpreference.nebula 
 ECHO @Package("Application Dictionary") type  Userquery{ >> Userquery.nebula
ECHO Org;>> Userquery.nebula
ECHO Tab;>> Userquery.nebula
ECHO Table;>> Userquery.nebula
ECHO !ID;>> Userquery.nebula
ECHO User;>> Userquery.nebula
ECHO Code Note;>> Userquery.nebula
ECHO Created Timestamp;>> Userquery.nebula
ECHO Createdby Date;>> Userquery.nebula
ECHO Description;>> Userquery.nebula
ECHO Isactive YesNo;>> Userquery.nebula
ECHO !Name;>> Userquery.nebula
ECHO Updated Timestamp;>> Userquery.nebula
ECHO Updatedby Date;>> Userquery.nebula
ECHO };>> Userquery.nebula 
 ECHO @Package("Application Dictionary") type  Userqueryline{ >> Userqueryline.nebula
ECHO Org;>> Userqueryline.nebula
ECHO !ID;>> Userqueryline.nebula
ECHO Userquery;>> Userqueryline.nebula
ECHO Created Timestamp;>> Userqueryline.nebula
ECHO Createdby Date;>> Userqueryline.nebula
ECHO Isactive YesNo;>> Userqueryline.nebula
ECHO Isand YesNo;>> Userqueryline.nebula
ECHO Keyname Name;>> Userqueryline.nebula
ECHO Keyvalue String;>> Userqueryline.nebula
ECHO Operator String;>> Userqueryline.nebula
ECHO SeqNo;>> Userqueryline.nebula
ECHO Updated Timestamp;>> Userqueryline.nebula
ECHO Updatedby Date;>> Userqueryline.nebula
ECHO Value1name Name;>> Userqueryline.nebula
ECHO Value1value String;>> Userqueryline.nebula
ECHO Value2name Name;>> Userqueryline.nebula
ECHO Value2value String;>> Userqueryline.nebula
ECHO };>> Userqueryline.nebula 
 ECHO @Package("Application Dictionary") type  Val_Rule{ >> Val_Rule.nebula
ECHO Org;>> Val_Rule.nebula
ECHO !ID;>> Val_Rule.nebula
ECHO Code Note;>> Val_Rule.nebula
ECHO Created Timestamp;>> Val_Rule.nebula
ECHO Createdby Date;>> Val_Rule.nebula
ECHO Description;>> Val_Rule.nebula
ECHO Entitytype Attr;>> Val_Rule.nebula
ECHO Isactive YesNo;>> Val_Rule.nebula
ECHO !Name;>> Val_Rule.nebula
ECHO Type YesNo;>> Val_Rule.nebula
ECHO Updated Timestamp;>> Val_Rule.nebula
ECHO Updatedby Date;>> Val_Rule.nebula
ECHO };>> Val_Rule.nebula 
 ECHO @Package("Application Dictionary") type  Version{ >> Version.nebula
ECHO Org;>> Version.nebula
ECHO !ID;>> Version.nebula
ECHO Created Timestamp;>> Version.nebula
ECHO Createdby Date;>> Version.nebula
ECHO Description;>> Version.nebula
ECHO Entitytype Attr;>> Version.nebula
ECHO Help;>> Version.nebula
ECHO Isactive YesNo;>> Version.nebula
ECHO !Name;>> Version.nebula
ECHO Updated Timestamp;>> Version.nebula
ECHO Updatedby Date;>> Version.nebula
ECHO Value String;>> Version.nebula
ECHO };>> Version.nebula 
 ECHO @Package("Application Dictionary") type  Viewcolumn{ >> Viewcolumn.nebula
ECHO Org;>> Viewcolumn.nebula
ECHO !ID;>> Viewcolumn.nebula
ECHO Viewcomponent;>> Viewcolumn.nebula
ECHO Columnname Name;>> Viewcolumn.nebula
ECHO Columnsql Note;>> Viewcolumn.nebula
ECHO Created Timestamp;>> Viewcolumn.nebula
ECHO Createdby Date;>> Viewcolumn.nebula
ECHO Dbdatatype YesNo;>> Viewcolumn.nebula
ECHO Description;>> Viewcolumn.nebula
ECHO Entitytype Attr;>> Viewcolumn.nebula
ECHO Help;>> Viewcolumn.nebula
ECHO Isactive YesNo;>> Viewcolumn.nebula
ECHO Updated Timestamp;>> Viewcolumn.nebula
ECHO Updatedby Date;>> Viewcolumn.nebula
ECHO };>> Viewcolumn.nebula 
 ECHO @Package("Application Dictionary") type  Viewcomponent{ >> Viewcomponent.nebula
ECHO Org;>> Viewcomponent.nebula
ECHO Table;>> Viewcomponent.nebula
ECHO !ID;>> Viewcomponent.nebula
ECHO Created Timestamp;>> Viewcomponent.nebula
ECHO Createdby Date;>> Viewcomponent.nebula
ECHO Description;>> Viewcomponent.nebula
ECHO Entitytype Attr;>> Viewcomponent.nebula
ECHO Fromclause Note;>> Viewcomponent.nebula
ECHO Help;>> Viewcomponent.nebula
ECHO Isactive YesNo;>> Viewcomponent.nebula
ECHO !Name;>> Viewcomponent.nebula
ECHO Otherclause Note;>> Viewcomponent.nebula
ECHO Referenced_Table Table;>> Viewcomponent.nebula
ECHO SeqNo;>> Viewcomponent.nebula
ECHO Updated Timestamp;>> Viewcomponent.nebula
ECHO Updatedby Date;>> Viewcomponent.nebula
ECHO Whereclause Note;>> Viewcomponent.nebula
ECHO };>> Viewcomponent.nebula 
 ECHO @Package("Application Dictionary") type  Wf_Activity{ >> Wf_Activity.nebula
ECHO Message;>> Wf_Activity.nebula
ECHO Org;>> Wf_Activity.nebula
ECHO Table;>> Wf_Activity.nebula
ECHO User;>> Wf_Activity.nebula
ECHO !ID;>> Wf_Activity.nebula
ECHO Wf_Node;>> Wf_Activity.nebula
ECHO Wf_Process;>> Wf_Activity.nebula
ECHO Wf_Responsible;>> Wf_Activity.nebula
ECHO Workflow;>> Wf_Activity.nebula
ECHO Created Timestamp;>> Wf_Activity.nebula
ECHO Createdby Date;>> Wf_Activity.nebula
ECHO Datelastalert Date;>> Wf_Activity.nebula
ECHO Dynprioritystart Number;>> Wf_Activity.nebula
ECHO Endwaittime Date;>> Wf_Activity.nebula
ECHO Isactive YesNo;>> Wf_Activity.nebula
ECHO Priority;>> Wf_Activity.nebula
ECHO Processed YesNo;>> Wf_Activity.nebula
ECHO Processing YesNo;>> Wf_Activity.nebula

ECHO Textmsg MSG;>> Wf_Activity.nebula
ECHO Updated Timestamp;>> Wf_Activity.nebula
ECHO Updatedby Date;>> Wf_Activity.nebula
ECHO Wfstate Attr;>> Wf_Activity.nebula
ECHO };>> Wf_Activity.nebula 
 ECHO @Package("Application Dictionary") type  Wf_Activityresult{ >> Wf_Activityresult.nebula
ECHO Org;>> Wf_Activityresult.nebula
ECHO !ID;>> Wf_Activityresult.nebula
ECHO Wf_Activity;>> Wf_Activityresult.nebula
ECHO Attributename Name;>> Wf_Activityresult.nebula
ECHO Attributevalue Note;>> Wf_Activityresult.nebula
ECHO Created Timestamp;>> Wf_Activityresult.nebula
ECHO Createdby Date;>> Wf_Activityresult.nebula
ECHO Description;>> Wf_Activityresult.nebula
ECHO Help;>> Wf_Activityresult.nebula
ECHO Isactive YesNo;>> Wf_Activityresult.nebula
ECHO Updated Timestamp;>> Wf_Activityresult.nebula
ECHO Updatedby Date;>> Wf_Activityresult.nebula
ECHO };>> Wf_Activityresult.nebula 
 ECHO @Package("Application Dictionary") type  Wf_Block{ >> Wf_Block.nebula
ECHO Org;>> Wf_Block.nebula
ECHO !ID;>> Wf_Block.nebula
ECHO Workflow;>> Wf_Block.nebula
ECHO Created Timestamp;>> Wf_Block.nebula
ECHO Createdby Date;>> Wf_Block.nebula
ECHO Description;>> Wf_Block.nebula
ECHO Isactive YesNo;>> Wf_Block.nebula
ECHO !Name;>> Wf_Block.nebula
ECHO Updated Timestamp;>> Wf_Block.nebula
ECHO Updatedby Date;>> Wf_Block.nebula
ECHO };>> Wf_Block.nebula 
 ECHO @Package("Application Dictionary") type  Wf_Eventaudit{ >> Wf_Eventaudit.nebula
ECHO Org;>> Wf_Eventaudit.nebula
ECHO Table;>> Wf_Eventaudit.nebula
ECHO User;>> Wf_Eventaudit.nebula
ECHO !ID;>> Wf_Eventaudit.nebula
ECHO Wf_Node;>> Wf_Eventaudit.nebula
ECHO Wf_Process;>> Wf_Eventaudit.nebula
ECHO Wf_Responsible;>> Wf_Eventaudit.nebula
ECHO Attributename Name;>> Wf_Eventaudit.nebula
ECHO Created Timestamp;>> Wf_Eventaudit.nebula
ECHO Createdby Date;>> Wf_Eventaudit.nebula
ECHO Description;>> Wf_Eventaudit.nebula
ECHO Elapsedtimems Number;>> Wf_Eventaudit.nebula
ECHO Eventtype Attr;>> Wf_Eventaudit.nebula
ECHO Isactive YesNo;>> Wf_Eventaudit.nebula
ECHO Newvalue Note;>> Wf_Eventaudit.nebula
ECHO Oldvalue Note;>> Wf_Eventaudit.nebula

ECHO Textmsg MSG;>> Wf_Eventaudit.nebula
ECHO Updated Timestamp;>> Wf_Eventaudit.nebula
ECHO Updatedby Date;>> Wf_Eventaudit.nebula
ECHO Wfstate Attr;>> Wf_Eventaudit.nebula
ECHO };>> Wf_Eventaudit.nebula 
 ECHO @Package("Application Dictionary") type  Wf_Nextcondition{ >> Wf_Nextcondition.nebula
ECHO Column;>> Wf_Nextcondition.nebula
ECHO Org;>> Wf_Nextcondition.nebula
ECHO !ID;>> Wf_Nextcondition.nebula
ECHO Wf_Nodenext;>> Wf_Nextcondition.nebula
ECHO Andor YesNo;>> Wf_Nextcondition.nebula
ECHO Created Timestamp;>> Wf_Nextcondition.nebula
ECHO Createdby Date;>> Wf_Nextcondition.nebula
ECHO Entitytype Attr;>> Wf_Nextcondition.nebula
ECHO Isactive YesNo;>> Wf_Nextcondition.nebula
ECHO Operation Attr;>> Wf_Nextcondition.nebula
ECHO SeqNo;>> Wf_Nextcondition.nebula
ECHO Updated Timestamp;>> Wf_Nextcondition.nebula
ECHO Updatedby Date;>> Wf_Nextcondition.nebula
ECHO Value String;>> Wf_Nextcondition.nebula
ECHO Value2 String;>> Wf_Nextcondition.nebula
ECHO };>> Wf_Nextcondition.nebula 
 ECHO @Package("Application Dictionary") type  Wf_Node{ >> Wf_Node.nebula
ECHO Column;>> Wf_Node.nebula
ECHO Form;>> Wf_Node.nebula
ECHO Image;>> Wf_Node.nebula
ECHO Org;>> Wf_Node.nebula
ECHO Process;>> Wf_Node.nebula
ECHO Task;>> Wf_Node.nebula
ECHO Wf_Block;>> Wf_Node.nebula
ECHO !ID;>> Wf_Node.nebula
ECHO Wf_Responsible;>> Wf_Node.nebula
ECHO Window;>> Wf_Node.nebula
ECHO Workflow;>> Wf_Node.nebula
ECHO Action YesNo;>> Wf_Node.nebula
ECHO Attributename Name;>> Wf_Node.nebula
ECHO Attributevalue Note;>> Wf_Node.nebula
ECHO Cost Number;>> Wf_Node.nebula
ECHO Created Timestamp;>> Wf_Node.nebula
ECHO Createdby Date;>> Wf_Node.nebula
ECHO Description;>> Wf_Node.nebula
ECHO Docaction Attr;>> Wf_Node.nebula
ECHO Duration Long;>> Wf_Node.nebula
ECHO Durationlimit Long;>> Wf_Node.nebula
ECHO Dynprioritychange Priority;>> Wf_Node.nebula
ECHO Dynpriorityunit YesNo;>> Wf_Node.nebula
ECHO Email;>> Wf_Node.nebula
ECHO Emailrecipient YesNo;>> Wf_Node.nebula
ECHO Entitytype Attr;>> Wf_Node.nebula
ECHO Finishmode YesNo;>> Wf_Node.nebula
ECHO Help;>> Wf_Node.nebula
ECHO Isactive YesNo;>> Wf_Node.nebula
ECHO Iscentrallymaintained YesNo;>> Wf_Node.nebula
ECHO Joinelement YesNo;>> Wf_Node.nebula
ECHO !Name;>> Wf_Node.nebula
ECHO Priority;>> Wf_Node.nebula
ECHO Mailtext;>> Wf_Node.nebula
ECHO Splitelement YesNo;>> Wf_Node.nebula
ECHO Startmode YesNo;>> Wf_Node.nebula
ECHO Subflowexecution YesNo;>> Wf_Node.nebula
ECHO Updated Timestamp;>> Wf_Node.nebula
ECHO Updatedby Date;>> Wf_Node.nebula
ECHO Value String;>> Wf_Node.nebula
ECHO Waittime Long;>> Wf_Node.nebula
ECHO Waitingtime Long;>> Wf_Node.nebula
ECHO Workflow;>> Wf_Node.nebula
ECHO Workingtime Long;>> Wf_Node.nebula
ECHO Xposition Long;>> Wf_Node.nebula
ECHO Yposition Long;>> Wf_Node.nebula
ECHO };>> Wf_Node.nebula 
 ECHO @Package("Application Dictionary") type  Wf_Node_para{ >> Wf_Node_para.nebula
ECHO Org;>> Wf_Node_para.nebula
ECHO Process_Para;>> Wf_Node_para.nebula
ECHO Wf_Node;>> Wf_Node_para.nebula
ECHO !ID;>> Wf_Node_para.nebula
ECHO Attributename Name;>> Wf_Node_para.nebula
ECHO Attributevalue Note;>> Wf_Node_para.nebula
ECHO Created Timestamp;>> Wf_Node_para.nebula
ECHO Createdby Date;>> Wf_Node_para.nebula
ECHO Description;>> Wf_Node_para.nebula
ECHO Entitytype Attr;>> Wf_Node_para.nebula
ECHO Isactive YesNo;>> Wf_Node_para.nebula
ECHO Updated Timestamp;>> Wf_Node_para.nebula
ECHO Updatedby Date;>> Wf_Node_para.nebula
ECHO };>> Wf_Node_para.nebula 
 ECHO @Package("Application Dictionary") type  Wf_Node_trl{ >> Wf_Node_trl.nebula
ECHO !Ad_Language Language;>> Wf_Node_trl.nebula
ECHO Org;>> Wf_Node_trl.nebula
ECHO !Wf_Node;>> Wf_Node_trl.nebula
ECHO Created Timestamp;>> Wf_Node_trl.nebula
ECHO Createdby Date;>> Wf_Node_trl.nebula
ECHO Description;>> Wf_Node_trl.nebula
ECHO Help;>> Wf_Node_trl.nebula
ECHO Isactive YesNo;>> Wf_Node_trl.nebula
ECHO Istranslated YesNo;>> Wf_Node_trl.nebula
ECHO !Name;>> Wf_Node_trl.nebula
ECHO Updated Timestamp;>> Wf_Node_trl.nebula
ECHO Updatedby Date;>> Wf_Node_trl.nebula
ECHO };>> Wf_Node_trl.nebula 
 ECHO @Package("Application Dictionary") type  Wf_Nodenext{ >> Wf_Nodenext.nebula
ECHO Org;>> Wf_Nodenext.nebula
ECHO Wf_Next Wf_Node;>> Wf_Nodenext.nebula
ECHO !ID;>> Wf_Nodenext.nebula
ECHO Wf_Node;>> Wf_Nodenext.nebula
ECHO Created Timestamp;>> Wf_Nodenext.nebula
ECHO Createdby Date;>> Wf_Nodenext.nebula
ECHO Description;>> Wf_Nodenext.nebula
ECHO Entitytype Attr;>> Wf_Nodenext.nebula
ECHO Isactive YesNo;>> Wf_Nodenext.nebula
ECHO Isstduserworkflow YesNo;>> Wf_Nodenext.nebula
ECHO SeqNo;>> Wf_Nodenext.nebula
ECHO Transitioncode Note;>> Wf_Nodenext.nebula
ECHO Updated Timestamp;>> Wf_Nodenext.nebula
ECHO Updatedby Date;>> Wf_Nodenext.nebula
ECHO };>> Wf_Nodenext.nebula 
 ECHO @Package("Application Dictionary") type  Wf_Process{ >> Wf_Process.nebula
ECHO Message;>> Wf_Process.nebula
ECHO Org;>> Wf_Process.nebula
ECHO Table;>> Wf_Process.nebula
ECHO User;>> Wf_Process.nebula
ECHO !ID;>> Wf_Process.nebula
ECHO Wf_Responsible;>> Wf_Process.nebula
ECHO Workflow;>> Wf_Process.nebula
ECHO Created Timestamp;>> Wf_Process.nebula
ECHO Createdby Date;>> Wf_Process.nebula
ECHO Isactive YesNo;>> Wf_Process.nebula
ECHO Priority;>> Wf_Process.nebula
ECHO Processed YesNo;>> Wf_Process.nebula
ECHO Processing YesNo;>> Wf_Process.nebula

ECHO Textmsg MSG;>> Wf_Process.nebula
ECHO Updated Timestamp;>> Wf_Process.nebula
ECHO Updatedby Date;>> Wf_Process.nebula
ECHO Wfstate Attr;>> Wf_Process.nebula
ECHO };>> Wf_Process.nebula 
 ECHO @Package("Application Dictionary") type  Wf_Processdata{ >> Wf_Processdata.nebula
ECHO Org;>> Wf_Processdata.nebula
ECHO !ID;>> Wf_Processdata.nebula
ECHO Wf_Process;>> Wf_Processdata.nebula
ECHO Attributename Name;>> Wf_Processdata.nebula
ECHO Attributevalue Note;>> Wf_Processdata.nebula
ECHO Created Timestamp;>> Wf_Processdata.nebula
ECHO Createdby Date;>> Wf_Processdata.nebula
ECHO Isactive YesNo;>> Wf_Processdata.nebula
ECHO Updated Timestamp;>> Wf_Processdata.nebula
ECHO Updatedby Date;>> Wf_Processdata.nebula
ECHO };>> Wf_Processdata.nebula 
 ECHO @Package("Application Dictionary") type  Wf_Responsible{ >> Wf_Responsible.nebula
ECHO Org;>> Wf_Responsible.nebula
ECHO Role;>> Wf_Responsible.nebula
ECHO User;>> Wf_Responsible.nebula
ECHO !ID;>> Wf_Responsible.nebula
ECHO Created Timestamp;>> Wf_Responsible.nebula
ECHO Createdby Date;>> Wf_Responsible.nebula
ECHO Description;>> Wf_Responsible.nebula
ECHO Entitytype Attr;>> Wf_Responsible.nebula
ECHO Isactive YesNo;>> Wf_Responsible.nebula
ECHO !Name;>> Wf_Responsible.nebula
ECHO Responsibletype YesNo;>> Wf_Responsible.nebula
ECHO Updated Timestamp;>> Wf_Responsible.nebula
ECHO Updatedby Date;>> Wf_Responsible.nebula
ECHO };>> Wf_Responsible.nebula 
 ECHO @Package("Application Dictionary") type  Window{ >> Window.nebula
ECHO Color;>> Window.nebula
ECHO Ctxarea;>> Window.nebula
ECHO Image;>> Window.nebula
ECHO Org;>> Window.nebula
ECHO !ID;>> Window.nebula
ECHO Created Timestamp;>> Window.nebula
ECHO Createdby Date;>> Window.nebula
ECHO Description;>> Window.nebula
ECHO Entitytype Attr;>> Window.nebula
ECHO Help;>> Window.nebula
ECHO Isactive YesNo;>> Window.nebula
ECHO Isbetafunctionality YesNo;>> Window.nebula
ECHO Iscustomdefault YesNo;>> Window.nebula
ECHO Isdefault YesNo;>> Window.nebula
ECHO !Name;>> Window.nebula
ECHO Processing YesNo;>> Window.nebula
ECHO Updated Timestamp;>> Window.nebula
ECHO Updatedby Date;>> Window.nebula
ECHO Winheight Height;>> Window.nebula
ECHO Winwidth Width;>> Window.nebula
ECHO Windowtype YesNo;>> Window.nebula
ECHO };>> Window.nebula 
 ECHO @Package("Application Dictionary") type  Window_Access{ >> Window_Access.nebula
ECHO Org;>> Window_Access.nebula
ECHO !Role;>> Window_Access.nebula
ECHO !Window;>> Window_Access.nebula
ECHO Created Timestamp;>> Window_Access.nebula
ECHO Createdby Date;>> Window_Access.nebula
ECHO Isactive YesNo;>> Window_Access.nebula
ECHO Isreadwrite YesNo;>> Window_Access.nebula
ECHO Updated Timestamp;>> Window_Access.nebula
ECHO Updatedby Date;>> Window_Access.nebula
ECHO };>> Window_Access.nebula 
 ECHO @Package("Application Dictionary") type  Window_Trl{ >> Window_Trl.nebula
ECHO !Ad_Language Language;>> Window_Trl.nebula
ECHO Org;>> Window_Trl.nebula
ECHO !Window;>> Window_Trl.nebula
ECHO Created Timestamp;>> Window_Trl.nebula
ECHO Createdby Date;>> Window_Trl.nebula
ECHO Description;>> Window_Trl.nebula
ECHO Help;>> Window_Trl.nebula
ECHO Isactive YesNo;>> Window_Trl.nebula
ECHO Istranslated YesNo;>> Window_Trl.nebula
ECHO !Name;>> Window_Trl.nebula
ECHO Updated Timestamp;>> Window_Trl.nebula
ECHO Updatedby Date;>> Window_Trl.nebula
ECHO };>> Window_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Windowlog{ >> Windowlog.nebula
ECHO Form;>> Windowlog.nebula
ECHO Org;>> Windowlog.nebula
ECHO Role;>> Windowlog.nebula
ECHO Session;>> Windowlog.nebula
ECHO !ID;>> Windowlog.nebula
ECHO Window;>> Windowlog.nebula
ECHO Created Timestamp;>> Windowlog.nebula
ECHO Createdby Date;>> Windowlog.nebula
ECHO Isactive YesNo;>> Windowlog.nebula
ECHO Updated Timestamp;>> Windowlog.nebula
ECHO Updatedby Date;>> Windowlog.nebula
ECHO };>> Windowlog.nebula 
 ECHO @Package("Application Dictionary") type  Workbench{ >> Workbench.nebula
ECHO Color;>> Workbench.nebula
ECHO Column;>> Workbench.nebula
ECHO Image;>> Workbench.nebula
ECHO Org;>> Workbench.nebula
ECHO !ID;>> Workbench.nebula
ECHO Created Timestamp;>> Workbench.nebula
ECHO Createdby Date;>> Workbench.nebula
ECHO Description;>> Workbench.nebula
ECHO Entitytype Attr;>> Workbench.nebula
ECHO Help;>> Workbench.nebula
ECHO Isactive YesNo;>> Workbench.nebula
ECHO !Name;>> Workbench.nebula
ECHO Updated Timestamp;>> Workbench.nebula
ECHO Updatedby Date;>> Workbench.nebula
ECHO };>> Workbench.nebula 
 ECHO @Package("Application Dictionary") type  Workbench_Trl{ >> Workbench_Trl.nebula
ECHO !Ad_Language Language;>> Workbench_Trl.nebula
ECHO Org;>> Workbench_Trl.nebula
ECHO !Workbench;>> Workbench_Trl.nebula
ECHO Created Timestamp;>> Workbench_Trl.nebula
ECHO Createdby Date;>> Workbench_Trl.nebula
ECHO Description;>> Workbench_Trl.nebula
ECHO Help;>> Workbench_Trl.nebula
ECHO Isactive YesNo;>> Workbench_Trl.nebula
ECHO Istranslated YesNo;>> Workbench_Trl.nebula
ECHO !Name;>> Workbench_Trl.nebula
ECHO Updated Timestamp;>> Workbench_Trl.nebula
ECHO Updatedby Date;>> Workbench_Trl.nebula
ECHO };>> Workbench_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Workbenchwindow{ >> Workbenchwindow.nebula
ECHO Form;>> Workbenchwindow.nebula
ECHO Org;>> Workbenchwindow.nebula
ECHO Process;>> Workbenchwindow.nebula
ECHO Task;>> Workbenchwindow.nebula
ECHO Window;>> Workbenchwindow.nebula
ECHO !ID;>> Workbenchwindow.nebula
ECHO Workbench;>> Workbenchwindow.nebula
ECHO Created Timestamp;>> Workbenchwindow.nebula
ECHO Createdby Date;>> Workbenchwindow.nebula
ECHO Entitytype Attr;>> Workbenchwindow.nebula
ECHO Isactive YesNo;>> Workbenchwindow.nebula
ECHO Isprimary YesNo;>> Workbenchwindow.nebula
ECHO SeqNo;>> Workbenchwindow.nebula
ECHO Updated Timestamp;>> Workbenchwindow.nebula
ECHO Updatedby Date;>> Workbenchwindow.nebula
ECHO };>> Workbenchwindow.nebula 
 ECHO @Package("Application Dictionary") type  Workflow{ >> Workflow.nebula
ECHO Org;>> Workflow.nebula
ECHO Table;>> Workflow.nebula
ECHO Wf_Node;>> Workflow.nebula
ECHO Wf_Responsible;>> Workflow.nebula
ECHO Workflowprocessor;>> Workflow.nebula
ECHO !ID;>> Workflow.nebula
ECHO Accesslevel YesNo;>> Workflow.nebula
ECHO Author String;>> Workflow.nebula
ECHO Cost Number;>> Workflow.nebula
ECHO Created Timestamp;>> Workflow.nebula
ECHO Createdby Date;>> Workflow.nebula
ECHO Description;>> Workflow.nebula
ECHO Docvaluelogic Note;>> Workflow.nebula
ECHO Duration Long;>> Workflow.nebula
ECHO Durationlimit Long;>> Workflow.nebula
ECHO Durationunit Attr;>> Workflow.nebula
ECHO Entitytype Attr;>> Workflow.nebula
ECHO Help;>> Workflow.nebula
ECHO Isactive YesNo;>> Workflow.nebula
ECHO Isdefault YesNo;>> Workflow.nebula
ECHO Isvalid YesNo;>> Workflow.nebula
ECHO !Name;>> Workflow.nebula
ECHO Priority;>> Workflow.nebula
ECHO Publishstatus YesNo;>> Workflow.nebula
ECHO Updated Timestamp;>> Workflow.nebula
ECHO Updatedby Date;>> Workflow.nebula
ECHO Validfrom Date;>> Workflow.nebula
ECHO Validto Date;>> Workflow.nebula
ECHO Validateworkflow YesNo;>> Workflow.nebula
ECHO Value String;>> Workflow.nebula
ECHO Version Number;>> Workflow.nebula
ECHO Waitingtime Long;>> Workflow.nebula
ECHO Workflowtype YesNo;>> Workflow.nebula
ECHO Workingtime Long;>> Workflow.nebula
ECHO };>> Workflow.nebula 
 ECHO @Package("Application Dictionary") type  Workflow_Access{ >> Workflow_Access.nebula
ECHO Org;>> Workflow_Access.nebula
ECHO !Role;>> Workflow_Access.nebula
ECHO !Workflow;>> Workflow_Access.nebula
ECHO Created Timestamp;>> Workflow_Access.nebula
ECHO Createdby Date;>> Workflow_Access.nebula
ECHO Isactive YesNo;>> Workflow_Access.nebula
ECHO Isreadwrite YesNo;>> Workflow_Access.nebula
ECHO Updated Timestamp;>> Workflow_Access.nebula
ECHO Updatedby Date;>> Workflow_Access.nebula
ECHO };>> Workflow_Access.nebula 
 ECHO @Package("Application Dictionary") type  Workflow_Trl{ >> Workflow_Trl.nebula
ECHO !Ad_Language Language;>> Workflow_Trl.nebula
ECHO Org;>> Workflow_Trl.nebula
ECHO !Workflow;>> Workflow_Trl.nebula
ECHO Created Timestamp;>> Workflow_Trl.nebula
ECHO Createdby Date;>> Workflow_Trl.nebula
ECHO Description;>> Workflow_Trl.nebula
ECHO Help;>> Workflow_Trl.nebula
ECHO Isactive YesNo;>> Workflow_Trl.nebula
ECHO Istranslated YesNo;>> Workflow_Trl.nebula
ECHO !Name;>> Workflow_Trl.nebula
ECHO Updated Timestamp;>> Workflow_Trl.nebula
ECHO Updatedby Date;>> Workflow_Trl.nebula
ECHO };>> Workflow_Trl.nebula 
 ECHO @Package("Application Dictionary") type  Workflowprocessor{ >> Workflowprocessor.nebula
ECHO Org;>> Workflowprocessor.nebula
ECHO Schedule;>> Workflowprocessor.nebula
ECHO !ID;>> Workflowprocessor.nebula
ECHO Alertoverpriority Priority;>> Workflowprocessor.nebula
ECHO Created Timestamp;>> Workflowprocessor.nebula
ECHO Createdby Date;>> Workflowprocessor.nebula
ECHO Datelastrun Date;>> Workflowprocessor.nebula
ECHO Datenextrun Date;>> Workflowprocessor.nebula
ECHO Description;>> Workflowprocessor.nebula
ECHO Frequency;>> Workflowprocessor.nebula
ECHO Frequencytype YesNo;>> Workflowprocessor.nebula
ECHO Inactivityalertdays Count;>> Workflowprocessor.nebula
ECHO Isactive YesNo;>> Workflowprocessor.nebula
ECHO Keeplogdays Count;>> Workflowprocessor.nebula
ECHO !Name;>> Workflowprocessor.nebula
ECHO Processing YesNo;>> Workflowprocessor.nebula
ECHO Reminddays Count;>> Workflowprocessor.nebula
ECHO Supervisor User;>> Workflowprocessor.nebula
ECHO Updated Timestamp;>> Workflowprocessor.nebula
ECHO Updatedby Date;>> Workflowprocessor.nebula
ECHO };>> Workflowprocessor.nebula 
 ECHO @Package("Application Dictionary") type  Workflowprocessorlog{ >> Workflowprocessorlog.nebula
ECHO Org;>> Workflowprocessorlog.nebula
ECHO !ID;>> Workflowprocessorlog.nebula
ECHO Workflowprocessor;>> Workflowprocessorlog.nebula
ECHO Binarydata Note;>> Workflowprocessorlog.nebula
ECHO Created Timestamp;>> Workflowprocessorlog.nebula
ECHO Createdby Date;>> Workflowprocessorlog.nebula
ECHO Description;>> Workflowprocessorlog.nebula
ECHO Isactive YesNo;>> Workflowprocessorlog.nebula
ECHO Iserror YesNo;>> Workflowprocessorlog.nebula
ECHO Reference String;>> Workflowprocessorlog.nebula
ECHO Summary Note;>> Workflowprocessorlog.nebula
ECHO Textmsg MSG;>> Workflowprocessorlog.nebula
ECHO Updated Timestamp;>> Workflowprocessorlog.nebula
ECHO Updatedby Date;>> Workflowprocessorlog.nebula
ECHO };>> Workflowprocessorlog.nebula 
 ECHO @Package("Marketplace") type  Bid{ >> Bid.nebula
ECHO Org;>> Bid.nebula
ECHO User;>> Bid.nebula
ECHO !ID;>> Bid.nebula
ECHO Buyerfunds;>> Bid.nebula
ECHO Topic;>> Bid.nebula
ECHO Created Timestamp;>> Bid.nebula
ECHO Createdby Date;>> Bid.nebula
ECHO Isactive YesNo;>> Bid.nebula
ECHO Iswillingtocommit YesNo;>> Bid.nebula
ECHO !Name;>> Bid.nebula
ECHO Privatenote Note;>> Bid.nebula
ECHO Textmsg MSG;>> Bid.nebula
ECHO Updated Timestamp;>> Bid.nebula
ECHO Updatedby Date;>> Bid.nebula
ECHO };>> Bid.nebula 
 ECHO @Package("Marketplace") type  Bidcomment{ >> Bidcomment.nebula
ECHO Org;>> Bidcomment.nebula
ECHO User;>> Bidcomment.nebula
ECHO !ID;>> Bidcomment.nebula
ECHO Topic;>> Bidcomment.nebula
ECHO Created Timestamp;>> Bidcomment.nebula
ECHO Createdby Date;>> Bidcomment.nebula
ECHO Isactive YesNo;>> Bidcomment.nebula
ECHO Textmsg MSG;>> Bidcomment.nebula
ECHO Updated Timestamp;>> Bidcomment.nebula
ECHO Updatedby Date;>> Bidcomment.nebula
ECHO };>> Bidcomment.nebula 
 ECHO @Package("Marketplace") type  Buyer{ >> Buyer.nebula
ECHO Org;>> Buyer.nebula
ECHO !User;>> Buyer.nebula
ECHO Created Timestamp;>> Buyer.nebula
ECHO Createdby Date;>> Buyer.nebula
ECHO Description;>> Buyer.nebula
ECHO Isactive YesNo;>> Buyer.nebula
ECHO !Name;>> Buyer.nebula
ECHO Updated Timestamp;>> Buyer.nebula
ECHO Updatedby Date;>> Buyer.nebula
ECHO Validto Date;>> Buyer.nebula
ECHO };>> Buyer.nebula 
 ECHO @Package("Marketplace") type  Buyerfunds{ >> Buyerfunds.nebula
ECHO Org;>> Buyerfunds.nebula
ECHO User;>> Buyerfunds.nebula
ECHO !ID;>> Buyerfunds.nebula
ECHO Order;>> Buyerfunds.nebula
ECHO Payment;>> Buyerfunds.nebula
ECHO Committedamt Amount;>> Buyerfunds.nebula
ECHO Created Timestamp;>> Buyerfunds.nebula
ECHO Createdby Date;>> Buyerfunds.nebula
ECHO Isactive YesNo;>> Buyerfunds.nebula
ECHO Noncommittedamt Amount;>> Buyerfunds.nebula
ECHO Updated Timestamp;>> Buyerfunds.nebula
ECHO Updatedby Date;>> Buyerfunds.nebula
ECHO };>> Buyerfunds.nebula 
 ECHO @Package("Marketplace") type  Offer{ >> Offer.nebula
ECHO Org;>> Offer.nebula
ECHO User;>> Offer.nebula
ECHO !ID;>> Offer.nebula
ECHO Sellerfunds;>> Offer.nebula
ECHO Topic;>> Offer.nebula
ECHO Created Timestamp;>> Offer.nebula
ECHO Createdby Date;>> Offer.nebula
ECHO Isactive YesNo;>> Offer.nebula
ECHO Iswillingtocommit YesNo;>> Offer.nebula
ECHO !Name;>> Offer.nebula
ECHO Privatenote Note;>> Offer.nebula
ECHO Textmsg MSG;>> Offer.nebula
ECHO Updated Timestamp;>> Offer.nebula
ECHO Updatedby Date;>> Offer.nebula
ECHO };>> Offer.nebula 
 ECHO @Package("Marketplace") type  Seller{ >> Seller.nebula
ECHO Org;>> Seller.nebula
ECHO !User;>> Seller.nebula
ECHO Created Timestamp;>> Seller.nebula
ECHO Createdby Date;>> Seller.nebula
ECHO Description;>> Seller.nebula
ECHO Isactive YesNo;>> Seller.nebula
ECHO Isinternal YesNo;>> Seller.nebula
ECHO !Name;>> Seller.nebula
ECHO Updated Timestamp;>> Seller.nebula
ECHO Updatedby Date;>> Seller.nebula
ECHO Validto Date;>> Seller.nebula
ECHO };>> Seller.nebula 
 ECHO @Package("Marketplace") type  Sellerfunds{ >> Sellerfunds.nebula
ECHO Org;>> Sellerfunds.nebula
ECHO User;>> Sellerfunds.nebula
ECHO !ID;>> Sellerfunds.nebula
ECHO Order;>> Sellerfunds.nebula
ECHO Payment;>> Sellerfunds.nebula
ECHO Committedamt Amount;>> Sellerfunds.nebula
ECHO Created Timestamp;>> Sellerfunds.nebula
ECHO Createdby Date;>> Sellerfunds.nebula
ECHO Isactive YesNo;>> Sellerfunds.nebula
ECHO Noncommittedamt Amount;>> Sellerfunds.nebula
ECHO Updated Timestamp;>> Sellerfunds.nebula
ECHO Updatedby Date;>> Sellerfunds.nebula
ECHO };>> Sellerfunds.nebula 
 ECHO @Package("Marketplace") type  Topic{ >> Topic.nebula
ECHO Org;>> Topic.nebula
ECHO Topiccategory;>> Topic.nebula
ECHO Topictype;>> Topic.nebula
ECHO !ID;>> Topic.nebula
ECHO Created Timestamp;>> Topic.nebula
ECHO Createdby Date;>> Topic.nebula
ECHO Decisiondate Date;>> Topic.nebula
ECHO Description;>> Topic.nebula
ECHO Documentno SeqNo;>> Topic.nebula
ECHO Isactive YesNo;>> Topic.nebula
ECHO Ispublished YesNo;>> Topic.nebula
ECHO !Name;>> Topic.nebula
ECHO Processed YesNo;>> Topic.nebula
ECHO Processing YesNo;>> Topic.nebula
ECHO Textdetails Note;>> Topic.nebula
ECHO Textmsg MSG;>> Topic.nebula
ECHO Topicaction Attr;>> Topic.nebula
ECHO Topicstatus Attr;>> Topic.nebula
ECHO Updated Timestamp;>> Topic.nebula
ECHO Updatedby Date;>> Topic.nebula
ECHO };>> Topic.nebula 
 ECHO @Package("Marketplace") type  Topiccategory{ >> Topiccategory.nebula
ECHO Org;>> Topiccategory.nebula
ECHO !ID;>> Topiccategory.nebula
ECHO Topictype;>> Topiccategory.nebula
ECHO Created Timestamp;>> Topiccategory.nebula
ECHO Createdby Date;>> Topiccategory.nebula
ECHO Description;>> Topiccategory.nebula
ECHO Isactive YesNo;>> Topiccategory.nebula
ECHO !Name;>> Topiccategory.nebula
ECHO Updated Timestamp;>> Topiccategory.nebula
ECHO Updatedby Date;>> Topiccategory.nebula
ECHO };>> Topiccategory.nebula 
 ECHO @Package("Marketplace") type  Topictype{ >> Topictype.nebula
ECHO Org;>> Topictype.nebula
ECHO Auctiontype YesNo;>> Topictype.nebula
ECHO !ID;>> Topictype.nebula
ECHO Created Timestamp;>> Topictype.nebula
ECHO Createdby Date;>> Topictype.nebula
ECHO Description;>> Topictype.nebula
ECHO Help;>> Topictype.nebula
ECHO Isactive YesNo;>> Topictype.nebula
ECHO Pricelist;>> Topictype.nebula
ECHO Productmember Product;>> Topictype.nebula
ECHO Product;>> Topictype.nebula
ECHO !Name;>> Topictype.nebula
ECHO Updated Timestamp;>> Topictype.nebula
ECHO Updatedby Date;>> Topictype.nebula
ECHO };>> Topictype.nebula 
 ECHO @Package("Common or Core Functionality") type  Acctprocessor{ >> Acctprocessor.nebula
ECHO Org;>> Acctprocessor.nebula
ECHO Schedule;>> Acctprocessor.nebula
ECHO Table;>> Acctprocessor.nebula
ECHO !ID;>> Acctprocessor.nebula
ECHO Acctschema;>> Acctprocessor.nebula
ECHO Created Timestamp;>> Acctprocessor.nebula
ECHO Createdby Date;>> Acctprocessor.nebula
ECHO Datelastrun Date;>> Acctprocessor.nebula
ECHO Datenextrun Date;>> Acctprocessor.nebula
ECHO Description;>> Acctprocessor.nebula
ECHO Frequency;>> Acctprocessor.nebula
ECHO Frequencytype YesNo;>> Acctprocessor.nebula
ECHO Isactive YesNo;>> Acctprocessor.nebula
ECHO Keeplogdays Count;>> Acctprocessor.nebula
ECHO !Name;>> Acctprocessor.nebula
ECHO Processing YesNo;>> Acctprocessor.nebula
ECHO Supervisor User;>> Acctprocessor.nebula
ECHO Updated Timestamp;>> Acctprocessor.nebula
ECHO Updatedby Date;>> Acctprocessor.nebula
ECHO };>> Acctprocessor.nebula 
 ECHO @Package("Common or Core Functionality") type  Acctprocessorlog{ >> Acctprocessorlog.nebula
ECHO Org;>> Acctprocessorlog.nebula
ECHO Binarydata Note;>> Acctprocessorlog.nebula
ECHO !ID;>> Acctprocessorlog.nebula
ECHO Acctprocessor;>> Acctprocessorlog.nebula
ECHO Created Timestamp;>> Acctprocessorlog.nebula
ECHO Createdby Date;>> Acctprocessorlog.nebula
ECHO Description;>> Acctprocessorlog.nebula
ECHO Isactive YesNo;>> Acctprocessorlog.nebula
ECHO Iserror YesNo;>> Acctprocessorlog.nebula
ECHO Reference String;>> Acctprocessorlog.nebula
ECHO Summary Note;>> Acctprocessorlog.nebula
ECHO Textmsg MSG;>> Acctprocessorlog.nebula
ECHO Updated Timestamp;>> Acctprocessorlog.nebula
ECHO Updatedby Date;>> Acctprocessorlog.nebula
ECHO };>> Acctprocessorlog.nebula 
 ECHO @Package("Common or Core Functionality") type  Acctschema{ >> Acctschema.nebula
ECHO Orgonly Org;>> Acctschema.nebula
ECHO Org;>> Acctschema.nebula
ECHO Autoperiodcontrol YesNo;>> Acctschema.nebula
ECHO !ID;>> Acctschema.nebula
ECHO Currency;>> Acctschema.nebula
ECHO Period;>> Acctschema.nebula
ECHO Commitmenttype YesNo;>> Acctschema.nebula
ECHO Costinglevel YesNo;>> Acctschema.nebula
ECHO Costingmethod YesNo;>> Acctschema.nebula
ECHO Created Timestamp;>> Acctschema.nebula
ECHO Createdby Date;>> Acctschema.nebula
ECHO Description;>> Acctschema.nebula
ECHO GAAP;>> Acctschema.nebula
ECHO Hasalias YesNo;>> Acctschema.nebula
ECHO Hascombination YesNo;>> Acctschema.nebula
ECHO Isaccrual YesNo;>> Acctschema.nebula
ECHO Isactive YesNo;>> Acctschema.nebula
ECHO Isadjustcogs YesNo;>> Acctschema.nebula
ECHO Isdiscountcorrectstax YesNo;>> Acctschema.nebula
ECHO Isexplicitcostadjustment YesNo;>> Acctschema.nebula
ECHO Ispostservices YesNo;>> Acctschema.nebula
ECHO Istradediscountposted YesNo;>> Acctschema.nebula
ECHO Costtype;>> Acctschema.nebula
ECHO !Name;>> Acctschema.nebula
ECHO Period_Openfuture Number;>> Acctschema.nebula
ECHO Period_Openhistory Number;>> Acctschema.nebula
ECHO Processing YesNo;>> Acctschema.nebula
ECHO Separator String;>> Acctschema.nebula
ECHO Taxcorrectiontype YesNo;>> Acctschema.nebula
ECHO Updated Timestamp;>> Acctschema.nebula
ECHO Updatedby Date;>> Acctschema.nebula
ECHO };>> Acctschema.nebula 
 ECHO @Package("Common or Core Functionality") type  Acctschema_Default{ >> Acctschema_Default.nebula
ECHO Org;>> Acctschema_Default.nebula
ECHO B_Asset_acct Account;>> Acctschema_Default.nebula
ECHO B_Expense_acct Account;>> Acctschema_Default.nebula
ECHO B_Intransit_acct Account;>> Acctschema_Default.nebula
ECHO B_Interestexp_acct Account;>> Acctschema_Default.nebula
ECHO B_Interestrev_acct Account;>> Acctschema_Default.nebula
ECHO B_Paymentselect_acct Account;>> Acctschema_Default.nebula
ECHO B_Revaluationgain_acct Account;>> Acctschema_Default.nebula
ECHO B_Revaluationloss_acct Account;>> Acctschema_Default.nebula
ECHO B_Settlementgain_acct Account;>> Acctschema_Default.nebula
ECHO B_Settlementloss_acct Account;>> Acctschema_Default.nebula
ECHO B_Unallocatedcash_acct Account;>> Acctschema_Default.nebula
ECHO B_Unidentified_acct Account;>> Acctschema_Default.nebula
ECHO Cb_Asset_acct Account;>> Acctschema_Default.nebula
ECHO Cb_Cashtransfer_acct Account;>> Acctschema_Default.nebula
ECHO Cb_Differences_acct Account;>> Acctschema_Default.nebula
ECHO Cb_Expense_acct Account;>> Acctschema_Default.nebula
ECHO Cb_Receipt_acct Account;>> Acctschema_Default.nebula
ECHO !Acctschema;>> Acctschema_Default.nebula
ECHO C_Prepayment_acct Account;>> Acctschema_Default.nebula
ECHO C_Receivable_acct Account;>> Acctschema_Default.nebula
ECHO C_Receivable_services_acct Account;>> Acctschema_Default.nebula
ECHO Ch_Expense_acct Account;>> Acctschema_Default.nebula
ECHO Ch_Revenue_acct Account;>> Acctschema_Default.nebula
ECHO Created Timestamp;>> Acctschema_Default.nebula
ECHO Createdby Date;>> Acctschema_Default.nebula
ECHO E_Expense_acct Account;>> Acctschema_Default.nebula
ECHO E_Prepayment_acct Account;>> Acctschema_Default.nebula
ECHO Isactive YesNo;>> Acctschema_Default.nebula
ECHO Notinvoicedreceipts_Acct Account;>> Acctschema_Default.nebula
ECHO Notinvoicedreceivables_Acct Account;>> Acctschema_Default.nebula
ECHO Notinvoicedrevenue_Acct Account;>> Acctschema_Default.nebula
ECHO Pj_Asset_acct Account;>> Acctschema_Default.nebula
ECHO Pj_Wip_acct Account;>> Acctschema_Default.nebula
ECHO P_Asset_acct Account;>> Acctschema_Default.nebula
ECHO P_Cogs_acct Account;>> Acctschema_Default.nebula
ECHO P_Costadjustment_acct Account;>> Acctschema_Default.nebula
ECHO P_Expense_acct Account;>> Acctschema_Default.nebula
ECHO P_Inventoryclearing_acct Account;>> Acctschema_Default.nebula
ECHO P_Invoicepricevariance_acct Account;>> Acctschema_Default.nebula
ECHO P_Purchasepricevariance_acct Account;>> Acctschema_Default.nebula
ECHO P_Revenue_acct Account;>> Acctschema_Default.nebula
ECHO P_Tradediscountgrant_acct Account;>> Acctschema_Default.nebula
ECHO P_Tradediscountrec_acct Account;>> Acctschema_Default.nebula
ECHO Paydiscount_Exp_acct Account;>> Acctschema_Default.nebula
ECHO Paydiscount_Rev_acct Account;>> Acctschema_Default.nebula
ECHO Processing YesNo;>> Acctschema_Default.nebula
ECHO Realizedgain_Acct Account;>> Acctschema_Default.nebula
ECHO Realizedloss_Acct Account;>> Acctschema_Default.nebula
ECHO T_Credit_acct Account;>> Acctschema_Default.nebula
ECHO T_Due_acct Account;>> Acctschema_Default.nebula
ECHO T_Expense_acct Account;>> Acctschema_Default.nebula
ECHO T_Liability_acct Account;>> Acctschema_Default.nebula
ECHO T_Receivables_acct Account;>> Acctschema_Default.nebula
ECHO Unearnedrevenue_Acct Account;>> Acctschema_Default.nebula
ECHO Unrealizedgain_Acct Account;>> Acctschema_Default.nebula
ECHO Unrealizedloss_Acct Account;>> Acctschema_Default.nebula
ECHO Updated Timestamp;>> Acctschema_Default.nebula
ECHO Updatedby Date;>> Acctschema_Default.nebula
ECHO V_Liability_acct Account;>> Acctschema_Default.nebula
ECHO V_Liability_services_acct Account;>> Acctschema_Default.nebula
ECHO V_Prepayment_acct Account;>> Acctschema_Default.nebula
ECHO Wo_Closeexpense_acct Account;>> Acctschema_Default.nebula
ECHO Wo_Materialoverhdvariance_acct Account;>> Acctschema_Default.nebula
ECHO Wo_Materialoverhd_acct Account;>> Acctschema_Default.nebula
ECHO Wo_Materialvariance_acct Account;>> Acctschema_Default.nebula
ECHO Wo_Material_acct Account;>> Acctschema_Default.nebula
ECHO Wo_Ospvariance_acct Account;>> Acctschema_Default.nebula
ECHO Wo_Osp_acct Account;>> Acctschema_Default.nebula
ECHO Wo_Resourceoverhdvariance_acct Account;>> Acctschema_Default.nebula
ECHO Wo_Resourceoverhd_acct Account;>> Acctschema_Default.nebula
ECHO Wo_Resourcevariance_acct Account;>> Acctschema_Default.nebula
ECHO Wo_Resource_acct Account;>> Acctschema_Default.nebula
ECHO W_Differences_acct Account;>> Acctschema_Default.nebula
ECHO W_Invactualadjust_acct Account;>> Acctschema_Default.nebula
ECHO W_Inventory_acct Account;>> Acctschema_Default.nebula
ECHO W_Revaluation_acct Account;>> Acctschema_Default.nebula
ECHO Withholding_Acct Account;>> Acctschema_Default.nebula
ECHO Writeoff_Acct Account;>> Acctschema_Default.nebula
ECHO };>> Acctschema_Default.nebula 
 ECHO @Package("Common or Core Functionality") type  Acctschema_Element{ >> Acctschema_Element.nebula
ECHO Column;>> Acctschema_Element.nebula
ECHO Org;>> Acctschema_Element.nebula
ECHO !ID;>> Acctschema_Element.nebula
ECHO Acctschema;>> Acctschema_Element.nebula
ECHO Activity;>> Acctschema_Element.nebula
ECHO Bpartner;>> Acctschema_Element.nebula
ECHO Campaign;>> Acctschema_Element.nebula
ECHO Elementvalue;>> Acctschema_Element.nebula
ECHO Element;>> Acctschema_Element.nebula
ECHO Location;>> Acctschema_Element.nebula
ECHO Project;>> Acctschema_Element.nebula
ECHO Salesregion;>> Acctschema_Element.nebula
ECHO Created Timestamp;>> Acctschema_Element.nebula
ECHO Createdby Date;>> Acctschema_Element.nebula
ECHO Elementtype Attr;>> Acctschema_Element.nebula
ECHO Isactive YesNo;>> Acctschema_Element.nebula
ECHO Isbalanced YesNo;>> Acctschema_Element.nebula
ECHO Ismandatory YesNo;>> Acctschema_Element.nebula
ECHO Product;>> Acctschema_Element.nebula
ECHO !Name;>> Acctschema_Element.nebula
ECHO Org;>> Acctschema_Element.nebula
ECHO SeqNo;>> Acctschema_Element.nebula
ECHO Updated Timestamp;>> Acctschema_Element.nebula
ECHO Updatedby Date;>> Acctschema_Element.nebula
ECHO };>> Acctschema_Element.nebula 
 ECHO @Package("Common or Core Functionality") type  Acctschema_Gl{ >> Acctschema_Gl.nebula
ECHO Org;>> Acctschema_Gl.nebula
ECHO !Acctschema;>> Acctschema_Gl.nebula
ECHO Commitmentoffset_Acct Account;>> Acctschema_Gl.nebula
ECHO Created Timestamp;>> Acctschema_Gl.nebula
ECHO Createdby Date;>> Acctschema_Gl.nebula
ECHO Currencybalancing_Acct Account;>> Acctschema_Gl.nebula
ECHO Incomesummary_Acct Account;>> Acctschema_Gl.nebula
ECHO Intercompanyduefrom_Acct Account;>> Acctschema_Gl.nebula
ECHO Intercompanydueto_Acct Account;>> Acctschema_Gl.nebula
ECHO Isactive YesNo;>> Acctschema_Gl.nebula
ECHO Ppvoffset_Acct Account;>> Acctschema_Gl.nebula
ECHO Retainedearning_Acct Account;>> Acctschema_Gl.nebula
ECHO Suspensebalancing_Acct Account;>> Acctschema_Gl.nebula
ECHO Suspenseerror_Acct Account;>> Acctschema_Gl.nebula
ECHO Updated Timestamp;>> Acctschema_Gl.nebula
ECHO Updatedby Date;>> Acctschema_Gl.nebula
ECHO Usecurrencybalancing YesNo;>> Acctschema_Gl.nebula
ECHO Usesuspensebalancing YesNo;>> Acctschema_Gl.nebula
ECHO Usesuspenseerror YesNo;>> Acctschema_Gl.nebula
ECHO };>> Acctschema_Gl.nebula 
 ECHO @Package("Common or Core Functionality") type  Activity{ >> Activity.nebula
ECHO Org;>> Activity.nebula
ECHO !ID;>> Activity.nebula
ECHO Created Timestamp;>> Activity.nebula
ECHO Createdby Date;>> Activity.nebula
ECHO Description;>> Activity.nebula
ECHO Help;>> Activity.nebula
ECHO Isactive YesNo;>> Activity.nebula
ECHO Issummary YesNo;>> Activity.nebula
ECHO !Name;>> Activity.nebula
ECHO Updated Timestamp;>> Activity.nebula
ECHO Updatedby Date;>> Activity.nebula
ECHO Value String;>> Activity.nebula
ECHO };>> Activity.nebula 
 ECHO @Package("Common or Core Functionality") type  Allocationhdr{ >> Allocationhdr.nebula
ECHO Orgtrx Org;>> Allocationhdr.nebula
ECHO Org;>> Allocationhdr.nebula
ECHO Approvalamt Amount;>> Allocationhdr.nebula
ECHO Activity;>> Allocationhdr.nebula
ECHO !ID;>> Allocationhdr.nebula
ECHO Currency;>> Allocationhdr.nebula
ECHO Created Timestamp;>> Allocationhdr.nebula
ECHO Createdby Date;>> Allocationhdr.nebula
ECHO Dateacct Date;>> Allocationhdr.nebula
ECHO Datetrx Date;>> Allocationhdr.nebula
ECHO Description;>> Allocationhdr.nebula
ECHO Docaction Attr;>> Allocationhdr.nebula
ECHO Docstatus Attr;>> Allocationhdr.nebula
ECHO Documentno SeqNo;>> Allocationhdr.nebula
ECHO Isactive YesNo;>> Allocationhdr.nebula
ECHO Isapproved YesNo;>> Allocationhdr.nebula
ECHO Ismanual YesNo;>> Allocationhdr.nebula
ECHO Posted YesNo;>> Allocationhdr.nebula
ECHO Processed YesNo;>> Allocationhdr.nebula
ECHO Processing YesNo;>> Allocationhdr.nebula
ECHO Updated Timestamp;>> Allocationhdr.nebula
ECHO Updatedby Date;>> Allocationhdr.nebula
ECHO };>> Allocationhdr.nebula 
 ECHO @Package("Common or Core Functionality") type  Allocationline{ >> Allocationline.nebula
ECHO Orgtrx Org;>> Allocationline.nebula
ECHO Org;>> Allocationline.nebula
ECHO Amount;>> Allocationline.nebula
ECHO Activity;>> Allocationline.nebula
ECHO Allocationhdr;>> Allocationline.nebula
ECHO !ID;>> Allocationline.nebula
ECHO Bpartner;>> Allocationline.nebula
ECHO Cashline;>> Allocationline.nebula
ECHO Invoice;>> Allocationline.nebula
ECHO Order;>> Allocationline.nebula
ECHO Payment;>> Allocationline.nebula
ECHO Created Timestamp;>> Allocationline.nebula
ECHO Createdby Date;>> Allocationline.nebula
ECHO Datetrx Date;>> Allocationline.nebula
ECHO Discountamt Amount;>> Allocationline.nebula
ECHO Isactive YesNo;>> Allocationline.nebula
ECHO Ismanual YesNo;>> Allocationline.nebula
ECHO Overunderamt Amount;>> Allocationline.nebula
ECHO Updated Timestamp;>> Allocationline.nebula
ECHO Updatedby Date;>> Allocationline.nebula
ECHO Writeoffamt Amount;>> Allocationline.nebula
ECHO };>> Allocationline.nebula 
 ECHO @Package("Common or Core Functionality") type  Bank{ >> Bank.nebula
ECHO Org;>> Bank.nebula
ECHO Bankverificationclass Name;>> Bank.nebula
ECHO !ID;>> Bank.nebula
ECHO Location;>> Bank.nebula
ECHO Created Timestamp;>> Bank.nebula
ECHO Createdby Date;>> Bank.nebula
ECHO Description;>> Bank.nebula
ECHO Isactive YesNo;>> Bank.nebula
ECHO Isownbank YesNo;>> Bank.nebula
ECHO !Name;>> Bank.nebula
ECHO Routingno Code;>> Bank.nebula
ECHO SWIFTCode;>> Bank.nebula
ECHO Updated Timestamp;>> Bank.nebula
ECHO Updatedby Date;>> Bank.nebula
ECHO };>> Bank.nebula 
 ECHO @Package("Common or Core Functionality") type  Bankaccount{ >> Bankaccount.nebula
ECHO Org;>> Bankaccount.nebula
ECHO AccountNo;>> Bankaccount.nebula
ECHO BBAN;>> Bankaccount.nebula
ECHO Bankaccounttype YesNo;>> Bankaccount.nebula
ECHO !ID;>> Bankaccount.nebula
ECHO Bank;>> Bankaccount.nebula
ECHO Currency;>> Bankaccount.nebula
ECHO Created Timestamp;>> Bankaccount.nebula
ECHO Createdby Date;>> Bankaccount.nebula
ECHO Creditlimit Number;>> Bankaccount.nebula
ECHO Currentbalance Amount;>> Bankaccount.nebula
ECHO Description;>> Bankaccount.nebula
ECHO IBAN;>> Bankaccount.nebula
ECHO Isactive YesNo;>> Bankaccount.nebula
ECHO Isdefault YesNo;>> Bankaccount.nebula
ECHO Updated Timestamp;>> Bankaccount.nebula
ECHO Updatedby Date;>> Bankaccount.nebula
ECHO };>> Bankaccount.nebula 
 ECHO @Package("Common or Core Functionality") type  Bankaccount_Acct{ >> Bankaccount_Acct.nebula
ECHO Org;>> Bankaccount_Acct.nebula
ECHO B_Asset_acct Account;>> Bankaccount_Acct.nebula
ECHO B_Expense_acct Account;>> Bankaccount_Acct.nebula
ECHO B_Intransit_acct Account;>> Bankaccount_Acct.nebula
ECHO B_Interestexp_acct Account;>> Bankaccount_Acct.nebula
ECHO B_Interestrev_acct Account;>> Bankaccount_Acct.nebula
ECHO B_Paymentselect_acct Account;>> Bankaccount_Acct.nebula
ECHO B_Revaluationgain_acct Account;>> Bankaccount_Acct.nebula
ECHO B_Revaluationloss_acct Account;>> Bankaccount_Acct.nebula
ECHO B_Settlementgain_acct Account;>> Bankaccount_Acct.nebula
ECHO B_Settlementloss_acct Account;>> Bankaccount_Acct.nebula
ECHO B_Unallocatedcash_acct Account;>> Bankaccount_Acct.nebula
ECHO B_Unidentified_acct Account;>> Bankaccount_Acct.nebula
ECHO !Acctschema;>> Bankaccount_Acct.nebula
ECHO !Bankaccount;>> Bankaccount_Acct.nebula
ECHO Created Timestamp;>> Bankaccount_Acct.nebula
ECHO Createdby Date;>> Bankaccount_Acct.nebula
ECHO Isactive YesNo;>> Bankaccount_Acct.nebula
ECHO Updated Timestamp;>> Bankaccount_Acct.nebula
ECHO Updatedby Date;>> Bankaccount_Acct.nebula
ECHO };>> Bankaccount_Acct.nebula 
 ECHO @Package("Common or Core Functionality") type  Bankaccountdoc{ >> Bankaccountdoc.nebula
ECHO Org;>> Bankaccountdoc.nebula
ECHO !ID;>> Bankaccountdoc.nebula
ECHO Bankaccount;>> Bankaccountdoc.nebula
ECHO Check_Printformat Printformat;>> Bankaccountdoc.nebula
ECHO Created Timestamp;>> Bankaccountdoc.nebula
ECHO Createdby Date;>> Bankaccountdoc.nebula
ECHO Currentnext Number;>> Bankaccountdoc.nebula
ECHO Description;>> Bankaccountdoc.nebula
ECHO Isactive YesNo;>> Bankaccountdoc.nebula
ECHO !Name;>> Bankaccountdoc.nebula
ECHO Paymentrule YesNo;>> Bankaccountdoc.nebula
ECHO Updated Timestamp;>> Bankaccountdoc.nebula
ECHO Updatedby Date;>> Bankaccountdoc.nebula
ECHO };>> Bankaccountdoc.nebula 
 ECHO @Package("Common or Core Functionality") type  Bankstatement{ >> Bankstatement.nebula
ECHO Org;>> Bankstatement.nebula
ECHO Beginningbalance Number;>> Bankstatement.nebula
ECHO Bankaccount;>> Bankstatement.nebula
ECHO !ID;>> Bankstatement.nebula
ECHO Createfrom YesNo;>> Bankstatement.nebula
ECHO Created Timestamp;>> Bankstatement.nebula
ECHO Createdby Date;>> Bankstatement.nebula
ECHO Description;>> Bankstatement.nebula
ECHO Docaction Attr;>> Bankstatement.nebula
ECHO Docstatus Attr;>> Bankstatement.nebula
ECHO Eftstatementdate Date;>> Bankstatement.nebula
ECHO Eftstatementreference String;>> Bankstatement.nebula
ECHO Endingbalance Number;>> Bankstatement.nebula
ECHO Isactive YesNo;>> Bankstatement.nebula
ECHO Isapproved YesNo;>> Bankstatement.nebula
ECHO Ismanual YesNo;>> Bankstatement.nebula
ECHO Matchstatement YesNo;>> Bankstatement.nebula
ECHO !Name;>> Bankstatement.nebula
ECHO Posted YesNo;>> Bankstatement.nebula
ECHO Processed YesNo;>> Bankstatement.nebula
ECHO Processing YesNo;>> Bankstatement.nebula
ECHO Statementdate Date;>> Bankstatement.nebula
ECHO Statementdifference Number;>> Bankstatement.nebula
ECHO Updated Timestamp;>> Bankstatement.nebula
ECHO Updatedby Date;>> Bankstatement.nebula
ECHO };>> Bankstatement.nebula 
 ECHO @Package("Common or Core Functionality") type  Bankstatementline{ >> Bankstatementline.nebula
ECHO Org;>> Bankstatementline.nebula
ECHO Bpartner;>> Bankstatementline.nebula
ECHO !ID;>> Bankstatementline.nebula
ECHO Bankstatement;>> Bankstatementline.nebula
ECHO Charge;>> Bankstatementline.nebula
ECHO Currency;>> Bankstatementline.nebula
ECHO Invoice;>> Bankstatementline.nebula
ECHO Payment;>> Bankstatementline.nebula
ECHO Chargeamt Amount;>> Bankstatementline.nebula
ECHO Createpayment YesNo;>> Bankstatementline.nebula
ECHO Created Timestamp;>> Bankstatementline.nebula
ECHO Createdby Date;>> Bankstatementline.nebula
ECHO Dateacct Date;>> Bankstatementline.nebula
ECHO Description;>> Bankstatementline.nebula
ECHO Eftamt Amount;>> Bankstatementline.nebula
ECHO Eftcheckno Code;>> Bankstatementline.nebula
ECHO Eftcurrency String;>> Bankstatementline.nebula
ECHO Eftmemo Note;>> Bankstatementline.nebula
ECHO Eftpayee String;>> Bankstatementline.nebula
ECHO Eftpayeeaccount String;>> Bankstatementline.nebula
ECHO Eftreference String;>> Bankstatementline.nebula
ECHO Eftstatementlinedate Date;>> Bankstatementline.nebula
ECHO Efttrxid String;>> Bankstatementline.nebula
ECHO Efttrxtype Attr;>> Bankstatementline.nebula
ECHO Eftvalutadate Date;>> Bankstatementline.nebula
ECHO Interestamt Amount;>> Bankstatementline.nebula
ECHO Isactive YesNo;>> Bankstatementline.nebula
ECHO Ismanual YesNo;>> Bankstatementline.nebula
ECHO Isreversal YesNo;>> Bankstatementline.nebula
ECHO Line Number;>> Bankstatementline.nebula
ECHO Matchstatement YesNo;>> Bankstatementline.nebula
ECHO Memo Note;>> Bankstatementline.nebula
ECHO Processed YesNo;>> Bankstatementline.nebula
ECHO Referenceno Code;>> Bankstatementline.nebula
ECHO Statementlinedate Date;>> Bankstatementline.nebula
ECHO Stmtamt Amount;>> Bankstatementline.nebula
ECHO Trxamt Amount;>> Bankstatementline.nebula
ECHO Updated Timestamp;>> Bankstatementline.nebula
ECHO Updatedby Date;>> Bankstatementline.nebula
ECHO Valutadate Date;>> Bankstatementline.nebula
ECHO };>> Bankstatementline.nebula 
 ECHO @Package("Common or Core Functionality") type  Bankstatementloader{ >> Bankstatementloader.nebula
ECHO Org;>> Bankstatementloader.nebula
ECHO AccountNo;>> Bankstatementloader.nebula
ECHO Branchid String;>> Bankstatementloader.nebula
ECHO Bankaccount;>> Bankstatementloader.nebula
ECHO !ID;>> Bankstatementloader.nebula
ECHO Created Timestamp;>> Bankstatementloader.nebula
ECHO Createdby Date;>> Bankstatementloader.nebula
ECHO Dateformat String;>> Bankstatementloader.nebula
ECHO Datelastrun Date;>> Bankstatementloader.nebula
ECHO Description;>> Bankstatementloader.nebula
ECHO Filename Name;>> Bankstatementloader.nebula
ECHO Financialinstitutionid String;>> Bankstatementloader.nebula
ECHO Hostaddress String;>> Bankstatementloader.nebula
ECHO Hostport Long;>> Bankstatementloader.nebula
ECHO Isactive YesNo;>> Bankstatementloader.nebula
ECHO !Name;>> Bankstatementloader.nebula
ECHO PIN;>> Bankstatementloader.nebula
ECHO Password String;>> Bankstatementloader.nebula
ECHO Proxyaddress String;>> Bankstatementloader.nebula
ECHO Proxylogon String;>> Bankstatementloader.nebula
ECHO Proxypassword String;>> Bankstatementloader.nebula
ECHO Proxyport String;>> Bankstatementloader.nebula
ECHO Stmtloaderclass String;>> Bankstatementloader.nebula
ECHO Updated Timestamp;>> Bankstatementloader.nebula
ECHO Updatedby Date;>> Bankstatementloader.nebula
ECHO Userid Name;>> Bankstatementloader.nebula
ECHO };>> Bankstatementloader.nebula 
 ECHO @Package("Common or Core Functionality") type  Bankstatementmatcher{ >> Bankstatementmatcher.nebula
ECHO Org;>> Bankstatementmatcher.nebula
ECHO !ID;>> Bankstatementmatcher.nebula
ECHO Classname Name;>> Bankstatementmatcher.nebula
ECHO Created Timestamp;>> Bankstatementmatcher.nebula
ECHO Createdby Date;>> Bankstatementmatcher.nebula
ECHO Description;>> Bankstatementmatcher.nebula
ECHO Isactive YesNo;>> Bankstatementmatcher.nebula
ECHO !Name;>> Bankstatementmatcher.nebula
ECHO SeqNo;>> Bankstatementmatcher.nebula
ECHO Updated Timestamp;>> Bankstatementmatcher.nebula
ECHO Updatedby Date;>> Bankstatementmatcher.nebula
ECHO };>> Bankstatementmatcher.nebula 
 ECHO @Package("Common or Core Functionality") type  Bp_Bankaccount{ >> Bp_Bankaccount.nebula
ECHO Org;>> Bp_Bankaccount.nebula
ECHO User;>> Bp_Bankaccount.nebula
ECHO A_City Name;>> Bp_Bankaccount.nebula
ECHO A_Country Name;>> Bp_Bankaccount.nebula
ECHO A_Email Email;>> Bp_Bankaccount.nebula
ECHO Ident_Dl String;>> Bp_Bankaccount.nebula
ECHO Ident_Ssn SSN;>> Bp_Bankaccount.nebula
ECHO !A_Name Name;>> Bp_Bankaccount.nebula
ECHO A_State Name;>> Bp_Bankaccount.nebula
ECHO A_Street Name;>> Bp_Bankaccount.nebula
ECHO A_Zip Zip;>> Bp_Bankaccount.nebula
ECHO AccountNo;>> Bp_Bankaccount.nebula
ECHO BBAN;>> Bp_Bankaccount.nebula
ECHO Bpbankacctuse YesNo;>> Bp_Bankaccount.nebula
ECHO Bankaccounttype YesNo;>> Bp_Bankaccount.nebula
ECHO !ID;>> Bp_Bankaccount.nebula
ECHO Bpartner;>> Bp_Bankaccount.nebula
ECHO Bank;>> Bp_Bankaccount.nebula
ECHO Created Timestamp;>> Bp_Bankaccount.nebula
ECHO Createdby Date;>> Bp_Bankaccount.nebula
ECHO Creditcardexpmm Number;>> Bp_Bankaccount.nebula
ECHO Creditcardexpyy Number;>> Bp_Bankaccount.nebula
ECHO Creditcardnumber Code;>> Bp_Bankaccount.nebula
ECHO Creditcardtype YesNo;>> Bp_Bankaccount.nebula
ECHO IBAN;>> Bp_Bankaccount.nebula
ECHO Isach YesNo;>> Bp_Bankaccount.nebula
ECHO Isactive YesNo;>> Bp_Bankaccount.nebula
ECHO R_Avsaddr YesNo;>> Bp_Bankaccount.nebula
ECHO R_Avszip YesNo;>> Bp_Bankaccount.nebula
ECHO Routingno Code;>> Bp_Bankaccount.nebula
ECHO Updated Timestamp;>> Bp_Bankaccount.nebula
ECHO Updatedby Date;>> Bp_Bankaccount.nebula
ECHO };>> Bp_Bankaccount.nebula 
 ECHO @Package("Common or Core Functionality") type  Bp_Customer_acct{ >> Bp_Customer_acct.nebula
ECHO Org;>> Bp_Customer_acct.nebula
ECHO !Acctschema;>> Bp_Customer_acct.nebula
ECHO !Bpartner;>> Bp_Customer_acct.nebula
ECHO C_Prepayment_acct Account;>> Bp_Customer_acct.nebula
ECHO C_Receivable_acct Account;>> Bp_Customer_acct.nebula
ECHO C_Receivable_services_acct Account;>> Bp_Customer_acct.nebula
ECHO Created Timestamp;>> Bp_Customer_acct.nebula
ECHO Createdby Date;>> Bp_Customer_acct.nebula
ECHO Isactive YesNo;>> Bp_Customer_acct.nebula
ECHO Updated Timestamp;>> Bp_Customer_acct.nebula
ECHO Updatedby Date;>> Bp_Customer_acct.nebula
ECHO };>> Bp_Customer_acct.nebula 
 ECHO @Package("Common or Core Functionality") type  Bp_Employee_acct{ >> Bp_Employee_acct.nebula
ECHO Org;>> Bp_Employee_acct.nebula
ECHO !Acctschema;>> Bp_Employee_acct.nebula
ECHO !Bpartner;>> Bp_Employee_acct.nebula
ECHO Created Timestamp;>> Bp_Employee_acct.nebula
ECHO Createdby Date;>> Bp_Employee_acct.nebula
ECHO E_Expense_acct Account;>> Bp_Employee_acct.nebula
ECHO E_Prepayment_acct Account;>> Bp_Employee_acct.nebula
ECHO Isactive YesNo;>> Bp_Employee_acct.nebula
ECHO Updated Timestamp;>> Bp_Employee_acct.nebula
ECHO Updatedby Date;>> Bp_Employee_acct.nebula
ECHO };>> Bp_Employee_acct.nebula 
 ECHO @Package("Common or Core Functionality") type  Bp_Group{ >> Bp_Group.nebula
ECHO Org;>> Bp_Group.nebula
ECHO Printcolor;>> Bp_Group.nebula
ECHO !ID;>> Bp_Group.nebula
ECHO Consolidationreference;>> Bp_Group.nebula
ECHO Dunning;>> Bp_Group.nebula
ECHO Created Timestamp;>> Bp_Group.nebula
ECHO Createdby Date;>> Bp_Group.nebula
ECHO Creditwatchpercent Number;>> Bp_Group.nebula
ECHO Description;>> Bp_Group.nebula
ECHO Isactive YesNo;>> Bp_Group.nebula
ECHO Isconfidentialinfo YesNo;>> Bp_Group.nebula
ECHO Isdefault YesNo;>> Bp_Group.nebula
ECHO Discountschema;>> Bp_Group.nebula
ECHO Pricelist;>> Bp_Group.nebula
ECHO Returnpolicy;>> Bp_Group.nebula
ECHO !Name;>> Bp_Group.nebula
ECHO Po_Discountschema Discountschema;>> Bp_Group.nebula
ECHO Po_Pricelist Pricelist;>> Bp_Group.nebula
ECHO Po_Returnpolicy Returnpolicy;>> Bp_Group.nebula
ECHO Pricematchtolerance Price;>> Bp_Group.nebula
ECHO Prioritybase YesNo;>> Bp_Group.nebula
ECHO Updated Timestamp;>> Bp_Group.nebula
ECHO Updatedby Date;>> Bp_Group.nebula
ECHO Value String;>> Bp_Group.nebula
ECHO };>> Bp_Group.nebula 
 ECHO @Package("Common or Core Functionality") type  Bp_Group_acct{ >> Bp_Group_acct.nebula
ECHO Org;>> Bp_Group_acct.nebula
ECHO !Acctschema;>> Bp_Group_acct.nebula
ECHO !Bp_Group;>> Bp_Group_acct.nebula
ECHO C_Prepayment_acct Account;>> Bp_Group_acct.nebula
ECHO C_Receivable_acct Account;>> Bp_Group_acct.nebula
ECHO C_Receivable_services_acct Account;>> Bp_Group_acct.nebula
ECHO Created Timestamp;>> Bp_Group_acct.nebula
ECHO Createdby Date;>> Bp_Group_acct.nebula
ECHO Isactive YesNo;>> Bp_Group_acct.nebula
ECHO Notinvoicedreceipts_Acct Account;>> Bp_Group_acct.nebula
ECHO Notinvoicedreceivables_Acct Account;>> Bp_Group_acct.nebula
ECHO Notinvoicedrevenue_Acct Account;>> Bp_Group_acct.nebula
ECHO Paydiscount_Exp_acct Account;>> Bp_Group_acct.nebula
ECHO Paydiscount_Rev_acct Account;>> Bp_Group_acct.nebula
ECHO Processing YesNo;>> Bp_Group_acct.nebula
ECHO Unearnedrevenue_Acct Account;>> Bp_Group_acct.nebula
ECHO Updated Timestamp;>> Bp_Group_acct.nebula
ECHO Updatedby Date;>> Bp_Group_acct.nebula
ECHO V_Liability_acct Account;>> Bp_Group_acct.nebula
ECHO V_Liability_services_acct Account;>> Bp_Group_acct.nebula
ECHO V_Prepayment_acct Account;>> Bp_Group_acct.nebula
ECHO Writeoff_Acct Account;>> Bp_Group_acct.nebula
ECHO };>> Bp_Group_acct.nebula 
 ECHO @Package("Common or Core Functionality") type  Bp_Relation{ >> Bp_Relation.nebula
ECHO Org;>> Bp_Relation.nebula
ECHO !ID;>> Bp_Relation.nebula
ECHO Bpartnerrelation Bpartner;>> Bp_Relation.nebula
ECHO Bpartnerrelation_Location Bpartner_Location;>> Bp_Relation.nebula
ECHO Bpartner;>> Bp_Relation.nebula
ECHO Bpartner_Location;>> Bp_Relation.nebula
ECHO Created Timestamp;>> Bp_Relation.nebula
ECHO Createdby Date;>> Bp_Relation.nebula
ECHO Description;>> Bp_Relation.nebula
ECHO Isactive YesNo;>> Bp_Relation.nebula
ECHO Isbillto YesNo;>> Bp_Relation.nebula
ECHO Ispayfrom YesNo;>> Bp_Relation.nebula
ECHO Isremitto YesNo;>> Bp_Relation.nebula
ECHO Isshipto YesNo;>> Bp_Relation.nebula
ECHO !Name;>> Bp_Relation.nebula
ECHO Updated Timestamp;>> Bp_Relation.nebula
ECHO Updatedby Date;>> Bp_Relation.nebula
ECHO };>> Bp_Relation.nebula 
 ECHO @Package("Common or Core Functionality") type  Bp_Size{ >> Bp_Size.nebula
ECHO Org;>> Bp_Size.nebula
ECHO !ID;>> Bp_Size.nebula
ECHO Created Timestamp;>> Bp_Size.nebula
ECHO Createdby Date;>> Bp_Size.nebula
ECHO Description;>> Bp_Size.nebula
ECHO Help;>> Bp_Size.nebula
ECHO Isactive YesNo;>> Bp_Size.nebula
ECHO !Name;>> Bp_Size.nebula
ECHO Updated Timestamp;>> Bp_Size.nebula
ECHO Updatedby Date;>> Bp_Size.nebula
ECHO Value String;>> Bp_Size.nebula
ECHO };>> Bp_Size.nebula 
 ECHO @Package("Common or Core Functionality") type  Bp_Status{ >> Bp_Status.nebula
ECHO Org;>> Bp_Status.nebula
ECHO !ID;>> Bp_Status.nebula
ECHO Created Timestamp;>> Bp_Status.nebula
ECHO Createdby Date;>> Bp_Status.nebula
ECHO Description;>> Bp_Status.nebula
ECHO Help;>> Bp_Status.nebula
ECHO Isactive YesNo;>> Bp_Status.nebula
ECHO !Name;>> Bp_Status.nebula
ECHO Updated Timestamp;>> Bp_Status.nebula
ECHO Updatedby Date;>> Bp_Status.nebula
ECHO Value String;>> Bp_Status.nebula
ECHO };>> Bp_Status.nebula 
 ECHO @Package("Common or Core Functionality") type  Bp_Vendor_acct{ >> Bp_Vendor_acct.nebula
ECHO Org;>> Bp_Vendor_acct.nebula
ECHO !Acctschema;>> Bp_Vendor_acct.nebula
ECHO !Bpartner;>> Bp_Vendor_acct.nebula
ECHO Created Timestamp;>> Bp_Vendor_acct.nebula
ECHO Createdby Date;>> Bp_Vendor_acct.nebula
ECHO Isactive YesNo;>> Bp_Vendor_acct.nebula
ECHO Updated Timestamp;>> Bp_Vendor_acct.nebula
ECHO Updatedby Date;>> Bp_Vendor_acct.nebula
ECHO V_Liability_acct Account;>> Bp_Vendor_acct.nebula
ECHO V_Liability_services_acct Account;>> Bp_Vendor_acct.nebula
ECHO V_Prepayment_acct Account;>> Bp_Vendor_acct.nebula
ECHO };>> Bp_Vendor_acct.nebula 
 ECHO @Package("Common or Core Functionality") type  Bp_Withholding{ >> Bp_Withholding.nebula
ECHO Org;>> Bp_Withholding.nebula
ECHO !Bpartner;>> Bp_Withholding.nebula
ECHO !Withholding;>> Bp_Withholding.nebula
ECHO Created Timestamp;>> Bp_Withholding.nebula
ECHO Createdby Date;>> Bp_Withholding.nebula
ECHO Exemptreason Attr;>> Bp_Withholding.nebula
ECHO Isactive YesNo;>> Bp_Withholding.nebula
ECHO Ismandatorywithholding YesNo;>> Bp_Withholding.nebula
ECHO Istemporaryexempt YesNo;>> Bp_Withholding.nebula
ECHO Updated Timestamp;>> Bp_Withholding.nebula
ECHO Updatedby Date;>> Bp_Withholding.nebula
ECHO };>> Bp_Withholding.nebula 
 ECHO @Package("Common or Core Functionality") type  Bpartner{ >> Bpartner.nebula
ECHO Ad_Language Language;>> Bpartner.nebula
ECHO Orgbp Org;>> Bpartner.nebula
ECHO Org;>> Bpartner.nebula
ECHO Acqusitioncost Number;>> Bpartner.nebula
ECHO Actuallifetimevalue Number;>> Bpartner.nebula

ECHO Bp_Group;>> Bpartner.nebula
ECHO Bp_Size;>> Bpartner.nebula
ECHO Bp_Status;>> Bpartner.nebula
ECHO !ID;>> Bpartner.nebula
ECHO Consolidationreference;>> Bpartner.nebula
ECHO Dunning;>> Bpartner.nebula
ECHO Greeting;>> Bpartner.nebula
ECHO Industrycode;>> Bpartner.nebula
ECHO Invoiceschedule;>> Bpartner.nebula
ECHO Paymentterm;>> Bpartner.nebula
ECHO Created Timestamp;>> Bpartner.nebula
ECHO Createdby Date;>> Bpartner.nebula
ECHO DUNS;>> Bpartner.nebula
ECHO Deliveryrule YesNo;>> Bpartner.nebula
ECHO Deliveryviarule YesNo;>> Bpartner.nebula
ECHO Description;>> Bpartner.nebula
ECHO Documentcopies Count;>> Bpartner.nebula
ECHO Firstsale YesNo;>> Bpartner.nebula
ECHO Flatdiscount Number;>> Bpartner.nebula
ECHO Freightcostrule YesNo;>> Bpartner.nebula
ECHO Invoicerule YesNo;>> Bpartner.nebula
ECHO Invoice_Printformat Printformat;>> Bpartner.nebula
ECHO Isactive YesNo;>> Bpartner.nebula
ECHO Iscustomer YesNo;>> Bpartner.nebula
ECHO Isdiscountprinted YesNo;>> Bpartner.nebula
ECHO Isemployee YesNo;>> Bpartner.nebula
ECHO Isonetime YesNo;>> Bpartner.nebula
ECHO Isprospect YesNo;>> Bpartner.nebula
ECHO Issalesrep YesNo;>> Bpartner.nebula
ECHO Issummary YesNo;>> Bpartner.nebula
ECHO Istaxexempt YesNo;>> Bpartner.nebula
ECHO Isvendor YesNo;>> Bpartner.nebula
ECHO Discountschema;>> Bpartner.nebula
ECHO Pricelist;>> Bpartner.nebula
ECHO Returnpolicy;>> Bpartner.nebula
ECHO NAICS;>> Bpartner.nebula
ECHO !Name;>> Bpartner.nebula
ECHO Name2 Name;>> Bpartner.nebula
ECHO Numberemployees Count;>> Bpartner.nebula
ECHO Order_Printformat Printformat;>> Bpartner.nebula
ECHO Poreference String;>> Bpartner.nebula
ECHO Po_Discountschema Discountschema;>> Bpartner.nebula
ECHO Po_Paymentterm Paymentterm;>> Bpartner.nebula
ECHO Po_Pricelist Pricelist;>> Bpartner.nebula
ECHO Po_Returnpolicy Returnpolicy;>> Bpartner.nebula
ECHO Paymentrule YesNo;>> Bpartner.nebula
ECHO Paymentrulepo YesNo;>> Bpartner.nebula
ECHO Potentiallifetimevalue Number;>> Bpartner.nebula
ECHO Rating YesNo;>> Bpartner.nebula
ECHO Referenceno Code;>> Bpartner.nebula
ECHO Socreditstatus YesNo;>> Bpartner.nebula
ECHO So_Creditlimit Number;>> Bpartner.nebula
ECHO So_Creditused Number;>> Bpartner.nebula
ECHO So_Description Description;>> Bpartner.nebula
ECHO Salesrep User;>> Bpartner.nebula
ECHO Salesvolume Number;>> Bpartner.nebula
ECHO Sendemail YesNo;>> Bpartner.nebula
ECHO Shareofcustomer Number;>> Bpartner.nebula
ECHO Shelflifeminpct Number;>> Bpartner.nebula
ECHO Taxid ID;>> Bpartner.nebula
ECHO Totalopenbalance Number;>> Bpartner.nebula
ECHO URL;>> Bpartner.nebula
ECHO Updated Timestamp;>> Bpartner.nebula
ECHO Updatedby Date;>> Bpartner.nebula
ECHO Value String;>> Bpartner.nebula
ECHO };>> Bpartner.nebula 
 ECHO @Package("Common or Core Functionality") type  Bpartner_Location{ >> Bpartner_Location.nebula
ECHO Org;>> Bpartner_Location.nebula
ECHO Bpartner;>> Bpartner_Location.nebula
ECHO !ID;>> Bpartner_Location.nebula
ECHO Location;>> Bpartner_Location.nebula
ECHO Salesregion;>> Bpartner_Location.nebula
ECHO Created Timestamp;>> Bpartner_Location.nebula
ECHO Createdby Date;>> Bpartner_Location.nebula
ECHO Fax;>> Bpartner_Location.nebula
ECHO Isdn Code;>> Bpartner_Location.nebula
ECHO Isactive YesNo;>> Bpartner_Location.nebula
ECHO Isbillto YesNo;>> Bpartner_Location.nebula
ECHO Ispayfrom YesNo;>> Bpartner_Location.nebula
ECHO Isremitto YesNo;>> Bpartner_Location.nebula
ECHO Isshipto YesNo;>> Bpartner_Location.nebula
ECHO !Name;>> Bpartner_Location.nebula
ECHO Phone PhoneNumber;>> Bpartner_Location.nebula
ECHO Phone2 PhoneNumber;>> Bpartner_Location.nebula
ECHO Updated Timestamp;>> Bpartner_Location.nebula
ECHO Updatedby Date;>> Bpartner_Location.nebula
ECHO };>> Bpartner_Location.nebula 
 ECHO @Package("Common or Core Functionality") type  Bpartner_Product{ >> Bpartner_Product.nebula
ECHO Org;>> Bpartner_Product.nebula
ECHO !Bpartner;>> Bpartner_Product.nebula
ECHO Created Timestamp;>> Bpartner_Product.nebula
ECHO Createdby Date;>> Bpartner_Product.nebula
ECHO Description;>> Bpartner_Product.nebula
ECHO Isactive YesNo;>> Bpartner_Product.nebula
ECHO !Product;>> Bpartner_Product.nebula
ECHO Manufacturer String;>> Bpartner_Product.nebula
ECHO Qualityrating Number;>> Bpartner_Product.nebula
ECHO Shelflifemindays Count;>> Bpartner_Product.nebula
ECHO Shelflifeminpct Number;>> Bpartner_Product.nebula
ECHO Updated Timestamp;>> Bpartner_Product.nebula
ECHO Updatedby Date;>> Bpartner_Product.nebula
ECHO Vendorcategory String;>> Bpartner_Product.nebula
ECHO Vendorproductno Code;>> Bpartner_Product.nebula
ECHO };>> Bpartner_Product.nebula 
 ECHO @Package("Common or Core Functionality") type  Calendar{ >> Calendar.nebula
ECHO Org;>> Calendar.nebula
ECHO !ID;>> Calendar.nebula
ECHO Created Timestamp;>> Calendar.nebula
ECHO Createdby Date;>> Calendar.nebula
ECHO Description;>> Calendar.nebula
ECHO Isactive YesNo;>> Calendar.nebula
ECHO !Name;>> Calendar.nebula
ECHO Updated Timestamp;>> Calendar.nebula
ECHO Updatedby Date;>> Calendar.nebula
ECHO };>> Calendar.nebula 
 ECHO @Package("Common or Core Functionality") type  Campaign{ >> Campaign.nebula
ECHO Org;>> Campaign.nebula
ECHO !ID;>> Campaign.nebula
ECHO Channel;>> Campaign.nebula
ECHO Costs Number;>> Campaign.nebula
ECHO Created Timestamp;>> Campaign.nebula
ECHO Createdby Date;>> Campaign.nebula
ECHO Description;>> Campaign.nebula
ECHO Enddate Date;>> Campaign.nebula
ECHO Isactive YesNo;>> Campaign.nebula
ECHO Issummary YesNo;>> Campaign.nebula
ECHO !Name;>> Campaign.nebula
ECHO Startdate Date;>> Campaign.nebula
ECHO Updated Timestamp;>> Campaign.nebula
ECHO Updatedby Date;>> Campaign.nebula
ECHO Value String;>> Campaign.nebula
ECHO };>> Campaign.nebula 
 ECHO @Package("Common or Core Functionality") type  Cash{ >> Cash.nebula
ECHO Orgtrx Org;>> Cash.nebula
ECHO Org;>> Cash.nebula
ECHO Beginningbalance Number;>> Cash.nebula
ECHO Activity;>> Cash.nebula
ECHO Campaign;>> Cash.nebula
ECHO Cashbook;>> Cash.nebula
ECHO !ID;>> Cash.nebula
ECHO Project;>> Cash.nebula
ECHO Created Timestamp;>> Cash.nebula
ECHO Createdby Date;>> Cash.nebula
ECHO Dateacct Date;>> Cash.nebula
ECHO Description;>> Cash.nebula
ECHO Docaction Attr;>> Cash.nebula
ECHO Docstatus Attr;>> Cash.nebula
ECHO Endingbalance Number;>> Cash.nebula
ECHO Isactive YesNo;>> Cash.nebula
ECHO Isapproved YesNo;>> Cash.nebula
ECHO !Name;>> Cash.nebula
ECHO Posted YesNo;>> Cash.nebula
ECHO Processed YesNo;>> Cash.nebula
ECHO Processing YesNo;>> Cash.nebula
ECHO Statementdate Date;>> Cash.nebula
ECHO Statementdifference Number;>> Cash.nebula
ECHO Updated Timestamp;>> Cash.nebula
ECHO Updatedby Date;>> Cash.nebula
ECHO User1 Elementvalue;>> Cash.nebula
ECHO User2 Elementvalue;>> Cash.nebula
ECHO };>> Cash.nebula 
 ECHO @Package("Common or Core Functionality") type  Cashbook{ >> Cashbook.nebula
ECHO Org;>> Cashbook.nebula
ECHO !ID;>> Cashbook.nebula
ECHO Currency;>> Cashbook.nebula
ECHO Created Timestamp;>> Cashbook.nebula
ECHO Createdby Date;>> Cashbook.nebula
ECHO Description;>> Cashbook.nebula
ECHO Isactive YesNo;>> Cashbook.nebula
ECHO Isdefault YesNo;>> Cashbook.nebula
ECHO !Name;>> Cashbook.nebula
ECHO Updated Timestamp;>> Cashbook.nebula
ECHO Updatedby Date;>> Cashbook.nebula
ECHO };>> Cashbook.nebula 
 ECHO @Package("Common or Core Functionality") type  Cashbook_Acct{ >> Cashbook_Acct.nebula
ECHO Org;>> Cashbook_Acct.nebula
ECHO Cb_Asset_acct Account;>> Cashbook_Acct.nebula
ECHO Cb_Cashtransfer_acct Account;>> Cashbook_Acct.nebula
ECHO Cb_Differences_acct Account;>> Cashbook_Acct.nebula
ECHO Cb_Expense_acct Account;>> Cashbook_Acct.nebula
ECHO Cb_Receipt_acct Account;>> Cashbook_Acct.nebula
ECHO !Acctschema;>> Cashbook_Acct.nebula
ECHO !Cashbook;>> Cashbook_Acct.nebula
ECHO Created Timestamp;>> Cashbook_Acct.nebula
ECHO Createdby Date;>> Cashbook_Acct.nebula
ECHO Isactive YesNo;>> Cashbook_Acct.nebula
ECHO Updated Timestamp;>> Cashbook_Acct.nebula
ECHO Updatedby Date;>> Cashbook_Acct.nebula
ECHO };>> Cashbook_Acct.nebula 
 ECHO @Package("Common or Core Functionality") type  Cashline{ >> Cashline.nebula
ECHO Orgtrx Org;>> Cashline.nebula
ECHO Org;>> Cashline.nebula
ECHO Amount;>> Cashline.nebula
ECHO Activity;>> Cashline.nebula
ECHO Bankaccount;>> Cashline.nebula
ECHO !ID;>> Cashline.nebula
ECHO Cash;>> Cashline.nebula
ECHO Charge;>> Cashline.nebula
ECHO Currency;>> Cashline.nebula
ECHO Invoice;>> Cashline.nebula
ECHO Cashtype YesNo;>> Cashline.nebula
ECHO Created Timestamp;>> Cashline.nebula
ECHO Createdby Date;>> Cashline.nebula
ECHO Description;>> Cashline.nebula
ECHO Discountamt Amount;>> Cashline.nebula
ECHO Isactive YesNo;>> Cashline.nebula
ECHO Isgenerated YesNo;>> Cashline.nebula
ECHO Line Number;>> Cashline.nebula
ECHO Processed YesNo;>> Cashline.nebula
ECHO Updated Timestamp;>> Cashline.nebula
ECHO Updatedby Date;>> Cashline.nebula
ECHO Writeoffamt Amount;>> Cashline.nebula
ECHO };>> Cashline.nebula 
 ECHO @Package("Common or Core Functionality") type  Channel{ >> Channel.nebula
ECHO Org;>> Channel.nebula
ECHO Printcolor;>> Channel.nebula
ECHO !ID;>> Channel.nebula
ECHO Created Timestamp;>> Channel.nebula
ECHO Createdby Date;>> Channel.nebula
ECHO Description;>> Channel.nebula
ECHO Isactive YesNo;>> Channel.nebula
ECHO !Name;>> Channel.nebula
ECHO Updated Timestamp;>> Channel.nebula
ECHO Updatedby Date;>> Channel.nebula
ECHO };>> Channel.nebula 
 ECHO @Package("Common or Core Functionality") type  Charge{ >> Charge.nebula
ECHO Org;>> Charge.nebula
ECHO Bpartner;>> Charge.nebula
ECHO !ID;>> Charge.nebula
ECHO Taxcategory;>> Charge.nebula
ECHO Chargeamt Amount;>> Charge.nebula
ECHO Created Timestamp;>> Charge.nebula
ECHO Createdby Date;>> Charge.nebula
ECHO Description;>> Charge.nebula
ECHO Isactive YesNo;>> Charge.nebula
ECHO Issamecurrency YesNo;>> Charge.nebula
ECHO Issametax YesNo;>> Charge.nebula
ECHO Istaxincluded YesNo;>> Charge.nebula
ECHO !Name;>> Charge.nebula
ECHO Updated Timestamp;>> Charge.nebula
ECHO Updatedby Date;>> Charge.nebula
ECHO };>> Charge.nebula 
 ECHO @Package("Common or Core Functionality") type  Charge_Acct{ >> Charge_Acct.nebula
ECHO Org;>> Charge_Acct.nebula
ECHO !Acctschema;>> Charge_Acct.nebula
ECHO !Charge;>> Charge_Acct.nebula
ECHO Ch_Expense_acct Account;>> Charge_Acct.nebula
ECHO Ch_Revenue_acct Account;>> Charge_Acct.nebula
ECHO Created Timestamp;>> Charge_Acct.nebula
ECHO Createdby Date;>> Charge_Acct.nebula
ECHO Isactive YesNo;>> Charge_Acct.nebula
ECHO Updated Timestamp;>> Charge_Acct.nebula
ECHO Updatedby Date;>> Charge_Acct.nebula
ECHO };>> Charge_Acct.nebula 
 ECHO @Package("Common or Core Functionality") type  Charge_Trl{ >> Charge_Trl.nebula
ECHO !Ad_Language Language;>> Charge_Trl.nebula
ECHO Org;>> Charge_Trl.nebula
ECHO !Charge;>> Charge_Trl.nebula
ECHO Created Timestamp;>> Charge_Trl.nebula
ECHO Createdby Date;>> Charge_Trl.nebula
ECHO Isactive YesNo;>> Charge_Trl.nebula
ECHO Istranslated YesNo;>> Charge_Trl.nebula
ECHO !Name;>> Charge_Trl.nebula
ECHO Updated Timestamp;>> Charge_Trl.nebula
ECHO Updatedby Date;>> Charge_Trl.nebula
ECHO };>> Charge_Trl.nebula 
 ECHO @Package("Common or Core Functionality") type  City{ >> City.nebula
ECHO Org;>> City.nebula
ECHO Areacode Code;>> City.nebula
ECHO !ID;>> City.nebula
ECHO Country;>> City.nebula
ECHO Region;>> City.nebula
ECHO Coordinates;>> City.nebula
ECHO Created Timestamp;>> City.nebula
ECHO Createdby Date;>> City.nebula
ECHO Isactive YesNo;>> City.nebula
ECHO Locode Attr;>> City.nebula
ECHO !Name;>> City.nebula
ECHO Postal PostalCode;>> City.nebula
ECHO Updated Timestamp;>> City.nebula
ECHO Updatedby Date;>> City.nebula
ECHO };>> City.nebula 
 ECHO @Package("Common or Core Functionality") type  Commission{ >> Commission.nebula
ECHO Org;>> Commission.nebula
ECHO Bpartner;>> Commission.nebula
ECHO Charge;>> Commission.nebula
ECHO !ID;>> Commission.nebula
ECHO Currency;>> Commission.nebula
ECHO Createfrom YesNo;>> Commission.nebula
ECHO Created Timestamp;>> Commission.nebula
ECHO Createdby Date;>> Commission.nebula
ECHO Datelastrun Date;>> Commission.nebula
ECHO Description;>> Commission.nebula
ECHO Docbasistype YesNo;>> Commission.nebula
ECHO Frequencytype YesNo;>> Commission.nebula
ECHO Isactive YesNo;>> Commission.nebula
ECHO Listdetails YesNo;>> Commission.nebula
ECHO !Name;>> Commission.nebula
ECHO Processing YesNo;>> Commission.nebula
ECHO Updated Timestamp;>> Commission.nebula
ECHO Updatedby Date;>> Commission.nebula
ECHO };>> Commission.nebula 
 ECHO @Package("Common or Core Functionality") type  Commissionamt{ >> Commissionamt.nebula
ECHO Org;>> Commissionamt.nebula
ECHO Actualqty Quantity;>> Commissionamt.nebula
ECHO !ID;>> Commissionamt.nebula
ECHO Commissionline;>> Commissionamt.nebula
ECHO Commissionrun;>> Commissionamt.nebula
ECHO Commissionamt Amount;>> Commissionamt.nebula
ECHO Convertedamt Amount;>> Commissionamt.nebula
ECHO Created Timestamp;>> Commissionamt.nebula
ECHO Createdby Date;>> Commissionamt.nebula
ECHO Isactive YesNo;>> Commissionamt.nebula
ECHO Updated Timestamp;>> Commissionamt.nebula
ECHO Updatedby Date;>> Commissionamt.nebula
ECHO };>> Commissionamt.nebula 
 ECHO @Package("Common or Core Functionality") type  Commissiondetail{ >> Commissiondetail.nebula
ECHO Org;>> Commissiondetail.nebula
ECHO Actualamt Amount;>> Commissiondetail.nebula
ECHO Actualqty Quantity;>> Commissiondetail.nebula
ECHO Commissionamt;>> Commissiondetail.nebula
ECHO !ID;>> Commissiondetail.nebula
ECHO Currency;>> Commissiondetail.nebula
ECHO Invoiceline;>> Commissiondetail.nebula
ECHO Orderline;>> Commissiondetail.nebula
ECHO Convertedamt Amount;>> Commissiondetail.nebula
ECHO Created Timestamp;>> Commissiondetail.nebula
ECHO Createdby Date;>> Commissiondetail.nebula
ECHO Info Description;>> Commissiondetail.nebula
ECHO Isactive YesNo;>> Commissiondetail.nebula
ECHO Reference String;>> Commissiondetail.nebula
ECHO Updated Timestamp;>> Commissiondetail.nebula
ECHO Updatedby Date;>> Commissiondetail.nebula
ECHO };>> Commissiondetail.nebula 
 ECHO @Package("Common or Core Functionality") type  Commissionline{ >> Commissionline.nebula
ECHO Org;>> Commissionline.nebula
ECHO Amtmultiplier Amount;>> Commissionline.nebula
ECHO Amtsubtract Amount;>> Commissionline.nebula
ECHO Bp_Group;>> Commissionline.nebula
ECHO Bpartner;>> Commissionline.nebula
ECHO !ID;>> Commissionline.nebula
ECHO Commission;>> Commissionline.nebula
ECHO Salesregion;>> Commissionline.nebula
ECHO Commissionorders YesNo;>> Commissionline.nebula
ECHO Created Timestamp;>> Commissionline.nebula
ECHO Createdby Date;>> Commissionline.nebula
ECHO Description;>> Commissionline.nebula
ECHO Isactive YesNo;>> Commissionline.nebula
ECHO Ispositiveonly YesNo;>> Commissionline.nebula
ECHO Line Number;>> Commissionline.nebula
ECHO Product_Category;>> Commissionline.nebula
ECHO Product;>> Commissionline.nebula
ECHO Org;>> Commissionline.nebula
ECHO Qtymultiplier Quantity;>> Commissionline.nebula
ECHO Qtysubtract Quantity;>> Commissionline.nebula
ECHO Updated Timestamp;>> Commissionline.nebula
ECHO Updatedby Date;>> Commissionline.nebula
ECHO };>> Commissionline.nebula 
 ECHO @Package("Common or Core Functionality") type  Commissionrun{ >> Commissionrun.nebula
ECHO Org;>> Commissionrun.nebula
ECHO !ID;>> Commissionrun.nebula
ECHO Commission;>> Commissionrun.nebula
ECHO Invoice;>> Commissionrun.nebula
ECHO Created Timestamp;>> Commissionrun.nebula
ECHO Createdby Date;>> Commissionrun.nebula
ECHO Description;>> Commissionrun.nebula
ECHO Documentno SeqNo;>> Commissionrun.nebula
ECHO Grandtotal Number;>> Commissionrun.nebula
ECHO Isactive YesNo;>> Commissionrun.nebula
ECHO Processed YesNo;>> Commissionrun.nebula
ECHO Processing YesNo;>> Commissionrun.nebula
ECHO Startdate Date;>> Commissionrun.nebula
ECHO Updated Timestamp;>> Commissionrun.nebula
ECHO Updatedby Date;>> Commissionrun.nebula
ECHO };>> Commissionrun.nebula 
 ECHO @Package("Common or Core Functionality") type  Consolidationreference{ >> Consolidationreference.nebula
ECHO Org;>> Consolidationreference.nebula
ECHO !ID;>> Consolidationreference.nebula
ECHO Created Timestamp;>> Consolidationreference.nebula
ECHO Createdby Date;>> Consolidationreference.nebula
ECHO Description;>> Consolidationreference.nebula
ECHO Isactive YesNo;>> Consolidationreference.nebula
ECHO !Name;>> Consolidationreference.nebula
ECHO Updated Timestamp;>> Consolidationreference.nebula
ECHO Updatedby Date;>> Consolidationreference.nebula
ECHO };>> Consolidationreference.nebula 
 ECHO @Package("Common or Core Functionality") type  Conversion_Rate{ >> Conversion_Rate.nebula
ECHO Org;>> Conversion_Rate.nebula
ECHO Conversiontype;>> Conversion_Rate.nebula
ECHO !ID;>> Conversion_Rate.nebula
ECHO Currency;>> Conversion_Rate.nebula

ECHO Currency_To Currency;>> Conversion_Rate.nebula
ECHO Created Timestamp;>> Conversion_Rate.nebula
ECHO Createdby Date;>> Conversion_Rate.nebula
ECHO Dividerate Rate;>> Conversion_Rate.nebula
ECHO Isactive YesNo;>> Conversion_Rate.nebula
ECHO Multiplyrate Rate;>> Conversion_Rate.nebula
ECHO Updated Timestamp;>> Conversion_Rate.nebula
ECHO Updatedby Date;>> Conversion_Rate.nebula
ECHO Validfrom Date;>> Conversion_Rate.nebula
ECHO Validto Date;>> Conversion_Rate.nebula
ECHO };>> Conversion_Rate.nebula 
 ECHO @Package("Common or Core Functionality") type  Conversiontype{ >> Conversiontype.nebula
ECHO Org;>> Conversiontype.nebula
ECHO !ID;>> Conversiontype.nebula
ECHO Created Timestamp;>> Conversiontype.nebula
ECHO Createdby Date;>> Conversiontype.nebula
ECHO Description;>> Conversiontype.nebula
ECHO Isactive YesNo;>> Conversiontype.nebula
ECHO Isdefault YesNo;>> Conversiontype.nebula
ECHO !Name;>> Conversiontype.nebula
ECHO Updated Timestamp;>> Conversiontype.nebula
ECHO Updatedby Date;>> Conversiontype.nebula
ECHO Value String;>> Conversiontype.nebula
ECHO };>> Conversiontype.nebula 
 ECHO @Package("Common or Core Functionality") type  Country{ >> Country.nebula
ECHO Ad_Language Language;>> Country.nebula
ECHO Org;>> Country.nebula
ECHO !ID;>> Country.nebula
ECHO Currency;>> Country.nebula
ECHO Countrycode Attr;>> Country.nebula
ECHO Created Timestamp;>> Country.nebula
ECHO Createdby Date;>> Country.nebula
ECHO Description;>> Country.nebula
ECHO Displaysequence String;>> Country.nebula
ECHO Displaysequencelocal String;>> Country.nebula
ECHO Expressionbankaccountno AccountNo;>> Country.nebula
ECHO Expressionbankroutingno Code;>> Country.nebula
ECHO Expressionphone PhoneNumber;>> Country.nebula
ECHO Expressionpostal PostalCode;>> Country.nebula
ECHO Expressionpostal_Add PostalCode;>> Country.nebula
ECHO Haspostal_Add YesNo;>> Country.nebula
ECHO Hasregion YesNo;>> Country.nebula
ECHO Isactive YesNo;>> Country.nebula
ECHO Isaddresslineslocalreverse YesNo;>> Country.nebula
ECHO Isaddresslinesreverse YesNo;>> Country.nebula
ECHO Issummary YesNo;>> Country.nebula
ECHO Mediasize Long;>> Country.nebula
ECHO !Name;>> Country.nebula
ECHO Regionname Name;>> Country.nebula
ECHO Updated Timestamp;>> Country.nebula
ECHO Updatedby Date;>> Country.nebula
ECHO };>> Country.nebula 
 ECHO @Package("Common or Core Functionality") type  Country_Trl{ >> Country_Trl.nebula
ECHO !Ad_Language Language;>> Country_Trl.nebula
ECHO Org;>> Country_Trl.nebula
ECHO !Country;>> Country_Trl.nebula
ECHO Created Timestamp;>> Country_Trl.nebula
ECHO Createdby Date;>> Country_Trl.nebula
ECHO Description;>> Country_Trl.nebula
ECHO Isactive YesNo;>> Country_Trl.nebula
ECHO Istranslated YesNo;>> Country_Trl.nebula
ECHO !Name;>> Country_Trl.nebula
ECHO Regionname Name;>> Country_Trl.nebula
ECHO Updated Timestamp;>> Country_Trl.nebula
ECHO Updatedby Date;>> Country_Trl.nebula
ECHO };>> Country_Trl.nebula 
 ECHO @Package("Common or Core Functionality") type  Currency{ >> Currency.nebula
ECHO Org;>> Currency.nebula
ECHO !ID;>> Currency.nebula
ECHO Costingprecision Precision;>> Currency.nebula
ECHO Created Timestamp;>> Currency.nebula
ECHO Createdby Date;>> Currency.nebula
ECHO Cursymbol Symbol;>> Currency.nebula
ECHO Description;>> Currency.nebula
ECHO Emuentrydate Date;>> Currency.nebula
ECHO Emurate Rate;>> Currency.nebula
ECHO Iso_Code Code;>> Currency.nebula
ECHO Isactive YesNo;>> Currency.nebula
ECHO Isemumember YesNo;>> Currency.nebula
ECHO Iseuro YesNo;>> Currency.nebula
ECHO Stdprecision Precision;>> Currency.nebula
ECHO Updated Timestamp;>> Currency.nebula
ECHO Updatedby Date;>> Currency.nebula
ECHO };>> Currency.nebula 
 ECHO @Package("Common or Core Functionality") type  Currency_Acct{ >> Currency_Acct.nebula
ECHO Org;>> Currency_Acct.nebula
ECHO !Acctschema;>> Currency_Acct.nebula
ECHO !Currency;>> Currency_Acct.nebula
ECHO Created Timestamp;>> Currency_Acct.nebula
ECHO Createdby Date;>> Currency_Acct.nebula
ECHO Isactive YesNo;>> Currency_Acct.nebula
ECHO Realizedgain_Acct Account;>> Currency_Acct.nebula
ECHO Realizedloss_Acct Account;>> Currency_Acct.nebula
ECHO Unrealizedgain_Acct Account;>> Currency_Acct.nebula
ECHO Unrealizedloss_Acct Account;>> Currency_Acct.nebula
ECHO Updated Timestamp;>> Currency_Acct.nebula
ECHO Updatedby Date;>> Currency_Acct.nebula
ECHO };>> Currency_Acct.nebula 
 ECHO @Package("Common or Core Functionality") type  Currency_Trl{ >> Currency_Trl.nebula
ECHO !Ad_Language Language;>> Currency_Trl.nebula
ECHO Org;>> Currency_Trl.nebula
ECHO !Currency;>> Currency_Trl.nebula
ECHO Created Timestamp;>> Currency_Trl.nebula
ECHO Createdby Date;>> Currency_Trl.nebula
ECHO Cursymbol Symbol;>> Currency_Trl.nebula
ECHO Description;>> Currency_Trl.nebula
ECHO Isactive YesNo;>> Currency_Trl.nebula
ECHO Istranslated YesNo;>> Currency_Trl.nebula
ECHO Updated Timestamp;>> Currency_Trl.nebula
ECHO Updatedby Date;>> Currency_Trl.nebula
ECHO };>> Currency_Trl.nebula 
 ECHO @Package("Common or Core Functionality") type  Cycle{ >> Cycle.nebula
ECHO Org;>> Cycle.nebula
ECHO Currency;>> Cycle.nebula
ECHO !ID;>> Cycle.nebula
ECHO Created Timestamp;>> Cycle.nebula
ECHO Createdby Date;>> Cycle.nebula
ECHO Description;>> Cycle.nebula
ECHO Isactive YesNo;>> Cycle.nebula
ECHO !Name;>> Cycle.nebula
ECHO Updated Timestamp;>> Cycle.nebula
ECHO Updatedby Date;>> Cycle.nebula
ECHO };>> Cycle.nebula 
 ECHO @Package("Common or Core Functionality") type  Cyclephase{ >> Cyclephase.nebula
ECHO Org;>> Cyclephase.nebula
ECHO !Cyclestep;>> Cyclephase.nebula
ECHO !Phase;>> Cyclephase.nebula
ECHO Created Timestamp;>> Cyclephase.nebula
ECHO Createdby Date;>> Cyclephase.nebula
ECHO Isactive YesNo;>> Cyclephase.nebula
ECHO Updated Timestamp;>> Cyclephase.nebula
ECHO Updatedby Date;>> Cyclephase.nebula
ECHO };>> Cyclephase.nebula 
 ECHO @Package("Common or Core Functionality") type  Cyclestep{ >> Cyclestep.nebula
ECHO Org;>> Cyclestep.nebula
ECHO !ID;>> Cyclestep.nebula
ECHO Cycle;>> Cyclestep.nebula
ECHO Created Timestamp;>> Cyclestep.nebula
ECHO Createdby Date;>> Cyclestep.nebula
ECHO Isactive YesNo;>> Cyclestep.nebula
ECHO !Name;>> Cyclestep.nebula
ECHO Relativeweight Number;>> Cyclestep.nebula
ECHO SeqNo;>> Cyclestep.nebula
ECHO Updated Timestamp;>> Cyclestep.nebula
ECHO Updatedby Date;>> Cyclestep.nebula
ECHO };>> Cyclestep.nebula 
 ECHO @Package("Common or Core Functionality") type  Docbasetype{ >> Docbasetype.nebula
ECHO Org;>> Docbasetype.nebula
ECHO Table;>> Docbasetype.nebula
ECHO Accountingclassname Name;>> Docbasetype.nebula
ECHO !ID;>> Docbasetype.nebula
ECHO Created Timestamp;>> Docbasetype.nebula
ECHO Createdby Date;>> Docbasetype.nebula
ECHO Description;>> Docbasetype.nebula
ECHO Docbasetype Attr;>> Docbasetype.nebula
ECHO Entitytype Attr;>> Docbasetype.nebula
ECHO Help;>> Docbasetype.nebula
ECHO Isactive YesNo;>> Docbasetype.nebula
ECHO !Name;>> Docbasetype.nebula
ECHO Updated Timestamp;>> Docbasetype.nebula
ECHO Updatedby Date;>> Docbasetype.nebula
ECHO };>> Docbasetype.nebula 
 ECHO @Package("Common or Core Functionality") type  Doctype{ >> Doctype.nebula
ECHO Org;>> Doctype.nebula
ECHO Printformat;>> Doctype.nebula
ECHO Doctypedifference Doctype;>> Doctype.nebula
ECHO Doctypeinvoice Doctype;>> Doctype.nebula
ECHO Doctypeproforma Doctype;>> Doctype.nebula
ECHO Doctypeshipment Doctype;>> Doctype.nebula
ECHO !ID;>> Doctype.nebula
ECHO Created Timestamp;>> Doctype.nebula
ECHO Createdby Date;>> Doctype.nebula
ECHO Description;>> Doctype.nebula
ECHO Docbasetype Attr;>> Doctype.nebula
ECHO Docnosequence Sequence;>> Doctype.nebula
ECHO Docsubtypeso Attr;>> Doctype.nebula
ECHO Documentcopies Count;>> Doctype.nebula
ECHO Documentnote Note;>> Doctype.nebula
ECHO Category;>> Doctype.nebula
ECHO Hascharges YesNo;>> Doctype.nebula
ECHO Hasproforma YesNo;>> Doctype.nebula
ECHO Isactive YesNo;>> Doctype.nebula
ECHO Iscreatecounter YesNo;>> Doctype.nebula
ECHO Isdefault YesNo;>> Doctype.nebula
ECHO Isdefaultcounterdoc YesNo;>> Doctype.nebula
ECHO Isdocnocontrolled YesNo;>> Doctype.nebula
ECHO Isintransit YesNo;>> Doctype.nebula
ECHO Isindexed YesNo;>> Doctype.nebula
ECHO Ispickqaconfirm YesNo;>> Doctype.nebula
ECHO Isreturntrx YesNo;>> Doctype.nebula
ECHO Issotrx YesNo;>> Doctype.nebula
ECHO Isshipconfirm YesNo;>> Doctype.nebula
ECHO Issplitwhendifference YesNo;>> Doctype.nebula
ECHO !Name;>> Doctype.nebula
ECHO Printname Name;>> Doctype.nebula
ECHO Updated Timestamp;>> Doctype.nebula
ECHO Updatedby Date;>> Doctype.nebula
ECHO };>> Doctype.nebula 
 ECHO @Package("Common or Core Functionality") type  Doctype_Trl{ >> Doctype_Trl.nebula
ECHO !Ad_Language Language;>> Doctype_Trl.nebula
ECHO Org;>> Doctype_Trl.nebula
ECHO !Doctype;>> Doctype_Trl.nebula
ECHO Created Timestamp;>> Doctype_Trl.nebula
ECHO Createdby Date;>> Doctype_Trl.nebula
ECHO Documentnote Note;>> Doctype_Trl.nebula
ECHO Isactive YesNo;>> Doctype_Trl.nebula
ECHO Istranslated YesNo;>> Doctype_Trl.nebula
ECHO !Name;>> Doctype_Trl.nebula
ECHO Printname Name;>> Doctype_Trl.nebula
ECHO Updated Timestamp;>> Doctype_Trl.nebula
ECHO Updatedby Date;>> Doctype_Trl.nebula
ECHO };>> Doctype_Trl.nebula 
 ECHO @Package("Common or Core Functionality") type  Doctypecounter{ >> Doctypecounter.nebula
ECHO Org;>> Doctypecounter.nebula
ECHO !ID;>> Doctypecounter.nebula
ECHO Doctype;>> Doctypecounter.nebula
ECHO Counter_C_doctype Doctype;>> Doctypecounter.nebula
ECHO Created Timestamp;>> Doctypecounter.nebula
ECHO Createdby Date;>> Doctypecounter.nebula
ECHO Description;>> Doctypecounter.nebula
ECHO Docaction Attr;>> Doctypecounter.nebula
ECHO Isactive YesNo;>> Doctypecounter.nebula
ECHO Iscreatecounter YesNo;>> Doctypecounter.nebula
ECHO Isvalid YesNo;>> Doctypecounter.nebula
ECHO !Name;>> Doctypecounter.nebula
ECHO Processing YesNo;>> Doctypecounter.nebula
ECHO Updated Timestamp;>> Doctypecounter.nebula
ECHO Updatedby Date;>> Doctypecounter.nebula
ECHO };>> Doctypecounter.nebula 
 ECHO @Package("Common or Core Functionality") type  Doctypegroup{ >> Doctypegroup.nebula
ECHO Org;>> Doctypegroup.nebula
ECHO !ID;>> Doctypegroup.nebula
ECHO Created Timestamp;>> Doctypegroup.nebula
ECHO Createdby Date;>> Doctypegroup.nebula
ECHO Description;>> Doctypegroup.nebula
ECHO Isactive YesNo;>> Doctypegroup.nebula
ECHO !Name;>> Doctypegroup.nebula
ECHO Updated Timestamp;>> Doctypegroup.nebula
ECHO Updatedby Date;>> Doctypegroup.nebula
ECHO };>> Doctypegroup.nebula 
 ECHO @Package("Common or Core Functionality") type  Doctypegroupline{ >> Doctypegroupline.nebula
ECHO Org;>> Doctypegroupline.nebula
ECHO !ID;>> Doctypegroupline.nebula
ECHO Doctypegroup;>> Doctypegroupline.nebula
ECHO Doctype;>> Doctypegroupline.nebula
ECHO Created Timestamp;>> Doctypegroupline.nebula
ECHO Createdby Date;>> Doctypegroupline.nebula
ECHO Isactive YesNo;>> Doctypegroupline.nebula
ECHO SeqNo;>> Doctypegroupline.nebula
ECHO Updated Timestamp;>> Doctypegroupline.nebula
ECHO Updatedby Date;>> Doctypegroupline.nebula
ECHO };>> Doctypegroupline.nebula 
 ECHO @Package("Common or Core Functionality") type  Dunning{ >> Dunning.nebula
ECHO Org;>> Dunning.nebula
ECHO !ID;>> Dunning.nebula
ECHO Createlevelssequentially YesNo;>> Dunning.nebula
ECHO Created Timestamp;>> Dunning.nebula
ECHO Createdby Date;>> Dunning.nebula
ECHO Description;>> Dunning.nebula
ECHO Isactive YesNo;>> Dunning.nebula
ECHO Isdefault YesNo;>> Dunning.nebula
ECHO !Name;>> Dunning.nebula
ECHO Senddunningletter YesNo;>> Dunning.nebula
ECHO Updated Timestamp;>> Dunning.nebula
ECHO Updatedby Date;>> Dunning.nebula
ECHO };>> Dunning.nebula 
 ECHO @Package("Common or Core Functionality") type  Dunninglevel{ >> Dunninglevel.nebula
ECHO Org;>> Dunninglevel.nebula
ECHO !ID;>> Dunninglevel.nebula
ECHO Dunning;>> Dunninglevel.nebula
ECHO Paymentterm;>> Dunninglevel.nebula
ECHO Chargefee YesNo;>> Dunninglevel.nebula
ECHO Chargeinterest YesNo;>> Dunninglevel.nebula
ECHO Created Timestamp;>> Dunninglevel.nebula
ECHO Createdby Date;>> Dunninglevel.nebula
ECHO Daysafterdue Count;>> Dunninglevel.nebula
ECHO Daysbetweendunning Count;>> Dunninglevel.nebula
ECHO Description;>> Dunninglevel.nebula
ECHO Dunning_Printformat Printformat;>> Dunninglevel.nebula
ECHO Feeamt Amount;>> Dunninglevel.nebula
ECHO Interestpercent Percent;>> Dunninglevel.nebula
ECHO Invoicecollectiontype YesNo;>> Dunninglevel.nebula
ECHO Isactive YesNo;>> Dunninglevel.nebula
ECHO Issetcreditstop YesNo;>> Dunninglevel.nebula
ECHO Issetpaymentterm YesNo;>> Dunninglevel.nebula
ECHO Isshowalldue YesNo;>> Dunninglevel.nebula
ECHO Isshownotdue YesNo;>> Dunninglevel.nebula
ECHO !Name;>> Dunninglevel.nebula
ECHO Note;>> Dunninglevel.nebula
ECHO Printname Name;>> Dunninglevel.nebula
ECHO Updated Timestamp;>> Dunninglevel.nebula
ECHO Updatedby Date;>> Dunninglevel.nebula
ECHO };>> Dunninglevel.nebula 
 ECHO @Package("Common or Core Functionality") type  Dunninglevel_Trl{ >> Dunninglevel_Trl.nebula
ECHO !Ad_Language Language;>> Dunninglevel_Trl.nebula
ECHO Org;>> Dunninglevel_Trl.nebula
ECHO !Dunninglevel;>> Dunninglevel_Trl.nebula
ECHO Created Timestamp;>> Dunninglevel_Trl.nebula
ECHO Createdby Date;>> Dunninglevel_Trl.nebula
ECHO Isactive YesNo;>> Dunninglevel_Trl.nebula
ECHO Istranslated YesNo;>> Dunninglevel_Trl.nebula
ECHO Note;>> Dunninglevel_Trl.nebula
ECHO Printname Name;>> Dunninglevel_Trl.nebula
ECHO Updated Timestamp;>> Dunninglevel_Trl.nebula
ECHO Updatedby Date;>> Dunninglevel_Trl.nebula
ECHO };>> Dunninglevel_Trl.nebula 
 ECHO @Package("Common or Core Functionality") type  Dunningrun{ >> Dunningrun.nebula
ECHO Org;>> Dunningrun.nebula
ECHO Dunninglevel;>> Dunningrun.nebula
ECHO !ID;>> Dunningrun.nebula
ECHO Created Timestamp;>> Dunningrun.nebula
ECHO Createdby Date;>> Dunningrun.nebula
ECHO Description;>> Dunningrun.nebula
ECHO Dunningdate Date;>> Dunningrun.nebula
ECHO Isactive YesNo;>> Dunningrun.nebula
ECHO Processed YesNo;>> Dunningrun.nebula
ECHO Processing YesNo;>> Dunningrun.nebula
ECHO Sendit YesNo;>> Dunningrun.nebula
ECHO Updated Timestamp;>> Dunningrun.nebula
ECHO Updatedby Date;>> Dunningrun.nebula
ECHO };>> Dunningrun.nebula 
 ECHO @Package("Common or Core Functionality") type  Dunningrunentry{ >> Dunningrunentry.nebula
ECHO Org;>> Dunningrunentry.nebula
ECHO User;>> Dunningrunentry.nebula
ECHO Amt Amount;>> Dunningrunentry.nebula
ECHO Bpartner;>> Dunningrunentry.nebula
ECHO Bpartner_Location;>> Dunningrunentry.nebula
ECHO Currency;>> Dunningrunentry.nebula
ECHO !ID;>> Dunningrunentry.nebula
ECHO Dunningrun;>> Dunningrunentry.nebula
ECHO Created Timestamp;>> Dunningrunentry.nebula
ECHO Createdby Date;>> Dunningrunentry.nebula
ECHO Isactive YesNo;>> Dunningrunentry.nebula
ECHO Note;>> Dunningrunentry.nebula
ECHO Processed YesNo;>> Dunningrunentry.nebula
ECHO Qty Quantity;>> Dunningrunentry.nebula
ECHO Salesrep User;>> Dunningrunentry.nebula
ECHO Updated Timestamp;>> Dunningrunentry.nebula
ECHO Updatedby Date;>> Dunningrunentry.nebula
ECHO };>> Dunningrunentry.nebula 
 ECHO @Package("Common or Core Functionality") type  Dunningrunline{ >> Dunningrunline.nebula
ECHO Org;>> Dunningrunline.nebula
ECHO Amt Amount;>> Dunningrunline.nebula
ECHO Dunningrunentry;>> Dunningrunline.nebula
ECHO !ID;>> Dunningrunline.nebula
ECHO Invoice;>> Dunningrunline.nebula
ECHO Payment;>> Dunningrunline.nebula
ECHO Convertedamt Amount;>> Dunningrunline.nebula
ECHO Created Timestamp;>> Dunningrunline.nebula
ECHO Createdby Date;>> Dunningrunline.nebula
ECHO Daysdue Count;>> Dunningrunline.nebula
ECHO Feeamt Amount;>> Dunningrunline.nebula
ECHO Interestamt Amount;>> Dunningrunline.nebula
ECHO Isactive YesNo;>> Dunningrunline.nebula
ECHO Isindispute YesNo;>> Dunningrunline.nebula
ECHO Openamt Amount;>> Dunningrunline.nebula
ECHO Processed YesNo;>> Dunningrunline.nebula
ECHO Timesdunned Number;>> Dunningrunline.nebula
ECHO Totalamt Amount;>> Dunningrunline.nebula
ECHO Updated Timestamp;>> Dunningrunline.nebula
ECHO Updatedby Date;>> Dunningrunline.nebula
ECHO };>> Dunningrunline.nebula 
 ECHO @Package("Common or Core Functionality") type  Element{ >> Element.nebula
ECHO Org;>> Element.nebula
ECHO Tree;>> Element.nebula
ECHO !ID;>> Element.nebula
ECHO Created Timestamp;>> Element.nebula
ECHO Createdby Date;>> Element.nebula
ECHO Description;>> Element.nebula
ECHO Elementtype Attr;>> Element.nebula
ECHO Isactive YesNo;>> Element.nebula
ECHO Isbalancing YesNo;>> Element.nebula
ECHO Isnaturalaccount YesNo;>> Element.nebula
ECHO !Name;>> Element.nebula
ECHO Updated Timestamp;>> Element.nebula
ECHO Updatedby Date;>> Element.nebula
ECHO Vformat String;>> Element.nebula
ECHO };>> Element.nebula 
 ECHO @Package("Common or Core Functionality") type  Elementvalue{ >> Elementvalue.nebula
ECHO Org;>> Elementvalue.nebula
ECHO Accountsign YesNo;>> Elementvalue.nebula
ECHO Accounttype YesNo;>> Elementvalue.nebula
ECHO Bankaccount;>> Elementvalue.nebula
ECHO Currency;>> Elementvalue.nebula
ECHO !ID;>> Elementvalue.nebula
ECHO Element;>> Elementvalue.nebula
ECHO Created Timestamp;>> Elementvalue.nebula
ECHO Createdby Date;>> Elementvalue.nebula
ECHO Description;>> Elementvalue.nebula
ECHO Isactive YesNo;>> Elementvalue.nebula
ECHO Isbankaccount YesNo;>> Elementvalue.nebula
ECHO Isdoccontrolled YesNo;>> Elementvalue.nebula
ECHO Isforeigncurrency YesNo;>> Elementvalue.nebula
ECHO Issummary YesNo;>> Elementvalue.nebula
ECHO !Name;>> Elementvalue.nebula
ECHO Postactual YesNo;>> Elementvalue.nebula
ECHO Postbudget YesNo;>> Elementvalue.nebula
ECHO Postencumbrance YesNo;>> Elementvalue.nebula
ECHO Poststatistical YesNo;>> Elementvalue.nebula
ECHO Updated Timestamp;>> Elementvalue.nebula
ECHO Updatedby Date;>> Elementvalue.nebula
ECHO Validfrom Date;>> Elementvalue.nebula
ECHO Validto Date;>> Elementvalue.nebula
ECHO Value String;>> Elementvalue.nebula
ECHO };>> Elementvalue.nebula 
 ECHO @Package("Common or Core Functionality") type  Elementvalue_Trl{ >> Elementvalue_Trl.nebula
ECHO !Ad_Language Language;>> Elementvalue_Trl.nebula
ECHO Org;>> Elementvalue_Trl.nebula
ECHO !Elementvalue;>> Elementvalue_Trl.nebula
ECHO Created Timestamp;>> Elementvalue_Trl.nebula
ECHO Createdby Date;>> Elementvalue_Trl.nebula
ECHO Description;>> Elementvalue_Trl.nebula
ECHO Isactive YesNo;>> Elementvalue_Trl.nebula
ECHO Istranslated YesNo;>> Elementvalue_Trl.nebula
ECHO !Name;>> Elementvalue_Trl.nebula
ECHO Updated Timestamp;>> Elementvalue_Trl.nebula
ECHO Updatedby Date;>> Elementvalue_Trl.nebula
ECHO };>> Elementvalue_Trl.nebula 
 ECHO @Package("Common or Core Functionality") type  Greeting{ >> Greeting.nebula
ECHO Org;>> Greeting.nebula
ECHO !ID;>> Greeting.nebula
ECHO Created Timestamp;>> Greeting.nebula
ECHO Createdby Date;>> Greeting.nebula
ECHO Greeting Description;>> Greeting.nebula
ECHO Isactive YesNo;>> Greeting.nebula
ECHO Isdefault YesNo;>> Greeting.nebula
ECHO Isfirstnameonly YesNo;>> Greeting.nebula
ECHO !Name;>> Greeting.nebula
ECHO Updated Timestamp;>> Greeting.nebula
ECHO Updatedby Date;>> Greeting.nebula
ECHO };>> Greeting.nebula 
 ECHO @Package("Common or Core Functionality") type  Greeting_Trl{ >> Greeting_Trl.nebula
ECHO !Ad_Language Language;>> Greeting_Trl.nebula
ECHO Org;>> Greeting_Trl.nebula
ECHO !Greeting;>> Greeting_Trl.nebula
ECHO Created Timestamp;>> Greeting_Trl.nebula
ECHO Createdby Date;>> Greeting_Trl.nebula
ECHO Greeting Description;>> Greeting_Trl.nebula
ECHO Isactive YesNo;>> Greeting_Trl.nebula
ECHO Istranslated YesNo;>> Greeting_Trl.nebula
ECHO !Name;>> Greeting_Trl.nebula
ECHO Updated Timestamp;>> Greeting_Trl.nebula
ECHO Updatedby Date;>> Greeting_Trl.nebula
ECHO };>> Greeting_Trl.nebula 
 ECHO @Package("Common or Core Functionality") type  Industrycode{ >> Industrycode.nebula
ECHO Org;>> Industrycode.nebula
ECHO !ID;>> Industrycode.nebula
ECHO Created Timestamp;>> Industrycode.nebula
ECHO Createdby Date;>> Industrycode.nebula
ECHO Description;>> Industrycode.nebula
ECHO Isactive YesNo;>> Industrycode.nebula
ECHO !Name;>> Industrycode.nebula
ECHO Updated Timestamp;>> Industrycode.nebula
ECHO Updatedby Date;>> Industrycode.nebula
ECHO Value String;>> Industrycode.nebula
ECHO };>> Industrycode.nebula 
 ECHO @Package("Common or Core Functionality") type  Interorg_Acct{ >> Interorg_Acct.nebula
ECHO !Orgto Org;>> Interorg_Acct.nebula
ECHO !Org;>> Interorg_Acct.nebula
ECHO !Acctschema;>> Interorg_Acct.nebula
ECHO Created Timestamp;>> Interorg_Acct.nebula
ECHO Createdby Date;>> Interorg_Acct.nebula
ECHO Intercompanyduefrom_Acct Account;>> Interorg_Acct.nebula
ECHO Intercompanydueto_Acct Account;>> Interorg_Acct.nebula
ECHO Isactive YesNo;>> Interorg_Acct.nebula
ECHO Updated Timestamp;>> Interorg_Acct.nebula
ECHO Updatedby Date;>> Interorg_Acct.nebula
ECHO };>> Interorg_Acct.nebula 
 ECHO @Package("Common or Core Functionality") type  Invoice{ >> Invoice.nebula
ECHO Orgtrx Org;>> Invoice.nebula
ECHO Org;>> Invoice.nebula
ECHO User;>> Invoice.nebula
ECHO Activity;>> Invoice.nebula
ECHO Bp_Bankaccount;>> Invoice.nebula
ECHO Bpartnersr Bpartner;>> Invoice.nebula
ECHO Bpartner;>> Invoice.nebula
ECHO Bpartner_Location;>> Invoice.nebula
ECHO Campaign;>> Invoice.nebula
ECHO Cashline;>> Invoice.nebula
ECHO Charge;>> Invoice.nebula
ECHO Conversiontype;>> Invoice.nebula
ECHO Currency;>> Invoice.nebula
ECHO Doctypetarget Doctype;>> Invoice.nebula
ECHO Doctype;>> Invoice.nebula
ECHO !ID;>> Invoice.nebula
ECHO Order;>> Invoice.nebula
ECHO Paymentterm;>> Invoice.nebula
ECHO Payment;>> Invoice.nebula
ECHO Project;>> Invoice.nebula
ECHO Chargeamt Amount;>> Invoice.nebula
ECHO Copyfrom YesNo;>> Invoice.nebula
ECHO Createfrom YesNo;>> Invoice.nebula
ECHO Created Timestamp;>> Invoice.nebula
ECHO Createdby Date;>> Invoice.nebula
ECHO Dateacct Date;>> Invoice.nebula
ECHO Dateinvoiced Date;>> Invoice.nebula
ECHO Dateordered Date;>> Invoice.nebula
ECHO Dateprinted Date;>> Invoice.nebula
ECHO Description;>> Invoice.nebula
ECHO Docaction Attr;>> Invoice.nebula
ECHO Docstatus Attr;>> Invoice.nebula
ECHO Documentno SeqNo;>> Invoice.nebula
ECHO Generateto YesNo;>> Invoice.nebula
ECHO Grandtotal Number;>> Invoice.nebula
ECHO Invoicecollectiontype YesNo;>> Invoice.nebula
ECHO Isactive YesNo;>> Invoice.nebula
ECHO Isapproved YesNo;>> Invoice.nebula
ECHO Isdiscountprinted YesNo;>> Invoice.nebula
ECHO Isindispute YesNo;>> Invoice.nebula
ECHO Ispaid YesNo;>> Invoice.nebula
ECHO Ispayschedulevalid YesNo;>> Invoice.nebula
ECHO Isprinted YesNo;>> Invoice.nebula
ECHO Isreturntrx YesNo;>> Invoice.nebula
ECHO Issotrx YesNo;>> Invoice.nebula
ECHO Isselfservice YesNo;>> Invoice.nebula
ECHO Istaxincluded YesNo;>> Invoice.nebula
ECHO Istransferred YesNo;>> Invoice.nebula
ECHO Pricelist;>> Invoice.nebula
ECHO Matchrequirementi YesNo;>> Invoice.nebula
ECHO Poreference String;>> Invoice.nebula
ECHO Paymentrule YesNo;>> Invoice.nebula
ECHO Posted YesNo;>> Invoice.nebula
ECHO Processed YesNo;>> Invoice.nebula
ECHO Processing YesNo;>> Invoice.nebula

ECHO Salesrep User;>> Invoice.nebula
ECHO Sendemail YesNo;>> Invoice.nebula
ECHO Totallines Number;>> Invoice.nebula
ECHO Updated Timestamp;>> Invoice.nebula
ECHO Updatedby Date;>> Invoice.nebula
ECHO User1 Elementvalue;>> Invoice.nebula
ECHO User2 Elementvalue;>> Invoice.nebula
ECHO };>> Invoice.nebula 
 ECHO @Package("Common or Core Functionality") type  Invoicebatch{ >> Invoicebatch.nebula
ECHO Org;>> Invoicebatch.nebula
ECHO Conversiontype;>> Invoicebatch.nebula
ECHO Currency;>> Invoicebatch.nebula
ECHO !ID;>> Invoicebatch.nebula
ECHO Controlamt Amount;>> Invoicebatch.nebula
ECHO Created Timestamp;>> Invoicebatch.nebula
ECHO Createdby Date;>> Invoicebatch.nebula
ECHO Datedoc Date;>> Invoicebatch.nebula
ECHO Description;>> Invoicebatch.nebula
ECHO Documentamt Amount;>> Invoicebatch.nebula
ECHO Documentno SeqNo;>> Invoicebatch.nebula
ECHO Isactive YesNo;>> Invoicebatch.nebula
ECHO Issotrx YesNo;>> Invoicebatch.nebula
ECHO Processed YesNo;>> Invoicebatch.nebula
ECHO Processing YesNo;>> Invoicebatch.nebula
ECHO Salesrep User;>> Invoicebatch.nebula
ECHO Updated Timestamp;>> Invoicebatch.nebula
ECHO Updatedby Date;>> Invoicebatch.nebula
ECHO };>> Invoicebatch.nebula 
 ECHO @Package("Common or Core Functionality") type  Invoicebatchline{ >> Invoicebatchline.nebula
ECHO Orgtrx Org;>> Invoicebatchline.nebula
ECHO Org;>> Invoicebatchline.nebula
ECHO User;>> Invoicebatchline.nebula
ECHO Activity;>> Invoicebatchline.nebula
ECHO Bpartner;>> Invoicebatchline.nebula
ECHO Bpartner_Location;>> Invoicebatchline.nebula
ECHO Charge;>> Invoicebatchline.nebula
ECHO Doctype;>> Invoicebatchline.nebula
ECHO !ID;>> Invoicebatchline.nebula
ECHO Invoicebatch;>> Invoicebatchline.nebula
ECHO Invoiceline;>> Invoicebatchline.nebula
ECHO Invoice;>> Invoicebatchline.nebula
ECHO Project;>> Invoicebatchline.nebula
ECHO Tax;>> Invoicebatchline.nebula
ECHO Created Timestamp;>> Invoicebatchline.nebula
ECHO Createdby Date;>> Invoicebatchline.nebula
ECHO Dateacct Date;>> Invoicebatchline.nebula
ECHO Dateinvoiced Date;>> Invoicebatchline.nebula
ECHO Description;>> Invoicebatchline.nebula
ECHO Documentno SeqNo;>> Invoicebatchline.nebula
ECHO Isactive YesNo;>> Invoicebatchline.nebula
ECHO Istaxincluded YesNo;>> Invoicebatchline.nebula
ECHO Line Number;>> Invoicebatchline.nebula
ECHO Linenetamt Amount;>> Invoicebatchline.nebula
ECHO Linetotalamt Amount;>> Invoicebatchline.nebula
ECHO Priceentered Price;>> Invoicebatchline.nebula
ECHO Processed YesNo;>> Invoicebatchline.nebula
ECHO Qtyentered Quantity;>> Invoicebatchline.nebula
ECHO Taxamt Amount;>> Invoicebatchline.nebula
ECHO Updated Timestamp;>> Invoicebatchline.nebula
ECHO Updatedby Date;>> Invoicebatchline.nebula
ECHO User1 Elementvalue;>> Invoicebatchline.nebula
ECHO User2 Elementvalue;>> Invoicebatchline.nebula
ECHO };>> Invoicebatchline.nebula 
 ECHO @Package("Common or Core Functionality") type  Invoiceline{ >> Invoiceline.nebula
ECHO Orgtrx Org;>> Invoiceline.nebula
ECHO Org;>> Invoiceline.nebula
ECHO Asset;>> Invoiceline.nebula
ECHO Activity;>> Invoiceline.nebula
ECHO Campaign;>> Invoiceline.nebula
ECHO Charge;>> Invoiceline.nebula
ECHO !ID;>> Invoiceline.nebula
ECHO Invoice;>> Invoiceline.nebula
ECHO Orderline;>> Invoiceline.nebula
ECHO Projectphase;>> Invoiceline.nebula
ECHO Projecttask;>> Invoiceline.nebula
ECHO Project;>> Invoiceline.nebula
ECHO Tax;>> Invoiceline.nebula
ECHO Uom;>> Invoiceline.nebula
ECHO Created Timestamp;>> Invoiceline.nebula
ECHO Createdby Date;>> Invoiceline.nebula
ECHO Description;>> Invoiceline.nebula
ECHO Isactive YesNo;>> Invoiceline.nebula
ECHO Isassetaddition YesNo;>> Invoiceline.nebula
ECHO Isdescription YesNo;>> Invoiceline.nebula
ECHO Isprinted YesNo;>> Invoiceline.nebula
ECHO Line Number;>> Invoiceline.nebula
ECHO Linedocstatus Attr;>> Invoiceline.nebula
ECHO Linenetamt Amount;>> Invoiceline.nebula
ECHO Linetotalamt Amount;>> Invoiceline.nebula
ECHO Attributesetinstance;>> Invoiceline.nebula
ECHO Inoutline;>> Invoiceline.nebula
ECHO Product;>> Invoiceline.nebula
ECHO Priceactual Price;>> Invoiceline.nebula
ECHO Priceentered Price;>> Invoiceline.nebula
ECHO Pricelimit Price;>> Invoiceline.nebula
ECHO Pricelist Price;>> Invoiceline.nebula
ECHO Processed YesNo;>> Invoiceline.nebula
ECHO Qtyentered Quantity;>> Invoiceline.nebula
ECHO Qtyinvoiced Quantity;>> Invoiceline.nebula
ECHO Rramt Amount;>> Invoiceline.nebula
ECHO Rrstartdate Date;>> Invoiceline.nebula

ECHO Resourceassignment;>> Invoiceline.nebula
ECHO Taxamt Amount;>> Invoiceline.nebula
ECHO Updated Timestamp;>> Invoiceline.nebula
ECHO Updatedby Date;>> Invoiceline.nebula
ECHO !User1 Elementvalue;>> Invoiceline.nebula
ECHO User2 Elementvalue;>> Invoiceline.nebula
ECHO };>> Invoiceline.nebula 
 ECHO @Package("Common or Core Functionality") type  Invoicepayschedule{ >> Invoicepayschedule.nebula
ECHO Org;>> Invoicepayschedule.nebula
ECHO !ID;>> Invoicepayschedule.nebula
ECHO Invoice;>> Invoicepayschedule.nebula
ECHO Payschedule;>> Invoicepayschedule.nebula
ECHO Created Timestamp;>> Invoicepayschedule.nebula
ECHO Createdby Date;>> Invoicepayschedule.nebula
ECHO Discountamt Amount;>> Invoicepayschedule.nebula
ECHO Discountdate Date;>> Invoicepayschedule.nebula
ECHO Dueamt Amount;>> Invoicepayschedule.nebula
ECHO Duedate Date;>> Invoicepayschedule.nebula
ECHO Isactive YesNo;>> Invoicepayschedule.nebula
ECHO Isvalid YesNo;>> Invoicepayschedule.nebula
ECHO Processed YesNo;>> Invoicepayschedule.nebula
ECHO Processing YesNo;>> Invoicepayschedule.nebula
ECHO Updated Timestamp;>> Invoicepayschedule.nebula
ECHO Updatedby Date;>> Invoicepayschedule.nebula
ECHO };>> Invoicepayschedule.nebula 
 ECHO @Package("Common or Core Functionality") type  Invoiceschedule{ >> Invoiceschedule.nebula
ECHO Org;>> Invoiceschedule.nebula
ECHO Amt Amount;>> Invoiceschedule.nebula
ECHO !ID;>> Invoiceschedule.nebula
ECHO Created Timestamp;>> Invoiceschedule.nebula
ECHO Createdby Date;>> Invoiceschedule.nebula
ECHO Description;>> Invoiceschedule.nebula
ECHO Eveninvoiceweek YesNo;>> Invoiceschedule.nebula
ECHO Invoiceday Count;>> Invoiceschedule.nebula
ECHO Invoicedaycutoff Number;>> Invoiceschedule.nebula
ECHO Invoicefrequency YesNo;>> Invoiceschedule.nebula
ECHO Invoiceweekday YesNo;>> Invoiceschedule.nebula
ECHO Invoiceweekdaycutoff YesNo;>> Invoiceschedule.nebula
ECHO Isactive YesNo;>> Invoiceschedule.nebula
ECHO Isamount YesNo;>> Invoiceschedule.nebula
ECHO Isdefault YesNo;>> Invoiceschedule.nebula
ECHO !Name;>> Invoiceschedule.nebula
ECHO Updated Timestamp;>> Invoiceschedule.nebula
ECHO Updatedby Date;>> Invoiceschedule.nebula
ECHO };>> Invoiceschedule.nebula 
 ECHO @Package("Common or Core Functionality") type  Invoicetax{ >> Invoicetax.nebula
ECHO Org;>> Invoicetax.nebula
ECHO !Invoice;>> Invoicetax.nebula
ECHO !Tax;>> Invoicetax.nebula
ECHO Created Timestamp;>> Invoicetax.nebula
ECHO Createdby Date;>> Invoicetax.nebula
ECHO Isactive YesNo;>> Invoicetax.nebula
ECHO Istaxincluded YesNo;>> Invoicetax.nebula
ECHO Processed YesNo;>> Invoicetax.nebula
ECHO Taxamt Amount;>> Invoicetax.nebula
ECHO Taxbaseamt Amount;>> Invoicetax.nebula
ECHO Updated Timestamp;>> Invoicetax.nebula
ECHO Updatedby Date;>> Invoicetax.nebula
ECHO };>> Invoicetax.nebula 
 ECHO @Package("Common or Core Functionality") type  Job{ >> Job.nebula
ECHO Org;>> Job.nebula
ECHO Jobcategory;>> Job.nebula
ECHO !ID;>> Job.nebula
ECHO Created Timestamp;>> Job.nebula
ECHO Createdby Date;>> Job.nebula
ECHO Description;>> Job.nebula
ECHO Help;>> Job.nebula
ECHO Isactive YesNo;>> Job.nebula
ECHO Isemployee YesNo;>> Job.nebula
ECHO !Name;>> Job.nebula
ECHO Updated Timestamp;>> Job.nebula
ECHO Updatedby Date;>> Job.nebula
ECHO };>> Job.nebula 
 ECHO @Package("Common or Core Functionality") type  Jobassignment{ >> Jobassignment.nebula
ECHO Org;>> Jobassignment.nebula
ECHO User;>> Jobassignment.nebula
ECHO !ID;>> Jobassignment.nebula
ECHO Job;>> Jobassignment.nebula
ECHO Created Timestamp;>> Jobassignment.nebula
ECHO Createdby Date;>> Jobassignment.nebula
ECHO Description;>> Jobassignment.nebula
ECHO Isactive YesNo;>> Jobassignment.nebula
ECHO Jobassignmenttype YesNo;>> Jobassignment.nebula
ECHO Updated Timestamp;>> Jobassignment.nebula
ECHO Updatedby Date;>> Jobassignment.nebula
ECHO Validfrom Date;>> Jobassignment.nebula
ECHO Validto Date;>> Jobassignment.nebula
ECHO };>> Jobassignment.nebula 
 ECHO @Package("Common or Core Functionality") type  Jobcategory{ >> Jobcategory.nebula
ECHO Org;>> Jobcategory.nebula
ECHO !ID;>> Jobcategory.nebula
ECHO Created Timestamp;>> Jobcategory.nebula
ECHO Createdby Date;>> Jobcategory.nebula
ECHO Description;>> Jobcategory.nebula
ECHO Help;>> Jobcategory.nebula
ECHO Isactive YesNo;>> Jobcategory.nebula
ECHO !Name;>> Jobcategory.nebula
ECHO Updated Timestamp;>> Jobcategory.nebula
ECHO Updatedby Date;>> Jobcategory.nebula
ECHO };>> Jobcategory.nebula 
 ECHO @Package("Common or Core Functionality") type  Jobremuneration{ >> Jobremuneration.nebula
ECHO Org;>> Jobremuneration.nebula
ECHO !ID;>> Jobremuneration.nebula
ECHO Job;>> Jobremuneration.nebula
ECHO Remuneration;>> Jobremuneration.nebula
ECHO Created Timestamp;>> Jobremuneration.nebula
ECHO Createdby Date;>> Jobremuneration.nebula
ECHO Description;>> Jobremuneration.nebula
ECHO Isactive YesNo;>> Jobremuneration.nebula
ECHO Updated Timestamp;>> Jobremuneration.nebula
ECHO Updatedby Date;>> Jobremuneration.nebula
ECHO Validfrom Date;>> Jobremuneration.nebula
ECHO Validto Date;>> Jobremuneration.nebula
ECHO };>> Jobremuneration.nebula 
 ECHO @Package("Common or Core Functionality") type  Landedcost{ >> Landedcost.nebula
ECHO Org;>> Landedcost.nebula
ECHO Invoiceline;>> Landedcost.nebula
ECHO !ID;>> Landedcost.nebula
ECHO Created Timestamp;>> Landedcost.nebula
ECHO Createdby Date;>> Landedcost.nebula
ECHO Description;>> Landedcost.nebula
ECHO Landedcostdistribution YesNo;>> Landedcost.nebula
ECHO Costelement;>> Landedcost.nebula
ECHO Inoutline;>> Landedcost.nebula
ECHO Inout;>> Landedcost.nebula
ECHO Product;>> Landedcost.nebula
ECHO Processing YesNo;>> Landedcost.nebula
ECHO Updated Timestamp;>> Landedcost.nebula
ECHO Updatedby Date;>> Landedcost.nebula
ECHO };>> Landedcost.nebula 
 ECHO @Package("Common or Core Functionality") type  Landedcostallocation{ >> Landedcostallocation.nebula
ECHO Org;>> Landedcostallocation.nebula
ECHO Amt Amount;>> Landedcostallocation.nebula
ECHO Base Number;>> Landedcostallocation.nebula
ECHO Invoiceline;>> Landedcostallocation.nebula
ECHO !ID;>> Landedcostallocation.nebula
ECHO Created Timestamp;>> Landedcostallocation.nebula
ECHO Createdby Date;>> Landedcostallocation.nebula
ECHO Attributesetinstance;>> Landedcostallocation.nebula
ECHO Costelement;>> Landedcostallocation.nebula
ECHO Product;>> Landedcostallocation.nebula
ECHO Qty Quantity;>> Landedcostallocation.nebula
ECHO Updated Timestamp;>> Landedcostallocation.nebula
ECHO Updatedby Date;>> Landedcostallocation.nebula
ECHO };>> Landedcostallocation.nebula 
 ECHO @Package("Common or Core Functionality") type  Lead{ >> Lead.nebula
ECHO Org;>> Lead.nebula
ECHO User;>> Lead.nebula
ECHO Address1 String;>> Lead.nebula
ECHO Address2 String;>> Lead.nebula
ECHO Bpname Name;>> Lead.nebula
ECHO Bp_Group;>> Lead.nebula
ECHO Bp_Size;>> Lead.nebula
ECHO Bp_Status;>> Lead.nebula
ECHO Bpartnersr Bpartner;>> Lead.nebula
ECHO Bpartner;>> Lead.nebula
ECHO Bpartner_Location;>> Lead.nebula
ECHO Campaign;>> Lead.nebula
ECHO City;>> Lead.nebula
ECHO Country;>> Lead.nebula
ECHO Greeting;>> Lead.nebula
ECHO Industrycode;>> Lead.nebula
ECHO Job;>> Lead.nebula
ECHO Leadqualification;>> Lead.nebula
ECHO !ID;>> Lead.nebula
ECHO Project;>> Lead.nebula
ECHO Region;>> Lead.nebula
ECHO Salesregion;>> Lead.nebula
ECHO City Name;>> Lead.nebula
ECHO Contactname Name;>> Lead.nebula
ECHO Createbp YesNo;>> Lead.nebula
ECHO Createproject YesNo;>> Lead.nebula
ECHO Createrequest YesNo;>> Lead.nebula
ECHO Created Timestamp;>> Lead.nebula
ECHO Createdby Date;>> Lead.nebula
ECHO DUNS;>> Lead.nebula
ECHO Description;>> Lead.nebula
ECHO Documentno SeqNo;>> Lead.nebula
ECHO Email;>> Lead.nebula
ECHO Fax;>> Lead.nebula
ECHO Help;>> Lead.nebula
ECHO Isactive YesNo;>> Lead.nebula
ECHO Leadrating YesNo;>> Lead.nebula
ECHO NAICS;>> Lead.nebula
ECHO !Name;>> Lead.nebula
ECHO Numberemployees Count;>> Lead.nebula
ECHO Phone PhoneNumber;>> Lead.nebula
ECHO Phone2 PhoneNumber;>> Lead.nebula
ECHO Postal PostalCode;>> Lead.nebula
ECHO Postal_Add PostalCode;>> Lead.nebula
ECHO Processed YesNo;>> Lead.nebula
ECHO Interestarea;>> Lead.nebula
ECHO Request;>> Lead.nebula
ECHO Source;>> Lead.nebula
ECHO Status;>> Lead.nebula
ECHO Regionname Name;>> Lead.nebula
ECHO Remote_Addr URL;>> Lead.nebula
ECHO Remote_Host Host;>> Lead.nebula
ECHO Salesrep User;>> Lead.nebula
ECHO Salesvolume Number;>> Lead.nebula
ECHO Sendnewemail YesNo;>> Lead.nebula
ECHO Summary Note;>> Lead.nebula
ECHO Title String;>> Lead.nebula
ECHO URL;>> Lead.nebula
ECHO Updated Timestamp;>> Lead.nebula
ECHO Updatedby Date;>> Lead.nebula
ECHO };>> Lead.nebula 
 ECHO @Package("Common or Core Functionality") type  Leadqualification{ >> Leadqualification.nebula
ECHO Org;>> Leadqualification.nebula
ECHO !ID;>> Leadqualification.nebula
ECHO Created Timestamp;>> Leadqualification.nebula
ECHO Createdby Date;>> Leadqualification.nebula
ECHO Description;>> Leadqualification.nebula
ECHO Isactive YesNo;>> Leadqualification.nebula
ECHO Isqualified YesNo;>> Leadqualification.nebula
ECHO !Name;>> Leadqualification.nebula
ECHO Updated Timestamp;>> Leadqualification.nebula
ECHO Updatedby Date;>> Leadqualification.nebula
ECHO Value String;>> Leadqualification.nebula
ECHO };>> Leadqualification.nebula 
 ECHO @Package("Common or Core Functionality") type  Location{ >> Location.nebula
ECHO Org;>> Location.nebula
ECHO Address1 String;>> Location.nebula
ECHO Address2 String;>> Location.nebula
ECHO Address3 String;>> Location.nebula
ECHO Address4 String;>> Location.nebula
ECHO City;>> Location.nebula
ECHO Country;>> Location.nebula
ECHO !ID;>> Location.nebula
ECHO Region;>> Location.nebula
ECHO City Name;>> Location.nebula
ECHO Created Timestamp;>> Location.nebula
ECHO Createdby Date;>> Location.nebula
ECHO Isactive YesNo;>> Location.nebula
ECHO Postal PostalCode;>> Location.nebula
ECHO Postal_Add PostalCode;>> Location.nebula
ECHO Regionname Name;>> Location.nebula
ECHO Updated Timestamp;>> Location.nebula
ECHO Updatedby Date;>> Location.nebula
ECHO };>> Location.nebula 
 ECHO @Package("Common or Core Functionality") type  Nonbusinessday{ >> Nonbusinessday.nebula
ECHO Org;>> Nonbusinessday.nebula
ECHO Calendar;>> Nonbusinessday.nebula
ECHO !ID;>> Nonbusinessday.nebula
ECHO Created Timestamp;>> Nonbusinessday.nebula
ECHO Createdby Date;>> Nonbusinessday.nebula
ECHO Date1 Date;>> Nonbusinessday.nebula
ECHO Isactive YesNo;>> Nonbusinessday.nebula
ECHO !Name;>> Nonbusinessday.nebula
ECHO Updated Timestamp;>> Nonbusinessday.nebula
ECHO Updatedby Date;>> Nonbusinessday.nebula
ECHO };>> Nonbusinessday.nebula 
 ECHO @Package("Common or Core Functionality") type  Order{ >> Order.nebula
ECHO Orgtrx Org;>> Order.nebula
ECHO Org;>> Order.nebula
ECHO User;>> Order.nebula
ECHO Bill_Bpartner Bpartner;>> Order.nebula
ECHO Bill_Location Bpartner_Location;>> Order.nebula
ECHO Bill_User User;>> Order.nebula
ECHO Activity;>> Order.nebula
ECHO Bp_Bankaccount;>> Order.nebula
ECHO Bpartnersr Bpartner;>> Order.nebula
ECHO Bpartner;>> Order.nebula
ECHO Bpartner_Location;>> Order.nebula
ECHO Campaign;>> Order.nebula
ECHO Cashline;>> Order.nebula
ECHO Charge;>> Order.nebula
ECHO Conversiontype;>> Order.nebula
ECHO Currency;>> Order.nebula
ECHO Doctypetarget Doctype;>> Order.nebula
ECHO Doctype;>> Order.nebula
ECHO !ID;>> Order.nebula
ECHO Paymentterm;>> Order.nebula
ECHO Payment;>> Order.nebula
ECHO Project;>> Order.nebula
ECHO Chargeamt Amount;>> Order.nebula
ECHO Copyfrom YesNo;>> Order.nebula
ECHO Created Timestamp;>> Order.nebula
ECHO Createdby Date;>> Order.nebula
ECHO Dateacct Date;>> Order.nebula
ECHO Dateordered Date;>> Order.nebula
ECHO Dateprinted Date;>> Order.nebula
ECHO Datepromised Date;>> Order.nebula
ECHO Deliveryrule YesNo;>> Order.nebula
ECHO Deliveryviarule YesNo;>> Order.nebula
ECHO Description;>> Order.nebula
ECHO Docaction Attr;>> Order.nebula
ECHO Docstatus Attr;>> Order.nebula
ECHO Documentno SeqNo;>> Order.nebula
ECHO Freightamt Amount;>> Order.nebula
ECHO Freightcostrule YesNo;>> Order.nebula
ECHO Grandtotal Number;>> Order.nebula
ECHO Invoicerule YesNo;>> Order.nebula
ECHO Isactive YesNo;>> Order.nebula
ECHO Isapproved YesNo;>> Order.nebula
ECHO Iscreditapproved YesNo;>> Order.nebula
ECHO Isdelivered YesNo;>> Order.nebula
ECHO Isdiscountprinted YesNo;>> Order.nebula
ECHO Isdropship YesNo;>> Order.nebula
ECHO Isinvoiced YesNo;>> Order.nebula
ECHO Isprinted YesNo;>> Order.nebula
ECHO Isreturntrx YesNo;>> Order.nebula
ECHO Issotrx YesNo;>> Order.nebula
ECHO Isselected YesNo;>> Order.nebula
ECHO Isselfservice YesNo;>> Order.nebula
ECHO Istaxincluded YesNo;>> Order.nebula
ECHO Istransferred YesNo;>> Order.nebula
ECHO Pricelist;>> Order.nebula
ECHO Rmacategory;>> Order.nebula
ECHO Returnpolicy;>> Order.nebula
ECHO Shipper;>> Order.nebula
ECHO Warehouse;>> Order.nebula
ECHO Orig_Inout Inout;>> Order.nebula
ECHO Orig_Order Order;>> Order.nebula
ECHO Poreference String;>> Order.nebula


ECHO Paymentrule YesNo;>> Order.nebula
ECHO Posted YesNo;>> Order.nebula
ECHO Priorityrule YesNo;>> Order.nebula
ECHO Processed YesNo;>> Order.nebula
ECHO Processing YesNo;>> Order.nebula
ECHO Ref_Order Order;>> Order.nebula
ECHO Salesrep User;>> Order.nebula
ECHO Sendemail YesNo;>> Order.nebula
ECHO Totallines Number;>> Order.nebula
ECHO Updated Timestamp;>> Order.nebula
ECHO Updatedby Date;>> Order.nebula
ECHO User1 Elementvalue;>> Order.nebula
ECHO User2 Elementvalue;>> Order.nebula
ECHO Volume Long;>> Order.nebula
ECHO Weight;>> Order.nebula
ECHO };>> Order.nebula 
 ECHO @Package("Common or Core Functionality") type  Orderline{ >> Orderline.nebula
ECHO Orgtrx Org;>> Orderline.nebula
ECHO Org;>> Orderline.nebula
ECHO Activity;>> Orderline.nebula
ECHO Bpartner;>> Orderline.nebula
ECHO Bpartner_Location;>> Orderline.nebula
ECHO Campaign;>> Orderline.nebula
ECHO Charge;>> Orderline.nebula
ECHO Currency;>> Orderline.nebula
ECHO !ID;>> Orderline.nebula
ECHO Order;>> Orderline.nebula
ECHO Projectphase;>> Orderline.nebula
ECHO Projecttask;>> Orderline.nebula
ECHO Project;>> Orderline.nebula
ECHO Tax;>> Orderline.nebula
ECHO Uom;>> Orderline.nebula
ECHO Created Timestamp;>> Orderline.nebula
ECHO Createdby Date;>> Orderline.nebula
ECHO Datedelivered Date;>> Orderline.nebula
ECHO Dateinvoiced Date;>> Orderline.nebula
ECHO Dateordered Date;>> Orderline.nebula
ECHO Datepromised Date;>> Orderline.nebula
ECHO Description;>> Orderline.nebula
ECHO Discount Number;>> Orderline.nebula
ECHO Freightamt Amount;>> Orderline.nebula
ECHO Isactive YesNo;>> Orderline.nebula
ECHO Isdescription YesNo;>> Orderline.nebula
ECHO Line Number;>> Orderline.nebula
ECHO Linenetamt Amount;>> Orderline.nebula
ECHO Attributesetinstance;>> Orderline.nebula
ECHO Product;>> Orderline.nebula
ECHO Shipper;>> Orderline.nebula
ECHO Warehouse;>> Orderline.nebula
ECHO Orig_Inoutline Inoutline;>> Orderline.nebula
ECHO Orig_Orderline Orderline;>> Orderline.nebula
ECHO Priceactual Price;>> Orderline.nebula
ECHO Pricecost Number;>> Orderline.nebula
ECHO Priceentered Price;>> Orderline.nebula
ECHO Pricelimit Price;>> Orderline.nebula
ECHO Pricelist Price;>> Orderline.nebula
ECHO Processed YesNo;>> Orderline.nebula
ECHO Qtyallocated Quantity;>> Orderline.nebula
ECHO Qtydedicated Quantity;>> Orderline.nebula
ECHO Qtydelivered Quantity;>> Orderline.nebula
ECHO Qtyentered Quantity;>> Orderline.nebula
ECHO Qtyinvoiced Quantity;>> Orderline.nebula
ECHO Qtylostsales Quantity;>> Orderline.nebula
ECHO Qtyordered Quantity;>> Orderline.nebula
ECHO Qtyreserved Quantity;>> Orderline.nebula
ECHO Qtyreturned Quantity;>> Orderline.nebula
ECHO Rramt Amount;>> Orderline.nebula
ECHO Rrstartdate Date;>> Orderline.nebula
ECHO Ref_Orderline Orderline;>> Orderline.nebula
ECHO Resourceassignment;>> Orderline.nebula
ECHO Updated Timestamp;>> Orderline.nebula
ECHO Updatedby Date;>> Orderline.nebula
ECHO User1 Elementvalue;>> Orderline.nebula
ECHO User2 Elementvalue;>> Orderline.nebula
ECHO };>> Orderline.nebula 
 ECHO @Package("Common or Core Functionality") type  Ordertax{ >> Ordertax.nebula
ECHO Org;>> Ordertax.nebula
ECHO !Order;>> Ordertax.nebula
ECHO !Tax;>> Ordertax.nebula
ECHO Created Timestamp;>> Ordertax.nebula
ECHO Createdby Date;>> Ordertax.nebula
ECHO Isactive YesNo;>> Ordertax.nebula
ECHO Istaxincluded YesNo;>> Ordertax.nebula
ECHO Processed YesNo;>> Ordertax.nebula
ECHO Taxamt Amount;>> Ordertax.nebula
ECHO Taxbaseamt Amount;>> Ordertax.nebula
ECHO Updated Timestamp;>> Ordertax.nebula
ECHO Updatedby Date;>> Ordertax.nebula
ECHO };>> Ordertax.nebula 
 ECHO @Package("Common or Core Functionality") type  Orgassignment{ >> Orgassignment.nebula
ECHO Org;>> Orgassignment.nebula
ECHO User;>> Orgassignment.nebula
ECHO !ID;>> Orgassignment.nebula
ECHO Created Timestamp;>> Orgassignment.nebula
ECHO Createdby Date;>> Orgassignment.nebula
ECHO Description;>> Orgassignment.nebula
ECHO Isactive YesNo;>> Orgassignment.nebula
ECHO Orgassignmenttype YesNo;>> Orgassignment.nebula
ECHO Updated Timestamp;>> Orgassignment.nebula
ECHO Updatedby Date;>> Orgassignment.nebula
ECHO Validfrom Date;>> Orgassignment.nebula
ECHO Validto Date;>> Orgassignment.nebula
ECHO };>> Orgassignment.nebula 
 ECHO @Package("Common or Core Functionality") type  Payment{ >> Payment.nebula
ECHO Orgtrx Org;>> Payment.nebula
ECHO Org;>> Payment.nebula
ECHO A_City Name;>> Payment.nebula
ECHO A_Country Name;>> Payment.nebula
ECHO A_Email Email;>> Payment.nebula
ECHO Ident_Dl String;>> Payment.nebula
ECHO Ident_Ssn SSN;>> Payment.nebula
ECHO !A_Name Name;>> Payment.nebula
ECHO A_State Name;>> Payment.nebula
ECHO A_Street Name;>> Payment.nebula
ECHO A_Zip Zip;>> Payment.nebula
ECHO AccountNo;>> Payment.nebula
ECHO Activity;>> Payment.nebula
ECHO Bp_Bankaccount;>> Payment.nebula
ECHO Bpartner;>> Payment.nebula
ECHO Bankaccount;>> Payment.nebula
ECHO Campaign;>> Payment.nebula
ECHO Charge;>> Payment.nebula
ECHO Conversiontype;>> Payment.nebula
ECHO Currency;>> Payment.nebula
ECHO Doctype;>> Payment.nebula
ECHO Invoice;>> Payment.nebula
ECHO Order;>> Payment.nebula
ECHO Paymentbatch;>> Payment.nebula
ECHO !ID;>> Payment.nebula
ECHO Project;>> Payment.nebula
ECHO Chargeamt Amount;>> Payment.nebula
ECHO Checkno Code;>> Payment.nebula
ECHO Created Timestamp;>> Payment.nebula
ECHO Createdby Date;>> Payment.nebula
ECHO Creditcardexpmm Number;>> Payment.nebula
ECHO Creditcardexpyy Number;>> Payment.nebula
ECHO Creditcardnumber Code;>> Payment.nebula
ECHO Creditcardtype YesNo;>> Payment.nebula
ECHO Dateacct Date;>> Payment.nebula
ECHO Datetrx Date;>> Payment.nebula
ECHO Description;>> Payment.nebula
ECHO Discountamt Amount;>> Payment.nebula
ECHO Docaction Attr;>> Payment.nebula
ECHO Docstatus Attr;>> Payment.nebula
ECHO Documentno SeqNo;>> Payment.nebula
ECHO Isactive YesNo;>> Payment.nebula
ECHO Isallocated YesNo;>> Payment.nebula
ECHO Isapproved YesNo;>> Payment.nebula
ECHO Isdelayedcapture YesNo;>> Payment.nebula
ECHO Isonline YesNo;>> Payment.nebula
ECHO Isoverunderpayment YesNo;>> Payment.nebula
ECHO Isprepayment YesNo;>> Payment.nebula
ECHO Isreceipt YesNo;>> Payment.nebula
ECHO Isreconciled YesNo;>> Payment.nebula
ECHO Isselfservice YesNo;>> Payment.nebula
ECHO Micr String;>> Payment.nebula
ECHO Oprocessing YesNo;>> Payment.nebula
ECHO Orig_Trxid String;>> Payment.nebula
ECHO Overunderamt Amount;>> Payment.nebula
ECHO Ponum String;>> Payment.nebula
ECHO Payamt Amount;>> Payment.nebula
ECHO Posted YesNo;>> Payment.nebula
ECHO Processed YesNo;>> Payment.nebula
ECHO Processing YesNo;>> Payment.nebula
ECHO R_Authcode Attr;>> Payment.nebula
ECHO R_Authcode_dc String;>> Payment.nebula
ECHO R_Avsaddr YesNo;>> Payment.nebula
ECHO R_Avszip YesNo;>> Payment.nebula
ECHO R_Cvv2match YesNo;>> Payment.nebula
ECHO R_Info Description;>> Payment.nebula
ECHO R_Pnref String;>> Payment.nebula
ECHO R_Pnref_dc String;>> Payment.nebula
ECHO R_Respmsg MSG;>> Payment.nebula
ECHO R_Result String;>> Payment.nebula
ECHO Ref_Payment Payment;>> Payment.nebula
ECHO Routingno Code;>> Payment.nebula
ECHO Swipe String;>> Payment.nebula
ECHO Taxamt Amount;>> Payment.nebula
ECHO Tendertype YesNo;>> Payment.nebula
ECHO Trxtype YesNo;>> Payment.nebula
ECHO Updated Timestamp;>> Payment.nebula
ECHO Updatedby Date;>> Payment.nebula
ECHO User1 Elementvalue;>> Payment.nebula
ECHO User2 Elementvalue;>> Payment.nebula
ECHO Voiceauthcode Attr;>> Payment.nebula
ECHO Writeoffamt Amount;>> Payment.nebula
ECHO };>> Payment.nebula 
 ECHO @Package("Common or Core Functionality") type  Paymentallocate{ >> Paymentallocate.nebula
ECHO Org;>> Paymentallocate.nebula
ECHO Amount;>> Paymentallocate.nebula
ECHO Allocationline;>> Paymentallocate.nebula
ECHO Invoice;>> Paymentallocate.nebula
ECHO !ID;>> Paymentallocate.nebula
ECHO Payment;>> Paymentallocate.nebula
ECHO Created Timestamp;>> Paymentallocate.nebula
ECHO Createdby Date;>> Paymentallocate.nebula
ECHO Discountamt Amount;>> Paymentallocate.nebula
ECHO Invoiceamt Amount;>> Paymentallocate.nebula
ECHO Isactive YesNo;>> Paymentallocate.nebula
ECHO Overunderamt Amount;>> Paymentallocate.nebula
ECHO Updated Timestamp;>> Paymentallocate.nebula
ECHO Updatedby Date;>> Paymentallocate.nebula
ECHO Writeoffamt Amount;>> Paymentallocate.nebula
ECHO };>> Paymentallocate.nebula 
 ECHO @Package("Common or Core Functionality") type  Paymentbatch{ >> Paymentbatch.nebula
ECHO Org;>> Paymentbatch.nebula
ECHO !ID;>> Paymentbatch.nebula
ECHO Paymentprocessor;>> Paymentbatch.nebula
ECHO Created Timestamp;>> Paymentbatch.nebula
ECHO Createdby Date;>> Paymentbatch.nebula
ECHO Documentno SeqNo;>> Paymentbatch.nebula
ECHO Isactive YesNo;>> Paymentbatch.nebula
ECHO !Name;>> Paymentbatch.nebula
ECHO Processed YesNo;>> Paymentbatch.nebula
ECHO Processing YesNo;>> Paymentbatch.nebula
ECHO Processingdate Date;>> Paymentbatch.nebula
ECHO Updated Timestamp;>> Paymentbatch.nebula
ECHO Updatedby Date;>> Paymentbatch.nebula
ECHO };>> Paymentbatch.nebula 
 ECHO @Package("Common or Core Functionality") type  Paymentprocessor{ >> Paymentprocessor.nebula
ECHO Org;>> Paymentprocessor.nebula
ECHO Sequence;>> Paymentprocessor.nebula
ECHO Acceptamex YesNo;>> Paymentprocessor.nebula
ECHO Acceptatm YesNo;>> Paymentprocessor.nebula
ECHO Acceptcheck YesNo;>> Paymentprocessor.nebula
ECHO Acceptcorporate YesNo;>> Paymentprocessor.nebula
ECHO Acceptdiners YesNo;>> Paymentprocessor.nebula
ECHO Acceptdirectdebit YesNo;>> Paymentprocessor.nebula
ECHO Acceptdirectdeposit YesNo;>> Paymentprocessor.nebula
ECHO Acceptdiscover YesNo;>> Paymentprocessor.nebula
ECHO Acceptmc YesNo;>> Paymentprocessor.nebula
ECHO Acceptvisa YesNo;>> Paymentprocessor.nebula
ECHO Bankaccount;>> Paymentprocessor.nebula
ECHO Currency;>> Paymentprocessor.nebula
ECHO !ID;>> Paymentprocessor.nebula
ECHO Commission Number;>> Paymentprocessor.nebula
ECHO Costpertrx Number;>> Paymentprocessor.nebula
ECHO Created Timestamp;>> Paymentprocessor.nebula
ECHO Createdby Date;>> Paymentprocessor.nebula
ECHO Description;>> Paymentprocessor.nebula
ECHO Hostaddress String;>> Paymentprocessor.nebula
ECHO Hostport Long;>> Paymentprocessor.nebula
ECHO Isactive YesNo;>> Paymentprocessor.nebula
ECHO Minimumamt Amount;>> Paymentprocessor.nebula
ECHO !Name;>> Paymentprocessor.nebula
ECHO Partnerid ID;>> Paymentprocessor.nebula
ECHO Password String;>> Paymentprocessor.nebula
ECHO Payprocessorclass Name;>> Paymentprocessor.nebula
ECHO Proxyaddress String;>> Paymentprocessor.nebula
ECHO Proxylogon String;>> Paymentprocessor.nebula
ECHO Proxypassword String;>> Paymentprocessor.nebula
ECHO Proxyport String;>> Paymentprocessor.nebula
ECHO Requirevv YesNo;>> Paymentprocessor.nebula
ECHO Updated Timestamp;>> Paymentprocessor.nebula
ECHO Updatedby Date;>> Paymentprocessor.nebula
ECHO Userid Name;>> Paymentprocessor.nebula
ECHO Vendorid ID;>> Paymentprocessor.nebula
ECHO };>> Paymentprocessor.nebula 
 ECHO @Package("Common or Core Functionality") type  Paymentterm{ >> Paymentterm.nebula
ECHO Org;>> Paymentterm.nebula
ECHO Afterdelivery YesNo;>> Paymentterm.nebula
ECHO !ID;>> Paymentterm.nebula
ECHO Created Timestamp;>> Paymentterm.nebula
ECHO Createdby Date;>> Paymentterm.nebula
ECHO Description;>> Paymentterm.nebula
ECHO Discount Number;>> Paymentterm.nebula
ECHO Discount2 Number;>> Paymentterm.nebula
ECHO Discountdays Count;>> Paymentterm.nebula
ECHO Discountdays2 Count;>> Paymentterm.nebula
ECHO Documentnote Note;>> Paymentterm.nebula
ECHO Fixmonthcutoff Number;>> Paymentterm.nebula
ECHO Fixmonthday Count;>> Paymentterm.nebula
ECHO Fixmonthoffset Count;>> Paymentterm.nebula
ECHO Gracedays Count;>> Paymentterm.nebula
ECHO Isactive YesNo;>> Paymentterm.nebula
ECHO Isdefault YesNo;>> Paymentterm.nebula
ECHO Isduefixed YesNo;>> Paymentterm.nebula
ECHO Isnextbusinessday YesNo;>> Paymentterm.nebula
ECHO Isvalid YesNo;>> Paymentterm.nebula
ECHO !Name;>> Paymentterm.nebula
ECHO Netday YesNo;>> Paymentterm.nebula
ECHO Netdays Count;>> Paymentterm.nebula
ECHO Processing YesNo;>> Paymentterm.nebula
ECHO Updated Timestamp;>> Paymentterm.nebula
ECHO Updatedby Date;>> Paymentterm.nebula
ECHO Value String;>> Paymentterm.nebula
ECHO };>> Paymentterm.nebula 
 ECHO @Package("Common or Core Functionality") type  Paymentterm_Trl{ >> Paymentterm_Trl.nebula
ECHO !Ad_Language Language;>> Paymentterm_Trl.nebula
ECHO Org;>> Paymentterm_Trl.nebula
ECHO !Paymentterm;>> Paymentterm_Trl.nebula
ECHO Created Timestamp;>> Paymentterm_Trl.nebula
ECHO Createdby Date;>> Paymentterm_Trl.nebula
ECHO Description;>> Paymentterm_Trl.nebula
ECHO Documentnote Note;>> Paymentterm_Trl.nebula
ECHO Isactive YesNo;>> Paymentterm_Trl.nebula
ECHO Istranslated YesNo;>> Paymentterm_Trl.nebula
ECHO !Name;>> Paymentterm_Trl.nebula
ECHO Updated Timestamp;>> Paymentterm_Trl.nebula
ECHO Updatedby Date;>> Paymentterm_Trl.nebula
ECHO };>> Paymentterm_Trl.nebula 
 ECHO @Package("Common or Core Functionality") type  Payschedule{ >> Payschedule.nebula
ECHO Org;>> Payschedule.nebula
ECHO !ID;>> Payschedule.nebula
ECHO Paymentterm;>> Payschedule.nebula
ECHO Created Timestamp;>> Payschedule.nebula
ECHO Createdby Date;>> Payschedule.nebula
ECHO Discount Number;>> Payschedule.nebula
ECHO Discountdays Count;>> Payschedule.nebula
ECHO Gracedays Count;>> Payschedule.nebula
ECHO Isactive YesNo;>> Payschedule.nebula
ECHO Isvalid YesNo;>> Payschedule.nebula
ECHO Netday YesNo;>> Payschedule.nebula
ECHO Netdays Count;>> Payschedule.nebula
ECHO Percentage Number;>> Payschedule.nebula
ECHO Updated Timestamp;>> Payschedule.nebula
ECHO Updatedby Date;>> Payschedule.nebula
ECHO };>> Payschedule.nebula 
 ECHO @Package("Common or Core Functionality") type  Payselection{ >> Payselection.nebula
ECHO Org;>> Payselection.nebula
ECHO Bankaccount;>> Payselection.nebula
ECHO !ID;>> Payselection.nebula
ECHO Createfrom YesNo;>> Payselection.nebula
ECHO Created Timestamp;>> Payselection.nebula
ECHO Createdby Date;>> Payselection.nebula
ECHO Description;>> Payselection.nebula
ECHO Isactive YesNo;>> Payselection.nebula
ECHO Isapproved YesNo;>> Payselection.nebula
ECHO !Name;>> Payselection.nebula
ECHO Paydate Date;>> Payselection.nebula
ECHO Processed YesNo;>> Payselection.nebula
ECHO Processing YesNo;>> Payselection.nebula
ECHO Totalamt Amount;>> Payselection.nebula
ECHO Updated Timestamp;>> Payselection.nebula
ECHO Updatedby Date;>> Payselection.nebula
ECHO };>> Payselection.nebula 
 ECHO @Package("Common or Core Functionality") type  Payselectioncheck{ >> Payselectioncheck.nebula
ECHO Org;>> Payselectioncheck.nebula
ECHO Bp_Bankaccount;>> Payselectioncheck.nebula
ECHO Bpartner;>> Payselectioncheck.nebula
ECHO !ID;>> Payselectioncheck.nebula
ECHO Payselection;>> Payselectioncheck.nebula
ECHO Payment;>> Payselectioncheck.nebula
ECHO Created Timestamp;>> Payselectioncheck.nebula
ECHO Createdby Date;>> Payselectioncheck.nebula
ECHO Discountamt Amount;>> Payselectioncheck.nebula
ECHO Documentno SeqNo;>> Payselectioncheck.nebula
ECHO Isactive YesNo;>> Payselectioncheck.nebula
ECHO Isprinted YesNo;>> Payselectioncheck.nebula
ECHO Isreceipt YesNo;>> Payselectioncheck.nebula
ECHO Payamt Amount;>> Payselectioncheck.nebula
ECHO Paymentrule YesNo;>> Payselectioncheck.nebula
ECHO Processed YesNo;>> Payselectioncheck.nebula
ECHO Qty Quantity;>> Payselectioncheck.nebula
ECHO Updated Timestamp;>> Payselectioncheck.nebula
ECHO Updatedby Date;>> Payselectioncheck.nebula
ECHO };>> Payselectioncheck.nebula 
 ECHO @Package("Common or Core Functionality") type  Payselectionline{ >> Payselectionline.nebula
ECHO Org;>> Payselectionline.nebula
ECHO Invoice;>> Payselectionline.nebula
ECHO Payselectioncheck;>> Payselectionline.nebula
ECHO !ID;>> Payselectionline.nebula
ECHO Payselection;>> Payselectionline.nebula
ECHO Created Timestamp;>> Payselectionline.nebula
ECHO Createdby Date;>> Payselectionline.nebula
ECHO Description;>> Payselectionline.nebula
ECHO Differenceamt Amount;>> Payselectionline.nebula
ECHO Discountamt Amount;>> Payselectionline.nebula
ECHO Isactive YesNo;>> Payselectionline.nebula
ECHO Iscancelled YesNo;>> Payselectionline.nebula
ECHO Ismanual YesNo;>> Payselectionline.nebula
ECHO Issotrx YesNo;>> Payselectionline.nebula
ECHO Line Number;>> Payselectionline.nebula
ECHO Openamt Amount;>> Payselectionline.nebula
ECHO Payamt Amount;>> Payselectionline.nebula
ECHO Paymentrule YesNo;>> Payselectionline.nebula
ECHO Processed YesNo;>> Payselectionline.nebula
ECHO Updated Timestamp;>> Payselectionline.nebula
ECHO Updatedby Date;>> Payselectionline.nebula
ECHO };>> Payselectionline.nebula 
 ECHO @Package("Common or Core Functionality") type  Period{ >> Period.nebula
ECHO Org;>> Period.nebula
ECHO !ID;>> Period.nebula
ECHO Year;>> Period.nebula
ECHO Created Timestamp;>> Period.nebula
ECHO Createdby Date;>> Period.nebula
ECHO Enddate Date;>> Period.nebula
ECHO Isactive YesNo;>> Period.nebula
ECHO !Name;>> Period.nebula
ECHO Periodno SeqNo;>> Period.nebula
ECHO Periodtype YesNo;>> Period.nebula
ECHO Processing YesNo;>> Period.nebula
ECHO Startdate Date;>> Period.nebula
ECHO Updated Timestamp;>> Period.nebula
ECHO Updatedby Date;>> Period.nebula
ECHO };>> Period.nebula 
 ECHO @Package("Common or Core Functionality") type  Periodcontrol{ >> Periodcontrol.nebula
ECHO Org;>> Periodcontrol.nebula
ECHO !ID;>> Periodcontrol.nebula
ECHO Period;>> Periodcontrol.nebula
ECHO Created Timestamp;>> Periodcontrol.nebula
ECHO Createdby Date;>> Periodcontrol.nebula
ECHO Docbasetype Attr;>> Periodcontrol.nebula
ECHO Isactive YesNo;>> Periodcontrol.nebula
ECHO Periodaction YesNo;>> Periodcontrol.nebula
ECHO Periodstatus YesNo;>> Periodcontrol.nebula
ECHO Processing YesNo;>> Periodcontrol.nebula
ECHO Updated Timestamp;>> Periodcontrol.nebula
ECHO Updatedby Date;>> Periodcontrol.nebula
ECHO };>> Periodcontrol.nebula 
 ECHO @Package("Common or Core Functionality") type  Phase{ >> Phase.nebula
ECHO Org;>> Phase.nebula
ECHO !ID;>> Phase.nebula
ECHO Projecttype;>> Phase.nebula
ECHO Created Timestamp;>> Phase.nebula
ECHO Createdby Date;>> Phase.nebula
ECHO Description;>> Phase.nebula
ECHO Help;>> Phase.nebula
ECHO Isactive YesNo;>> Phase.nebula
ECHO Product;>> Phase.nebula
ECHO !Name;>> Phase.nebula
ECHO SeqNo;>> Phase.nebula
ECHO Standardqty Quantity;>> Phase.nebula
ECHO Updated Timestamp;>> Phase.nebula
ECHO Updatedby Date;>> Phase.nebula
ECHO };>> Phase.nebula 
 ECHO @Package("Common or Core Functionality") type  Pos{ >> Pos.nebula
ECHO Org;>> Pos.nebula
ECHO Bpartnercashtrx Bpartner;>> Pos.nebula
ECHO Cashbook;>> Pos.nebula
ECHO Doctype;>> Pos.nebula
ECHO Poskeylayout;>> Pos.nebula
ECHO !ID;>> Pos.nebula
ECHO Created Timestamp;>> Pos.nebula
ECHO Createdby Date;>> Pos.nebula
ECHO Description;>> Pos.nebula
ECHO Help;>> Pos.nebula
ECHO Isactive YesNo;>> Pos.nebula
ECHO Ismodifyprice YesNo;>> Pos.nebula
ECHO Pricelist;>> Pos.nebula
ECHO Warehouse;>> Pos.nebula
ECHO !Name;>> Pos.nebula
ECHO Printername Name;>> Pos.nebula
ECHO Salesrep User;>> Pos.nebula
ECHO Updated Timestamp;>> Pos.nebula
ECHO Updatedby Date;>> Pos.nebula
ECHO };>> Pos.nebula 
 ECHO @Package("Common or Core Functionality") type  Poskey{ >> Poskey.nebula
ECHO Org;>> Poskey.nebula
ECHO Printcolor;>> Poskey.nebula
ECHO Poskeylayout;>> Poskey.nebula
ECHO !ID;>> Poskey.nebula
ECHO Created Timestamp;>> Poskey.nebula
ECHO Createdby Date;>> Poskey.nebula
ECHO Description;>> Poskey.nebula
ECHO Isactive YesNo;>> Poskey.nebula
ECHO Product;>> Poskey.nebula
ECHO !Name;>> Poskey.nebula
ECHO Qty Quantity;>> Poskey.nebula
ECHO SeqNo;>> Poskey.nebula
ECHO Updated Timestamp;>> Poskey.nebula
ECHO Updatedby Date;>> Poskey.nebula
ECHO };>> Poskey.nebula 
 ECHO @Package("Common or Core Functionality") type  Poskeylayout{ >> Poskeylayout.nebula
ECHO Org;>> Poskeylayout.nebula
ECHO !ID;>> Poskeylayout.nebula
ECHO Created Timestamp;>> Poskeylayout.nebula
ECHO Createdby Date;>> Poskeylayout.nebula
ECHO Description;>> Poskeylayout.nebula
ECHO Help;>> Poskeylayout.nebula
ECHO Isactive YesNo;>> Poskeylayout.nebula
ECHO !Name;>> Poskeylayout.nebula
ECHO Updated Timestamp;>> Poskeylayout.nebula
ECHO Updatedby Date;>> Poskeylayout.nebula
ECHO };>> Poskeylayout.nebula 
 ECHO @Package("Common or Core Functionality") type  Project{ >> Project.nebula
ECHO Org;>> Project.nebula
ECHO User;>> Project.nebula
ECHO Bpartnersr Bpartner;>> Project.nebula
ECHO Bpartner;>> Project.nebula
ECHO Bpartner_Location;>> Project.nebula
ECHO Campaign;>> Project.nebula
ECHO Currency;>> Project.nebula
ECHO Paymentterm;>> Project.nebula
ECHO Phase;>> Project.nebula
ECHO Projecttype;>> Project.nebula
ECHO !ID;>> Project.nebula
ECHO Salesregion;>> Project.nebula
ECHO Committedamt Amount;>> Project.nebula
ECHO Committedqty Quantity;>> Project.nebula
ECHO Copyfrom YesNo;>> Project.nebula
ECHO Created Timestamp;>> Project.nebula
ECHO Createdby Date;>> Project.nebula
ECHO Datecontract Date;>> Project.nebula
ECHO Datefinish Date;>> Project.nebula
ECHO Description;>> Project.nebula
ECHO Generateto YesNo;>> Project.nebula
ECHO Invoicedamt Amount;>> Project.nebula
ECHO Invoicedqty Quantity;>> Project.nebula
ECHO Isactive YesNo;>> Project.nebula
ECHO Iscommitceiling YesNo;>> Project.nebula
ECHO Iscommitment YesNo;>> Project.nebula
ECHO Issummary YesNo;>> Project.nebula
ECHO Pricelist_Version;>> Project.nebula
ECHO Warehouse;>> Project.nebula
ECHO !Name;>> Project.nebula
ECHO Note;>> Project.nebula
ECHO Poreference String;>> Project.nebula
ECHO Plannedamt Amount;>> Project.nebula
ECHO Planneddate Date;>> Project.nebula
ECHO Plannedmarginamt Amount;>> Project.nebula
ECHO Plannedqty Quantity;>> Project.nebula
ECHO Probability;>> Project.nebula
ECHO Processed YesNo;>> Project.nebula
ECHO Processing YesNo;>> Project.nebula
ECHO Projinvoicerule YesNo;>> Project.nebula
ECHO Projectbalanceamt Amount;>> Project.nebula
ECHO Projectcategory YesNo;>> Project.nebula
ECHO Projectlinelevel YesNo;>> Project.nebula
ECHO Salesrep User;>> Project.nebula
ECHO Updated Timestamp;>> Project.nebula
ECHO Updatedby Date;>> Project.nebula
ECHO Value String;>> Project.nebula
ECHO };>> Project.nebula 
 ECHO @Package("Common or Core Functionality") type  Project_Acct{ >> Project_Acct.nebula
ECHO Org;>> Project_Acct.nebula
ECHO !Acctschema;>> Project_Acct.nebula
ECHO !Project;>> Project_Acct.nebula
ECHO Created Timestamp;>> Project_Acct.nebula
ECHO Createdby Date;>> Project_Acct.nebula
ECHO Isactive YesNo;>> Project_Acct.nebula
ECHO Pj_Asset_acct Account;>> Project_Acct.nebula
ECHO Pj_Wip_acct Account;>> Project_Acct.nebula
ECHO Updated Timestamp;>> Project_Acct.nebula
ECHO Updatedby Date;>> Project_Acct.nebula
ECHO };>> Project_Acct.nebula 
 ECHO @Package("Common or Core Functionality") type  Projectissue{ >> Projectissue.nebula
ECHO Org;>> Projectissue.nebula
ECHO !ID;>> Projectissue.nebula
ECHO Project;>> Projectissue.nebula
ECHO Created Timestamp;>> Projectissue.nebula
ECHO Createdby Date;>> Projectissue.nebula
ECHO Description;>> Projectissue.nebula
ECHO Isactive YesNo;>> Projectissue.nebula
ECHO Line Number;>> Projectissue.nebula
ECHO Attributesetinstance;>> Projectissue.nebula
ECHO Inoutline;>> Projectissue.nebula
ECHO Locator;>> Projectissue.nebula
ECHO Product;>> Projectissue.nebula
ECHO Movementdate Date;>> Projectissue.nebula
ECHO Movementqty Quantity;>> Projectissue.nebula
ECHO Posted YesNo;>> Projectissue.nebula
ECHO Processed YesNo;>> Projectissue.nebula
ECHO Processing YesNo;>> Projectissue.nebula
ECHO Timeexpenseline;>> Projectissue.nebula
ECHO Updated Timestamp;>> Projectissue.nebula
ECHO Updatedby Date;>> Projectissue.nebula
ECHO };>> Projectissue.nebula 
 ECHO @Package("Common or Core Functionality") type  Projectissuema{ >> Projectissuema.nebula
ECHO Org;>> Projectissuema.nebula
ECHO !Projectissue;>> Projectissuema.nebula
ECHO Created Timestamp;>> Projectissuema.nebula
ECHO Createdby Date;>> Projectissuema.nebula
ECHO Isactive YesNo;>> Projectissuema.nebula
ECHO !Attributesetinstance;>> Projectissuema.nebula
ECHO Movementqty Quantity;>> Projectissuema.nebula
ECHO Updated Timestamp;>> Projectissuema.nebula
ECHO Updatedby Date;>> Projectissuema.nebula
ECHO };>> Projectissuema.nebula 
 ECHO @Package("Common or Core Functionality") type  Projectline{ >> Projectline.nebula
ECHO Org;>> Projectline.nebula
ECHO Orderpo Order;>> Projectline.nebula
ECHO Order;>> Projectline.nebula
ECHO Projectissue;>> Projectline.nebula
ECHO !ID;>> Projectline.nebula
ECHO Projectphase;>> Projectline.nebula
ECHO Projecttask;>> Projectline.nebula
ECHO Project;>> Projectline.nebula
ECHO Committedamt Amount;>> Projectline.nebula
ECHO Committedqty Quantity;>> Projectline.nebula
ECHO Created Timestamp;>> Projectline.nebula
ECHO Createdby Date;>> Projectline.nebula
ECHO Description;>> Projectline.nebula
ECHO Discount Number;>> Projectline.nebula
ECHO Dopricing YesNo;>> Projectline.nebula
ECHO Invoicedamt Amount;>> Projectline.nebula
ECHO Invoicedqty Quantity;>> Projectline.nebula
ECHO Isactive YesNo;>> Projectline.nebula
ECHO Isprinted YesNo;>> Projectline.nebula
ECHO Line Number;>> Projectline.nebula
ECHO Product_Category;>> Projectline.nebula
ECHO Product;>> Projectline.nebula
ECHO Plannedamt Amount;>> Projectline.nebula
ECHO Planneddate Date;>> Projectline.nebula
ECHO Plannedmarginamt Amount;>> Projectline.nebula
ECHO Plannedprice Price;>> Projectline.nebula
ECHO Plannedqty Quantity;>> Projectline.nebula
ECHO Pricelist Price;>> Projectline.nebula
ECHO Probability;>> Projectline.nebula
ECHO Processed YesNo;>> Projectline.nebula
ECHO Updated Timestamp;>> Projectline.nebula
ECHO Updatedby Date;>> Projectline.nebula
ECHO };>> Projectline.nebula 
 ECHO @Package("Common or Core Functionality") type  Projectphase{ >> Projectphase.nebula
ECHO Org;>> Projectphase.nebula
ECHO Order;>> Projectphase.nebula
ECHO Phase;>> Projectphase.nebula
ECHO !ID;>> Projectphase.nebula
ECHO Project;>> Projectphase.nebula
ECHO Committedamt Amount;>> Projectphase.nebula
ECHO Created Timestamp;>> Projectphase.nebula
ECHO Createdby Date;>> Projectphase.nebula
ECHO Description;>> Projectphase.nebula
ECHO Enddate Date;>> Projectphase.nebula
ECHO Generateorder YesNo;>> Projectphase.nebula
ECHO Help;>> Projectphase.nebula
ECHO Isactive YesNo;>> Projectphase.nebula
ECHO Iscommitceiling YesNo;>> Projectphase.nebula
ECHO Iscomplete YesNo;>> Projectphase.nebula
ECHO Product;>> Projectphase.nebula
ECHO !Name;>> Projectphase.nebula
ECHO Plannedamt Amount;>> Projectphase.nebula
ECHO Plannedqty Quantity;>> Projectphase.nebula
ECHO Priceactual Price;>> Projectphase.nebula
ECHO Projinvoicerule YesNo;>> Projectphase.nebula
ECHO Qty Quantity;>> Projectphase.nebula
ECHO SeqNo;>> Projectphase.nebula
ECHO Startdate Date;>> Projectphase.nebula
ECHO Updated Timestamp;>> Projectphase.nebula
ECHO Updatedby Date;>> Projectphase.nebula
ECHO };>> Projectphase.nebula 
 ECHO @Package("Common or Core Functionality") type  Projecttask{ >> Projecttask.nebula
ECHO Org;>> Projecttask.nebula
ECHO Projectphase;>> Projecttask.nebula
ECHO Projecttaskprerequisite Projecttask;>> Projecttask.nebula
ECHO !ID;>> Projecttask.nebula
ECHO Task;>> Projecttask.nebula
ECHO Committedamt Amount;>> Projecttask.nebula
ECHO Created Timestamp;>> Projecttask.nebula
ECHO Createdby Date;>> Projecttask.nebula
ECHO Description;>> Projecttask.nebula
ECHO Help;>> Projecttask.nebula
ECHO Isactive YesNo;>> Projecttask.nebula
ECHO Product;>> Projecttask.nebula
ECHO !Name;>> Projecttask.nebula
ECHO Plannedamt Amount;>> Projecttask.nebula
ECHO Plannedqty Quantity;>> Projecttask.nebula
ECHO Projinvoicerule YesNo;>> Projecttask.nebula
ECHO Qty Quantity;>> Projecttask.nebula
ECHO SeqNo;>> Projecttask.nebula
ECHO Tasktype YesNo;>> Projecttask.nebula
ECHO Updated Timestamp;>> Projecttask.nebula
ECHO Updatedby Date;>> Projecttask.nebula
ECHO };>> Projecttask.nebula 
 ECHO @Package("Common or Core Functionality") type  Projecttype{ >> Projecttype.nebula
ECHO Org;>> Projecttype.nebula
ECHO !ID;>> Projecttype.nebula
ECHO Created Timestamp;>> Projecttype.nebula
ECHO Createdby Date;>> Projecttype.nebula
ECHO Description;>> Projecttype.nebula
ECHO Help;>> Projecttype.nebula
ECHO Isactive YesNo;>> Projecttype.nebula
ECHO !Name;>> Projecttype.nebula
ECHO Projectcategory YesNo;>> Projecttype.nebula
ECHO Updated Timestamp;>> Projecttype.nebula
ECHO Updatedby Date;>> Projecttype.nebula
ECHO };>> Projecttype.nebula 
 ECHO @Package("Common or Core Functionality") type  Recurring{ >> Recurring.nebula
ECHO Org;>> Recurring.nebula
ECHO Invoice;>> Recurring.nebula
ECHO Order;>> Recurring.nebula
ECHO Payment;>> Recurring.nebula
ECHO Project;>> Recurring.nebula
ECHO !ID;>> Recurring.nebula
ECHO Created Timestamp;>> Recurring.nebula
ECHO Createdby Date;>> Recurring.nebula
ECHO Datelastrun Date;>> Recurring.nebula
ECHO Datenextrun Date;>> Recurring.nebula
ECHO Description;>> Recurring.nebula
ECHO Frequency;>> Recurring.nebula
ECHO Frequencytype YesNo;>> Recurring.nebula
ECHO Journalbatch;>> Recurring.nebula
ECHO Help;>> Recurring.nebula
ECHO Isactive YesNo;>> Recurring.nebula
ECHO !Name;>> Recurring.nebula
ECHO Processing YesNo;>> Recurring.nebula
ECHO Recurringtype YesNo;>> Recurring.nebula
ECHO Runsmax Number;>> Recurring.nebula
ECHO Runsremaining Number;>> Recurring.nebula
ECHO Updated Timestamp;>> Recurring.nebula
ECHO Updatedby Date;>> Recurring.nebula
ECHO };>> Recurring.nebula 
 ECHO @Package("Common or Core Functionality") type  Recurring_Run{ >> Recurring_Run.nebula
ECHO Org;>> Recurring_Run.nebula
ECHO Invoice;>> Recurring_Run.nebula
ECHO Order;>> Recurring_Run.nebula
ECHO Payment;>> Recurring_Run.nebula
ECHO Project;>> Recurring_Run.nebula
ECHO Recurring;>> Recurring_Run.nebula
ECHO !ID Name;>> Recurring_Run.nebula
ECHO Created Timestamp;>> Recurring_Run.nebula
ECHO Createdby Date;>> Recurring_Run.nebula
ECHO Datedoc Date;>> Recurring_Run.nebula
ECHO Journalbatch;>> Recurring_Run.nebula
ECHO Isactive YesNo;>> Recurring_Run.nebula
ECHO Updated Timestamp;>> Recurring_Run.nebula
ECHO Updatedby Date;>> Recurring_Run.nebula
ECHO };>> Recurring_Run.nebula 
 ECHO @Package("Common or Core Functionality") type  Region{ >> Region.nebula
ECHO Org;>> Region.nebula
ECHO Country;>> Region.nebula
ECHO !ID;>> Region.nebula
ECHO Created Timestamp;>> Region.nebula
ECHO Createdby Date;>> Region.nebula
ECHO Description;>> Region.nebula
ECHO Isactive YesNo;>> Region.nebula
ECHO Isdefault YesNo;>> Region.nebula
ECHO !Name;>> Region.nebula
ECHO Updated Timestamp;>> Region.nebula
ECHO Updatedby Date;>> Region.nebula
ECHO };>> Region.nebula 
 ECHO @Package("Common or Core Functionality") type  Remuneration{ >> Remuneration.nebula
ECHO Org;>> Remuneration.nebula
ECHO !ID;>> Remuneration.nebula
ECHO Created Timestamp;>> Remuneration.nebula
ECHO Createdby Date;>> Remuneration.nebula
ECHO Description;>> Remuneration.nebula
ECHO Grossramt Amount;>> Remuneration.nebula
ECHO Grossrcost Number;>> Remuneration.nebula
ECHO Help;>> Remuneration.nebula
ECHO Isactive YesNo;>> Remuneration.nebula
ECHO !Name;>> Remuneration.nebula
ECHO Overtimeamt Amount;>> Remuneration.nebula
ECHO Overtimecost Number;>> Remuneration.nebula
ECHO Remunerationtype YesNo;>> Remuneration.nebula
ECHO Standardhours Number;>> Remuneration.nebula
ECHO Updated Timestamp;>> Remuneration.nebula
ECHO Updatedby Date;>> Remuneration.nebula
ECHO };>> Remuneration.nebula 
 ECHO @Package("Common or Core Functionality") type  Rfq{ >> Rfq.nebula
ECHO Org;>> Rfq.nebula
ECHO User;>> Rfq.nebula
ECHO Bpartner;>> Rfq.nebula
ECHO Bpartner_Location;>> Rfq.nebula
ECHO Currency;>> Rfq.nebula
ECHO Order;>> Rfq.nebula
ECHO !ID;>> Rfq.nebula
ECHO Rfq_Topic;>> Rfq.nebula
ECHO Copylines YesNo;>> Rfq.nebula
ECHO Createpo YesNo;>> Rfq.nebula
ECHO Createso YesNo;>> Rfq.nebula
ECHO Created Timestamp;>> Rfq.nebula
ECHO Createdby Date;>> Rfq.nebula
ECHO Dateresponse Date;>> Rfq.nebula
ECHO Dateworkcomplete Date;>> Rfq.nebula
ECHO Dateworkstart Date;>> Rfq.nebula
ECHO Deliverydays Count;>> Rfq.nebula
ECHO Description;>> Rfq.nebula
ECHO Documentno SeqNo;>> Rfq.nebula
ECHO Help;>> Rfq.nebula
ECHO Isactive YesNo;>> Rfq.nebula
ECHO Isinvitedvendorsonly YesNo;>> Rfq.nebula
ECHO Isquoteallqty YesNo;>> Rfq.nebula
ECHO Isquotetotalamt YesNo;>> Rfq.nebula
ECHO Isrfqresponseaccepted YesNo;>> Rfq.nebula
ECHO Isselfservice YesNo;>> Rfq.nebula
ECHO Margin Long;>> Rfq.nebula
ECHO !Name;>> Rfq.nebula
ECHO Processed YesNo;>> Rfq.nebula
ECHO Processing YesNo;>> Rfq.nebula
ECHO Publishrfq YesNo;>> Rfq.nebula
ECHO Quotetype YesNo;>> Rfq.nebula
ECHO Rankrfq YesNo;>> Rfq.nebula
ECHO Salesrep User;>> Rfq.nebula
ECHO Updated Timestamp;>> Rfq.nebula
ECHO Updatedby Date;>> Rfq.nebula
ECHO };>> Rfq.nebula 
 ECHO @Package("Common or Core Functionality") type  Rfq_Topic{ >> Rfq_Topic.nebula
ECHO Org;>> Rfq_Topic.nebula
ECHO Printformat;>> Rfq_Topic.nebula
ECHO !ID;>> Rfq_Topic.nebula
ECHO Created Timestamp;>> Rfq_Topic.nebula
ECHO Createdby Date;>> Rfq_Topic.nebula
ECHO Description;>> Rfq_Topic.nebula
ECHO Isactive YesNo;>> Rfq_Topic.nebula
ECHO Isselfservice YesNo;>> Rfq_Topic.nebula
ECHO !Name;>> Rfq_Topic.nebula
ECHO Updated Timestamp;>> Rfq_Topic.nebula
ECHO Updatedby Date;>> Rfq_Topic.nebula
ECHO };>> Rfq_Topic.nebula 
 ECHO @Package("Common or Core Functionality") type  Rfq_Topicsubscriber{ >> Rfq_Topicsubscriber.nebula
ECHO Org;>> Rfq_Topicsubscriber.nebula
ECHO User;>> Rfq_Topicsubscriber.nebula
ECHO Bpartner;>> Rfq_Topicsubscriber.nebula
ECHO Bpartner_Location;>> Rfq_Topicsubscriber.nebula
ECHO !ID;>> Rfq_Topicsubscriber.nebula
ECHO Rfq_Topic;>> Rfq_Topicsubscriber.nebula
ECHO Created Timestamp;>> Rfq_Topicsubscriber.nebula
ECHO Createdby Date;>> Rfq_Topicsubscriber.nebula
ECHO Isactive YesNo;>> Rfq_Topicsubscriber.nebula
ECHO Optoutdate Date;>> Rfq_Topicsubscriber.nebula
ECHO Subscribedate Date;>> Rfq_Topicsubscriber.nebula
ECHO Updated Timestamp;>> Rfq_Topicsubscriber.nebula
ECHO Updatedby Date;>> Rfq_Topicsubscriber.nebula
ECHO };>> Rfq_Topicsubscriber.nebula 
 ECHO @Package("Common or Core Functionality") type  Rfq_Topicsubscriberonly{ >> Rfq_Topicsubscriberonly.nebula
ECHO Org;>> Rfq_Topicsubscriberonly.nebula
ECHO !ID;>> Rfq_Topicsubscriberonly.nebula
ECHO Rfq_Topicsubscriber;>> Rfq_Topicsubscriberonly.nebula
ECHO Created Timestamp;>> Rfq_Topicsubscriberonly.nebula
ECHO Createdby Date;>> Rfq_Topicsubscriberonly.nebula
ECHO Description;>> Rfq_Topicsubscriberonly.nebula
ECHO Isactive YesNo;>> Rfq_Topicsubscriberonly.nebula
ECHO Product_Category;>> Rfq_Topicsubscriberonly.nebula
ECHO Product;>> Rfq_Topicsubscriberonly.nebula
ECHO Updated Timestamp;>> Rfq_Topicsubscriberonly.nebula
ECHO Updatedby Date;>> Rfq_Topicsubscriberonly.nebula
ECHO };>> Rfq_Topicsubscriberonly.nebula 
 ECHO @Package("Common or Core Functionality") type  Rfqline{ >> Rfqline.nebula
ECHO Org;>> Rfqline.nebula
ECHO !ID;>> Rfqline.nebula
ECHO Rfq;>> Rfqline.nebula
ECHO Created Timestamp;>> Rfqline.nebula
ECHO Createdby Date;>> Rfqline.nebula
ECHO Dateworkcomplete Date;>> Rfqline.nebula
ECHO Dateworkstart Date;>> Rfqline.nebula
ECHO Deliverydays Count;>> Rfqline.nebula
ECHO Description;>> Rfqline.nebula
ECHO Help;>> Rfqline.nebula
ECHO Isactive YesNo;>> Rfqline.nebula
ECHO Line Number;>> Rfqline.nebula
ECHO Attributesetinstance;>> Rfqline.nebula
ECHO Product;>> Rfqline.nebula
ECHO Updated Timestamp;>> Rfqline.nebula
ECHO Updatedby Date;>> Rfqline.nebula
ECHO };>> Rfqline.nebula 
 ECHO @Package("Common or Core Functionality") type  Rfqlineqty{ >> Rfqlineqty.nebula
ECHO Org;>> Rfqlineqty.nebula
ECHO Benchmarkprice Price;>> Rfqlineqty.nebula
ECHO Bestresponseamt Amount;>> Rfqlineqty.nebula
ECHO !ID;>> Rfqlineqty.nebula
ECHO Rfqline;>> Rfqlineqty.nebula
ECHO Uom;>> Rfqlineqty.nebula
ECHO Created Timestamp;>> Rfqlineqty.nebula
ECHO Createdby Date;>> Rfqlineqty.nebula
ECHO Isactive YesNo;>> Rfqlineqty.nebula
ECHO Isofferqty YesNo;>> Rfqlineqty.nebula
ECHO Ispurchaseqty YesNo;>> Rfqlineqty.nebula
ECHO Isrfqqty YesNo;>> Rfqlineqty.nebula
ECHO Margin Long;>> Rfqlineqty.nebula
ECHO Offeramt Amount;>> Rfqlineqty.nebula
ECHO Qty Quantity;>> Rfqlineqty.nebula
ECHO Updated Timestamp;>> Rfqlineqty.nebula
ECHO Updatedby Date;>> Rfqlineqty.nebula
ECHO };>> Rfqlineqty.nebula 
 ECHO @Package("Common or Core Functionality") type  Rfqresponse{ >> Rfqresponse.nebula
ECHO Org;>> Rfqresponse.nebula
ECHO User;>> Rfqresponse.nebula
ECHO Bpartner;>> Rfqresponse.nebula
ECHO Bpartner_Location;>> Rfqresponse.nebula
ECHO Currency;>> Rfqresponse.nebula
ECHO Order;>> Rfqresponse.nebula
ECHO !ID;>> Rfqresponse.nebula
ECHO Rfq;>> Rfqresponse.nebula
ECHO Checkcomplete YesNo;>> Rfqresponse.nebula
ECHO Created Timestamp;>> Rfqresponse.nebula
ECHO Createdby Date;>> Rfqresponse.nebula
ECHO Dateinvited Date;>> Rfqresponse.nebula
ECHO Dateresponse Date;>> Rfqresponse.nebula
ECHO Dateworkcomplete Date;>> Rfqresponse.nebula
ECHO Dateworkstart Date;>> Rfqresponse.nebula
ECHO Deliverydays Count;>> Rfqresponse.nebula
ECHO Description;>> Rfqresponse.nebula
ECHO Help;>> Rfqresponse.nebula
ECHO Isactive YesNo;>> Rfqresponse.nebula
ECHO Iscomplete YesNo;>> Rfqresponse.nebula
ECHO Isselectedwinner YesNo;>> Rfqresponse.nebula
ECHO Isselfservice YesNo;>> Rfqresponse.nebula
ECHO !Name;>> Rfqresponse.nebula
ECHO Price;>> Rfqresponse.nebula
ECHO Processed YesNo;>> Rfqresponse.nebula
ECHO Processing YesNo;>> Rfqresponse.nebula
ECHO Ranking;>> Rfqresponse.nebula
ECHO Updated Timestamp;>> Rfqresponse.nebula
ECHO Updatedby Date;>> Rfqresponse.nebula
ECHO };>> Rfqresponse.nebula 
 ECHO @Package("Common or Core Functionality") type  Rfqresponseline{ >> Rfqresponseline.nebula
ECHO Org;>> Rfqresponseline.nebula
ECHO Rfqline;>> Rfqresponseline.nebula
ECHO !ID;>> Rfqresponseline.nebula
ECHO Rfqresponse;>> Rfqresponseline.nebula
ECHO Created Timestamp;>> Rfqresponseline.nebula
ECHO Createdby Date;>> Rfqresponseline.nebula
ECHO Dateworkcomplete Date;>> Rfqresponseline.nebula
ECHO Dateworkstart Date;>> Rfqresponseline.nebula
ECHO Deliverydays Count;>> Rfqresponseline.nebula
ECHO Description;>> Rfqresponseline.nebula
ECHO Help;>> Rfqresponseline.nebula
ECHO Isactive YesNo;>> Rfqresponseline.nebula
ECHO Isselectedwinner YesNo;>> Rfqresponseline.nebula
ECHO Isselfservice YesNo;>> Rfqresponseline.nebula
ECHO Updated Timestamp;>> Rfqresponseline.nebula
ECHO Updatedby Date;>> Rfqresponseline.nebula
ECHO };>> Rfqresponseline.nebula 
 ECHO @Package("Common or Core Functionality") type  Rfqresponselineqty{ >> Rfqresponselineqty.nebula
ECHO Org;>> Rfqresponselineqty.nebula
ECHO Rfqlineqty;>> Rfqresponselineqty.nebula
ECHO !ID;>> Rfqresponselineqty.nebula
ECHO Rfqresponseline;>> Rfqresponselineqty.nebula
ECHO Created Timestamp;>> Rfqresponselineqty.nebula
ECHO Createdby Date;>> Rfqresponselineqty.nebula
ECHO Discount Number;>> Rfqresponselineqty.nebula
ECHO Isactive YesNo;>> Rfqresponselineqty.nebula
ECHO Price;>> Rfqresponselineqty.nebula
ECHO Ranking;>> Rfqresponselineqty.nebula
ECHO Updated Timestamp;>> Rfqresponselineqty.nebula
ECHO Updatedby Date;>> Rfqresponselineqty.nebula
ECHO };>> Rfqresponselineqty.nebula 
 ECHO @Package("Common or Core Functionality") type  Salesregion{ >> Salesregion.nebula
ECHO Org;>> Salesregion.nebula
ECHO !ID;>> Salesregion.nebula
ECHO Created Timestamp;>> Salesregion.nebula
ECHO Createdby Date;>> Salesregion.nebula
ECHO Description;>> Salesregion.nebula
ECHO Isactive YesNo;>> Salesregion.nebula
ECHO Isdefault YesNo;>> Salesregion.nebula
ECHO Issummary YesNo;>> Salesregion.nebula
ECHO !Name;>> Salesregion.nebula
ECHO Salesrep User;>> Salesregion.nebula
ECHO Updated Timestamp;>> Salesregion.nebula
ECHO Updatedby Date;>> Salesregion.nebula
ECHO Value String;>> Salesregion.nebula
ECHO };>> Salesregion.nebula 
 ECHO @Package("Common or Core Functionality") type  Subacct{ >> Subacct.nebula
ECHO Org;>> Subacct.nebula
ECHO Elementvalue;>> Subacct.nebula
ECHO !ID;>> Subacct.nebula
ECHO Created Timestamp;>> Subacct.nebula
ECHO Createdby Date;>> Subacct.nebula
ECHO Description;>> Subacct.nebula
ECHO Help;>> Subacct.nebula
ECHO Isactive YesNo;>> Subacct.nebula
ECHO !Name;>> Subacct.nebula
ECHO Updated Timestamp;>> Subacct.nebula
ECHO Updatedby Date;>> Subacct.nebula
ECHO Value String;>> Subacct.nebula
ECHO };>> Subacct.nebula 
 ECHO @Package("Common or Core Functionality") type  Subscription{ >> Subscription.nebula
ECHO Org;>> Subscription.nebula
ECHO Bpartner;>> Subscription.nebula
ECHO Subscriptiontype;>> Subscription.nebula
ECHO !ID;>> Subscription.nebula
ECHO Created Timestamp;>> Subscription.nebula
ECHO Createdby Date;>> Subscription.nebula
ECHO Isactive YesNo;>> Subscription.nebula
ECHO Isdue YesNo;>> Subscription.nebula
ECHO Product;>> Subscription.nebula
ECHO !Name;>> Subscription.nebula
ECHO Paiduntildate Date;>> Subscription.nebula
ECHO Renewaldate Date;>> Subscription.nebula
ECHO Startdate Date;>> Subscription.nebula
ECHO Updated Timestamp;>> Subscription.nebula
ECHO Updatedby Date;>> Subscription.nebula
ECHO };>> Subscription.nebula 
 ECHO @Package("Common or Core Functionality") type  Subscription_Delivery{ >> Subscription_Delivery.nebula
ECHO Org;>> Subscription_Delivery.nebula
ECHO !ID;>> Subscription_Delivery.nebula
ECHO Subscription;>> Subscription_Delivery.nebula
ECHO Created Timestamp;>> Subscription_Delivery.nebula
ECHO Createdby Date;>> Subscription_Delivery.nebula
ECHO Isactive YesNo;>> Subscription_Delivery.nebula
ECHO Updated Timestamp;>> Subscription_Delivery.nebula
ECHO Updatedby Date;>> Subscription_Delivery.nebula
ECHO };>> Subscription_Delivery.nebula 
 ECHO @Package("Common or Core Functionality") type  Subscriptiontype{ >> Subscriptiontype.nebula
ECHO Org;>> Subscriptiontype.nebula
ECHO !ID;>> Subscriptiontype.nebula
ECHO Created Timestamp;>> Subscriptiontype.nebula
ECHO Createdby Date;>> Subscriptiontype.nebula
ECHO Description;>> Subscriptiontype.nebula
ECHO Frequency;>> Subscriptiontype.nebula
ECHO Frequencytype YesNo;>> Subscriptiontype.nebula
ECHO Isactive YesNo;>> Subscriptiontype.nebula
ECHO !Name;>> Subscriptiontype.nebula
ECHO Updated Timestamp;>> Subscriptiontype.nebula
ECHO Updatedby Date;>> Subscriptiontype.nebula
ECHO };>> Subscriptiontype.nebula 
 ECHO @Package("Common or Core Functionality") type  Task{ >> Task.nebula
ECHO Org;>> Task.nebula
ECHO Phase;>> Task.nebula
ECHO !ID;>> Task.nebula
ECHO Created Timestamp;>> Task.nebula
ECHO Createdby Date;>> Task.nebula
ECHO Description;>> Task.nebula
ECHO Help;>> Task.nebula
ECHO Isactive YesNo;>> Task.nebula
ECHO Product;>> Task.nebula
ECHO !Name;>> Task.nebula
ECHO SeqNo;>> Task.nebula
ECHO Standardqty Quantity;>> Task.nebula
ECHO Tasktype YesNo;>> Task.nebula
ECHO Updated Timestamp;>> Task.nebula
ECHO Updatedby Date;>> Task.nebula
ECHO };>> Task.nebula 
 ECHO @Package("Common or Core Functionality") type  Tax{ >> Tax.nebula
ECHO Org;>> Tax.nebula
ECHO Country;>> Tax.nebula
ECHO Region;>> Tax.nebula
ECHO Taxcategory;>> Tax.nebula
ECHO !ID;>> Tax.nebula
ECHO Created Timestamp;>> Tax.nebula
ECHO Createdby Date;>> Tax.nebula
ECHO Description;>> Tax.nebula
ECHO Isactive YesNo;>> Tax.nebula
ECHO Isdefault YesNo;>> Tax.nebula
ECHO Isdocumentlevel YesNo;>> Tax.nebula
ECHO Issalestax YesNo;>> Tax.nebula
ECHO Issummary YesNo;>> Tax.nebula
ECHO Istaxexempt YesNo;>> Tax.nebula
ECHO !Name;>> Tax.nebula
ECHO Parent_Tax Tax;>> Tax.nebula
ECHO Rate;>> Tax.nebula
ECHO Requirestaxcertificate YesNo;>> Tax.nebula
ECHO Sopotype YesNo;>> Tax.nebula
ECHO Taxindicator Number;>> Tax.nebula
ECHO To_Country Country;>> Tax.nebula
ECHO To_Region Region;>> Tax.nebula
ECHO Updated Timestamp;>> Tax.nebula
ECHO Updatedby Date;>> Tax.nebula
ECHO Validfrom Date;>> Tax.nebula
ECHO };>> Tax.nebula 
 ECHO @Package("Common or Core Functionality") type  Tax_Acct{ >> Tax_Acct.nebula
ECHO Org;>> Tax_Acct.nebula
ECHO !Acctschema;>> Tax_Acct.nebula
ECHO !Tax;>> Tax_Acct.nebula
ECHO Created Timestamp;>> Tax_Acct.nebula
ECHO Createdby Date;>> Tax_Acct.nebula
ECHO Isactive YesNo;>> Tax_Acct.nebula
ECHO T_Credit_acct Account;>> Tax_Acct.nebula
ECHO T_Due_acct Account;>> Tax_Acct.nebula
ECHO T_Expense_acct Account;>> Tax_Acct.nebula
ECHO T_Liability_acct Account;>> Tax_Acct.nebula
ECHO T_Receivables_acct Account;>> Tax_Acct.nebula
ECHO Updated Timestamp;>> Tax_Acct.nebula
ECHO Updatedby Date;>> Tax_Acct.nebula
ECHO };>> Tax_Acct.nebula 
 ECHO @Package("Common or Core Functionality") type  Tax_Trl{ >> Tax_Trl.nebula
ECHO !Ad_Language Language;>> Tax_Trl.nebula
ECHO Org;>> Tax_Trl.nebula
ECHO !Tax;>> Tax_Trl.nebula
ECHO Created Timestamp;>> Tax_Trl.nebula
ECHO Createdby Date;>> Tax_Trl.nebula
ECHO Description;>> Tax_Trl.nebula
ECHO Isactive YesNo;>> Tax_Trl.nebula
ECHO Istranslated YesNo;>> Tax_Trl.nebula
ECHO !Name;>> Tax_Trl.nebula
ECHO Taxindicator Number;>> Tax_Trl.nebula
ECHO Updated Timestamp;>> Tax_Trl.nebula
ECHO Updatedby Date;>> Tax_Trl.nebula
ECHO };>> Tax_Trl.nebula 
 ECHO @Package("Common or Core Functionality") type  Taxcategory{ >> Taxcategory.nebula
ECHO Org;>> Taxcategory.nebula
ECHO !ID;>> Taxcategory.nebula
ECHO Commoditycode Attr;>> Taxcategory.nebula
ECHO Created Timestamp;>> Taxcategory.nebula
ECHO Createdby Date;>> Taxcategory.nebula
ECHO Description;>> Taxcategory.nebula
ECHO Isactive YesNo;>> Taxcategory.nebula
ECHO Isdefault YesNo;>> Taxcategory.nebula
ECHO !Name;>> Taxcategory.nebula
ECHO Updated Timestamp;>> Taxcategory.nebula
ECHO Updatedby Date;>> Taxcategory.nebula
ECHO };>> Taxcategory.nebula 
 ECHO @Package("Common or Core Functionality") type  Taxcategory_Trl{ >> Taxcategory_Trl.nebula
ECHO !Ad_Language Language;>> Taxcategory_Trl.nebula
ECHO Org;>> Taxcategory_Trl.nebula
ECHO !Taxcategory;>> Taxcategory_Trl.nebula
ECHO Created Timestamp;>> Taxcategory_Trl.nebula
ECHO Createdby Date;>> Taxcategory_Trl.nebula
ECHO Description;>> Taxcategory_Trl.nebula
ECHO Isactive YesNo;>> Taxcategory_Trl.nebula
ECHO Istranslated YesNo;>> Taxcategory_Trl.nebula
ECHO !Name;>> Taxcategory_Trl.nebula
ECHO Updated Timestamp;>> Taxcategory_Trl.nebula
ECHO Updatedby Date;>> Taxcategory_Trl.nebula
ECHO };>> Taxcategory_Trl.nebula 
 ECHO @Package("Common or Core Functionality") type  Taxdeclaration{ >> Taxdeclaration.nebula
ECHO Org;>> Taxdeclaration.nebula
ECHO !ID;>> Taxdeclaration.nebula
ECHO Created Timestamp;>> Taxdeclaration.nebula
ECHO Createdby Date;>> Taxdeclaration.nebula
ECHO Datefrom Date;>> Taxdeclaration.nebula
ECHO Dateto Date;>> Taxdeclaration.nebula
ECHO Datetrx Date;>> Taxdeclaration.nebula
ECHO Description;>> Taxdeclaration.nebula
ECHO Isactive YesNo;>> Taxdeclaration.nebula
ECHO !Name;>> Taxdeclaration.nebula
ECHO Processed YesNo;>> Taxdeclaration.nebula
ECHO Processing YesNo;>> Taxdeclaration.nebula
ECHO Updated Timestamp;>> Taxdeclaration.nebula
ECHO Updatedby Date;>> Taxdeclaration.nebula
ECHO };>> Taxdeclaration.nebula 
 ECHO @Package("Common or Core Functionality") type  Taxdeclarationacct{ >> Taxdeclarationacct.nebula
ECHO Org;>> Taxdeclarationacct.nebula
ECHO Acctschema;>> Taxdeclarationacct.nebula
ECHO !ID;>> Taxdeclarationacct.nebula
ECHO Taxdeclaration;>> Taxdeclarationacct.nebula
ECHO Created Timestamp;>> Taxdeclarationacct.nebula
ECHO Createdby Date;>> Taxdeclarationacct.nebula
ECHO Description;>> Taxdeclarationacct.nebula
ECHO Acct;>> Taxdeclarationacct.nebula
ECHO Isactive YesNo;>> Taxdeclarationacct.nebula
ECHO Line Number;>> Taxdeclarationacct.nebula
ECHO Updated Timestamp;>> Taxdeclarationacct.nebula
ECHO Updatedby Date;>> Taxdeclarationacct.nebula
ECHO };>> Taxdeclarationacct.nebula 
 ECHO @Package("Common or Core Functionality") type  Taxdeclarationline{ >> Taxdeclarationline.nebula
ECHO Org;>> Taxdeclarationline.nebula
ECHO Allocationline;>> Taxdeclarationline.nebula
ECHO Bpartner;>> Taxdeclarationline.nebula
ECHO Currency;>> Taxdeclarationline.nebula
ECHO Invoiceline;>> Taxdeclarationline.nebula
ECHO Invoice;>> Taxdeclarationline.nebula
ECHO !ID;>> Taxdeclarationline.nebula
ECHO Taxdeclaration;>> Taxdeclarationline.nebula
ECHO Tax;>> Taxdeclarationline.nebula
ECHO Created Timestamp;>> Taxdeclarationline.nebula
ECHO Createdby Date;>> Taxdeclarationline.nebula
ECHO Dateacct Date;>> Taxdeclarationline.nebula
ECHO Description;>> Taxdeclarationline.nebula
ECHO Isactive YesNo;>> Taxdeclarationline.nebula
ECHO Ismanual YesNo;>> Taxdeclarationline.nebula
ECHO Line Number;>> Taxdeclarationline.nebula
ECHO Taxamt Amount;>> Taxdeclarationline.nebula
ECHO Taxbaseamt Amount;>> Taxdeclarationline.nebula
ECHO Updated Timestamp;>> Taxdeclarationline.nebula
ECHO Updatedby Date;>> Taxdeclarationline.nebula
ECHO };>> Taxdeclarationline.nebula 
 ECHO @Package("Common or Core Functionality") type  Taxpostal{ >> Taxpostal.nebula
ECHO Org;>> Taxpostal.nebula
ECHO !ID;>> Taxpostal.nebula
ECHO Tax;>> Taxpostal.nebula
ECHO Created Timestamp;>> Taxpostal.nebula
ECHO Createdby Date;>> Taxpostal.nebula
ECHO Isactive YesNo;>> Taxpostal.nebula
ECHO Postal PostalCode;>> Taxpostal.nebula
ECHO Postal_To PostalCode;>> Taxpostal.nebula
ECHO Updated Timestamp;>> Taxpostal.nebula
ECHO Updatedby Date;>> Taxpostal.nebula
ECHO };>> Taxpostal.nebula 
 ECHO @Package("Common or Core Functionality") type  Uom{ >> Uom.nebula
ECHO Org;>> Uom.nebula
ECHO !ID;>> Uom.nebula
ECHO Costingprecision Precision;>> Uom.nebula
ECHO Created Timestamp;>> Uom.nebula
ECHO Createdby Date;>> Uom.nebula
ECHO Description;>> Uom.nebula
ECHO Isactive YesNo;>> Uom.nebula
ECHO Isdefault YesNo;>> Uom.nebula
ECHO !Name;>> Uom.nebula
ECHO Stdprecision Precision;>> Uom.nebula
ECHO Uomsymbol Symbol;>> Uom.nebula
ECHO Updated Timestamp;>> Uom.nebula
ECHO Updatedby Date;>> Uom.nebula
ECHO X12de355 UOMEDICode;>> Uom.nebula
ECHO };>> Uom.nebula 
 ECHO @Package("Common or Core Functionality") type  Uom_Conversion{ >> Uom_Conversion.nebula
ECHO Org;>> Uom_Conversion.nebula
ECHO !ID;>> Uom_Conversion.nebula
ECHO Uom;>> Uom_Conversion.nebula
ECHO Uom_To Uom;>> Uom_Conversion.nebula
ECHO Created Timestamp;>> Uom_Conversion.nebula
ECHO Createdby Date;>> Uom_Conversion.nebula
ECHO Dividerate Rate;>> Uom_Conversion.nebula
ECHO Isactive YesNo;>> Uom_Conversion.nebula
ECHO Product;>> Uom_Conversion.nebula
ECHO Multiplyrate Rate;>> Uom_Conversion.nebula
ECHO Updated Timestamp;>> Uom_Conversion.nebula
ECHO Updatedby Date;>> Uom_Conversion.nebula
ECHO };>> Uom_Conversion.nebula 
 ECHO @Package("Common or Core Functionality") type  Uom_Trl{ >> Uom_Trl.nebula
ECHO !Ad_Language Language;>> Uom_Trl.nebula
ECHO Org;>> Uom_Trl.nebula
ECHO !Uom;>> Uom_Trl.nebula
ECHO Created Timestamp;>> Uom_Trl.nebula
ECHO Createdby Date;>> Uom_Trl.nebula
ECHO Description;>> Uom_Trl.nebula
ECHO Isactive YesNo;>> Uom_Trl.nebula
ECHO Istranslated YesNo;>> Uom_Trl.nebula
ECHO !Name;>> Uom_Trl.nebula
ECHO Uomsymbol Symbol;>> Uom_Trl.nebula
ECHO Updated Timestamp;>> Uom_Trl.nebula
ECHO Updatedby Date;>> Uom_Trl.nebula
ECHO };>> Uom_Trl.nebula 
 ECHO @Package("Common or Core Functionality") type  Userremuneration{ >> Userremuneration.nebula
ECHO Org;>> Userremuneration.nebula
ECHO User;>> Userremuneration.nebula
ECHO Remuneration;>> Userremuneration.nebula
ECHO !ID;>> Userremuneration.nebula
ECHO Created Timestamp;>> Userremuneration.nebula
ECHO Createdby Date;>> Userremuneration.nebula
ECHO Description;>> Userremuneration.nebula
ECHO Grossramt Amount;>> Userremuneration.nebula
ECHO Grossrcost Number;>> Userremuneration.nebula
ECHO Isactive YesNo;>> Userremuneration.nebula
ECHO Overtimeamt Amount;>> Userremuneration.nebula
ECHO Overtimecost Number;>> Userremuneration.nebula
ECHO Updated Timestamp;>> Userremuneration.nebula
ECHO Updatedby Date;>> Userremuneration.nebula
ECHO Validfrom Date;>> Userremuneration.nebula
ECHO Validto Date;>> Userremuneration.nebula
ECHO };>> Userremuneration.nebula 
 ECHO @Package("Common or Core Functionality") type  Validcombination{ >> Validcombination.nebula
ECHO Orgtrx Org;>> Validcombination.nebula
ECHO Org;>> Validcombination.nebula
ECHO Account Elementvalue;>> Validcombination.nebula
ECHO Alias Name;>> Validcombination.nebula
ECHO Acctschema;>> Validcombination.nebula
ECHO Activity;>> Validcombination.nebula
ECHO Bpartner;>> Validcombination.nebula
ECHO Campaign;>> Validcombination.nebula
ECHO Locfrom Location;>> Validcombination.nebula
ECHO Locto Location;>> Validcombination.nebula
ECHO Project;>> Validcombination.nebula
ECHO Salesregion;>> Validcombination.nebula
ECHO Subacct;>> Validcombination.nebula
ECHO !ID;>> Validcombination.nebula
ECHO Combination String;>> Validcombination.nebula
ECHO Created Timestamp;>> Validcombination.nebula
ECHO Createdby Date;>> Validcombination.nebula
ECHO Description;>> Validcombination.nebula
ECHO Isactive YesNo;>> Validcombination.nebula
ECHO Isfullyqualified YesNo;>> Validcombination.nebula
ECHO Product;>> Validcombination.nebula
ECHO Updated Timestamp;>> Validcombination.nebula
ECHO Updatedby Date;>> Validcombination.nebula
ECHO User1 Elementvalue;>> Validcombination.nebula
ECHO User2 Elementvalue;>> Validcombination.nebula


ECHO };>> Validcombination.nebula 
 ECHO @Package("Common or Core Functionality") type  Wave{ >> Wave.nebula
ECHO Org;>> Wave.nebula
ECHO !ID;>> Wave.nebula
ECHO Created Timestamp;>> Wave.nebula
ECHO Createdby Date;>> Wave.nebula
ECHO Documentno SeqNo;>> Wave.nebula
ECHO Isactive YesNo;>> Wave.nebula
ECHO Warehouse;>> Wave.nebula
ECHO Updated Timestamp;>> Wave.nebula
ECHO Updatedby Date;>> Wave.nebula
ECHO };>> Wave.nebula 
 ECHO @Package("Common or Core Functionality") type  Waveline{ >> Waveline.nebula
ECHO Org;>> Waveline.nebula
ECHO Orderline;>> Waveline.nebula
ECHO !ID;>> Waveline.nebula
ECHO Wave;>> Waveline.nebula
ECHO Created Timestamp;>> Waveline.nebula
ECHO Createdby Date;>> Waveline.nebula
ECHO Isactive YesNo;>> Waveline.nebula
ECHO Movementqty Quantity;>> Waveline.nebula
ECHO Updated Timestamp;>> Waveline.nebula
ECHO Updatedby Date;>> Waveline.nebula
ECHO };>> Waveline.nebula 
 ECHO @Package("Common or Core Functionality") type  Wavesortcriteria{ >> Wavesortcriteria.nebula
ECHO Org;>> Wavesortcriteria.nebula
ECHO !ID;>> Wavesortcriteria.nebula
ECHO Created Timestamp;>> Wavesortcriteria.nebula
ECHO Createdby Date;>> Wavesortcriteria.nebula
ECHO Description;>> Wavesortcriteria.nebula
ECHO Isactive YesNo;>> Wavesortcriteria.nebula
ECHO !Name;>> Wavesortcriteria.nebula
ECHO Updated Timestamp;>> Wavesortcriteria.nebula
ECHO Updatedby Date;>> Wavesortcriteria.nebula
ECHO };>> Wavesortcriteria.nebula 
 ECHO @Package("Common or Core Functionality") type  Wavesortcriterialine{ >> Wavesortcriterialine.nebula
ECHO Infocolumn;>> Wavesortcriterialine.nebula
ECHO Org;>> Wavesortcriterialine.nebula
ECHO !ID;>> Wavesortcriterialine.nebula
ECHO Wavesortcriteria;>> Wavesortcriterialine.nebula
ECHO Created Timestamp;>> Wavesortcriterialine.nebula
ECHO Createdby Date;>> Wavesortcriterialine.nebula
ECHO Isactive YesNo;>> Wavesortcriterialine.nebula
ECHO Orderbytype YesNo;>> Wavesortcriterialine.nebula
ECHO SeqNo;>> Wavesortcriterialine.nebula
ECHO Updated Timestamp;>> Wavesortcriterialine.nebula
ECHO Updatedby Date;>> Wavesortcriterialine.nebula
ECHO };>> Wavesortcriterialine.nebula 
 ECHO @Package("Common or Core Functionality") type  Withholding{ >> Withholding.nebula
ECHO Org;>> Withholding.nebula
ECHO Beneficiary Bpartner;>> Withholding.nebula
ECHO Paymentterm;>> Withholding.nebula
ECHO !ID;>> Withholding.nebula
ECHO Created Timestamp;>> Withholding.nebula
ECHO Createdby Date;>> Withholding.nebula
ECHO Description;>> Withholding.nebula
ECHO Fixamt Amount;>> Withholding.nebula
ECHO Isactive YesNo;>> Withholding.nebula
ECHO Ispaidto3party YesNo;>> Withholding.nebula
ECHO Ispercentwithholding YesNo;>> Withholding.nebula
ECHO Istaxprorated YesNo;>> Withholding.nebula
ECHO Istaxwithholding YesNo;>> Withholding.nebula
ECHO Maxamt Amount;>> Withholding.nebula
ECHO Minamt Amount;>> Withholding.nebula
ECHO !Name;>> Withholding.nebula
ECHO Percentwithholding Number;>> Withholding.nebula
ECHO Thresholdmax Count;>> Withholding.nebula
ECHO Thresholdmin Count;>> Withholding.nebula
ECHO Updated Timestamp;>> Withholding.nebula
ECHO Updatedby Date;>> Withholding.nebula
ECHO };>> Withholding.nebula 
 ECHO @Package("Common or Core Functionality") type  Withholding_Acct{ >> Withholding_Acct.nebula
ECHO Org;>> Withholding_Acct.nebula
ECHO !Acctschema;>> Withholding_Acct.nebula
ECHO !Withholding;>> Withholding_Acct.nebula
ECHO Created Timestamp;>> Withholding_Acct.nebula
ECHO Createdby Date;>> Withholding_Acct.nebula
ECHO Isactive YesNo;>> Withholding_Acct.nebula
ECHO Updated Timestamp;>> Withholding_Acct.nebula
ECHO Updatedby Date;>> Withholding_Acct.nebula
ECHO Withholding_Acct Account;>> Withholding_Acct.nebula
ECHO };>> Withholding_Acct.nebula 
 ECHO @Package("Common or Core Functionality") type  Year{ >> Year.nebula
ECHO Org;>> Year.nebula
ECHO Calendar;>> Year.nebula
ECHO !ID;>> Year.nebula
ECHO Created Timestamp;>> Year.nebula
ECHO Createdby Date;>> Year.nebula
ECHO Description;>> Year.nebula
ECHO Fiscalyear String;>> Year.nebula
ECHO Isactive YesNo;>> Year.nebula
ECHO Processing YesNo;>> Year.nebula
ECHO Updated Timestamp;>> Year.nebula
ECHO Updatedby Date;>> Year.nebula
ECHO };>> Year.nebula 
 ECHO @Package("Collaboration Management") type  Accesschat{ >> Accesschat.nebula
ECHO Org;>> Accesschat.nebula
ECHO !Accessprofile;>> Accesschat.nebula
ECHO !Chat;>> Accesschat.nebula
ECHO Created Timestamp;>> Accesschat.nebula
ECHO Createdby Date;>> Accesschat.nebula
ECHO Isactive YesNo;>> Accesschat.nebula
ECHO Updated Timestamp;>> Accesschat.nebula
ECHO Updatedby Date;>> Accesschat.nebula
ECHO };>> Accesschat.nebula 
 ECHO @Package("Collaboration Management") type  Accesscontainer{ >> Accesscontainer.nebula
ECHO Org;>> Accesscontainer.nebula
ECHO !Accessprofile;>> Accesscontainer.nebula
ECHO !Container;>> Accesscontainer.nebula
ECHO Created Timestamp;>> Accesscontainer.nebula
ECHO Createdby Date;>> Accesscontainer.nebula
ECHO Isactive YesNo;>> Accesscontainer.nebula
ECHO Updated Timestamp;>> Accesscontainer.nebula
ECHO Updatedby Date;>> Accesscontainer.nebula
ECHO };>> Accesscontainer.nebula 
 ECHO @Package("Collaboration Management") type  Accesslistbpgroup{ >> Accesslistbpgroup.nebula
ECHO Org;>> Accesslistbpgroup.nebula
ECHO !Accessprofile;>> Accesslistbpgroup.nebula
ECHO !Bp_Group;>> Accesslistbpgroup.nebula
ECHO Created Timestamp;>> Accesslistbpgroup.nebula
ECHO Createdby Date;>> Accesslistbpgroup.nebula
ECHO Isactive YesNo;>> Accesslistbpgroup.nebula
ECHO Updated Timestamp;>> Accesslistbpgroup.nebula
ECHO Updatedby Date;>> Accesslistbpgroup.nebula
ECHO };>> Accesslistbpgroup.nebula 
 ECHO @Package("Collaboration Management") type  Accesslistrole{ >> Accesslistrole.nebula
ECHO Org;>> Accesslistrole.nebula
ECHO !Role;>> Accesslistrole.nebula
ECHO !Accessprofile;>> Accesslistrole.nebula
ECHO Created Timestamp;>> Accesslistrole.nebula
ECHO Createdby Date;>> Accesslistrole.nebula
ECHO Isactive YesNo;>> Accesslistrole.nebula
ECHO Updated Timestamp;>> Accesslistrole.nebula
ECHO Updatedby Date;>> Accesslistrole.nebula
ECHO };>> Accesslistrole.nebula 
 ECHO @Package("Collaboration Management") type  Accessmedia{ >> Accessmedia.nebula
ECHO Org;>> Accessmedia.nebula
ECHO !Accessprofile;>> Accessmedia.nebula
ECHO !Media;>> Accessmedia.nebula
ECHO Created Timestamp;>> Accessmedia.nebula
ECHO Createdby Date;>> Accessmedia.nebula
ECHO Isactive YesNo;>> Accessmedia.nebula
ECHO Updated Timestamp;>> Accessmedia.nebula
ECHO Updatedby Date;>> Accessmedia.nebula
ECHO };>> Accessmedia.nebula 
 ECHO @Package("Collaboration Management") type  Accessnewschannel{ >> Accessnewschannel.nebula
ECHO Org;>> Accessnewschannel.nebula
ECHO !Accessprofile;>> Accessnewschannel.nebula
ECHO !Newschannel;>> Accessnewschannel.nebula
ECHO Created Timestamp;>> Accessnewschannel.nebula
ECHO Createdby Date;>> Accessnewschannel.nebula
ECHO Isactive YesNo;>> Accessnewschannel.nebula
ECHO Updated Timestamp;>> Accessnewschannel.nebula
ECHO Updatedby Date;>> Accessnewschannel.nebula
ECHO };>> Accessnewschannel.nebula 
 ECHO @Package("Collaboration Management") type  Accessprofile{ >> Accessprofile.nebula
ECHO Org;>> Accessprofile.nebula
ECHO !ID;>> Accessprofile.nebula
ECHO Created Timestamp;>> Accessprofile.nebula
ECHO Createdby Date;>> Accessprofile.nebula
ECHO Description;>> Accessprofile.nebula
ECHO Help;>> Accessprofile.nebula
ECHO Isactive YesNo;>> Accessprofile.nebula
ECHO Isexclude YesNo;>> Accessprofile.nebula
ECHO !Name;>> Accessprofile.nebula
ECHO Updated Timestamp;>> Accessprofile.nebula
ECHO Updatedby Date;>> Accessprofile.nebula
ECHO };>> Accessprofile.nebula 
 ECHO @Package("Collaboration Management") type  Accessstage{ >> Accessstage.nebula
ECHO Org;>> Accessstage.nebula
ECHO !Accessprofile;>> Accessstage.nebula
ECHO !Cstage;>> Accessstage.nebula
ECHO Created Timestamp;>> Accessstage.nebula
ECHO Createdby Date;>> Accessstage.nebula
ECHO Isactive YesNo;>> Accessstage.nebula
ECHO Updated Timestamp;>> Accessstage.nebula
ECHO Updatedby Date;>> Accessstage.nebula
ECHO };>> Accessstage.nebula 
 ECHO @Package("Collaboration Management") type  Ad{ >> Ad.nebula
ECHO Org;>> Ad.nebula
ECHO Actualclick Number;>> Ad.nebula
ECHO Actualimpression Number;>> Ad.nebula
ECHO Ad_Cat;>> Ad.nebula
ECHO !ID;>> Ad.nebula
ECHO Media;>> Ad.nebula
ECHO Contenthtml Note;>> Ad.nebula
ECHO Created Timestamp;>> Ad.nebula
ECHO Createdby Date;>> Ad.nebula
ECHO Description;>> Ad.nebula
ECHO Enddate Date;>> Ad.nebula
ECHO Help;>> Ad.nebula
ECHO Isactive YesNo;>> Ad.nebula
ECHO Isadflag YesNo;>> Ad.nebula
ECHO Islogged YesNo;>> Ad.nebula
ECHO Maxclick Number;>> Ad.nebula
ECHO Maximpression Number;>> Ad.nebula
ECHO !Name;>> Ad.nebula
ECHO Startdate Date;>> Ad.nebula
ECHO Startimpression Number;>> Ad.nebula
ECHO Targeturl URL;>> Ad.nebula
ECHO Target_Frame String;>> Ad.nebula
ECHO Updated Timestamp;>> Ad.nebula
ECHO Updatedby Date;>> Ad.nebula
ECHO };>> Ad.nebula 
 ECHO @Package("Collaboration Management") type  Ad_Cat{ >> Ad_Cat.nebula
ECHO Org;>> Ad_Cat.nebula
ECHO !ID;>> Ad_Cat.nebula
ECHO Webproject;>> Ad_Cat.nebula
ECHO Created Timestamp;>> Ad_Cat.nebula
ECHO Createdby Date;>> Ad_Cat.nebula
ECHO Description;>> Ad_Cat.nebula
ECHO Help;>> Ad_Cat.nebula
ECHO Isactive YesNo;>> Ad_Cat.nebula
ECHO !Name;>> Ad_Cat.nebula
ECHO Updated Timestamp;>> Ad_Cat.nebula
ECHO Updatedby Date;>> Ad_Cat.nebula
ECHO };>> Ad_Cat.nebula 
 ECHO @Package("Collaboration Management") type  Broadcastserver{ >> Broadcastserver.nebula
ECHO Org;>> Broadcastserver.nebula
ECHO !ID;>> Broadcastserver.nebula
ECHO Webproject;>> Broadcastserver.nebula
ECHO Created Timestamp;>> Broadcastserver.nebula
ECHO Createdby Date;>> Broadcastserver.nebula
ECHO Description;>> Broadcastserver.nebula
ECHO Help;>> Broadcastserver.nebula
ECHO Ip_Address IPAddr;>> Broadcastserver.nebula
ECHO Isactive YesNo;>> Broadcastserver.nebula
ECHO Lastsynchronized Date;>> Broadcastserver.nebula
ECHO !Name;>> Broadcastserver.nebula
ECHO Processing YesNo;>> Broadcastserver.nebula
ECHO Updated Timestamp;>> Broadcastserver.nebula
ECHO Updatedby Date;>> Broadcastserver.nebula
ECHO };>> Broadcastserver.nebula 
 ECHO @Package("Collaboration Management") type  Chat{ >> Chat.nebula
ECHO Org;>> Chat.nebula
ECHO Table;>> Chat.nebula
ECHO Chattype;>> Chat.nebula
ECHO !ID;>> Chat.nebula
ECHO Confidentialtype YesNo;>> Chat.nebula
ECHO Created Timestamp;>> Chat.nebula
ECHO Createdby Date;>> Chat.nebula
ECHO Description;>> Chat.nebula
ECHO Isactive YesNo;>> Chat.nebula
ECHO Moderationtype YesNo;>> Chat.nebula

ECHO Updated Timestamp;>> Chat.nebula
ECHO Updatedby Date;>> Chat.nebula
ECHO };>> Chat.nebula 
 ECHO @Package("Collaboration Management") type  Chatentry{ >> Chatentry.nebula
ECHO Org;>> Chatentry.nebula
ECHO User;>> Chatentry.nebula
ECHO Chatentrygrandparent Chatentry;>> Chatentry.nebula
ECHO Chatentryparent Chatentry;>> Chatentry.nebula
ECHO !ID;>> Chatentry.nebula
ECHO Chat;>> Chatentry.nebula
ECHO Characterdata Note;>> Chatentry.nebula
ECHO Chatentrytype YesNo;>> Chatentry.nebula
ECHO Confidentialtype YesNo;>> Chatentry.nebula
ECHO Created Timestamp;>> Chatentry.nebula
ECHO Createdby Date;>> Chatentry.nebula
ECHO Isactive YesNo;>> Chatentry.nebula
ECHO Moderatorstatus YesNo;>> Chatentry.nebula
ECHO Subject;>> Chatentry.nebula
ECHO Updated Timestamp;>> Chatentry.nebula
ECHO Updatedby Date;>> Chatentry.nebula
ECHO };>> Chatentry.nebula 
 ECHO @Package("Collaboration Management") type  Chattype{ >> Chattype.nebula
ECHO Org;>> Chattype.nebula
ECHO Table;>> Chattype.nebula
ECHO !ID;>> Chattype.nebula
ECHO Created Timestamp;>> Chattype.nebula
ECHO Createdby Date;>> Chattype.nebula
ECHO Description;>> Chattype.nebula
ECHO Isactive YesNo;>> Chattype.nebula
ECHO Moderationtype YesNo;>> Chattype.nebula
ECHO !Name;>> Chattype.nebula
ECHO Updated Timestamp;>> Chattype.nebula
ECHO Updatedby Date;>> Chattype.nebula
ECHO };>> Chattype.nebula 
 ECHO @Package("Collaboration Management") type  Chattypeupdate{ >> Chattypeupdate.nebula
ECHO Org;>> Chattypeupdate.nebula
ECHO !User;>> Chattypeupdate.nebula
ECHO !Chattype;>> Chattypeupdate.nebula
ECHO Created Timestamp;>> Chattypeupdate.nebula
ECHO Createdby Date;>> Chattypeupdate.nebula
ECHO Isactive YesNo;>> Chattypeupdate.nebula
ECHO Isselfservice YesNo;>> Chattypeupdate.nebula
ECHO Updated Timestamp;>> Chattypeupdate.nebula
ECHO Updatedby Date;>> Chattypeupdate.nebula
ECHO };>> Chattypeupdate.nebula 
 ECHO @Package("Collaboration Management") type  Chatupdate{ >> Chatupdate.nebula
ECHO Org;>> Chatupdate.nebula
ECHO !User;>> Chatupdate.nebula
ECHO !Chat;>> Chatupdate.nebula
ECHO Created Timestamp;>> Chatupdate.nebula
ECHO Createdby Date;>> Chatupdate.nebula
ECHO Isactive YesNo;>> Chatupdate.nebula
ECHO Isselfservice YesNo;>> Chatupdate.nebula
ECHO Updated Timestamp;>> Chatupdate.nebula
ECHO Updatedby Date;>> Chatupdate.nebula
ECHO };>> Chatupdate.nebula 
 ECHO @Package("Collaboration Management") type  Container{ >> Container.nebula
ECHO Org;>> Container.nebula
ECHO Containerlink Container;>> Container.nebula
ECHO !ID;>> Container.nebula
ECHO Template;>> Container.nebula
ECHO Webproject;>> Container.nebula
ECHO Containerlinkurl URL;>> Container.nebula
ECHO Containertype YesNo;>> Container.nebula
ECHO Containerxml Note;>> Container.nebula
ECHO Created Timestamp;>> Container.nebula
ECHO Createdby Date;>> Container.nebula
ECHO Description;>> Container.nebula
ECHO Help;>> Container.nebula
ECHO Isactive YesNo;>> Container.nebula
ECHO Isindexed YesNo;>> Container.nebula
ECHO Issecure YesNo;>> Container.nebula
ECHO Issummary YesNo;>> Container.nebula
ECHO Isvalid YesNo;>> Container.nebula
ECHO Meta_Author Note;>> Container.nebula
ECHO Meta_Content Note;>> Container.nebula
ECHO Meta_Copyright Note;>> Container.nebula
ECHO Meta_Description Description;>> Container.nebula
ECHO Meta_Keywords Note;>> Container.nebula
ECHO Meta_Language Attr;>> Container.nebula
ECHO Meta_Publisher Note;>> Container.nebula
ECHO Meta_Robotstag Note;>> Container.nebula
ECHO !Name;>> Container.nebula
ECHO Relativeurl URL;>> Container.nebula
ECHO Structurexml Note;>> Container.nebula
ECHO Title String;>> Container.nebula
ECHO Updated Timestamp;>> Container.nebula
ECHO Updatedby Date;>> Container.nebula
ECHO };>> Container.nebula 
 ECHO @Package("Collaboration Management") type  Container_Element{ >> Container_Element.nebula
ECHO Org;>> Container_Element.nebula
ECHO !ID;>> Container_Element.nebula
ECHO Container;>> Container_Element.nebula
ECHO Contenthtml Note;>> Container_Element.nebula
ECHO Created Timestamp;>> Container_Element.nebula
ECHO Createdby Date;>> Container_Element.nebula
ECHO Description;>> Container_Element.nebula
ECHO Help;>> Container_Element.nebula
ECHO Isactive YesNo;>> Container_Element.nebula
ECHO Isvalid YesNo;>> Container_Element.nebula
ECHO !Name;>> Container_Element.nebula
ECHO Updated Timestamp;>> Container_Element.nebula
ECHO Updatedby Date;>> Container_Element.nebula
ECHO };>> Container_Element.nebula 
 ECHO @Package("Collaboration Management") type  Container_Element_trl{ >> Container_Element_trl.nebula
ECHO !Ad_Language Language;>> Container_Element_trl.nebula
ECHO Org;>> Container_Element_trl.nebula
ECHO !Container_Element;>> Container_Element_trl.nebula
ECHO Contenthtml Note;>> Container_Element_trl.nebula
ECHO Created Timestamp;>> Container_Element_trl.nebula
ECHO Createdby Date;>> Container_Element_trl.nebula
ECHO Description;>> Container_Element_trl.nebula
ECHO Help;>> Container_Element_trl.nebula
ECHO Isactive YesNo;>> Container_Element_trl.nebula
ECHO Istranslated YesNo;>> Container_Element_trl.nebula
ECHO !Name;>> Container_Element_trl.nebula
ECHO Updated Timestamp;>> Container_Element_trl.nebula
ECHO Updatedby Date;>> Container_Element_trl.nebula
ECHO };>> Container_Element_trl.nebula 
 ECHO @Package("Collaboration Management") type  Container_Trl{ >> Container_Trl.nebula
ECHO !Ad_Language Language;>> Container_Trl.nebula
ECHO Org;>> Container_Trl.nebula
ECHO !Container;>> Container_Trl.nebula
ECHO Containerxml Note;>> Container_Trl.nebula
ECHO Created Timestamp;>> Container_Trl.nebula
ECHO Createdby Date;>> Container_Trl.nebula
ECHO Isactive YesNo;>> Container_Trl.nebula
ECHO Istranslated YesNo;>> Container_Trl.nebula
ECHO Meta_Description Description;>> Container_Trl.nebula
ECHO Meta_Keywords Note;>> Container_Trl.nebula
ECHO !Name;>> Container_Trl.nebula
ECHO Structurexml Note;>> Container_Trl.nebula
ECHO Title String;>> Container_Trl.nebula
ECHO Updated Timestamp;>> Container_Trl.nebula
ECHO Updatedby Date;>> Container_Trl.nebula
ECHO };>> Container_Trl.nebula 
 ECHO @Package("Collaboration Management") type  Container_Url{ >> Container_Url.nebula
ECHO Org;>> Container_Url.nebula
ECHO Container;>> Container_Url.nebula
ECHO !ID;>> Container_Url.nebula
ECHO Checked Date;>> Container_Url.nebula
ECHO Created Timestamp;>> Container_Url.nebula
ECHO Createdby Date;>> Container_Url.nebula
ECHO Isactive YesNo;>> Container_Url.nebula
ECHO Last_Result Note;>> Container_Url.nebula
ECHO Status Attr;>> Container_Url.nebula
ECHO Updated Timestamp;>> Container_Url.nebula
ECHO Updatedby Date;>> Container_Url.nebula
ECHO };>> Container_Url.nebula 
 ECHO @Package("Collaboration Management") type  Containerttable{ >> Containerttable.nebula
ECHO Org;>> Containerttable.nebula
ECHO !ID;>> Containerttable.nebula
ECHO Container;>> Containerttable.nebula
ECHO Templatetable;>> Containerttable.nebula
ECHO Created Timestamp;>> Containerttable.nebula
ECHO Createdby Date;>> Containerttable.nebula
ECHO Description;>> Containerttable.nebula
ECHO Isactive YesNo;>> Containerttable.nebula
ECHO !Name;>> Containerttable.nebula
ECHO Otherclause Note;>> Containerttable.nebula

ECHO Updated Timestamp;>> Containerttable.nebula
ECHO Updatedby Date;>> Containerttable.nebula
ECHO Whereclause Note;>> Containerttable.nebula
ECHO };>> Containerttable.nebula 
 ECHO @Package("Collaboration Management") type  Cstage{ >> Cstage.nebula
ECHO Org;>> Cstage.nebula
ECHO Cstagelink Cstage;>> Cstage.nebula
ECHO !ID;>> Cstage.nebula
ECHO Template;>> Cstage.nebula
ECHO Webproject;>> Cstage.nebula
ECHO Containerlinkurl URL;>> Cstage.nebula
ECHO Containertype YesNo;>> Cstage.nebula
ECHO Containerxml Note;>> Cstage.nebula
ECHO Created Timestamp;>> Cstage.nebula
ECHO Createdby Date;>> Cstage.nebula
ECHO Description;>> Cstage.nebula
ECHO Help;>> Cstage.nebula
ECHO Isactive YesNo;>> Cstage.nebula
ECHO Isindexed YesNo;>> Cstage.nebula
ECHO Ismodified YesNo;>> Cstage.nebula
ECHO Issecure YesNo;>> Cstage.nebula
ECHO Issummary YesNo;>> Cstage.nebula
ECHO Isvalid YesNo;>> Cstage.nebula
ECHO Meta_Author Note;>> Cstage.nebula
ECHO Meta_Content Note;>> Cstage.nebula
ECHO Meta_Copyright Note;>> Cstage.nebula
ECHO Meta_Description Description;>> Cstage.nebula
ECHO Meta_Keywords Note;>> Cstage.nebula
ECHO Meta_Language Attr;>> Cstage.nebula
ECHO Meta_Publisher Note;>> Cstage.nebula
ECHO Meta_Robotstag Note;>> Cstage.nebula
ECHO !Name;>> Cstage.nebula
ECHO Processing YesNo;>> Cstage.nebula
ECHO Relativeurl URL;>> Cstage.nebula
ECHO Structurexml Note;>> Cstage.nebula
ECHO Title String;>> Cstage.nebula
ECHO Updated Timestamp;>> Cstage.nebula
ECHO Updatedby Date;>> Cstage.nebula
ECHO };>> Cstage.nebula 
 ECHO @Package("Collaboration Management") type  Cstage_Element{ >> Cstage_Element.nebula
ECHO Org;>> Cstage_Element.nebula
ECHO !ID;>> Cstage_Element.nebula
ECHO Cstage;>> Cstage_Element.nebula
ECHO Contenthtml Note;>> Cstage_Element.nebula
ECHO Created Timestamp;>> Cstage_Element.nebula
ECHO Createdby Date;>> Cstage_Element.nebula
ECHO Description;>> Cstage_Element.nebula
ECHO Help;>> Cstage_Element.nebula
ECHO Isactive YesNo;>> Cstage_Element.nebula
ECHO Isvalid YesNo;>> Cstage_Element.nebula
ECHO !Name;>> Cstage_Element.nebula
ECHO Updated Timestamp;>> Cstage_Element.nebula
ECHO Updatedby Date;>> Cstage_Element.nebula
ECHO };>> Cstage_Element.nebula 
 ECHO @Package("Collaboration Management") type  Cstage_Element_trl{ >> Cstage_Element_trl.nebula
ECHO !Ad_Language Language;>> Cstage_Element_trl.nebula
ECHO Org;>> Cstage_Element_trl.nebula
ECHO !Cstage_Element;>> Cstage_Element_trl.nebula
ECHO Contenthtml Note;>> Cstage_Element_trl.nebula
ECHO Created Timestamp;>> Cstage_Element_trl.nebula
ECHO Createdby Date;>> Cstage_Element_trl.nebula
ECHO Description;>> Cstage_Element_trl.nebula
ECHO Help;>> Cstage_Element_trl.nebula
ECHO Isactive YesNo;>> Cstage_Element_trl.nebula
ECHO Istranslated YesNo;>> Cstage_Element_trl.nebula
ECHO !Name;>> Cstage_Element_trl.nebula
ECHO Updated Timestamp;>> Cstage_Element_trl.nebula
ECHO Updatedby Date;>> Cstage_Element_trl.nebula
ECHO };>> Cstage_Element_trl.nebula 
 ECHO @Package("Collaboration Management") type  Cstage_Trl{ >> Cstage_Trl.nebula
ECHO !Ad_Language Language;>> Cstage_Trl.nebula
ECHO Org;>> Cstage_Trl.nebula
ECHO !Cstage;>> Cstage_Trl.nebula
ECHO Containerxml Note;>> Cstage_Trl.nebula
ECHO Created Timestamp;>> Cstage_Trl.nebula
ECHO Createdby Date;>> Cstage_Trl.nebula
ECHO Isactive YesNo;>> Cstage_Trl.nebula
ECHO Istranslated YesNo;>> Cstage_Trl.nebula
ECHO Meta_Description Description;>> Cstage_Trl.nebula
ECHO Meta_Keywords Note;>> Cstage_Trl.nebula
ECHO !Name;>> Cstage_Trl.nebula
ECHO Structurexml Note;>> Cstage_Trl.nebula
ECHO Title String;>> Cstage_Trl.nebula
ECHO Updated Timestamp;>> Cstage_Trl.nebula
ECHO Updatedby Date;>> Cstage_Trl.nebula
ECHO };>> Cstage_Trl.nebula 
 ECHO @Package("Collaboration Management") type  Cstagettable{ >> Cstagettable.nebula
ECHO Org;>> Cstagettable.nebula
ECHO !ID;>> Cstagettable.nebula
ECHO Cstage;>> Cstagettable.nebula
ECHO Templatetable;>> Cstagettable.nebula
ECHO Created Timestamp;>> Cstagettable.nebula
ECHO Createdby Date;>> Cstagettable.nebula
ECHO Description;>> Cstagettable.nebula
ECHO Isactive YesNo;>> Cstagettable.nebula
ECHO !Name;>> Cstagettable.nebula
ECHO Otherclause Note;>> Cstagettable.nebula

ECHO Updated Timestamp;>> Cstagettable.nebula
ECHO Updatedby Date;>> Cstagettable.nebula
ECHO Whereclause Note;>> Cstagettable.nebula
ECHO };>> Cstagettable.nebula 
 ECHO @Package("Collaboration Management") type  Media{ >> Media.nebula
ECHO Image;>> Media.nebula
ECHO Org;>> Media.nebula
ECHO !ID;>> Media.nebula
ECHO Webproject;>> Media.nebula
ECHO Contenttext Note;>> Media.nebula
ECHO Created Timestamp;>> Media.nebula
ECHO Createdby Date;>> Media.nebula
ECHO Description;>> Media.nebula
ECHO Directdeploy YesNo;>> Media.nebula
ECHO Help;>> Media.nebula
ECHO Isactive YesNo;>> Media.nebula
ECHO Issummary YesNo;>> Media.nebula
ECHO Mediatype Attr;>> Media.nebula
ECHO !Name;>> Media.nebula
ECHO Updated Timestamp;>> Media.nebula
ECHO Updatedby Date;>> Media.nebula
ECHO };>> Media.nebula 
 ECHO @Package("Collaboration Management") type  Media_Server{ >> Media_Server.nebula
ECHO Org;>> Media_Server.nebula
ECHO !ID;>> Media_Server.nebula
ECHO Webproject;>> Media_Server.nebula
ECHO Created Timestamp;>> Media_Server.nebula
ECHO Createdby Date;>> Media_Server.nebula
ECHO Description;>> Media_Server.nebula
ECHO Folder Name;>> Media_Server.nebula
ECHO Help;>> Media_Server.nebula
ECHO Ip_Address IPAddr;>> Media_Server.nebula
ECHO Isactive YesNo;>> Media_Server.nebula
ECHO Ispassive YesNo;>> Media_Server.nebula
ECHO !Name;>> Media_Server.nebula
ECHO Password String;>> Media_Server.nebula
ECHO URL;>> Media_Server.nebula
ECHO Updated Timestamp;>> Media_Server.nebula
ECHO Updatedby Date;>> Media_Server.nebula
ECHO Username Name;>> Media_Server.nebula
ECHO };>> Media_Server.nebula 
 ECHO @Package("Collaboration Management") type  Mediadeploy{ >> Mediadeploy.nebula
ECHO Org;>> Mediadeploy.nebula
ECHO !ID;>> Mediadeploy.nebula
ECHO Media;>> Mediadeploy.nebula
ECHO Media_Server;>> Mediadeploy.nebula
ECHO Created Timestamp;>> Mediadeploy.nebula
ECHO Createdby Date;>> Mediadeploy.nebula
ECHO Description;>> Mediadeploy.nebula
ECHO Isactive YesNo;>> Mediadeploy.nebula
ECHO Isdeployed YesNo;>> Mediadeploy.nebula
ECHO Lastsynchronized Date;>> Mediadeploy.nebula
ECHO Updated Timestamp;>> Mediadeploy.nebula
ECHO Updatedby Date;>> Mediadeploy.nebula
ECHO };>> Mediadeploy.nebula 
 ECHO @Package("Collaboration Management") type  Newschannel{ >> Newschannel.nebula
ECHO Ad_Language Language;>> Newschannel.nebula
ECHO Org;>> Newschannel.nebula
ECHO !ID;>> Newschannel.nebula
ECHO Webproject;>> Newschannel.nebula
ECHO Created Timestamp;>> Newschannel.nebula
ECHO Createdby Date;>> Newschannel.nebula
ECHO Description;>> Newschannel.nebula
ECHO Help;>> Newschannel.nebula
ECHO Isactive YesNo;>> Newschannel.nebula
ECHO Link String;>> Newschannel.nebula
ECHO !Name;>> Newschannel.nebula
ECHO Updated Timestamp;>> Newschannel.nebula
ECHO Updatedby Date;>> Newschannel.nebula
ECHO };>> Newschannel.nebula 
 ECHO @Package("Collaboration Management") type  Newsitem{ >> Newsitem.nebula
ECHO Org;>> Newsitem.nebula
ECHO Author String;>> Newsitem.nebula
ECHO Newschannel;>> Newsitem.nebula
ECHO !ID;>> Newsitem.nebula
ECHO Contenthtml Note;>> Newsitem.nebula
ECHO Created Timestamp;>> Newsitem.nebula
ECHO Createdby Date;>> Newsitem.nebula
ECHO Description;>> Newsitem.nebula
ECHO Isactive YesNo;>> Newsitem.nebula
ECHO Linkurl URL;>> Newsitem.nebula
ECHO Pubdate Date;>> Newsitem.nebula
ECHO Title String;>> Newsitem.nebula
ECHO Updated Timestamp;>> Newsitem.nebula
ECHO Updatedby Date;>> Newsitem.nebula
ECHO };>> Newsitem.nebula 
 ECHO @Package("Collaboration Management") type  Template{ >> Template.nebula
ECHO Org;>> Template.nebula
ECHO !ID;>> Template.nebula
ECHO Webproject;>> Template.nebula
ECHO Created Timestamp;>> Template.nebula
ECHO Createdby Date;>> Template.nebula
ECHO Description;>> Template.nebula
ECHO Elements Note;>> Template.nebula
ECHO Help;>> Template.nebula
ECHO Isactive YesNo;>> Template.nebula
ECHO Isinclude YesNo;>> Template.nebula
ECHO Isnews YesNo;>> Template.nebula
ECHO Issummary YesNo;>> Template.nebula
ECHO Isusead YesNo;>> Template.nebula
ECHO Isvalid YesNo;>> Template.nebula
ECHO !Name;>> Template.nebula
ECHO Processing YesNo;>> Template.nebula
ECHO Templatexst Note;>> Template.nebula
ECHO Updated Timestamp;>> Template.nebula
ECHO Updatedby Date;>> Template.nebula
ECHO Value String;>> Template.nebula
ECHO };>> Template.nebula 
 ECHO @Package("Collaboration Management") type  Template_Ad_cat{ >> Template_Ad_cat.nebula
ECHO Org;>> Template_Ad_cat.nebula
ECHO !Ad_Cat;>> Template_Ad_cat.nebula
ECHO !Template;>> Template_Ad_cat.nebula
ECHO Created Timestamp;>> Template_Ad_cat.nebula
ECHO Createdby Date;>> Template_Ad_cat.nebula
ECHO Description;>> Template_Ad_cat.nebula
ECHO Isactive YesNo;>> Template_Ad_cat.nebula
ECHO !Name;>> Template_Ad_cat.nebula
ECHO Updated Timestamp;>> Template_Ad_cat.nebula
ECHO Updatedby Date;>> Template_Ad_cat.nebula
ECHO };>> Template_Ad_cat.nebula 
 ECHO @Package("Collaboration Management") type  Templatetable{ >> Templatetable.nebula
ECHO Org;>> Templatetable.nebula
ECHO Table;>> Templatetable.nebula
ECHO !ID;>> Templatetable.nebula
ECHO Template;>> Templatetable.nebula
ECHO Created Timestamp;>> Templatetable.nebula
ECHO Createdby Date;>> Templatetable.nebula
ECHO Description;>> Templatetable.nebula
ECHO Isactive YesNo;>> Templatetable.nebula
ECHO !Name;>> Templatetable.nebula
ECHO Otherclause Note;>> Templatetable.nebula
ECHO Updated Timestamp;>> Templatetable.nebula
ECHO Updatedby Date;>> Templatetable.nebula
ECHO Whereclause Note;>> Templatetable.nebula
ECHO };>> Templatetable.nebula 
 ECHO @Package("Collaboration Management") type  Webaccesslog{ >> Webaccesslog.nebula
ECHO Org;>> Webaccesslog.nebula
ECHO User;>> Webaccesslog.nebula
ECHO Acceptlanguage Language;>> Webaccesslog.nebula
ECHO Broadcastserver;>> Webaccesslog.nebula
ECHO Media;>> Webaccesslog.nebula
ECHO !ID;>> Webaccesslog.nebula
ECHO Webproject;>> Webaccesslog.nebula
ECHO Created Timestamp;>> Webaccesslog.nebula
ECHO Createdby Date;>> Webaccesslog.nebula
ECHO Filesize Long;>> Webaccesslog.nebula
ECHO Hyphen String;>> Webaccesslog.nebula
ECHO Ip_Address IPAddr;>> Webaccesslog.nebula
ECHO Isactive YesNo;>> Webaccesslog.nebula
ECHO Logtype YesNo;>> Webaccesslog.nebula
ECHO Pageurl URL;>> Webaccesslog.nebula
ECHO Protocol String;>> Webaccesslog.nebula
ECHO Referrer String;>> Webaccesslog.nebula
ECHO Remote_Addr URL;>> Webaccesslog.nebula
ECHO Remote_Host Host;>> Webaccesslog.nebula
ECHO Requesttype Attr;>> Webaccesslog.nebula
ECHO Statuscode Number;>> Webaccesslog.nebula
ECHO Updated Timestamp;>> Webaccesslog.nebula
ECHO Updatedby Date;>> Webaccesslog.nebula
ECHO Useragent String;>> Webaccesslog.nebula
ECHO Websession String;>> Webaccesslog.nebula
ECHO };>> Webaccesslog.nebula 
 ECHO @Package("Collaboration Management") type  Webproject{ >> Webproject.nebula
ECHO Org;>> Webproject.nebula
ECHO Treecmc Tree;>> Webproject.nebula
ECHO Treecmm Tree;>> Webproject.nebula
ECHO Treecms Tree;>> Webproject.nebula
ECHO Treecmt Tree;>> Webproject.nebula
ECHO !ID;>> Webproject.nebula
ECHO Created Timestamp;>> Webproject.nebula
ECHO Createdby Date;>> Webproject.nebula
ECHO Description;>> Webproject.nebula
ECHO Help;>> Webproject.nebula
ECHO Isactive YesNo;>> Webproject.nebula
ECHO Meta_Author Note;>> Webproject.nebula
ECHO Meta_Content Note;>> Webproject.nebula
ECHO Meta_Copyright Note;>> Webproject.nebula
ECHO Meta_Publisher Note;>> Webproject.nebula
ECHO Meta_Robotstag Note;>> Webproject.nebula
ECHO !Name;>> Webproject.nebula
ECHO Updated Timestamp;>> Webproject.nebula
ECHO Updatedby Date;>> Webproject.nebula
ECHO };>> Webproject.nebula 
 ECHO @Package("Collaboration Management") type  Webproject_Domain{ >> Webproject_Domain.nebula
ECHO Org;>> Webproject_Domain.nebula
ECHO Container;>> Webproject_Domain.nebula
ECHO !ID;>> Webproject_Domain.nebula
ECHO Webproject;>> Webproject_Domain.nebula
ECHO Created Timestamp;>> Webproject_Domain.nebula
ECHO Createdby Date;>> Webproject_Domain.nebula
ECHO Description;>> Webproject_Domain.nebula
ECHO Fqdn String;>> Webproject_Domain.nebula
ECHO Help;>> Webproject_Domain.nebula
ECHO Isactive YesNo;>> Webproject_Domain.nebula
ECHO !Name;>> Webproject_Domain.nebula
ECHO Updated Timestamp;>> Webproject_Domain.nebula
ECHO Updatedby Date;>> Webproject_Domain.nebula
ECHO };>> Webproject_Domain.nebula 
 ECHO @Package("Collaboration Management") type  Wikitoken{ >> Wikitoken.nebula
ECHO Org;>> Wikitoken.nebula
ECHO Table;>> Wikitoken.nebula
ECHO !ID;>> Wikitoken.nebula
ECHO Created Timestamp;>> Wikitoken.nebula
ECHO Createdby Date;>> Wikitoken.nebula
ECHO Description;>> Wikitoken.nebula
ECHO Isactive YesNo;>> Wikitoken.nebula
ECHO Macro Note;>> Wikitoken.nebula
ECHO !Name;>> Wikitoken.nebula
ECHO Selectclause Note;>> Wikitoken.nebula
ECHO SeqNo;>> Wikitoken.nebula
ECHO Tokentype YesNo;>> Wikitoken.nebula
ECHO Updated Timestamp;>> Wikitoken.nebula
ECHO Updatedby Date;>> Wikitoken.nebula
ECHO Whereclause Note;>> Wikitoken.nebula
ECHO };>> Wikitoken.nebula 
 ECHO @Package("Multi-Dimensional Cube") type  Acct{ >> Acct.nebula
ECHO Orgtrx Org;>> Acct.nebula
ECHO Org;>> Acct.nebula
ECHO Table;>> Acct.nebula
ECHO Asset;>> Acct.nebula
ECHO Account Elementvalue;>> Acct.nebula
ECHO Amtacctcr Amount;>> Acct.nebula
ECHO Amtacctdr Amount;>> Acct.nebula
ECHO Amtsourcecr Amount;>> Acct.nebula
ECHO Amtsourcedr Amount;>> Acct.nebula
ECHO Acctschema;>> Acct.nebula
ECHO Activity;>> Acct.nebula
ECHO Bpartner;>> Acct.nebula
ECHO Campaign;>> Acct.nebula
ECHO Currency;>> Acct.nebula
ECHO Locfrom Location;>> Acct.nebula
ECHO Locto Location;>> Acct.nebula
ECHO Period;>> Acct.nebula
ECHO Projectphase;>> Acct.nebula
ECHO Projecttask;>> Acct.nebula
ECHO Project;>> Acct.nebula
ECHO Salesregion;>> Acct.nebula
ECHO Subacct;>> Acct.nebula
ECHO Tax;>> Acct.nebula
ECHO Uom;>> Acct.nebula
ECHO Created Timestamp;>> Acct.nebula
ECHO Createdby Date;>> Acct.nebula
ECHO Dateacct Date;>> Acct.nebula
ECHO Datetrx Date;>> Acct.nebula
ECHO Description;>> Acct.nebula
ECHO !ID;>> Acct.nebula
ECHO Budget;>> Acct.nebula
ECHO Category;>> Acct.nebula
ECHO Isactive YesNo;>> Acct.nebula

ECHO Locator;>> Acct.nebula
ECHO Product;>> Acct.nebula
ECHO Postingtype YesNo;>> Acct.nebula
ECHO Qty Quantity;>> Acct.nebula

ECHO Updated Timestamp;>> Acct.nebula
ECHO Updatedby Date;>> Acct.nebula
ECHO User1 Elementvalue;>> Acct.nebula
ECHO !User2 Elementvalue;>> Acct.nebula


ECHO };>> Acct.nebula 
 ECHO @Package("Multi-Dimensional Cube") type  Acct_Balance{ >> Acct_Balance.nebula
ECHO Orgtrx Org;>> Acct_Balance.nebula
ECHO Org;>> Acct_Balance.nebula
ECHO Account Elementvalue;>> Acct_Balance.nebula
ECHO Amtacctcr Amount;>> Acct_Balance.nebula
ECHO Amtacctdr Amount;>> Acct_Balance.nebula
ECHO Acctschema;>> Acct_Balance.nebula
ECHO Activity;>> Acct_Balance.nebula
ECHO Bpartner;>> Acct_Balance.nebula
ECHO Campaign;>> Acct_Balance.nebula
ECHO Locfrom Location;>> Acct_Balance.nebula
ECHO Locto Location;>> Acct_Balance.nebula
ECHO Projectphase;>> Acct_Balance.nebula
ECHO Projecttask;>> Acct_Balance.nebula
ECHO Project;>> Acct_Balance.nebula
ECHO Salesregion;>> Acct_Balance.nebula
ECHO Subacct;>> Acct_Balance.nebula
ECHO Created Timestamp;>> Acct_Balance.nebula
ECHO Createdby Date;>> Acct_Balance.nebula
ECHO Dateacct Date;>> Acct_Balance.nebula
ECHO Budget;>> Acct_Balance.nebula
ECHO Isactive YesNo;>> Acct_Balance.nebula
ECHO Product;>> Acct_Balance.nebula
ECHO Postingtype YesNo;>> Acct_Balance.nebula
ECHO Qty Quantity;>> Acct_Balance.nebula
ECHO Updated Timestamp;>> Acct_Balance.nebula
ECHO Updatedby Date;>> Acct_Balance.nebula
ECHO User1 Elementvalue;>> Acct_Balance.nebula
ECHO User2 Elementvalue;>> Acct_Balance.nebula


ECHO };>> Acct_Balance.nebula 
 ECHO @Package("Multi-Dimensional Cube") type  Accumulation{ >> Accumulation.nebula
ECHO Org;>> Accumulation.nebula
ECHO Balanceaccumulation YesNo;>> Accumulation.nebula
ECHO Acctschema;>> Accumulation.nebula
ECHO Calendar;>> Accumulation.nebula
ECHO Created Timestamp;>> Accumulation.nebula
ECHO Createdby Date;>> Accumulation.nebula
ECHO Dateto Date;>> Accumulation.nebula
ECHO Description;>> Accumulation.nebula
ECHO !ID;>> Accumulation.nebula
ECHO Isactive YesNo;>> Accumulation.nebula
ECHO Updated Timestamp;>> Accumulation.nebula
ECHO Updatedby Date;>> Accumulation.nebula
ECHO };>> Accumulation.nebula 
 ECHO @Package("General Ledger") type  Budget{ >> Budget.nebula
ECHO Org;>> Budget.nebula
ECHO Budgetstatus YesNo;>> Budget.nebula
ECHO Created Timestamp;>> Budget.nebula
ECHO Createdby Date;>> Budget.nebula
ECHO Description;>> Budget.nebula
ECHO !ID;>> Budget.nebula
ECHO Isactive YesNo;>> Budget.nebula
ECHO Isprimary YesNo;>> Budget.nebula
ECHO !Name;>> Budget.nebula
ECHO Updated Timestamp;>> Budget.nebula
ECHO Updatedby Date;>> Budget.nebula
ECHO };>> Budget.nebula 
 ECHO @Package("General Ledger") type  Budgetcontrol{ >> Budgetcontrol.nebula
ECHO Org;>> Budgetcontrol.nebula
ECHO Budgetcontrolscope YesNo;>> Budgetcontrol.nebula
ECHO Acctschema;>> Budgetcontrol.nebula
ECHO Commitmenttype YesNo;>> Budgetcontrol.nebula
ECHO Created Timestamp;>> Budgetcontrol.nebula
ECHO Createdby Date;>> Budgetcontrol.nebula
ECHO Description;>> Budgetcontrol.nebula
ECHO !ID;>> Budgetcontrol.nebula
ECHO Budget;>> Budgetcontrol.nebula
ECHO Help;>> Budgetcontrol.nebula
ECHO Isactive YesNo;>> Budgetcontrol.nebula
ECHO Isbeforeapproval YesNo;>> Budgetcontrol.nebula
ECHO !Name;>> Budgetcontrol.nebula
ECHO Updated Timestamp;>> Budgetcontrol.nebula
ECHO Updatedby Date;>> Budgetcontrol.nebula
ECHO };>> Budgetcontrol.nebula 
 ECHO @Package("General Ledger") type  Category{ >> Category.nebula
ECHO Org;>> Category.nebula
ECHO Categorytype YesNo;>> Category.nebula
ECHO Created Timestamp;>> Category.nebula
ECHO Createdby Date;>> Category.nebula
ECHO Description;>> Category.nebula
ECHO Docbasetype Attr;>> Category.nebula
ECHO !ID;>> Category.nebula
ECHO Isactive YesNo;>> Category.nebula
ECHO Isdefault YesNo;>> Category.nebula
ECHO !Name;>> Category.nebula
ECHO Updated Timestamp;>> Category.nebula
ECHO Updatedby Date;>> Category.nebula
ECHO };>> Category.nebula 
 ECHO @Package("General Ledger") type  Distribution{ >> Distribution.nebula
ECHO Orgtrx Org;>> Distribution.nebula
ECHO Org;>> Distribution.nebula
ECHO Account Elementvalue;>> Distribution.nebula
ECHO Anyacct YesNo;>> Distribution.nebula
ECHO Anyactivity YesNo;>> Distribution.nebula
ECHO Anybpartner YesNo;>> Distribution.nebula
ECHO Anycampaign YesNo;>> Distribution.nebula
ECHO Anylocfrom YesNo;>> Distribution.nebula
ECHO Anylocto YesNo;>> Distribution.nebula
ECHO Anyorg YesNo;>> Distribution.nebula
ECHO Anyorgtrx YesNo;>> Distribution.nebula
ECHO Anyproduct YesNo;>> Distribution.nebula
ECHO Anyproject YesNo;>> Distribution.nebula
ECHO Anysalesregion YesNo;>> Distribution.nebula
ECHO Anyuser1 YesNo;>> Distribution.nebula
ECHO Anyuser2 YesNo;>> Distribution.nebula
ECHO Acctschema;>> Distribution.nebula
ECHO Activity;>> Distribution.nebula
ECHO Bpartner;>> Distribution.nebula
ECHO Campaign;>> Distribution.nebula
ECHO Doctype;>> Distribution.nebula
ECHO Locfrom Location;>> Distribution.nebula
ECHO Locto Location;>> Distribution.nebula
ECHO Project;>> Distribution.nebula
ECHO Salesregion;>> Distribution.nebula
ECHO Created Timestamp;>> Distribution.nebula
ECHO Createdby Date;>> Distribution.nebula
ECHO Description;>> Distribution.nebula
ECHO !ID;>> Distribution.nebula
ECHO Help;>> Distribution.nebula
ECHO Isactive YesNo;>> Distribution.nebula
ECHO Isvalid YesNo;>> Distribution.nebula
ECHO Product;>> Distribution.nebula
ECHO !Name;>> Distribution.nebula
ECHO Org;>> Distribution.nebula
ECHO Percenttotal Number;>> Distribution.nebula
ECHO Postingtype YesNo;>> Distribution.nebula
ECHO Processing YesNo;>> Distribution.nebula
ECHO Updated Timestamp;>> Distribution.nebula
ECHO Updatedby Date;>> Distribution.nebula
ECHO User1 Elementvalue;>> Distribution.nebula
ECHO User2 Elementvalue;>> Distribution.nebula
ECHO };>> Distribution.nebula 
 ECHO @Package("General Ledger") type  Distributionline{ >> Distributionline.nebula
ECHO Orgtrx Org;>> Distributionline.nebula
ECHO Org;>> Distributionline.nebula
ECHO Account Elementvalue;>> Distributionline.nebula
ECHO Activity;>> Distributionline.nebula
ECHO Bpartner;>> Distributionline.nebula
ECHO Campaign;>> Distributionline.nebula
ECHO Locfrom Location;>> Distributionline.nebula
ECHO Locto Location;>> Distributionline.nebula
ECHO Project;>> Distributionline.nebula
ECHO Salesregion;>> Distributionline.nebula
ECHO Created Timestamp;>> Distributionline.nebula
ECHO Createdby Date;>> Distributionline.nebula
ECHO Description;>> Distributionline.nebula
ECHO !ID;>> Distributionline.nebula
ECHO Distribution;>> Distributionline.nebula
ECHO Isactive YesNo;>> Distributionline.nebula
ECHO Line Number;>> Distributionline.nebula
ECHO Product;>> Distributionline.nebula
ECHO Org;>> Distributionline.nebula
ECHO Overwriteacct YesNo;>> Distributionline.nebula
ECHO Overwriteactivity YesNo;>> Distributionline.nebula
ECHO Overwritebpartner YesNo;>> Distributionline.nebula
ECHO Overwritecampaign YesNo;>> Distributionline.nebula
ECHO Overwritelocfrom YesNo;>> Distributionline.nebula
ECHO Overwritelocto YesNo;>> Distributionline.nebula
ECHO Overwriteorg YesNo;>> Distributionline.nebula
ECHO Overwriteorgtrx YesNo;>> Distributionline.nebula
ECHO Overwriteproduct YesNo;>> Distributionline.nebula
ECHO Overwriteproject YesNo;>> Distributionline.nebula
ECHO Overwritesalesregion YesNo;>> Distributionline.nebula
ECHO Overwriteuser1 YesNo;>> Distributionline.nebula
ECHO Overwriteuser2 YesNo;>> Distributionline.nebula
ECHO Percentdistribution Number;>> Distributionline.nebula
ECHO Updated Timestamp;>> Distributionline.nebula
ECHO Updatedby Date;>> Distributionline.nebula
ECHO User1 Elementvalue;>> Distributionline.nebula
ECHO User2 Elementvalue;>> Distributionline.nebula
ECHO };>> Distributionline.nebula 
 ECHO @Package("General Ledger") type  Fund{ >> Fund.nebula
ECHO Org;>> Fund.nebula
ECHO Amt Amount;>> Fund.nebula
ECHO Acctschema;>> Fund.nebula
ECHO Created Timestamp;>> Fund.nebula
ECHO Createdby Date;>> Fund.nebula
ECHO Datefrom Date;>> Fund.nebula
ECHO Dateto Date;>> Fund.nebula
ECHO Description;>> Fund.nebula
ECHO !ID;>> Fund.nebula
ECHO Help;>> Fund.nebula
ECHO Isactive YesNo;>> Fund.nebula
ECHO !Name;>> Fund.nebula
ECHO Updated Timestamp;>> Fund.nebula
ECHO Updatedby Date;>> Fund.nebula
ECHO };>> Fund.nebula 
 ECHO @Package("General Ledger") type  Fundrestriction{ >> Fundrestriction.nebula
ECHO Org;>> Fundrestriction.nebula
ECHO Elementvalue;>> Fundrestriction.nebula
ECHO Created Timestamp;>> Fundrestriction.nebula
ECHO Createdby Date;>> Fundrestriction.nebula
ECHO Description;>> Fundrestriction.nebula
ECHO !ID Count;>> Fundrestriction.nebula
ECHO Fund;>> Fundrestriction.nebula
ECHO Isactive YesNo;>> Fundrestriction.nebula
ECHO !Name;>> Fundrestriction.nebula
ECHO Updated Timestamp;>> Fundrestriction.nebula
ECHO Updatedby Date;>> Fundrestriction.nebula
ECHO };>> Fundrestriction.nebula 
 ECHO @Package("General Ledger") type  Journal{ >> Journal.nebula
ECHO Org;>> Journal.nebula
ECHO Acctschema;>> Journal.nebula
ECHO Conversiontype;>> Journal.nebula
ECHO Currency;>> Journal.nebula
ECHO Doctype;>> Journal.nebula
ECHO Period;>> Journal.nebula
ECHO Controlamt Amount;>> Journal.nebula
ECHO Created Timestamp;>> Journal.nebula
ECHO Createdby Date;>> Journal.nebula
ECHO Currencyrate Rate;>> Journal.nebula
ECHO Dateacct Date;>> Journal.nebula
ECHO Datedoc Date;>> Journal.nebula
ECHO Description;>> Journal.nebula
ECHO Docaction Attr;>> Journal.nebula
ECHO Docstatus Attr;>> Journal.nebula
ECHO Documentno SeqNo;>> Journal.nebula
ECHO Budget;>> Journal.nebula
ECHO Category;>> Journal.nebula
ECHO Journalbatch;>> Journal.nebula
ECHO !ID;>> Journal.nebula
ECHO Isactive YesNo;>> Journal.nebula
ECHO Isapproved YesNo;>> Journal.nebula
ECHO Ismanual YesNo;>> Journal.nebula
ECHO Isprinted YesNo;>> Journal.nebula
ECHO Posted YesNo;>> Journal.nebula
ECHO Postingtype YesNo;>> Journal.nebula
ECHO Processed YesNo;>> Journal.nebula
ECHO Processing YesNo;>> Journal.nebula
ECHO Totalcr Number;>> Journal.nebula
ECHO Totaldr Number;>> Journal.nebula
ECHO Updated Timestamp;>> Journal.nebula
ECHO Updatedby Date;>> Journal.nebula
ECHO };>> Journal.nebula 
 ECHO @Package("General Ledger") type  Journalbatch{ >> Journalbatch.nebula
ECHO Org;>> Journalbatch.nebula
ECHO Currency;>> Journalbatch.nebula
ECHO Doctype;>> Journalbatch.nebula
ECHO Period;>> Journalbatch.nebula
ECHO Controlamt Amount;>> Journalbatch.nebula
ECHO Copyfrom YesNo;>> Journalbatch.nebula
ECHO Created Timestamp;>> Journalbatch.nebula
ECHO Createdby Date;>> Journalbatch.nebula
ECHO Dateacct Date;>> Journalbatch.nebula
ECHO Datedoc Date;>> Journalbatch.nebula
ECHO Description;>> Journalbatch.nebula
ECHO Docaction Attr;>> Journalbatch.nebula
ECHO Docstatus Attr;>> Journalbatch.nebula
ECHO Documentno SeqNo;>> Journalbatch.nebula
ECHO Category;>> Journalbatch.nebula
ECHO !ID;>> Journalbatch.nebula
ECHO Isactive YesNo;>> Journalbatch.nebula
ECHO Isapproved YesNo;>> Journalbatch.nebula
ECHO Postingtype YesNo;>> Journalbatch.nebula
ECHO Processed YesNo;>> Journalbatch.nebula
ECHO Processing YesNo;>> Journalbatch.nebula
ECHO Totalcr Number;>> Journalbatch.nebula
ECHO Totaldr Number;>> Journalbatch.nebula
ECHO Updated Timestamp;>> Journalbatch.nebula
ECHO Updatedby Date;>> Journalbatch.nebula
ECHO };>> Journalbatch.nebula 
 ECHO @Package("General Ledger") type  Journalline{ >> Journalline.nebula
ECHO Org;>> Journalline.nebula
ECHO Amtacctcr Amount;>> Journalline.nebula
ECHO Amtacctdr Amount;>> Journalline.nebula
ECHO Amtsourcecr Amount;>> Journalline.nebula
ECHO Amtsourcedr Amount;>> Journalline.nebula
ECHO Accountalias Validcombination;>> Journalline.nebula
ECHO Conversiontype;>> Journalline.nebula
ECHO Currency;>> Journalline.nebula
ECHO Uom;>> Journalline.nebula
ECHO Validcombination;>> Journalline.nebula
ECHO Created Timestamp;>> Journalline.nebula
ECHO Createdby Date;>> Journalline.nebula
ECHO Currencyrate Rate;>> Journalline.nebula
ECHO Dateacct Date;>> Journalline.nebula
ECHO Description;>> Journalline.nebula
ECHO !ID;>> Journalline.nebula
ECHO Journal;>> Journalline.nebula
ECHO Isactive YesNo;>> Journalline.nebula
ECHO Isgenerated YesNo;>> Journalline.nebula
ECHO Line Number;>> Journalline.nebula
ECHO Processed YesNo;>> Journalline.nebula
ECHO Qty Quantity;>> Journalline.nebula
ECHO Updated Timestamp;>> Journalline.nebula
ECHO Updatedby Date;>> Journalline.nebula
ECHO };>> Journalline.nebula 
 ECHO @Package("Import") type  Bankstatement{ >> Bankstatement.nebula
ECHO Org;>> Bankstatement.nebula
ECHO Bpartnervalue String;>> Bankstatement.nebula
ECHO Bankaccountno AccountNo;>> Bankstatement.nebula
ECHO Bpartner;>> Bankstatement.nebula
ECHO Bankaccount;>> Bankstatement.nebula
ECHO Bankstatementline;>> Bankstatement.nebula
ECHO ID;>> Bankstatement.nebula
ECHO Charge;>> Bankstatement.nebula
ECHO Currency;>> Bankstatement.nebula
ECHO Invoice;>> Bankstatement.nebula
ECHO Payment;>> Bankstatement.nebula
ECHO Chargeamt Amount;>> Bankstatement.nebula
ECHO Chargename Name;>> Bankstatement.nebula
ECHO Createpayment YesNo;>> Bankstatement.nebula
ECHO Created Timestamp;>> Bankstatement.nebula
ECHO Createdby Date;>> Bankstatement.nebula
ECHO Dateacct Date;>> Bankstatement.nebula
ECHO Description;>> Bankstatement.nebula
ECHO Eftamt Amount;>> Bankstatement.nebula
ECHO Eftcheckno Code;>> Bankstatement.nebula
ECHO Eftcurrency String;>> Bankstatement.nebula
ECHO Eftmemo Note;>> Bankstatement.nebula
ECHO Eftpayee String;>> Bankstatement.nebula
ECHO Eftpayeeaccount String;>> Bankstatement.nebula
ECHO Eftreference String;>> Bankstatement.nebula
ECHO Eftstatementdate Date;>> Bankstatement.nebula
ECHO Eftstatementlinedate Date;>> Bankstatement.nebula
ECHO Eftstatementreference String;>> Bankstatement.nebula
ECHO Efttrxid String;>> Bankstatement.nebula
ECHO Efttrxtype Attr;>> Bankstatement.nebula
ECHO Eftvalutadate Date;>> Bankstatement.nebula
ECHO Iso_Code Code;>> Bankstatement.nebula
ECHO !ID Count;>> Bankstatement.nebula
ECHO I_Errormsg MSG;>> Bankstatement.nebula
ECHO I_Isimported YesNo;>> Bankstatement.nebula
ECHO Interestamt Amount;>> Bankstatement.nebula
ECHO Invoicedocumentno SeqNo;>> Bankstatement.nebula
ECHO Isactive YesNo;>> Bankstatement.nebula
ECHO Isreversal YesNo;>> Bankstatement.nebula
ECHO Line Number;>> Bankstatement.nebula
ECHO Linedescription Description;>> Bankstatement.nebula
ECHO Matchstatement YesNo;>> Bankstatement.nebula
ECHO Memo Note;>> Bankstatement.nebula
ECHO !Name;>> Bankstatement.nebula
ECHO Paymentdocumentno SeqNo;>> Bankstatement.nebula
ECHO Processed YesNo;>> Bankstatement.nebula
ECHO Processing YesNo;>> Bankstatement.nebula
ECHO Referenceno Code;>> Bankstatement.nebula
ECHO Routingno Code;>> Bankstatement.nebula
ECHO Statementdate Date;>> Bankstatement.nebula
ECHO Statementlinedate Date;>> Bankstatement.nebula
ECHO Stmtamt Amount;>> Bankstatement.nebula
ECHO Trxamt Amount;>> Bankstatement.nebula
ECHO Trxtype YesNo;>> Bankstatement.nebula
ECHO Updated Timestamp;>> Bankstatement.nebula
ECHO Updatedby Date;>> Bankstatement.nebula
ECHO Valutadate Date;>> Bankstatement.nebula
ECHO };>> Bankstatement.nebula 
 ECHO @Package("Import") type  Bpartner{ >> Bpartner.nebula
ECHO Org;>> Bpartner.nebula
ECHO User;>> Bpartner.nebula
ECHO Address1 String;>> Bpartner.nebula
ECHO Address2 String;>> Bpartner.nebula
ECHO Address3 String;>> Bpartner.nebula
ECHO Address4 String;>> Bpartner.nebula
ECHO Bpcontactgreeting Description;>> Bpartner.nebula
ECHO Birthday;>> Bpartner.nebula
ECHO Bp_Group;>> Bpartner.nebula
ECHO ID;>> Bpartner.nebula
ECHO Bpartner_Location;>> Bpartner.nebula
ECHO Country;>> Bpartner.nebula
ECHO Greeting;>> Bpartner.nebula
ECHO Region;>> Bpartner.nebula
ECHO City Name;>> Bpartner.nebula
ECHO Comments Note;>> Bpartner.nebula
ECHO Contactdescription Description;>> Bpartner.nebula
ECHO Contactname Name;>> Bpartner.nebula
ECHO Countrycode Attr;>> Bpartner.nebula
ECHO Created Timestamp;>> Bpartner.nebula
ECHO Createdby Date;>> Bpartner.nebula
ECHO DUNS;>> Bpartner.nebula
ECHO Description;>> Bpartner.nebula
ECHO Email;>> Bpartner.nebula
ECHO Fax;>> Bpartner.nebula
ECHO Groupvalue String;>> Bpartner.nebula
ECHO !ID Count;>> Bpartner.nebula
ECHO I_Errormsg MSG;>> Bpartner.nebula
ECHO I_Isimported YesNo;>> Bpartner.nebula
ECHO Interestareaname Name;>> Bpartner.nebula
ECHO Isactive YesNo;>> Bpartner.nebula
ECHO NAICS;>> Bpartner.nebula
ECHO !Name;>> Bpartner.nebula
ECHO Name2 Name;>> Bpartner.nebula
ECHO Password String;>> Bpartner.nebula
ECHO Phone PhoneNumber;>> Bpartner.nebula
ECHO Phone2 PhoneNumber;>> Bpartner.nebula
ECHO Postal PostalCode;>> Bpartner.nebula
ECHO Postal_Add PostalCode;>> Bpartner.nebula
ECHO Processed YesNo;>> Bpartner.nebula
ECHO Processing YesNo;>> Bpartner.nebula
ECHO Interestarea;>> Bpartner.nebula
ECHO Regionname Name;>> Bpartner.nebula
ECHO Taxid ID;>> Bpartner.nebula
ECHO Title String;>> Bpartner.nebula
ECHO Updated Timestamp;>> Bpartner.nebula
ECHO Updatedby Date;>> Bpartner.nebula
ECHO Value String;>> Bpartner.nebula
ECHO };>> Bpartner.nebula 
 ECHO @Package("Import") type  Contact{ >> Contact.nebula
ECHO Org;>> Contact.nebula
ECHO User;>> Contact.nebula
ECHO Bouncedinfo Description;>> Contact.nebula
ECHO Bpartner;>> Contact.nebula
ECHO Lead;>> Contact.nebula
ECHO Contactdescription Description;>> Contact.nebula
ECHO Contactname Name;>> Contact.nebula
ECHO Created Timestamp;>> Contact.nebula
ECHO Createdby Date;>> Contact.nebula
ECHO Email;>> Contact.nebula
ECHO !ID Count;>> Contact.nebula
ECHO I_Errormsg MSG;>> Contact.nebula
ECHO I_Isimported YesNo;>> Contact.nebula
ECHO Interestareaname Name;>> Contact.nebula
ECHO Isactive YesNo;>> Contact.nebula
ECHO Iscreatebp YesNo;>> Contact.nebula
ECHO Iscreatelead YesNo;>> Contact.nebula
ECHO Isemailbounced YesNo;>> Contact.nebula
ECHO Processed YesNo;>> Contact.nebula
ECHO Processing YesNo;>> Contact.nebula
ECHO Interestarea;>> Contact.nebula
ECHO Updated Timestamp;>> Contact.nebula
ECHO Updatedby Date;>> Contact.nebula
ECHO };>> Contact.nebula 
 ECHO @Package("Import") type  Conversion_Rate{ >> Conversion_Rate.nebula
ECHO Org;>> Conversion_Rate.nebula
ECHO Conversiontype;>> Conversion_Rate.nebula
ECHO ID;>> Conversion_Rate.nebula
ECHO Currency;>> Conversion_Rate.nebula
ECHO Currency_To Currency;>> Conversion_Rate.nebula
ECHO Conversiontypevalue String;>> Conversion_Rate.nebula
ECHO Createreciprocalrate YesNo;>> Conversion_Rate.nebula
ECHO Created Timestamp;>> Conversion_Rate.nebula
ECHO Createdby Date;>> Conversion_Rate.nebula
ECHO Dividerate Rate;>> Conversion_Rate.nebula
ECHO Iso_Code Code;>> Conversion_Rate.nebula
ECHO Iso_Code_to YesNo;>> Conversion_Rate.nebula
ECHO !ID Count;>> Conversion_Rate.nebula
ECHO I_Errormsg MSG;>> Conversion_Rate.nebula
ECHO I_Isimported YesNo;>> Conversion_Rate.nebula
ECHO Isactive YesNo;>> Conversion_Rate.nebula
ECHO Multiplyrate Rate;>> Conversion_Rate.nebula
ECHO Processed YesNo;>> Conversion_Rate.nebula
ECHO Processing YesNo;>> Conversion_Rate.nebula
ECHO Updated Timestamp;>> Conversion_Rate.nebula
ECHO Updatedby Date;>> Conversion_Rate.nebula
ECHO Validfrom Date;>> Conversion_Rate.nebula
ECHO Validto Date;>> Conversion_Rate.nebula
ECHO };>> Conversion_Rate.nebula 
 ECHO @Package("Import") type  Elementvalue{ >> Elementvalue.nebula
ECHO Column;>> Elementvalue.nebula
ECHO Org;>> Elementvalue.nebula
ECHO Accountsign YesNo;>> Elementvalue.nebula
ECHO Accounttype YesNo;>> Elementvalue.nebula
ECHO ID;>> Elementvalue.nebula
ECHO Element;>> Elementvalue.nebula
ECHO Created Timestamp;>> Elementvalue.nebula
ECHO Createdby Date;>> Elementvalue.nebula
ECHO Default_Account Count;>> Elementvalue.nebula
ECHO Description;>> Elementvalue.nebula
ECHO Elementname Count;>> Elementvalue.nebula
ECHO !ID Count;>> Elementvalue.nebula
ECHO I_Errormsg MSG;>> Elementvalue.nebula
ECHO I_Isimported YesNo;>> Elementvalue.nebula
ECHO Isactive YesNo;>> Elementvalue.nebula
ECHO Isdoccontrolled YesNo;>> Elementvalue.nebula
ECHO Issummary YesNo;>> Elementvalue.nebula
ECHO !Name;>> Elementvalue.nebula
ECHO Parentelementvalue Elementvalue;>> Elementvalue.nebula
ECHO Parentvalue String;>> Elementvalue.nebula
ECHO Postactual YesNo;>> Elementvalue.nebula
ECHO Postbudget YesNo;>> Elementvalue.nebula
ECHO Postencumbrance YesNo;>> Elementvalue.nebula
ECHO Poststatistical YesNo;>> Elementvalue.nebula
ECHO Processed YesNo;>> Elementvalue.nebula
ECHO Processing YesNo;>> Elementvalue.nebula
ECHO Updated Timestamp;>> Elementvalue.nebula
ECHO Updatedby Date;>> Elementvalue.nebula
ECHO Value String;>> Elementvalue.nebula
ECHO };>> Elementvalue.nebula 
 ECHO @Package("Import") type  Gljournal{ >> Gljournal.nebula
ECHO Orgdoc Org;>> Gljournal.nebula
ECHO Orgtrx Org;>> Gljournal.nebula
ECHO Org;>> Gljournal.nebula
ECHO Accountvalue String;>> Gljournal.nebula
ECHO Account Elementvalue;>> Gljournal.nebula
ECHO Acctschemaname Name;>> Gljournal.nebula
ECHO Amtacctcr Amount;>> Gljournal.nebula
ECHO Amtacctdr Amount;>> Gljournal.nebula
ECHO Amtsourcecr Amount;>> Gljournal.nebula
ECHO Amtsourcedr Amount;>> Gljournal.nebula
ECHO Bpartnervalue String;>> Gljournal.nebula
ECHO Batchdescription Description;>> Gljournal.nebula
ECHO Batchdocumentno SeqNo;>> Gljournal.nebula
ECHO Budgetname Name;>> Gljournal.nebula
ECHO Acctschema;>> Gljournal.nebula
ECHO Activity;>> Gljournal.nebula
ECHO Bpartner;>> Gljournal.nebula
ECHO Campaign;>> Gljournal.nebula
ECHO Conversiontype;>> Gljournal.nebula
ECHO Currency;>> Gljournal.nebula
ECHO Doctype;>> Gljournal.nebula
ECHO Locfrom Location;>> Gljournal.nebula
ECHO Locto Location;>> Gljournal.nebula
ECHO Period;>> Gljournal.nebula
ECHO Project;>> Gljournal.nebula
ECHO Salesregion;>> Gljournal.nebula
ECHO Uom;>> Gljournal.nebula
ECHO Validcombination;>> Gljournal.nebula
ECHO Categoryname Name;>> Gljournal.nebula
ECHO Clientvalue String;>> Gljournal.nebula
ECHO Conversiontypevalue String;>> Gljournal.nebula
ECHO Created Timestamp;>> Gljournal.nebula
ECHO Createdby Date;>> Gljournal.nebula
ECHO Currencyrate Rate;>> Gljournal.nebula
ECHO Dateacct Date;>> Gljournal.nebula
ECHO Description;>> Gljournal.nebula
ECHO Doctypename Name;>> Gljournal.nebula
ECHO Budget;>> Gljournal.nebula
ECHO Category;>> Gljournal.nebula
ECHO Journalbatch;>> Gljournal.nebula
ECHO Journalline;>> Gljournal.nebula
ECHO Journal;>> Gljournal.nebula
ECHO Iso_Code Code;>> Gljournal.nebula
ECHO I_Errormsg MSG;>> Gljournal.nebula
ECHO !ID;>> Gljournal.nebula
ECHO I_Isimported YesNo;>> Gljournal.nebula
ECHO Isactive YesNo;>> Gljournal.nebula
ECHO Iscreatenewbatch YesNo;>> Gljournal.nebula
ECHO Iscreatenewjournal YesNo;>> Gljournal.nebula
ECHO Journaldocumentno SeqNo;>> Gljournal.nebula
ECHO Line Number;>> Gljournal.nebula
ECHO Product;>> Gljournal.nebula
ECHO Orgtrxvalue String;>> Gljournal.nebula
ECHO Orgvalue String;>> Gljournal.nebula
ECHO Postingtype YesNo;>> Gljournal.nebula
ECHO Processed YesNo;>> Gljournal.nebula
ECHO Processing YesNo;>> Gljournal.nebula
ECHO Productvalue String;>> Gljournal.nebula
ECHO Projectvalue String;>> Gljournal.nebula
ECHO Qty Quantity;>> Gljournal.nebula
ECHO Sku String;>> Gljournal.nebula
ECHO UPC;>> Gljournal.nebula
ECHO Updated Timestamp;>> Gljournal.nebula
ECHO Updatedby Date;>> Gljournal.nebula
ECHO User1 Elementvalue;>> Gljournal.nebula
ECHO User2 Elementvalue;>> Gljournal.nebula
ECHO };>> Gljournal.nebula 
 ECHO @Package("Import") type  Inoutlineconfirm{ >> Inoutlineconfirm.nebula
ECHO Org;>> Inoutlineconfirm.nebula
ECHO Confirmationno Code;>> Inoutlineconfirm.nebula
ECHO Confirmedqty Quantity;>> Inoutlineconfirm.nebula
ECHO Created Timestamp;>> Inoutlineconfirm.nebula
ECHO Createdby Date;>> Inoutlineconfirm.nebula
ECHO Description;>> Inoutlineconfirm.nebula
ECHO Differenceqty Quantity;>> Inoutlineconfirm.nebula
ECHO I_Errormsg MSG;>> Inoutlineconfirm.nebula
ECHO !ID;>> Inoutlineconfirm.nebula
ECHO I_Isimported YesNo;>> Inoutlineconfirm.nebula
ECHO Isactive YesNo;>> Inoutlineconfirm.nebula
ECHO ID;>> Inoutlineconfirm.nebula
ECHO Processed YesNo;>> Inoutlineconfirm.nebula
ECHO Processing YesNo;>> Inoutlineconfirm.nebula
ECHO Scrappedqty Quantity;>> Inoutlineconfirm.nebula
ECHO Updated Timestamp;>> Inoutlineconfirm.nebula
ECHO Updatedby Date;>> Inoutlineconfirm.nebula
ECHO };>> Inoutlineconfirm.nebula 
 ECHO @Package("Import") type  Inventory{ >> Inventory.nebula
ECHO Org;>> Inventory.nebula
ECHO Bin String;>> Inventory.nebula
ECHO Created Timestamp;>> Inventory.nebula
ECHO Createdby Date;>> Inventory.nebula
ECHO Description;>> Inventory.nebula
ECHO I_Errormsg MSG;>> Inventory.nebula
ECHO !ID;>> Inventory.nebula
ECHO I_Isimported YesNo;>> Inventory.nebula
ECHO Isactive YesNo;>> Inventory.nebula
ECHO Locatorvalue String;>> Inventory.nebula
ECHO LOT String;>> Inventory.nebula
ECHO Inventoryline;>> Inventory.nebula
ECHO ID;>> Inventory.nebula
ECHO Locator;>> Inventory.nebula
ECHO Product;>> Inventory.nebula
ECHO Warehouse;>> Inventory.nebula
ECHO Movementdate Date;>> Inventory.nebula
ECHO Position String;>> Inventory.nebula
ECHO Processed YesNo;>> Inventory.nebula
ECHO Processing YesNo;>> Inventory.nebula
ECHO Qtybook Quantity;>> Inventory.nebula
ECHO Qtycount Quantity;>> Inventory.nebula
ECHO SerNo Code;>> Inventory.nebula
ECHO UPC;>> Inventory.nebula
ECHO Updated Timestamp;>> Inventory.nebula
ECHO Updatedby Date;>> Inventory.nebula
ECHO Value String;>> Inventory.nebula
ECHO Warehousevalue String;>> Inventory.nebula
ECHO X String;>> Inventory.nebula
ECHO Y String;>> Inventory.nebula
ECHO Z String;>> Inventory.nebula
ECHO };>> Inventory.nebula 
 ECHO @Package("Import") type  Invoice{ >> Invoice.nebula
ECHO Orgtrx Org;>> Invoice.nebula
ECHO Org;>> Invoice.nebula
ECHO User;>> Invoice.nebula
ECHO Address1 String;>> Invoice.nebula
ECHO Address2 String;>> Invoice.nebula
ECHO Bpartnervalue String;>> Invoice.nebula
ECHO Activity;>> Invoice.nebula
ECHO Bpartner;>> Invoice.nebula
ECHO Bpartner_Location;>> Invoice.nebula
ECHO Campaign;>> Invoice.nebula
ECHO Charge;>> Invoice.nebula
ECHO Country;>> Invoice.nebula
ECHO Currency;>> Invoice.nebula
ECHO Doctype;>> Invoice.nebula
ECHO Invoiceline;>> Invoice.nebula
ECHO ID;>> Invoice.nebula
ECHO Location;>> Invoice.nebula
ECHO Paymentterm;>> Invoice.nebula
ECHO Project;>> Invoice.nebula
ECHO Region;>> Invoice.nebula
ECHO Tax;>> Invoice.nebula
ECHO Chargename Name;>> Invoice.nebula
ECHO City Name;>> Invoice.nebula
ECHO Contactname Name;>> Invoice.nebula
ECHO Countrycode Attr;>> Invoice.nebula
ECHO Created Timestamp;>> Invoice.nebula
ECHO Createdby Date;>> Invoice.nebula
ECHO Dateacct Date;>> Invoice.nebula
ECHO Dateinvoiced Date;>> Invoice.nebula
ECHO Description;>> Invoice.nebula
ECHO Doctypename Name;>> Invoice.nebula
ECHO Documentno SeqNo;>> Invoice.nebula
ECHO Email;>> Invoice.nebula
ECHO I_Errormsg MSG;>> Invoice.nebula
ECHO !ID;>> Invoice.nebula
ECHO I_Isimported YesNo;>> Invoice.nebula
ECHO Isactive YesNo;>> Invoice.nebula
ECHO Issotrx YesNo;>> Invoice.nebula
ECHO Linedescription Description;>> Invoice.nebula
ECHO Pricelist;>> Invoice.nebula
ECHO Product;>> Invoice.nebula
ECHO !Name;>> Invoice.nebula
ECHO Paymentrule YesNo;>> Invoice.nebula
ECHO Paymentrulename Name;>> Invoice.nebula
ECHO Paymenttermvalue String;>> Invoice.nebula
ECHO Phone PhoneNumber;>> Invoice.nebula
ECHO Postal PostalCode;>> Invoice.nebula
ECHO Priceactual Price;>> Invoice.nebula
ECHO Processed YesNo;>> Invoice.nebula
ECHO Processing YesNo;>> Invoice.nebula
ECHO Productvalue String;>> Invoice.nebula
ECHO Qtyordered Quantity;>> Invoice.nebula
ECHO Regionname Name;>> Invoice.nebula
ECHO Sku String;>> Invoice.nebula
ECHO Salesrep User;>> Invoice.nebula
ECHO Taxamt Amount;>> Invoice.nebula
ECHO Taxindicator Number;>> Invoice.nebula
ECHO UPC;>> Invoice.nebula
ECHO Updated Timestamp;>> Invoice.nebula
ECHO Updatedby Date;>> Invoice.nebula
ECHO };>> Invoice.nebula 
 ECHO @Package("Import") type  Locator{ >> Locator.nebula
ECHO Org;>> Locator.nebula
ECHO Bin String;>> Locator.nebula
ECHO Clientname Name;>> Locator.nebula
ECHO Clientvalue String;>> Locator.nebula
ECHO Created Timestamp;>> Locator.nebula
ECHO Createdby Date;>> Locator.nebula
ECHO I_Errormsg MSG;>> Locator.nebula
ECHO I_Isimported YesNo;>> Locator.nebula
ECHO !ID;>> Locator.nebula
ECHO Isactive YesNo;>> Locator.nebula
ECHO Isavailableforallocation YesNo;>> Locator.nebula
ECHO Isavailabletopromise YesNo;>> Locator.nebula
ECHO Isdefault YesNo;>> Locator.nebula
ECHO ID;>> Locator.nebula
ECHO Product;>> Locator.nebula
ECHO Warehouse;>> Locator.nebula
ECHO Maxquantity Quantity;>> Locator.nebula
ECHO Minquantity Quantity;>> Locator.nebula
ECHO Orgname Name;>> Locator.nebula
ECHO Orgvalue String;>> Locator.nebula
ECHO Pickingseqno SeqNo;>> Locator.nebula
ECHO Pickinguomname Name;>> Locator.nebula
ECHO Pickinguomsymbol Symbol;>> Locator.nebula
ECHO Picking_Uom Uom;>> Locator.nebula
ECHO Position String;>> Locator.nebula
ECHO Priorityno SeqNo;>> Locator.nebula
ECHO Processed YesNo;>> Locator.nebula
ECHO Processing YesNo;>> Locator.nebula
ECHO Productmaxquantity Quantity;>> Locator.nebula
ECHO Productminquantity Quantity;>> Locator.nebula
ECHO Productname Name;>> Locator.nebula
ECHO Productvalue String;>> Locator.nebula
ECHO Putawayseqno SeqNo;>> Locator.nebula
ECHO Stockinguomname Name;>> Locator.nebula
ECHO Stockinguomsymbol Symbol;>> Locator.nebula
ECHO Stocking_Uom Uom;>> Locator.nebula
ECHO Updated Timestamp;>> Locator.nebula
ECHO Updatedby Date;>> Locator.nebula
ECHO Value String;>> Locator.nebula
ECHO Warehousename Name;>> Locator.nebula
ECHO Warehousevalue String;>> Locator.nebula
ECHO X String;>> Locator.nebula
ECHO Y String;>> Locator.nebula
ECHO Z String;>> Locator.nebula
ECHO };>> Locator.nebula 
 ECHO @Package("Import") type  Order{ >> Order.nebula
ECHO Orgtrx Org;>> Order.nebula
ECHO Org;>> Order.nebula
ECHO User;>> Order.nebula
ECHO Address1 String;>> Order.nebula
ECHO Address2 String;>> Order.nebula
ECHO Bpartnervalue String;>> Order.nebula
ECHO Billto Bpartner_Location;>> Order.nebula
ECHO Activity;>> Order.nebula
ECHO Bpartner;>> Order.nebula
ECHO Bpartner_Location;>> Order.nebula
ECHO Campaign;>> Order.nebula
ECHO Charge;>> Order.nebula
ECHO Country;>> Order.nebula
ECHO Currency;>> Order.nebula
ECHO Doctype;>> Order.nebula
ECHO Location;>> Order.nebula
ECHO Orderline;>> Order.nebula
ECHO ID;>> Order.nebula
ECHO Paymentterm;>> Order.nebula
ECHO Project;>> Order.nebula
ECHO Region;>> Order.nebula
ECHO Tax;>> Order.nebula
ECHO Uom;>> Order.nebula
ECHO Chargename Name;>> Order.nebula
ECHO City Name;>> Order.nebula
ECHO Contactname Name;>> Order.nebula
ECHO Countrycode Attr;>> Order.nebula
ECHO Created Timestamp;>> Order.nebula
ECHO Createdby Date;>> Order.nebula
ECHO Dateacct Date;>> Order.nebula
ECHO Dateordered Date;>> Order.nebula
ECHO Description;>> Order.nebula
ECHO Doctypename Name;>> Order.nebula
ECHO Documentno SeqNo;>> Order.nebula
ECHO Email;>> Order.nebula
ECHO Freightamt Amount;>> Order.nebula
ECHO I_Errormsg MSG;>> Order.nebula
ECHO I_Isimported YesNo;>> Order.nebula
ECHO !ID;>> Order.nebula
ECHO Isactive YesNo;>> Order.nebula
ECHO Issotrx YesNo;>> Order.nebula
ECHO Linedescription Description;>> Order.nebula
ECHO Pricelist;>> Order.nebula
ECHO Product;>> Order.nebula
ECHO Shipper;>> Order.nebula
ECHO Warehouse;>> Order.nebula
ECHO !Name;>> Order.nebula
ECHO Paymentrule YesNo;>> Order.nebula
ECHO Paymentrulename Name;>> Order.nebula
ECHO Paymenttermvalue String;>> Order.nebula
ECHO Phone PhoneNumber;>> Order.nebula
ECHO Postal PostalCode;>> Order.nebula
ECHO Priceactual Price;>> Order.nebula
ECHO Processed YesNo;>> Order.nebula
ECHO Processing YesNo;>> Order.nebula
ECHO Productvalue String;>> Order.nebula
ECHO Qtyordered Quantity;>> Order.nebula
ECHO Regionname Name;>> Order.nebula
ECHO Sku String;>> Order.nebula
ECHO Salesrep User;>> Order.nebula
ECHO Taxamt Amount;>> Order.nebula
ECHO Taxindicator Number;>> Order.nebula
ECHO UPC;>> Order.nebula
ECHO Updated Timestamp;>> Order.nebula
ECHO Updatedby Date;>> Order.nebula
ECHO Warehousevalue String;>> Order.nebula
ECHO };>> Order.nebula 
 ECHO @Package("Import") type  Payment{ >> Payment.nebula
ECHO Org;>> Payment.nebula
ECHO A_City Name;>> Payment.nebula
ECHO A_Country Name;>> Payment.nebula
ECHO A_Email Email;>> Payment.nebula
ECHO Ident_Dl String;>> Payment.nebula
ECHO Ident_Ssn SSN;>> Payment.nebula
ECHO !A_Name Name;>> Payment.nebula
ECHO A_State Name;>> Payment.nebula
ECHO A_Street Name;>> Payment.nebula
ECHO A_Zip Zip;>> Payment.nebula
ECHO AccountNo;>> Payment.nebula
ECHO Bpartnervalue String;>> Payment.nebula
ECHO Bankaccountno AccountNo;>> Payment.nebula
ECHO Bpartner;>> Payment.nebula
ECHO Bankaccount;>> Payment.nebula
ECHO Charge;>> Payment.nebula
ECHO Currency;>> Payment.nebula
ECHO Doctype;>> Payment.nebula
ECHO Invoice;>> Payment.nebula
ECHO ID;>> Payment.nebula
ECHO Chargeamt Amount;>> Payment.nebula
ECHO Chargename Name;>> Payment.nebula
ECHO Checkno Code;>> Payment.nebula
ECHO Created Timestamp;>> Payment.nebula
ECHO Createdby Date;>> Payment.nebula
ECHO Creditcardexpmm Number;>> Payment.nebula
ECHO Creditcardexpyy Number;>> Payment.nebula
ECHO Creditcardnumber Code;>> Payment.nebula
ECHO Creditcardtype YesNo;>> Payment.nebula
ECHO Creditcardvv String;>> Payment.nebula
ECHO Dateacct Date;>> Payment.nebula
ECHO Datetrx Date;>> Payment.nebula
ECHO Discountamt Amount;>> Payment.nebula
ECHO Doctypename Name;>> Payment.nebula
ECHO Documentno SeqNo;>> Payment.nebula
ECHO Iso_Code Code;>> Payment.nebula
ECHO I_Errormsg MSG;>> Payment.nebula
ECHO I_Isimported YesNo;>> Payment.nebula
ECHO !ID;>> Payment.nebula
ECHO Invoicedocumentno SeqNo;>> Payment.nebula
ECHO Isactive YesNo;>> Payment.nebula
ECHO Isapproved YesNo;>> Payment.nebula
ECHO Isdelayedcapture YesNo;>> Payment.nebula
ECHO Isoverunderpayment YesNo;>> Payment.nebula
ECHO Isreceipt YesNo;>> Payment.nebula
ECHO Isselfservice YesNo;>> Payment.nebula
ECHO Micr String;>> Payment.nebula
ECHO Orig_Trxid String;>> Payment.nebula
ECHO Overunderamt Amount;>> Payment.nebula
ECHO Ponum String;>> Payment.nebula
ECHO Payamt Amount;>> Payment.nebula
ECHO Processed YesNo;>> Payment.nebula
ECHO Processing YesNo;>> Payment.nebula
ECHO R_Authcode Attr;>> Payment.nebula
ECHO R_Info Description;>> Payment.nebula
ECHO R_Pnref String;>> Payment.nebula
ECHO R_Respmsg MSG;>> Payment.nebula
ECHO R_Result String;>> Payment.nebula
ECHO Routingno Code;>> Payment.nebula
ECHO Swipe String;>> Payment.nebula
ECHO Taxamt Amount;>> Payment.nebula
ECHO Tendertype YesNo;>> Payment.nebula
ECHO Trxtype YesNo;>> Payment.nebula
ECHO Updated Timestamp;>> Payment.nebula
ECHO Updatedby Date;>> Payment.nebula
ECHO Voiceauthcode Attr;>> Payment.nebula
ECHO Writeoffamt Amount;>> Payment.nebula
ECHO };>> Payment.nebula 
 ECHO @Package("Import") type  Product{ >> Product.nebula
ECHO Org;>> Product.nebula
ECHO Bpartner_Value String;>> Product.nebula
ECHO Bpartner;>> Product.nebula
ECHO Currency;>> Product.nebula
ECHO Uom;>> Product.nebula
ECHO Classification Attr;>> Product.nebula
ECHO Costperorder Number;>> Product.nebula
ECHO Created Timestamp;>> Product.nebula
ECHO Createdby Date;>> Product.nebula
ECHO Deliverytime_Promised Long;>> Product.nebula
ECHO Description;>> Product.nebula
ECHO Descriptionurl URL;>> Product.nebula
ECHO Discontinued YesNo;>> Product.nebula
ECHO Discontinuedby Date;>> Product.nebula
ECHO Documentnote Note;>> Product.nebula
ECHO Help;>> Product.nebula
ECHO Iso_Code Code;>> Product.nebula
ECHO I_Errormsg MSG;>> Product.nebula
ECHO I_Isimported YesNo;>> Product.nebula
ECHO !ID;>> Product.nebula
ECHO Imageurl URL;>> Product.nebula
ECHO Isactive YesNo;>> Product.nebula
ECHO Product_Category;>> Product.nebula
ECHO ID;>> Product.nebula
ECHO Manufacturer String;>> Product.nebula
ECHO !Name;>> Product.nebula
ECHO Order_Min Number;>> Product.nebula
ECHO Order_Pack Number;>> Product.nebula
ECHO Priceeffective Date;>> Product.nebula
ECHO Pricelimit Price;>> Product.nebula
ECHO Pricelist Price;>> Product.nebula
ECHO Pricepo Price;>> Product.nebula
ECHO Pricestd Price;>> Product.nebula
ECHO Processed YesNo;>> Product.nebula
ECHO Processing YesNo;>> Product.nebula
ECHO Productcategory_Value String;>> Product.nebula
ECHO Producttype YesNo;>> Product.nebula
ECHO Royaltyamt Amount;>> Product.nebula
ECHO Sku String;>> Product.nebula
ECHO Shelfdepth Depth;>> Product.nebula
ECHO Shelfheight Height;>> Product.nebula
ECHO Shelfwidth Width;>> Product.nebula
ECHO UPC;>> Product.nebula
ECHO Unitsperpallet Number;>> Product.nebula
ECHO Updated Timestamp;>> Product.nebula
ECHO Updatedby Date;>> Product.nebula
ECHO Value String;>> Product.nebula
ECHO Vendorcategory String;>> Product.nebula
ECHO Vendorproductno Code;>> Product.nebula
ECHO Volume Long;>> Product.nebula
ECHO Weight;>> Product.nebula
ECHO X12de355 UOMEDICode;>> Product.nebula
ECHO };>> Product.nebula 
 ECHO @Package("Import") type  Reportline{ >> Reportline.nebula
ECHO Org;>> Reportline.nebula
ECHO Amounttype Attr;>> Reportline.nebula
ECHO Elementvalue;>> Reportline.nebula
ECHO Calculationtype YesNo;>> Reportline.nebula
ECHO Created Timestamp;>> Reportline.nebula
ECHO Createdby Date;>> Reportline.nebula
ECHO Description;>> Reportline.nebula
ECHO Elementvalue String;>> Reportline.nebula
ECHO I_Errormsg MSG;>> Reportline.nebula
ECHO I_Isimported YesNo;>> Reportline.nebula
ECHO !ID;>> Reportline.nebula
ECHO Isactive YesNo;>> Reportline.nebula
ECHO Isprinted YesNo;>> Reportline.nebula
ECHO Issummary YesNo;>> Reportline.nebula
ECHO Linetype YesNo;>> Reportline.nebula
ECHO !Name;>> Reportline.nebula
ECHO Reportlineset;>> Reportline.nebula
ECHO ID;>> Reportline.nebula
ECHO Reportsource;>> Reportline.nebula
ECHO Postingtype YesNo;>> Reportline.nebula
ECHO Processed YesNo;>> Reportline.nebula
ECHO Processing YesNo;>> Reportline.nebula
ECHO Reportlinesetname Name;>> Reportline.nebula
ECHO SeqNo;>> Reportline.nebula
ECHO Updated Timestamp;>> Reportline.nebula
ECHO Updatedby Date;>> Reportline.nebula
ECHO };>> Reportline.nebula 
 ECHO @Package("Import") type  Request{ >> Request.nebula
ECHO Org;>> Request.nebula
ECHO Role;>> Request.nebula
ECHO Table;>> Request.nebula
ECHO User;>> Request.nebula
ECHO Asset;>> Request.nebula
ECHO Activityname Name;>> Request.nebula
ECHO Activityvalue String;>> Request.nebula
ECHO Assetname Name;>> Request.nebula
ECHO Assetvalue String;>> Request.nebula
ECHO Bpartnername Name;>> Request.nebula
ECHO Bpartnersrname Name;>> Request.nebula
ECHO Bpartnersrvalue String;>> Request.nebula
ECHO Bpartnervalue String;>> Request.nebula
ECHO Activity;>> Request.nebula
ECHO Bpartnersr Bpartner;>> Request.nebula
ECHO Bpartner;>> Request.nebula
ECHO Campaign;>> Request.nebula
ECHO Invoice;>> Request.nebula
ECHO Lead;>> Request.nebula
ECHO Order;>> Request.nebula
ECHO Payment;>> Request.nebula
ECHO Project;>> Request.nebula
ECHO Salesregion;>> Request.nebula
ECHO Campaignname Name;>> Request.nebula
ECHO Campaignvalue String;>> Request.nebula
ECHO Categoryname Name;>> Request.nebula
ECHO Changerequestname Name;>> Request.nebula
ECHO Clientname Name;>> Request.nebula
ECHO Clientvalue String;>> Request.nebula
ECHO Closedate Date;>> Request.nebula
ECHO Confidentialtype YesNo;>> Request.nebula
ECHO Confidentialtypeentry YesNo;>> Request.nebula
ECHO Contactname Name;>> Request.nebula
ECHO Contactvalue String;>> Request.nebula
ECHO Created Timestamp;>> Request.nebula
ECHO Createdby Date;>> Request.nebula
ECHO Datecompleteplan Date;>> Request.nebula
ECHO Datelastaction Date;>> Request.nebula
ECHO Datelastalert Date;>> Request.nebula
ECHO Datenextaction Date;>> Request.nebula
ECHO Datestartplan Date;>> Request.nebula
ECHO Documentno SeqNo;>> Request.nebula
ECHO Duetype YesNo;>> Request.nebula
ECHO Endtime Timestamp;>> Request.nebula
ECHO Groupname Name;>> Request.nebula
ECHO I_Errormsg MSG;>> Request.nebula
ECHO I_Isimported YesNo;>> Request.nebula
ECHO !ID;>> Request.nebula
ECHO Inoutdocumentno SeqNo;>> Request.nebula
ECHO Invoicedocumentno SeqNo;>> Request.nebula
ECHO Isactive YesNo;>> Request.nebula
ECHO Isescalated YesNo;>> Request.nebula
ECHO Isinvoiced YesNo;>> Request.nebula
ECHO Isselfservice YesNo;>> Request.nebula
ECHO Lastresult String;>> Request.nebula
ECHO Leaddocumentno SeqNo;>> Request.nebula
ECHO Changerequest;>> Request.nebula
ECHO Inout;>> Request.nebula
ECHO Productspent Product;>> Request.nebula
ECHO Product;>> Request.nebula
ECHO Nextaction YesNo;>> Request.nebula
ECHO Orderdocumentno SeqNo;>> Request.nebula
ECHO Orgname Name;>> Request.nebula
ECHO Orgvalue String;>> Request.nebula
ECHO Paymentdocumentno SeqNo;>> Request.nebula
ECHO Priority;>> Request.nebula
ECHO Priorityuser YesNo;>> Request.nebula
ECHO Processed YesNo;>> Request.nebula
ECHO Processing YesNo;>> Request.nebula
ECHO Productname Name;>> Request.nebula
ECHO Productspentname Name;>> Request.nebula
ECHO Productspentvalue String;>> Request.nebula
ECHO Productvalue String;>> Request.nebula
ECHO Projectname Name;>> Request.nebula
ECHO Projectvalue String;>> Request.nebula
ECHO Qtyinvoiced Quantity;>> Request.nebula
ECHO Qtyplan Quantity;>> Request.nebula
ECHO Qtyspent Quantity;>> Request.nebula
ECHO Category;>> Request.nebula
ECHO Group;>> Request.nebula
ECHO Requestrelated Request;>> Request.nebula
ECHO Requesttype;>> Request.nebula
ECHO ID;>> Request.nebula
ECHO Resolution;>> Request.nebula
ECHO Source;>> Request.nebula
ECHO Status;>> Request.nebula
ECHO Reqtypename Name;>> Request.nebula
ECHO Requestamt Amount;>> Request.nebula
ECHO Requestrelateddocno Code;>> Request.nebula
ECHO Resolutionname Name;>> Request.nebula
ECHO Result Number;>> Request.nebula
ECHO Rolename Name;>> Request.nebula
ECHO Salesregionname Name;>> Request.nebula
ECHO Salesregionvalue String;>> Request.nebula
ECHO Salesrepname Name;>> Request.nebula
ECHO Salesrepvalue String;>> Request.nebula
ECHO Salesrep User;>> Request.nebula
ECHO Sourcename Name;>> Request.nebula
ECHO Sourcevalue String;>> Request.nebula
ECHO Startdate Date;>> Request.nebula
ECHO Starttime Timestamp;>> Request.nebula
ECHO Statusname Name;>> Request.nebula
ECHO Summary Note;>> Request.nebula
ECHO Tablename Name;>> Request.nebula
ECHO Taskstatus YesNo;>> Request.nebula
ECHO Updated Timestamp;>> Request.nebula
ECHO Updatedby Date;>> Request.nebula
ECHO };>> Request.nebula 
 





















ECHO @Package("Knowledge Management") type  Category{ >> Category.nebula
ECHO Org;>> Category.nebula
ECHO Created Timestamp;>> Category.nebula
ECHO Createdby Date;>> Category.nebula
ECHO Description;>> Category.nebula
ECHO Help;>> Category.nebula
ECHO Isactive YesNo;>> Category.nebula
ECHO !ID;>> Category.nebula
ECHO !Name;>> Category.nebula
ECHO Updated Timestamp;>> Category.nebula
ECHO Updatedby Date;>> Category.nebula
ECHO };>> Category.nebula 
 ECHO @Package("Knowledge Management") type  Categoryvalue{ >> Categoryvalue.nebula
ECHO Org;>> Categoryvalue.nebula
ECHO Created Timestamp;>> Categoryvalue.nebula
ECHO Createdby Date;>> Categoryvalue.nebula
ECHO Description;>> Categoryvalue.nebula
ECHO Isactive YesNo;>> Categoryvalue.nebula
ECHO !ID;>> Categoryvalue.nebula
ECHO Category;>> Categoryvalue.nebula
ECHO !Name;>> Categoryvalue.nebula
ECHO Updated Timestamp;>> Categoryvalue.nebula
ECHO Updatedby Date;>> Categoryvalue.nebula
ECHO };>> Categoryvalue.nebula 
 ECHO @Package("Knowledge Management") type  Comment{ >> Comment.nebula
ECHO Org;>> Comment.nebula
ECHO Session;>> Comment.nebula
ECHO Created Timestamp;>> Comment.nebula
ECHO Createdby Date;>> Comment.nebula
ECHO Isactive YesNo;>> Comment.nebula
ECHO Ispublic YesNo;>> Comment.nebula
ECHO !ID;>> Comment.nebula
ECHO Entry;>> Comment.nebula
ECHO Rating YesNo;>> Comment.nebula
ECHO Textmsg MSG;>> Comment.nebula
ECHO Updated Timestamp;>> Comment.nebula
ECHO Updatedby Date;>> Comment.nebula
ECHO };>> Comment.nebula 
 ECHO @Package("Knowledge Management") type  Entry{ >> Entry.nebula
ECHO Org;>> Entry.nebula
ECHO Session;>> Entry.nebula
ECHO Created Timestamp;>> Entry.nebula
ECHO Createdby Date;>> Entry.nebula
ECHO Descriptionurl URL;>> Entry.nebula
ECHO Isactive YesNo;>> Entry.nebula
ECHO Ispublic YesNo;>> Entry.nebula
ECHO !ID;>> Entry.nebula
ECHO Source;>> Entry.nebula
ECHO Topic;>> Entry.nebula
ECHO Keywords String;>> Entry.nebula
ECHO !Name;>> Entry.nebula
ECHO Rating YesNo;>> Entry.nebula
ECHO Textmsg MSG;>> Entry.nebula
ECHO Updated Timestamp;>> Entry.nebula
ECHO Updatedby Date;>> Entry.nebula
ECHO Validto Date;>> Entry.nebula
ECHO };>> Entry.nebula 
 ECHO @Package("Knowledge Management") type  Entrycategory{ >> Entrycategory.nebula
ECHO Org;>> Entrycategory.nebula
ECHO Created Timestamp;>> Entrycategory.nebula
ECHO Createdby Date;>> Entrycategory.nebula
ECHO Isactive YesNo;>> Entrycategory.nebula
ECHO Categoryvalue;>> Entrycategory.nebula
ECHO !Category;>> Entrycategory.nebula
ECHO !Entry;>> Entrycategory.nebula
ECHO Updated Timestamp;>> Entrycategory.nebula
ECHO Updatedby Date;>> Entrycategory.nebula
ECHO };>> Entrycategory.nebula 
 ECHO @Package("Knowledge Management") type  Entryrelated{ >> Entryrelated.nebula
ECHO Org;>> Entryrelated.nebula
ECHO Created Timestamp;>> Entryrelated.nebula
ECHO Createdby Date;>> Entryrelated.nebula
ECHO Isactive YesNo;>> Entryrelated.nebula
ECHO !ID;>> Entryrelated.nebula
ECHO Entry;>> Entryrelated.nebula
ECHO !Name;>> Entryrelated.nebula
ECHO Updated Timestamp;>> Entryrelated.nebula
ECHO Updatedby Date;>> Entryrelated.nebula
ECHO };>> Entryrelated.nebula 
 ECHO @Package("Knowledge Management") type  Index{ >> Index.nebula
ECHO Org;>> Index.nebula
ECHO Table;>> Index.nebula
ECHO Webproject;>> Index.nebula
ECHO Doctype;>> Index.nebula
ECHO Created Timestamp;>> Index.nebula
ECHO Createdby Date;>> Index.nebula
ECHO Excerpt Note;>> Index.nebula
ECHO Isactive YesNo;>> Index.nebula
ECHO !ID;>> Index.nebula
ECHO Keyword String;>> Index.nebula
ECHO Requesttype;>> Index.nebula

ECHO Sourceupdated Date;>> Index.nebula
ECHO Updated Timestamp;>> Index.nebula
ECHO Updatedby Date;>> Index.nebula
ECHO };>> Index.nebula 
 ECHO @Package("Knowledge Management") type  Indexlog{ >> Indexlog.nebula
ECHO Org;>> Indexlog.nebula
ECHO Created Timestamp;>> Indexlog.nebula
ECHO Createdby Date;>> Indexlog.nebula
ECHO Indexquery String;>> Indexlog.nebula
ECHO Indexqueryresult Number;>> Indexlog.nebula
ECHO Isactive YesNo;>> Indexlog.nebula
ECHO !ID;>> Indexlog.nebula
ECHO Querysource YesNo;>> Indexlog.nebula
ECHO Updated Timestamp;>> Indexlog.nebula
ECHO Updatedby Date;>> Indexlog.nebula
ECHO };>> Indexlog.nebula 
 ECHO @Package("Knowledge Management") type  Indexstop{ >> Indexstop.nebula
ECHO Org;>> Indexstop.nebula
ECHO Webproject;>> Indexstop.nebula
ECHO Doctype;>> Indexstop.nebula
ECHO Created Timestamp;>> Indexstop.nebula
ECHO Createdby Date;>> Indexstop.nebula
ECHO Isactive YesNo;>> Indexstop.nebula
ECHO Ismanual YesNo;>> Indexstop.nebula
ECHO !ID;>> Indexstop.nebula
ECHO Keyword String;>> Indexstop.nebula
ECHO Requesttype;>> Indexstop.nebula
ECHO Updated Timestamp;>> Indexstop.nebula
ECHO Updatedby Date;>> Indexstop.nebula
ECHO };>> Indexstop.nebula 
 ECHO @Package("Knowledge Management") type  Source{ >> Source.nebula
ECHO Org;>> Source.nebula
ECHO Created Timestamp;>> Source.nebula
ECHO Createdby Date;>> Source.nebula
ECHO Descriptionurl URL;>> Source.nebula
ECHO Isactive YesNo;>> Source.nebula
ECHO !ID;>> Source.nebula
ECHO !Name;>> Source.nebula
ECHO Updated Timestamp;>> Source.nebula
ECHO Updatedby Date;>> Source.nebula
ECHO };>> Source.nebula 
 ECHO @Package("Knowledge Management") type  Synonym{ >> Synonym.nebula
ECHO Ad_Language Language;>> Synonym.nebula
ECHO Org;>> Synonym.nebula
ECHO Created Timestamp;>> Synonym.nebula
ECHO Createdby Date;>> Synonym.nebula
ECHO Isactive YesNo;>> Synonym.nebula
ECHO !ID;>> Synonym.nebula
ECHO !Name;>> Synonym.nebula
ECHO Synonymname Name;>> Synonym.nebula
ECHO Updated Timestamp;>> Synonym.nebula
ECHO Updatedby Date;>> Synonym.nebula
ECHO };>> Synonym.nebula 
 ECHO @Package("Knowledge Management") type  Topic{ >> Topic.nebula
ECHO Org;>> Topic.nebula
ECHO Created Timestamp;>> Topic.nebula
ECHO Createdby Date;>> Topic.nebula
ECHO Description;>> Topic.nebula
ECHO Help;>> Topic.nebula
ECHO Isactive YesNo;>> Topic.nebula
ECHO Ispublic YesNo;>> Topic.nebula
ECHO Ispublicwrite YesNo;>> Topic.nebula
ECHO !ID;>> Topic.nebula
ECHO Type;>> Topic.nebula
ECHO !Name;>> Topic.nebula
ECHO Updated Timestamp;>> Topic.nebula
ECHO Updatedby Date;>> Topic.nebula
ECHO };>> Topic.nebula 
 ECHO @Package("Knowledge Management") type  Type{ >> Type.nebula
ECHO Org;>> Type.nebula
ECHO Created Timestamp;>> Type.nebula
ECHO Createdby Date;>> Type.nebula
ECHO Description;>> Type.nebula
ECHO Help;>> Type.nebula
ECHO Isactive YesNo;>> Type.nebula
ECHO Ispublic YesNo;>> Type.nebula
ECHO Ispublicwrite YesNo;>> Type.nebula
ECHO !ID;>> Type.nebula
ECHO !Name;>> Type.nebula
ECHO Updated Timestamp;>> Type.nebula
ECHO Updatedby Date;>> Type.nebula
ECHO };>> Type.nebula 
 ECHO @Package("Material Management") type  Attribute{ >> Attribute.nebula
ECHO Org;>> Attribute.nebula
ECHO Attributevaluetype YesNo;>> Attribute.nebula
ECHO Created Timestamp;>> Attribute.nebula
ECHO Createdby Date;>> Attribute.nebula
ECHO Description;>> Attribute.nebula
ECHO Isactive YesNo;>> Attribute.nebula
ECHO Isinstanceattribute YesNo;>> Attribute.nebula
ECHO Ismandatory YesNo;>> Attribute.nebula
ECHO Attributesearch;>> Attribute.nebula
ECHO !ID;>> Attribute.nebula
ECHO !Name;>> Attribute.nebula
ECHO Updated Timestamp;>> Attribute.nebula
ECHO Updatedby Date;>> Attribute.nebula
ECHO };>> Attribute.nebula 
 ECHO @Package("Material Management") type  Attributeinstance{ >> Attributeinstance.nebula
ECHO Org;>> Attributeinstance.nebula
ECHO Created Timestamp;>> Attributeinstance.nebula
ECHO Createdby Date;>> Attributeinstance.nebula
ECHO Isactive YesNo;>> Attributeinstance.nebula
ECHO !Attributesetinstance;>> Attributeinstance.nebula
ECHO Attributevalue;>> Attributeinstance.nebula
ECHO !Attribute;>> Attributeinstance.nebula
ECHO Updated Timestamp;>> Attributeinstance.nebula
ECHO Updatedby Date;>> Attributeinstance.nebula
ECHO Value String;>> Attributeinstance.nebula
ECHO Valuenumber Number;>> Attributeinstance.nebula
ECHO };>> Attributeinstance.nebula 
 ECHO @Package("Material Management") type  Attributesearch{ >> Attributesearch.nebula
ECHO Org;>> Attributesearch.nebula
ECHO Created Timestamp;>> Attributesearch.nebula
ECHO Createdby Date;>> Attributesearch.nebula
ECHO Description;>> Attributesearch.nebula
ECHO Isactive YesNo;>> Attributesearch.nebula
ECHO !ID;>> Attributesearch.nebula
ECHO !Name;>> Attributesearch.nebula
ECHO Updated Timestamp;>> Attributesearch.nebula
ECHO Updatedby Date;>> Attributesearch.nebula
ECHO };>> Attributesearch.nebula 
 ECHO @Package("Material Management") type  Attributeset{ >> Attributeset.nebula
ECHO Org;>> Attributeset.nebula
ECHO Created Timestamp;>> Attributeset.nebula
ECHO Createdby Date;>> Attributeset.nebula
ECHO Description;>> Attributeset.nebula
ECHO Guaranteedays Count;>> Attributeset.nebula
ECHO Isactive YesNo;>> Attributeset.nebula
ECHO Isguaranteedate YesNo;>> Attributeset.nebula
ECHO Isguaranteedatemandatory YesNo;>> Attributeset.nebula
ECHO Isinstanceattribute YesNo;>> Attributeset.nebula
ECHO Islot YesNo;>> Attributeset.nebula
ECHO Islotmandatory YesNo;>> Attributeset.nebula
ECHO Isserno YesNo;>> Attributeset.nebula
ECHO Issernomandatory YesNo;>> Attributeset.nebula
ECHO Lotchareoverwrite YesNo;>> Attributeset.nebula
ECHO Lotcharsoverwrite YesNo;>> Attributeset.nebula
ECHO !ID;>> Attributeset.nebula
ECHO Lotctl;>> Attributeset.nebula
ECHO Sernoctl;>> Attributeset.nebula
ECHO Mandatorytype YesNo;>> Attributeset.nebula
ECHO !Name;>> Attributeset.nebula
ECHO Sernochareoverwrite YesNo;>> Attributeset.nebula
ECHO Sernocharsoverwrite YesNo;>> Attributeset.nebula
ECHO Updated Timestamp;>> Attributeset.nebula
ECHO Updatedby Date;>> Attributeset.nebula
ECHO };>> Attributeset.nebula 
 ECHO @Package("Material Management") type  Attributesetexclude{ >> Attributesetexclude.nebula
ECHO Org;>> Attributesetexclude.nebula
ECHO Table;>> Attributesetexclude.nebula
ECHO Created Timestamp;>> Attributesetexclude.nebula
ECHO Createdby Date;>> Attributesetexclude.nebula
ECHO Isactive YesNo;>> Attributesetexclude.nebula
ECHO Issotrx YesNo;>> Attributesetexclude.nebula
ECHO !ID;>> Attributesetexclude.nebula
ECHO Attributeset;>> Attributesetexclude.nebula
ECHO Updated Timestamp;>> Attributesetexclude.nebula
ECHO Updatedby Date;>> Attributesetexclude.nebula
ECHO };>> Attributesetexclude.nebula 
 ECHO @Package("Material Management") type  Attributesetinstance{ >> Attributesetinstance.nebula
ECHO Org;>> Attributesetinstance.nebula
ECHO Created Timestamp;>> Attributesetinstance.nebula
ECHO Createdby Date;>> Attributesetinstance.nebula
ECHO Description;>> Attributesetinstance.nebula
ECHO Guaranteedate Date;>> Attributesetinstance.nebula
ECHO Isactive YesNo;>> Attributesetinstance.nebula
ECHO LOT String;>> Attributesetinstance.nebula
ECHO !ID;>> Attributesetinstance.nebula
ECHO Attributeset;>> Attributesetinstance.nebula
ECHO Lot;>> Attributesetinstance.nebula
ECHO SerNo Code;>> Attributesetinstance.nebula
ECHO Updated Timestamp;>> Attributesetinstance.nebula
ECHO Updatedby Date;>> Attributesetinstance.nebula
ECHO };>> Attributesetinstance.nebula 
 ECHO @Package("Material Management") type  Attributeuse{ >> Attributeuse.nebula
ECHO Org;>> Attributeuse.nebula
ECHO Created Timestamp;>> Attributeuse.nebula
ECHO Createdby Date;>> Attributeuse.nebula
ECHO Isactive YesNo;>> Attributeuse.nebula
ECHO !Attributeset;>> Attributeuse.nebula
ECHO !Attribute;>> Attributeuse.nebula
ECHO SeqNo;>> Attributeuse.nebula
ECHO Updated Timestamp;>> Attributeuse.nebula
ECHO Updatedby Date;>> Attributeuse.nebula
ECHO };>> Attributeuse.nebula 
 ECHO @Package("Material Management") type  Attributevalue{ >> Attributevalue.nebula
ECHO Org;>> Attributevalue.nebula
ECHO Created Timestamp;>> Attributevalue.nebula
ECHO Createdby Date;>> Attributevalue.nebula
ECHO Description;>> Attributevalue.nebula
ECHO Isactive YesNo;>> Attributevalue.nebula
ECHO !ID;>> Attributevalue.nebula
ECHO Attribute;>> Attributevalue.nebula
ECHO !Name;>> Attributevalue.nebula
ECHO Updated Timestamp;>> Attributevalue.nebula
ECHO Updatedby Date;>> Attributevalue.nebula
ECHO Value String;>> Attributevalue.nebula
ECHO };>> Attributevalue.nebula 
 ECHO @Package("Material Management") type  Bom{ >> Bom.nebula
ECHO Org;>> Bom.nebula
ECHO Bomtype YesNo;>> Bom.nebula
ECHO Bomuse YesNo;>> Bom.nebula
ECHO Created Timestamp;>> Bom.nebula
ECHO Createdby Date;>> Bom.nebula
ECHO Description;>> Bom.nebula
ECHO Help;>> Bom.nebula
ECHO Isactive YesNo;>> Bom.nebula
ECHO !ID;>> Bom.nebula
ECHO Changenotice;>> Bom.nebula
ECHO Product;>> Bom.nebula
ECHO !Name;>> Bom.nebula
ECHO Processing YesNo;>> Bom.nebula
ECHO Updated Timestamp;>> Bom.nebula
ECHO Updatedby Date;>> Bom.nebula
ECHO };>> Bom.nebula 
 ECHO @Package("Material Management") type  Bomalternative{ >> Bomalternative.nebula
ECHO Org;>> Bomalternative.nebula
ECHO Created Timestamp;>> Bomalternative.nebula
ECHO Createdby Date;>> Bomalternative.nebula
ECHO Description;>> Bomalternative.nebula
ECHO Isactive YesNo;>> Bomalternative.nebula
ECHO !ID;>> Bomalternative.nebula
ECHO Product;>> Bomalternative.nebula
ECHO !Name;>> Bomalternative.nebula
ECHO Updated Timestamp;>> Bomalternative.nebula
ECHO Updatedby Date;>> Bomalternative.nebula
ECHO };>> Bomalternative.nebula 
 ECHO @Package("Material Management") type  Bomproduct{ >> Bomproduct.nebula
ECHO Org;>> Bomproduct.nebula
ECHO Bomproducttype YesNo;>> Bomproduct.nebula
ECHO Bomqty Quantity;>> Bomproduct.nebula
ECHO Created Timestamp;>> Bomproduct.nebula
ECHO Createdby Date;>> Bomproduct.nebula
ECHO Description;>> Bomproduct.nebula
ECHO Help;>> Bomproduct.nebula
ECHO Isactive YesNo;>> Bomproduct.nebula
ECHO Isphantom YesNo;>> Bomproduct.nebula
ECHO Leadtimeoffset Long;>> Bomproduct.nebula
ECHO Line Number;>> Bomproduct.nebula
ECHO Attributesetinstance;>> Bomproduct.nebula
ECHO Bomalternative;>> Bomproduct.nebula
ECHO !ID;>> Bomproduct.nebula
ECHO Bom;>> Bomproduct.nebula
ECHO Locator;>> Bomproduct.nebula
ECHO Productbomversion Bom;>> Bomproduct.nebula
ECHO Productbom Product;>> Bomproduct.nebula
ECHO Productoperation;>> Bomproduct.nebula
ECHO SeqNo;>> Bomproduct.nebula
ECHO Supplytype YesNo;>> Bomproduct.nebula
ECHO Updated Timestamp;>> Bomproduct.nebula
ECHO Updatedby Date;>> Bomproduct.nebula
ECHO };>> Bomproduct.nebula 
 ECHO @Package("Material Management") type  Changenotice{ >> Changenotice.nebula
ECHO Org;>> Changenotice.nebula
ECHO Created Timestamp;>> Changenotice.nebula
ECHO Createdby Date;>> Changenotice.nebula
ECHO Description;>> Changenotice.nebula
ECHO Detailinfo Description;>> Changenotice.nebula
ECHO Help;>> Changenotice.nebula
ECHO Isactive YesNo;>> Changenotice.nebula
ECHO Isapproved YesNo;>> Changenotice.nebula
ECHO !ID;>> Changenotice.nebula
ECHO !Name;>> Changenotice.nebula
ECHO Processed YesNo;>> Changenotice.nebula
ECHO Processing YesNo;>> Changenotice.nebula
ECHO Updated Timestamp;>> Changenotice.nebula
ECHO Updatedby Date;>> Changenotice.nebula
ECHO };>> Changenotice.nebula 
 ECHO @Package("Material Management") type  Changerequest{ >> Changerequest.nebula
ECHO Org;>> Changerequest.nebula
ECHO Created Timestamp;>> Changerequest.nebula
ECHO Createdby Date;>> Changerequest.nebula
ECHO Description;>> Changerequest.nebula
ECHO Detailinfo Description;>> Changerequest.nebula
ECHO Documentno SeqNo;>> Changerequest.nebula
ECHO Help;>> Changerequest.nebula
ECHO Isactive YesNo;>> Changerequest.nebula
ECHO Isapproved YesNo;>> Changerequest.nebula
ECHO Bom;>> Changerequest.nebula
ECHO Changenotice;>> Changerequest.nebula
ECHO !ID;>> Changerequest.nebula
ECHO Fixchangenotice Changenotice;>> Changerequest.nebula
ECHO !Name;>> Changerequest.nebula
ECHO Processed YesNo;>> Changerequest.nebula
ECHO Updated Timestamp;>> Changerequest.nebula
ECHO Updatedby Date;>> Changerequest.nebula
ECHO };>> Changerequest.nebula 
 ECHO @Package("Material Management") type  Cost{ >> Cost.nebula
ECHO !Org;>> Cost.nebula
ECHO !Acctschema;>> Cost.nebula
ECHO Created Timestamp;>> Cost.nebula
ECHO Createdby Date;>> Cost.nebula
ECHO Cumulatedamt Amount;>> Cost.nebula
ECHO Cumulatedqty Quantity;>> Cost.nebula
ECHO Currentcostprice Price;>> Cost.nebula
ECHO Currentqty Quantity;>> Cost.nebula
ECHO Description;>> Cost.nebula
ECHO Futurecostprice Price;>> Cost.nebula
ECHO Isactive YesNo;>> Cost.nebula
ECHO Lastcostprice Price;>> Cost.nebula
ECHO !Attributesetinstance;>> Cost.nebula
ECHO !Costelement;>> Cost.nebula
ECHO !Costtype;>> Cost.nebula
ECHO !Product;>> Cost.nebula
ECHO Percentcost Number;>> Cost.nebula
ECHO Updated Timestamp;>> Cost.nebula
ECHO Updatedby Date;>> Cost.nebula
ECHO };>> Cost.nebula 
 ECHO @Package("Material Management") type  Costdetail{ >> Costdetail.nebula
ECHO Org;>> Costdetail.nebula
ECHO Amt Amount;>> Costdetail.nebula
ECHO Acctschema;>> Costdetail.nebula
ECHO Invoiceline;>> Costdetail.nebula
ECHO Orderline;>> Costdetail.nebula
ECHO Projectissue;>> Costdetail.nebula
ECHO Created Timestamp;>> Costdetail.nebula
ECHO Createdby Date;>> Costdetail.nebula
ECHO Deltaamt Amount;>> Costdetail.nebula
ECHO Deltaqty Quantity;>> Costdetail.nebula
ECHO Description;>> Costdetail.nebula
ECHO Isactive YesNo;>> Costdetail.nebula
ECHO Issotrx YesNo;>> Costdetail.nebula
ECHO Attributesetinstance;>> Costdetail.nebula
ECHO !ID;>> Costdetail.nebula
ECHO Costelement;>> Costdetail.nebula
ECHO Inoutline;>> Costdetail.nebula
ECHO Inventoryline;>> Costdetail.nebula
ECHO Movementline;>> Costdetail.nebula
ECHO Product;>> Costdetail.nebula
ECHO Productionline;>> Costdetail.nebula
ECHO Workordertransactionline;>> Costdetail.nebula
ECHO Processed YesNo;>> Costdetail.nebula
ECHO Qty Quantity;>> Costdetail.nebula
ECHO Updated Timestamp;>> Costdetail.nebula
ECHO Updatedby Date;>> Costdetail.nebula
ECHO };>> Costdetail.nebula 
 ECHO @Package("Material Management") type  Costelement{ >> Costelement.nebula
ECHO Org;>> Costelement.nebula
ECHO Costelementtype YesNo;>> Costelement.nebula
ECHO Costingmethod YesNo;>> Costelement.nebula
ECHO Created Timestamp;>> Costelement.nebula
ECHO Createdby Date;>> Costelement.nebula
ECHO Description;>> Costelement.nebula
ECHO Isactive YesNo;>> Costelement.nebula
ECHO Iscalculated YesNo;>> Costelement.nebula
ECHO !ID;>> Costelement.nebula
ECHO !Name;>> Costelement.nebula
ECHO Updated Timestamp;>> Costelement.nebula
ECHO Updatedby Date;>> Costelement.nebula
ECHO };>> Costelement.nebula 
 ECHO @Package("Material Management") type  Costqueue{ >> Costqueue.nebula
ECHO Org;>> Costqueue.nebula
ECHO Acctschema;>> Costqueue.nebula
ECHO Created Timestamp;>> Costqueue.nebula
ECHO Createdby Date;>> Costqueue.nebula
ECHO Currentcostprice Price;>> Costqueue.nebula
ECHO Currentqty Quantity;>> Costqueue.nebula
ECHO Isactive YesNo;>> Costqueue.nebula
ECHO Attributesetinstance;>> Costqueue.nebula
ECHO Costelement;>> Costqueue.nebula
ECHO !ID;>> Costqueue.nebula
ECHO Costtype;>> Costqueue.nebula
ECHO Product;>> Costqueue.nebula
ECHO Updated Timestamp;>> Costqueue.nebula
ECHO Updatedby Date;>> Costqueue.nebula
ECHO };>> Costqueue.nebula 
 ECHO @Package("Material Management") type  Costtype{ >> Costtype.nebula
ECHO Org;>> Costtype.nebula
ECHO Created Timestamp;>> Costtype.nebula
ECHO Createdby Date;>> Costtype.nebula
ECHO Description;>> Costtype.nebula
ECHO Help;>> Costtype.nebula
ECHO Isactive YesNo;>> Costtype.nebula
ECHO !ID;>> Costtype.nebula
ECHO !Name;>> Costtype.nebula
ECHO Updated Timestamp;>> Costtype.nebula
ECHO Updatedby Date;>> Costtype.nebula
ECHO };>> Costtype.nebula 
 ECHO @Package("Material Management") type  Costupdate{ >> Costupdate.nebula
ECHO Orgtrx Org;>> Costupdate.nebula
ECHO Org;>> Costupdate.nebula
ECHO User;>> Costupdate.nebula
ECHO Activity;>> Costupdate.nebula
ECHO Campaign;>> Costupdate.nebula
ECHO Charge;>> Costupdate.nebula
ECHO Project;>> Costupdate.nebula
ECHO Created Timestamp;>> Costupdate.nebula
ECHO Createdby Date;>> Costupdate.nebula
ECHO Dateacct Date;>> Costupdate.nebula
ECHO Description;>> Costupdate.nebula
ECHO Docaction Attr;>> Costupdate.nebula
ECHO Docstatus Attr;>> Costupdate.nebula
ECHO Documentno SeqNo;>> Costupdate.nebula
ECHO Isactive YesNo;>> Costupdate.nebula
ECHO Issotrx YesNo;>> Costupdate.nebula
ECHO !ID;>> Costupdate.nebula
ECHO Product_Category;>> Costupdate.nebula
ECHO Posted YesNo;>> Costupdate.nebula
ECHO Processed YesNo;>> Costupdate.nebula
ECHO Processing YesNo;>> Costupdate.nebula
ECHO Updated Timestamp;>> Costupdate.nebula
ECHO Updatedby Date;>> Costupdate.nebula
ECHO User1 Elementvalue;>> Costupdate.nebula
ECHO User2 Elementvalue;>> Costupdate.nebula
ECHO };>> Costupdate.nebula 
 ECHO @Package("Material Management") type  Costupdateline{ >> Costupdateline.nebula
ECHO Orgtrx Org;>> Costupdateline.nebula
ECHO Org;>> Costupdateline.nebula
ECHO Activity;>> Costupdateline.nebula
ECHO Campaign;>> Costupdateline.nebula
ECHO Charge;>> Costupdateline.nebula
ECHO Projecttask;>> Costupdateline.nebula
ECHO Project;>> Costupdateline.nebula
ECHO Uom;>> Costupdateline.nebula
ECHO Created Timestamp;>> Costupdateline.nebula
ECHO Createdby Date;>> Costupdateline.nebula
ECHO Description;>> Costupdateline.nebula
ECHO Isactive YesNo;>> Costupdateline.nebula
ECHO Attributesetinstance;>> Costupdateline.nebula
ECHO !ID;>> Costupdateline.nebula
ECHO Costupdate;>> Costupdateline.nebula
ECHO Product;>> Costupdateline.nebula
ECHO Processed YesNo;>> Costupdateline.nebula
ECHO Updated Timestamp;>> Costupdateline.nebula
ECHO Updatedby Date;>> Costupdateline.nebula
ECHO User1 Elementvalue;>> Costupdateline.nebula
ECHO User2 Elementvalue;>> Costupdateline.nebula
ECHO };>> Costupdateline.nebula 
 ECHO @Package("Material Management") type  Demand{ >> Demand.nebula
ECHO Org;>> Demand.nebula
ECHO Calendar;>> Demand.nebula
ECHO Year;>> Demand.nebula
ECHO Created Timestamp;>> Demand.nebula
ECHO Createdby Date;>> Demand.nebula
ECHO Description;>> Demand.nebula
ECHO Help;>> Demand.nebula
ECHO Isactive YesNo;>> Demand.nebula
ECHO Isdefault YesNo;>> Demand.nebula
ECHO !ID;>> Demand.nebula
ECHO !Name;>> Demand.nebula
ECHO Processing YesNo;>> Demand.nebula
ECHO Updated Timestamp;>> Demand.nebula
ECHO Updatedby Date;>> Demand.nebula
ECHO };>> Demand.nebula 
 ECHO @Package("Material Management") type  Demanddetail{ >> Demanddetail.nebula
ECHO Org;>> Demanddetail.nebula
ECHO Orderline;>> Demanddetail.nebula
ECHO Created Timestamp;>> Demanddetail.nebula
ECHO Createdby Date;>> Demanddetail.nebula
ECHO Isactive YesNo;>> Demanddetail.nebula
ECHO !ID;>> Demanddetail.nebula
ECHO Demandline;>> Demanddetail.nebula
ECHO Forecastline;>> Demanddetail.nebula
ECHO Requisitionline;>> Demanddetail.nebula
ECHO Updated Timestamp;>> Demanddetail.nebula
ECHO Updatedby Date;>> Demanddetail.nebula
ECHO };>> Demanddetail.nebula 
 ECHO @Package("Material Management") type  Demandline{ >> Demandline.nebula
ECHO Org;>> Demandline.nebula
ECHO Period;>> Demandline.nebula
ECHO Created Timestamp;>> Demandline.nebula
ECHO Createdby Date;>> Demandline.nebula
ECHO Isactive YesNo;>> Demandline.nebula
ECHO !ID;>> Demandline.nebula
ECHO Demand;>> Demandline.nebula
ECHO Product;>> Demandline.nebula
ECHO Qty Quantity;>> Demandline.nebula
ECHO Qtycalculated Quantity;>> Demandline.nebula
ECHO Updated Timestamp;>> Demandline.nebula
ECHO Updatedby Date;>> Demandline.nebula
ECHO };>> Demandline.nebula 
 ECHO @Package("Material Management") type  Discountschema{ >> Discountschema.nebula
ECHO Org;>> Discountschema.nebula
ECHO Created Timestamp;>> Discountschema.nebula
ECHO Createdby Date;>> Discountschema.nebula
ECHO Cumulativelevel YesNo;>> Discountschema.nebula
ECHO Description;>> Discountschema.nebula
ECHO Discounttype YesNo;>> Discountschema.nebula
ECHO Flatdiscount Number;>> Discountschema.nebula
ECHO Isactive YesNo;>> Discountschema.nebula
ECHO Isbpartnerflatdiscount YesNo;>> Discountschema.nebula
ECHO Isquantitybased YesNo;>> Discountschema.nebula
ECHO !ID;>> Discountschema.nebula
ECHO !Name;>> Discountschema.nebula
ECHO Processing YesNo;>> Discountschema.nebula
ECHO Script Note;>> Discountschema.nebula
ECHO Updated Timestamp;>> Discountschema.nebula
ECHO Updatedby Date;>> Discountschema.nebula
ECHO Validfrom Date;>> Discountschema.nebula
ECHO };>> Discountschema.nebula 
 ECHO @Package("Material Management") type  Discountschemabreak{ >> Discountschemabreak.nebula
ECHO Org;>> Discountschemabreak.nebula
ECHO Breakdiscount Count;>> Discountschemabreak.nebula
ECHO Breakvalue Number;>> Discountschemabreak.nebula
ECHO Created Timestamp;>> Discountschemabreak.nebula
ECHO Createdby Date;>> Discountschemabreak.nebula
ECHO Isactive YesNo;>> Discountschemabreak.nebula
ECHO Isbpartnerflatdiscount YesNo;>> Discountschemabreak.nebula
ECHO !ID;>> Discountschemabreak.nebula
ECHO Discountschema;>> Discountschemabreak.nebula
ECHO Product_Category;>> Discountschemabreak.nebula
ECHO Product;>> Discountschemabreak.nebula
ECHO SeqNo;>> Discountschemabreak.nebula
ECHO Updated Timestamp;>> Discountschemabreak.nebula
ECHO Updatedby Date;>> Discountschemabreak.nebula
ECHO };>> Discountschemabreak.nebula 
 ECHO @Package("Material Management") type  Discountschemaline{ >> Discountschemaline.nebula
ECHO Org;>> Discountschemaline.nebula
ECHO Bpartner;>> Discountschemaline.nebula
ECHO Conversiontype;>> Discountschemaline.nebula
ECHO Conversiondate Date;>> Discountschemaline.nebula
ECHO Created Timestamp;>> Discountschemaline.nebula
ECHO Createdby Date;>> Discountschemaline.nebula
ECHO Description;>> Discountschemaline.nebula
ECHO Isactive YesNo;>> Discountschemaline.nebula
ECHO Limit_Addamt Amount;>> Discountschemaline.nebula
ECHO Limit_Base YesNo;>> Discountschemaline.nebula
ECHO Limit_Discount Count;>> Discountschemaline.nebula
ECHO Limit_Fixed Number;>> Discountschemaline.nebula
ECHO Limit_Maxamt Amount;>> Discountschemaline.nebula
ECHO Limit_Minamt Amount;>> Discountschemaline.nebula
ECHO Limit_Rounding YesNo;>> Discountschemaline.nebula
ECHO List_Addamt Amount;>> Discountschemaline.nebula
ECHO List_Base YesNo;>> Discountschemaline.nebula
ECHO List_Discount Count;>> Discountschemaline.nebula
ECHO List_Fixed Number;>> Discountschemaline.nebula
ECHO List_Maxamt Amount;>> Discountschemaline.nebula
ECHO List_Minamt Amount;>> Discountschemaline.nebula
ECHO List_Rounding YesNo;>> Discountschemaline.nebula
ECHO !ID;>> Discountschemaline.nebula
ECHO Discountschema;>> Discountschemaline.nebula
ECHO Product_Category;>> Discountschemaline.nebula
ECHO Product;>> Discountschemaline.nebula
ECHO SeqNo;>> Discountschemaline.nebula
ECHO Std_Addamt Amount;>> Discountschemaline.nebula
ECHO Std_Base YesNo;>> Discountschemaline.nebula
ECHO Std_Discount Count;>> Discountschemaline.nebula
ECHO Std_Fixed Number;>> Discountschemaline.nebula
ECHO Std_Maxamt Amount;>> Discountschemaline.nebula
ECHO Std_Minamt Amount;>> Discountschemaline.nebula
ECHO Std_Rounding YesNo;>> Discountschemaline.nebula
ECHO Updated Timestamp;>> Discountschemaline.nebula
ECHO Updatedby Date;>> Discountschemaline.nebula
ECHO };>> Discountschemaline.nebula 
 ECHO @Package("Material Management") type  Distributionlist{ >> Distributionlist.nebula
ECHO Org;>> Distributionlist.nebula
ECHO Created Timestamp;>> Distributionlist.nebula
ECHO Createdby Date;>> Distributionlist.nebula
ECHO Description;>> Distributionlist.nebula
ECHO Help;>> Distributionlist.nebula
ECHO Isactive YesNo;>> Distributionlist.nebula
ECHO !ID;>> Distributionlist.nebula
ECHO !Name;>> Distributionlist.nebula
ECHO Processing YesNo;>> Distributionlist.nebula
ECHO Ratiototal Ratio;>> Distributionlist.nebula
ECHO Updated Timestamp;>> Distributionlist.nebula
ECHO Updatedby Date;>> Distributionlist.nebula
ECHO };>> Distributionlist.nebula 
 ECHO @Package("Material Management") type  Distributionlistline{ >> Distributionlistline.nebula
ECHO Org;>> Distributionlistline.nebula
ECHO Bpartner;>> Distributionlistline.nebula
ECHO Bpartner_Location;>> Distributionlistline.nebula
ECHO Created Timestamp;>> Distributionlistline.nebula
ECHO Createdby Date;>> Distributionlistline.nebula
ECHO Description;>> Distributionlistline.nebula
ECHO Isactive YesNo;>> Distributionlistline.nebula
ECHO !ID;>> Distributionlistline.nebula
ECHO Distributionlist;>> Distributionlistline.nebula
ECHO Minqty Quantity;>> Distributionlistline.nebula
ECHO Ratio;>> Distributionlistline.nebula
ECHO Updated Timestamp;>> Distributionlistline.nebula
ECHO Updatedby Date;>> Distributionlistline.nebula
ECHO };>> Distributionlistline.nebula 
 ECHO @Package("Material Management") type  Distributionrun{ >> Distributionrun.nebula
ECHO Org;>> Distributionrun.nebula
ECHO Bpartner;>> Distributionrun.nebula
ECHO Bpartner_Location;>> Distributionrun.nebula
ECHO Created Timestamp;>> Distributionrun.nebula
ECHO Createdby Date;>> Distributionrun.nebula
ECHO Description;>> Distributionrun.nebula
ECHO Isactive YesNo;>> Distributionrun.nebula
ECHO Iscreatesingleorder YesNo;>> Distributionrun.nebula
ECHO !ID;>> Distributionrun.nebula
ECHO !Name;>> Distributionrun.nebula
ECHO Processing YesNo;>> Distributionrun.nebula
ECHO Updated Timestamp;>> Distributionrun.nebula
ECHO Updatedby Date;>> Distributionrun.nebula
ECHO };>> Distributionrun.nebula 
 ECHO @Package("Material Management") type  Distributionrunline{ >> Distributionrunline.nebula
ECHO Org;>> Distributionrunline.nebula
ECHO Created Timestamp;>> Distributionrunline.nebula
ECHO Createdby Date;>> Distributionrunline.nebula
ECHO Description;>> Distributionrunline.nebula
ECHO Isactive YesNo;>> Distributionrunline.nebula
ECHO Line Number;>> Distributionrunline.nebula
ECHO Distributionlist;>> Distributionrunline.nebula
ECHO !ID;>> Distributionrunline.nebula
ECHO Distributionrun;>> Distributionrunline.nebula
ECHO Product;>> Distributionrunline.nebula
ECHO Minqty Quantity;>> Distributionrunline.nebula
ECHO Totalqty Quantity;>> Distributionrunline.nebula
ECHO Updated Timestamp;>> Distributionrunline.nebula
ECHO Updatedby Date;>> Distributionrunline.nebula
ECHO };>> Distributionrunline.nebula 
 ECHO @Package("Material Management") type  Forecast{ >> Forecast.nebula
ECHO Org;>> Forecast.nebula
ECHO Calendar;>> Forecast.nebula
ECHO Year;>> Forecast.nebula
ECHO Created Timestamp;>> Forecast.nebula
ECHO Createdby Date;>> Forecast.nebula
ECHO Description;>> Forecast.nebula
ECHO Help;>> Forecast.nebula
ECHO Isactive YesNo;>> Forecast.nebula
ECHO Isdefault YesNo;>> Forecast.nebula
ECHO !ID;>> Forecast.nebula
ECHO !Name;>> Forecast.nebula
ECHO Processing YesNo;>> Forecast.nebula
ECHO Updated Timestamp;>> Forecast.nebula
ECHO Updatedby Date;>> Forecast.nebula
ECHO };>> Forecast.nebula 
 ECHO @Package("Material Management") type  Forecastline{ >> Forecastline.nebula
ECHO Org;>> Forecastline.nebula
ECHO Period;>> Forecastline.nebula
ECHO Created Timestamp;>> Forecastline.nebula
ECHO Createdby Date;>> Forecastline.nebula
ECHO Isactive YesNo;>> Forecastline.nebula
ECHO !ID;>> Forecastline.nebula
ECHO Forecast;>> Forecastline.nebula
ECHO Product;>> Forecastline.nebula
ECHO Qty Quantity;>> Forecastline.nebula
ECHO Qtycalculated Quantity;>> Forecastline.nebula
ECHO Updated Timestamp;>> Forecastline.nebula
ECHO Updatedby Date;>> Forecastline.nebula
ECHO };>> Forecastline.nebula 
 ECHO @Package("Material Management") type  Freight{ >> Freight.nebula
ECHO Org;>> Freight.nebula
ECHO Country;>> Freight.nebula
ECHO Currency;>> Freight.nebula
ECHO Region;>> Freight.nebula
ECHO Created Timestamp;>> Freight.nebula
ECHO Createdby Date;>> Freight.nebula
ECHO Freightamt Amount;>> Freight.nebula
ECHO Isactive YesNo;>> Freight.nebula
ECHO Freightcategory;>> Freight.nebula
ECHO !ID;>> Freight.nebula
ECHO Shipper;>> Freight.nebula
ECHO To_Country Country;>> Freight.nebula
ECHO To_Region Region;>> Freight.nebula
ECHO Updated Timestamp;>> Freight.nebula
ECHO Updatedby Date;>> Freight.nebula
ECHO Validfrom Date;>> Freight.nebula
ECHO };>> Freight.nebula 
 ECHO @Package("Material Management") type  Freightcategory{ >> Freightcategory.nebula
ECHO Org;>> Freightcategory.nebula
ECHO Created Timestamp;>> Freightcategory.nebula
ECHO Createdby Date;>> Freightcategory.nebula
ECHO Description;>> Freightcategory.nebula
ECHO Help;>> Freightcategory.nebula
ECHO Isactive YesNo;>> Freightcategory.nebula
ECHO !ID;>> Freightcategory.nebula
ECHO !Name;>> Freightcategory.nebula
ECHO Updated Timestamp;>> Freightcategory.nebula
ECHO Updatedby Date;>> Freightcategory.nebula
ECHO Value String;>> Freightcategory.nebula
ECHO };>> Freightcategory.nebula 
 ECHO @Package("Material Management") type  Inout{ >> Inout.nebula
ECHO Orgtrx Org;>> Inout.nebula
ECHO Org;>> Inout.nebula
ECHO User;>> Inout.nebula
ECHO Activity;>> Inout.nebula
ECHO Bpartner;>> Inout.nebula
ECHO Bpartner_Location;>> Inout.nebula
ECHO Campaign;>> Inout.nebula
ECHO Charge;>> Inout.nebula
ECHO Doctype;>> Inout.nebula
ECHO Invoice;>> Inout.nebula
ECHO Order;>> Inout.nebula
ECHO Project;>> Inout.nebula
ECHO Chargeamt Amount;>> Inout.nebula
ECHO Createconfirm YesNo;>> Inout.nebula
ECHO Createfrom YesNo;>> Inout.nebula
ECHO Createpackage YesNo;>> Inout.nebula
ECHO Created Timestamp;>> Inout.nebula
ECHO Createdby Date;>> Inout.nebula
ECHO Dateacct Date;>> Inout.nebula
ECHO Dateordered Date;>> Inout.nebula
ECHO Dateprinted Date;>> Inout.nebula
ECHO Datereceived Date;>> Inout.nebula
ECHO Deliveryrule YesNo;>> Inout.nebula
ECHO Deliveryviarule YesNo;>> Inout.nebula
ECHO Description;>> Inout.nebula
ECHO Docaction Attr;>> Inout.nebula
ECHO Docstatus Attr;>> Inout.nebula
ECHO Documentno SeqNo;>> Inout.nebula
ECHO Freightamt Amount;>> Inout.nebula
ECHO Freightcostrule YesNo;>> Inout.nebula
ECHO Generateto YesNo;>> Inout.nebula
ECHO Isactive YesNo;>> Inout.nebula
ECHO Isapproved YesNo;>> Inout.nebula
ECHO Isindispute YesNo;>> Inout.nebula
ECHO Isintransit YesNo;>> Inout.nebula
ECHO Isprinted YesNo;>> Inout.nebula
ECHO Isreturntrx YesNo;>> Inout.nebula
ECHO Issotrx YesNo;>> Inout.nebula
ECHO !ID;>> Inout.nebula
ECHO Shipper;>> Inout.nebula
ECHO Warehouse;>> Inout.nebula
ECHO Matchrequirementr YesNo;>> Inout.nebula
ECHO Movementdate Date;>> Inout.nebula
ECHO Movementtype Attr;>> Inout.nebula
ECHO Nopackages Number;>> Inout.nebula
ECHO Poreference String;>> Inout.nebula
ECHO Pickdate Date;>> Inout.nebula
ECHO Posted YesNo;>> Inout.nebula
ECHO Priorityrule YesNo;>> Inout.nebula
ECHO Processed YesNo;>> Inout.nebula
ECHO Processing YesNo;>> Inout.nebula

ECHO Salesrep User;>> Inout.nebula
ECHO Sendemail YesNo;>> Inout.nebula
ECHO Shipdate Date;>> Inout.nebula
ECHO Trackingno Code;>> Inout.nebula
ECHO Updated Timestamp;>> Inout.nebula
ECHO Updatedby Date;>> Inout.nebula
ECHO User1 Elementvalue;>> Inout.nebula
ECHO User2 Elementvalue;>> Inout.nebula
ECHO Volume Long;>> Inout.nebula
ECHO Weight;>> Inout.nebula
ECHO };>> Inout.nebula 
 ECHO @Package("Material Management") type  Inoutconfirm{ >> Inoutconfirm.nebula
ECHO Org;>> Inoutconfirm.nebula
ECHO Approvalamt Amount;>> Inoutconfirm.nebula
ECHO Invoice;>> Inoutconfirm.nebula
ECHO Confirmtype Attr;>> Inoutconfirm.nebula
ECHO Confirmationno Code;>> Inoutconfirm.nebula
ECHO Createpackage YesNo;>> Inoutconfirm.nebula
ECHO Created Timestamp;>> Inoutconfirm.nebula
ECHO Createdby Date;>> Inoutconfirm.nebula
ECHO Description;>> Inoutconfirm.nebula
ECHO Docaction Attr;>> Inoutconfirm.nebula
ECHO Docstatus Attr;>> Inoutconfirm.nebula
ECHO Documentno SeqNo;>> Inoutconfirm.nebula
ECHO Isactive YesNo;>> Inoutconfirm.nebula
ECHO Isapproved YesNo;>> Inoutconfirm.nebula
ECHO Iscancelled YesNo;>> Inoutconfirm.nebula
ECHO Isindispute YesNo;>> Inoutconfirm.nebula
ECHO !ID;>> Inoutconfirm.nebula
ECHO Inout;>> Inoutconfirm.nebula
ECHO Inventory;>> Inoutconfirm.nebula
ECHO Processed YesNo;>> Inoutconfirm.nebula
ECHO Processing YesNo;>> Inoutconfirm.nebula
ECHO Updated Timestamp;>> Inoutconfirm.nebula
ECHO Updatedby Date;>> Inoutconfirm.nebula
ECHO };>> Inoutconfirm.nebula 
 ECHO @Package("Material Management") type  Inoutline{ >> Inoutline.nebula
ECHO Orgtrx Org;>> Inoutline.nebula
ECHO Org;>> Inoutline.nebula
ECHO Activity;>> Inoutline.nebula
ECHO Campaign;>> Inoutline.nebula
ECHO Charge;>> Inoutline.nebula
ECHO Orderline;>> Inoutline.nebula
ECHO Projectphase;>> Inoutline.nebula
ECHO Projecttask;>> Inoutline.nebula
ECHO Project;>> Inoutline.nebula
ECHO Uom;>> Inoutline.nebula
ECHO Confirmedqty Quantity;>> Inoutline.nebula
ECHO Created Timestamp;>> Inoutline.nebula
ECHO Createdby Date;>> Inoutline.nebula
ECHO Description;>> Inoutline.nebula
ECHO Isactive YesNo;>> Inoutline.nebula
ECHO Isdescription YesNo;>> Inoutline.nebula
ECHO Isinvoiced YesNo;>> Inoutline.nebula
ECHO Line Number;>> Inoutline.nebula
ECHO Linedocstatus Attr;>> Inoutline.nebula
ECHO Attributesetinstance;>> Inoutline.nebula
ECHO !ID;>> Inoutline.nebula
ECHO Inout;>> Inoutline.nebula
ECHO Locator;>> Inoutline.nebula
ECHO Product;>> Inoutline.nebula
ECHO Movementqty Quantity;>> Inoutline.nebula
ECHO Pickedqty Quantity;>> Inoutline.nebula
ECHO Processed YesNo;>> Inoutline.nebula
ECHO Qtyallocated Quantity;>> Inoutline.nebula
ECHO Qtyentered Quantity;>> Inoutline.nebula

ECHO Scrappedqty Quantity;>> Inoutline.nebula
ECHO Targetqty Quantity;>> Inoutline.nebula
ECHO Updated Timestamp;>> Inoutline.nebula
ECHO Updatedby Date;>> Inoutline.nebula
ECHO User1 Elementvalue;>> Inoutline.nebula
ECHO User2 Elementvalue;>> Inoutline.nebula
ECHO };>> Inoutline.nebula 
 ECHO @Package("Material Management") type  Inoutlineconfirm{ >> Inoutlineconfirm.nebula
ECHO Org;>> Inoutlineconfirm.nebula
ECHO Invoiceline;>> Inoutlineconfirm.nebula
ECHO Confirmationno Code;>> Inoutlineconfirm.nebula
ECHO Confirmedqty Quantity;>> Inoutlineconfirm.nebula
ECHO Created Timestamp;>> Inoutlineconfirm.nebula
ECHO Createdby Date;>> Inoutlineconfirm.nebula
ECHO Description;>> Inoutlineconfirm.nebula
ECHO Differenceqty Quantity;>> Inoutlineconfirm.nebula
ECHO Isactive YesNo;>> Inoutlineconfirm.nebula
ECHO Inoutconfirm;>> Inoutlineconfirm.nebula
ECHO !ID;>> Inoutlineconfirm.nebula
ECHO Inoutline;>> Inoutlineconfirm.nebula
ECHO Inventoryline;>> Inoutlineconfirm.nebula
ECHO Processed YesNo;>> Inoutlineconfirm.nebula
ECHO Scrappedqty Quantity;>> Inoutlineconfirm.nebula
ECHO Targetqty Quantity;>> Inoutlineconfirm.nebula
ECHO Updated Timestamp;>> Inoutlineconfirm.nebula
ECHO Updatedby Date;>> Inoutlineconfirm.nebula
ECHO };>> Inoutlineconfirm.nebula 
 ECHO @Package("Material Management") type  Inoutlinema{ >> Inoutlinema.nebula
ECHO Org;>> Inoutlinema.nebula
ECHO Created Timestamp;>> Inoutlinema.nebula
ECHO Createdby Date;>> Inoutlinema.nebula
ECHO Isactive YesNo;>> Inoutlinema.nebula
ECHO !Attributesetinstance;>> Inoutlinema.nebula
ECHO !Inoutline;>> Inoutlinema.nebula
ECHO Movementqty Quantity;>> Inoutlinema.nebula
ECHO Qtyallocated Quantity;>> Inoutlinema.nebula
ECHO Updated Timestamp;>> Inoutlinema.nebula
ECHO Updatedby Date;>> Inoutlinema.nebula
ECHO };>> Inoutlinema.nebula 
 ECHO @Package("Material Management") type  Inoutstage{ >> Inoutstage.nebula
ECHO Org;>> Inoutstage.nebula
ECHO User;>> Inoutstage.nebula
ECHO Doctype;>> Inoutstage.nebula
ECHO Orderline;>> Inoutstage.nebula
ECHO Order;>> Inoutstage.nebula
ECHO Uom;>> Inoutstage.nebula
ECHO Createinoutdocaction Attr;>> Inoutstage.nebula
ECHO Created Timestamp;>> Inoutstage.nebula
ECHO Createdby Date;>> Inoutstage.nebula
ECHO ErrorMsg MSG;>> Inoutstage.nebula
ECHO Isactive YesNo;>> Inoutstage.nebula
ECHO Iscreateonsave YesNo;>> Inoutstage.nebula
ECHO Isinoutcreated YesNo;>> Inoutstage.nebula
ECHO Isreturntrx YesNo;>> Inoutstage.nebula
ECHO Issotrx YesNo;>> Inoutstage.nebula
ECHO !ID;>> Inoutstage.nebula
ECHO Inout;>> Inoutstage.nebula
ECHO Locator;>> Inoutstage.nebula
ECHO Product;>> Inoutstage.nebula
ECHO Warehouse;>> Inoutstage.nebula
ECHO Movementdate Date;>> Inoutstage.nebula
ECHO Processed YesNo;>> Inoutstage.nebula
ECHO Processing YesNo;>> Inoutstage.nebula
ECHO Qtyentered Quantity;>> Inoutstage.nebula
ECHO Updated Timestamp;>> Inoutstage.nebula
ECHO Updatedby Date;>> Inoutstage.nebula
ECHO };>> Inoutstage.nebula 
 ECHO @Package("Material Management") type  Inventory{ >> Inventory.nebula
ECHO Orgtrx Org;>> Inventory.nebula
ECHO Org;>> Inventory.nebula
ECHO Approvalamt Amount;>> Inventory.nebula
ECHO Activity;>> Inventory.nebula
ECHO Campaign;>> Inventory.nebula
ECHO Doctype;>> Inventory.nebula
ECHO Project;>> Inventory.nebula
ECHO Created Timestamp;>> Inventory.nebula
ECHO Createdby Date;>> Inventory.nebula
ECHO Description;>> Inventory.nebula
ECHO Docaction Attr;>> Inventory.nebula
ECHO Docstatus Attr;>> Inventory.nebula
ECHO Documentno SeqNo;>> Inventory.nebula
ECHO Generatelist YesNo;>> Inventory.nebula
ECHO Isactive YesNo;>> Inventory.nebula
ECHO Isapproved YesNo;>> Inventory.nebula
ECHO !ID;>> Inventory.nebula
ECHO Perpetualinv;>> Inventory.nebula
ECHO Warehouse;>> Inventory.nebula
ECHO Movementdate Date;>> Inventory.nebula
ECHO Posted YesNo;>> Inventory.nebula
ECHO Processed YesNo;>> Inventory.nebula
ECHO Processing YesNo;>> Inventory.nebula
ECHO Updateqty YesNo;>> Inventory.nebula
ECHO Updated Timestamp;>> Inventory.nebula
ECHO Updatedby Date;>> Inventory.nebula
ECHO User1 Elementvalue;>> Inventory.nebula
ECHO User2 Elementvalue;>> Inventory.nebula
ECHO };>> Inventory.nebula 
 ECHO @Package("Material Management") type  Inventoryline{ >> Inventoryline.nebula
ECHO Orgtrx Org;>> Inventoryline.nebula
ECHO Org;>> Inventoryline.nebula
ECHO Activity;>> Inventoryline.nebula
ECHO Charge;>> Inventoryline.nebula
ECHO Created Timestamp;>> Inventoryline.nebula
ECHO Createdby Date;>> Inventoryline.nebula
ECHO Description;>> Inventoryline.nebula
ECHO Inventorytype YesNo;>> Inventoryline.nebula
ECHO Isactive YesNo;>> Inventoryline.nebula
ECHO Isinternaluse YesNo;>> Inventoryline.nebula
ECHO Line Number;>> Inventoryline.nebula
ECHO Attributesetinstance;>> Inventoryline.nebula
ECHO !ID;>> Inventoryline.nebula
ECHO Inventory;>> Inventoryline.nebula
ECHO Locator;>> Inventoryline.nebula
ECHO Product;>> Inventoryline.nebula
ECHO Processed YesNo;>> Inventoryline.nebula
ECHO Qtybook Quantity;>> Inventoryline.nebula
ECHO Qtycount Quantity;>> Inventoryline.nebula
ECHO Qtyinternaluse Quantity;>> Inventoryline.nebula
ECHO Updated Timestamp;>> Inventoryline.nebula
ECHO Updatedby Date;>> Inventoryline.nebula
ECHO };>> Inventoryline.nebula 
 ECHO @Package("Material Management") type  Inventorylinema{ >> Inventorylinema.nebula
ECHO Org;>> Inventorylinema.nebula
ECHO Created Timestamp;>> Inventorylinema.nebula
ECHO Createdby Date;>> Inventorylinema.nebula
ECHO Isactive YesNo;>> Inventorylinema.nebula
ECHO !Attributesetinstance;>> Inventorylinema.nebula
ECHO !Inventoryline;>> Inventorylinema.nebula
ECHO Movementqty Quantity;>> Inventorylinema.nebula
ECHO Updated Timestamp;>> Inventorylinema.nebula
ECHO Updatedby Date;>> Inventorylinema.nebula
ECHO };>> Inventorylinema.nebula 
 ECHO @Package("Material Management") type  Locator{ >> Locator.nebula
ECHO Org;>> Locator.nebula
ECHO Bin String;>> Locator.nebula
ECHO Created Timestamp;>> Locator.nebula
ECHO Createdby Date;>> Locator.nebula
ECHO Isactive YesNo;>> Locator.nebula
ECHO Isavailableforallocation YesNo;>> Locator.nebula
ECHO Isavailabletopromise YesNo;>> Locator.nebula
ECHO Isdefault YesNo;>> Locator.nebula
ECHO Locatorcombination String;>> Locator.nebula
ECHO !ID;>> Locator.nebula
ECHO Warehouse;>> Locator.nebula
ECHO Maxquantity Quantity;>> Locator.nebula
ECHO Minquantity Quantity;>> Locator.nebula
ECHO Pickingseqno SeqNo;>> Locator.nebula
ECHO Picking_Uom Uom;>> Locator.nebula
ECHO Position String;>> Locator.nebula
ECHO Priorityno SeqNo;>> Locator.nebula
ECHO Putawayseqno SeqNo;>> Locator.nebula
ECHO Stocking_Uom Uom;>> Locator.nebula
ECHO Updated Timestamp;>> Locator.nebula
ECHO Updatedby Date;>> Locator.nebula
ECHO Value String;>> Locator.nebula
ECHO X String;>> Locator.nebula
ECHO Y String;>> Locator.nebula
ECHO Z String;>> Locator.nebula
ECHO };>> Locator.nebula 
 ECHO @Package("Material Management") type  Lot{ >> Lot.nebula
ECHO Org;>> Lot.nebula
ECHO Created Timestamp;>> Lot.nebula
ECHO Createdby Date;>> Lot.nebula
ECHO Datefrom Date;>> Lot.nebula
ECHO Dateto Date;>> Lot.nebula
ECHO Description;>> Lot.nebula
ECHO Help;>> Lot.nebula
ECHO Isactive YesNo;>> Lot.nebula
ECHO Lotctl;>> Lot.nebula
ECHO !ID;>> Lot.nebula
ECHO Product;>> Lot.nebula
ECHO !Name;>> Lot.nebula
ECHO Updated Timestamp;>> Lot.nebula
ECHO Updatedby Date;>> Lot.nebula
ECHO };>> Lot.nebula 
 ECHO @Package("Material Management") type  Lotctl{ >> Lotctl.nebula
ECHO Org;>> Lotctl.nebula
ECHO Created Timestamp;>> Lotctl.nebula
ECHO Createdby Date;>> Lotctl.nebula
ECHO Currentnext Number;>> Lotctl.nebula
ECHO Description;>> Lotctl.nebula
ECHO Incrementno SeqNo;>> Lotctl.nebula
ECHO Isactive YesNo;>> Lotctl.nebula
ECHO !ID;>> Lotctl.nebula
ECHO !Name;>> Lotctl.nebula
ECHO Prefix String;>> Lotctl.nebula
ECHO Startno SeqNo;>> Lotctl.nebula
ECHO Suffix String;>> Lotctl.nebula
ECHO Updated Timestamp;>> Lotctl.nebula
ECHO Updatedby Date;>> Lotctl.nebula
ECHO };>> Lotctl.nebula 
 ECHO @Package("Material Management") type  Lotctlexclude{ >> Lotctlexclude.nebula
ECHO Org;>> Lotctlexclude.nebula
ECHO Table;>> Lotctlexclude.nebula
ECHO Created Timestamp;>> Lotctlexclude.nebula
ECHO Createdby Date;>> Lotctlexclude.nebula
ECHO Isactive YesNo;>> Lotctlexclude.nebula
ECHO Issotrx YesNo;>> Lotctlexclude.nebula
ECHO !ID;>> Lotctlexclude.nebula
ECHO Lotctl;>> Lotctlexclude.nebula
ECHO Updated Timestamp;>> Lotctlexclude.nebula
ECHO Updatedby Date;>> Lotctlexclude.nebula
ECHO };>> Lotctlexclude.nebula 
 ECHO @Package("Material Management") type  Matchinv{ >> Matchinv.nebula
ECHO Orgtrx Org;>> Matchinv.nebula
ECHO Org;>> Matchinv.nebula
ECHO Activity;>> Matchinv.nebula
ECHO Invoiceline;>> Matchinv.nebula
ECHO Created Timestamp;>> Matchinv.nebula
ECHO Createdby Date;>> Matchinv.nebula
ECHO Dateacct Date;>> Matchinv.nebula
ECHO Datetrx Date;>> Matchinv.nebula
ECHO Description;>> Matchinv.nebula
ECHO Documentno SeqNo;>> Matchinv.nebula
ECHO Isactive YesNo;>> Matchinv.nebula
ECHO Attributesetinstance;>> Matchinv.nebula
ECHO Inoutline;>> Matchinv.nebula
ECHO !ID;>> Matchinv.nebula
ECHO Product;>> Matchinv.nebula
ECHO Posted YesNo;>> Matchinv.nebula
ECHO Processed YesNo;>> Matchinv.nebula
ECHO Processing YesNo;>> Matchinv.nebula
ECHO Qty Quantity;>> Matchinv.nebula
ECHO Updated Timestamp;>> Matchinv.nebula
ECHO Updatedby Date;>> Matchinv.nebula
ECHO };>> Matchinv.nebula 
 ECHO @Package("Material Management") type  Matchpo{ >> Matchpo.nebula
ECHO Orgtrx Org;>> Matchpo.nebula
ECHO Org;>> Matchpo.nebula
ECHO Activity;>> Matchpo.nebula
ECHO Invoiceline;>> Matchpo.nebula
ECHO Orderline;>> Matchpo.nebula
ECHO Created Timestamp;>> Matchpo.nebula
ECHO Createdby Date;>> Matchpo.nebula
ECHO Dateacct Date;>> Matchpo.nebula
ECHO Datetrx Date;>> Matchpo.nebula
ECHO Description;>> Matchpo.nebula
ECHO Documentno SeqNo;>> Matchpo.nebula
ECHO Isactive YesNo;>> Matchpo.nebula
ECHO Isapproved YesNo;>> Matchpo.nebula
ECHO Attributesetinstance;>> Matchpo.nebula
ECHO Inoutline;>> Matchpo.nebula
ECHO !ID;>> Matchpo.nebula
ECHO Product;>> Matchpo.nebula
ECHO Posted YesNo;>> Matchpo.nebula
ECHO Pricematchdifference Price;>> Matchpo.nebula
ECHO Processed YesNo;>> Matchpo.nebula
ECHO Processing YesNo;>> Matchpo.nebula
ECHO Qty Quantity;>> Matchpo.nebula
ECHO Updated Timestamp;>> Matchpo.nebula
ECHO Updatedby Date;>> Matchpo.nebula
ECHO };>> Matchpo.nebula 
 ECHO @Package("Material Management") type  Mmrule{ >> Mmrule.nebula
ECHO Org;>> Mmrule.nebula
ECHO Created Timestamp;>> Mmrule.nebula
ECHO Createdby Date;>> Mmrule.nebula
ECHO Description;>> Mmrule.nebula
ECHO Isactive YesNo;>> Mmrule.nebula
ECHO Ismaintainuomintegrity YesNo;>> Mmrule.nebula
ECHO Mmtype Attr;>> Mmrule.nebula
ECHO !ID;>> Mmrule.nebula
ECHO Warehouse;>> Mmrule.nebula
ECHO Zone;>> Mmrule.nebula
ECHO !Name;>> Mmrule.nebula
ECHO Rule String;>> Mmrule.nebula
ECHO Ruleclass Name;>> Mmrule.nebula
ECHO Updated Timestamp;>> Mmrule.nebula
ECHO Updatedby Date;>> Mmrule.nebula
ECHO };>> Mmrule.nebula 
 ECHO @Package("Material Management") type  Mmstrategy{ >> Mmstrategy.nebula
ECHO Org;>> Mmstrategy.nebula
ECHO Created Timestamp;>> Mmstrategy.nebula
ECHO Createdby Date;>> Mmstrategy.nebula
ECHO Description;>> Mmstrategy.nebula
ECHO Isactive YesNo;>> Mmstrategy.nebula
ECHO Isallowsplit YesNo;>> Mmstrategy.nebula
ECHO Mmtype Attr;>> Mmstrategy.nebula
ECHO !ID;>> Mmstrategy.nebula
ECHO Warehouse;>> Mmstrategy.nebula
ECHO !Name;>> Mmstrategy.nebula
ECHO Updated Timestamp;>> Mmstrategy.nebula
ECHO Updatedby Date;>> Mmstrategy.nebula
ECHO };>> Mmstrategy.nebula 
 ECHO @Package("Material Management") type  Mmstrategyline{ >> Mmstrategyline.nebula
ECHO Org;>> Mmstrategyline.nebula
ECHO Created Timestamp;>> Mmstrategyline.nebula
ECHO Createdby Date;>> Mmstrategyline.nebula
ECHO Isactive YesNo;>> Mmstrategyline.nebula
ECHO Mmrule;>> Mmstrategyline.nebula
ECHO !ID;>> Mmstrategyline.nebula
ECHO Mmstrategy;>> Mmstrategyline.nebula
ECHO SeqNo;>> Mmstrategyline.nebula
ECHO Updated Timestamp;>> Mmstrategyline.nebula
ECHO Updatedby Date;>> Mmstrategyline.nebula
ECHO };>> Mmstrategyline.nebula 
 ECHO @Package("Material Management") type  Mmstrategyset{ >> Mmstrategyset.nebula
ECHO Org;>> Mmstrategyset.nebula
ECHO Created Timestamp;>> Mmstrategyset.nebula
ECHO Createdby Date;>> Mmstrategyset.nebula
ECHO Description;>> Mmstrategyset.nebula
ECHO Isactive YesNo;>> Mmstrategyset.nebula
ECHO Isevaluateallstrategies YesNo;>> Mmstrategyset.nebula
ECHO Mmtype Attr;>> Mmstrategyset.nebula
ECHO Locator;>> Mmstrategyset.nebula
ECHO !ID;>> Mmstrategyset.nebula
ECHO Warehouse;>> Mmstrategyset.nebula
ECHO !Name;>> Mmstrategyset.nebula
ECHO Updated Timestamp;>> Mmstrategyset.nebula
ECHO Updatedby Date;>> Mmstrategyset.nebula
ECHO };>> Mmstrategyset.nebula 
 ECHO @Package("Material Management") type  Mmstrategysetline{ >> Mmstrategysetline.nebula
ECHO Org;>> Mmstrategysetline.nebula
ECHO Bp_Group;>> Mmstrategysetline.nebula
ECHO Bpartner;>> Mmstrategysetline.nebula
ECHO Created Timestamp;>> Mmstrategysetline.nebula
ECHO Createdby Date;>> Mmstrategysetline.nebula
ECHO Isactive YesNo;>> Mmstrategysetline.nebula
ECHO Locator;>> Mmstrategysetline.nebula
ECHO !ID;>> Mmstrategysetline.nebula
ECHO Mmstrategyset;>> Mmstrategysetline.nebula
ECHO Mmstrategy;>> Mmstrategysetline.nebula
ECHO Product_Category;>> Mmstrategysetline.nebula
ECHO Product;>> Mmstrategysetline.nebula
ECHO Zone;>> Mmstrategysetline.nebula
ECHO SeqNo;>> Mmstrategysetline.nebula
ECHO Updated Timestamp;>> Mmstrategysetline.nebula
ECHO Updatedby Date;>> Mmstrategysetline.nebula
ECHO };>> Mmstrategysetline.nebula 
 ECHO @Package("Material Management") type  Movement{ >> Movement.nebula
ECHO Orgtrx Org;>> Movement.nebula
ECHO Org;>> Movement.nebula
ECHO Approvalamt Amount;>> Movement.nebula
ECHO Activity;>> Movement.nebula
ECHO Campaign;>> Movement.nebula
ECHO Doctype;>> Movement.nebula
ECHO Project;>> Movement.nebula
ECHO Created Timestamp;>> Movement.nebula
ECHO Createdby Date;>> Movement.nebula
ECHO Datereceived Date;>> Movement.nebula
ECHO Description;>> Movement.nebula
ECHO Docaction Attr;>> Movement.nebula
ECHO Docstatus Attr;>> Movement.nebula
ECHO Documentno SeqNo;>> Movement.nebula
ECHO Isactive YesNo;>> Movement.nebula
ECHO Isapproved YesNo;>> Movement.nebula
ECHO Isintransit YesNo;>> Movement.nebula
ECHO !ID;>> Movement.nebula
ECHO Movementdate Date;>> Movement.nebula
ECHO Posted YesNo;>> Movement.nebula
ECHO Processed YesNo;>> Movement.nebula
ECHO Processing YesNo;>> Movement.nebula
ECHO Updated Timestamp;>> Movement.nebula
ECHO Updatedby Date;>> Movement.nebula
ECHO User1 Elementvalue;>> Movement.nebula
ECHO User2 Elementvalue;>> Movement.nebula
ECHO };>> Movement.nebula 
 ECHO @Package("Material Management") type  Movementconfirm{ >> Movementconfirm.nebula
ECHO Org;>> Movementconfirm.nebula
ECHO Approvalamt Amount;>> Movementconfirm.nebula
ECHO Created Timestamp;>> Movementconfirm.nebula
ECHO Createdby Date;>> Movementconfirm.nebula
ECHO Description;>> Movementconfirm.nebula
ECHO Docaction Attr;>> Movementconfirm.nebula
ECHO Docstatus Attr;>> Movementconfirm.nebula
ECHO Documentno SeqNo;>> Movementconfirm.nebula
ECHO Isactive YesNo;>> Movementconfirm.nebula
ECHO Isapproved YesNo;>> Movementconfirm.nebula
ECHO Inventory;>> Movementconfirm.nebula
ECHO !ID;>> Movementconfirm.nebula
ECHO Movement;>> Movementconfirm.nebula
ECHO Processed YesNo;>> Movementconfirm.nebula
ECHO Processing YesNo;>> Movementconfirm.nebula
ECHO Updated Timestamp;>> Movementconfirm.nebula
ECHO Updatedby Date;>> Movementconfirm.nebula
ECHO };>> Movementconfirm.nebula 
 ECHO @Package("Material Management") type  Movementline{ >> Movementline.nebula
ECHO Orgtrx Org;>> Movementline.nebula
ECHO Org;>> Movementline.nebula
ECHO Activity;>> Movementline.nebula
ECHO Confirmedqty Quantity;>> Movementline.nebula
ECHO Created Timestamp;>> Movementline.nebula
ECHO Createdby Date;>> Movementline.nebula
ECHO Description;>> Movementline.nebula
ECHO Isactive YesNo;>> Movementline.nebula
ECHO Line Number;>> Movementline.nebula
ECHO Attributesetinstanceto Attributesetinstance;>> Movementline.nebula
ECHO Attributesetinstance;>> Movementline.nebula
ECHO Locatorto Locator;>> Movementline.nebula
ECHO Locator;>> Movementline.nebula
ECHO !ID;>> Movementline.nebula
ECHO Movement;>> Movementline.nebula
ECHO Product;>> Movementline.nebula
ECHO Movementqty Quantity;>> Movementline.nebula
ECHO Processed YesNo;>> Movementline.nebula
ECHO Scrappedqty Quantity;>> Movementline.nebula
ECHO Targetqty Quantity;>> Movementline.nebula
ECHO Updated Timestamp;>> Movementline.nebula
ECHO Updatedby Date;>> Movementline.nebula
ECHO };>> Movementline.nebula 
 ECHO @Package("Material Management") type  Movementlineconfirm{ >> Movementlineconfirm.nebula
ECHO Org;>> Movementlineconfirm.nebula
ECHO Confirmedqty Quantity;>> Movementlineconfirm.nebula
ECHO Created Timestamp;>> Movementlineconfirm.nebula
ECHO Createdby Date;>> Movementlineconfirm.nebula
ECHO Description;>> Movementlineconfirm.nebula
ECHO Differenceqty Quantity;>> Movementlineconfirm.nebula
ECHO Isactive YesNo;>> Movementlineconfirm.nebula
ECHO Inventoryline;>> Movementlineconfirm.nebula
ECHO Movementconfirm;>> Movementlineconfirm.nebula
ECHO !ID;>> Movementlineconfirm.nebula
ECHO Movementline;>> Movementlineconfirm.nebula
ECHO Processed YesNo;>> Movementlineconfirm.nebula
ECHO Scrappedqty Quantity;>> Movementlineconfirm.nebula
ECHO Targetqty Quantity;>> Movementlineconfirm.nebula
ECHO Updated Timestamp;>> Movementlineconfirm.nebula
ECHO Updatedby Date;>> Movementlineconfirm.nebula
ECHO };>> Movementlineconfirm.nebula 
 ECHO @Package("Material Management") type  Movementlinema{ >> Movementlinema.nebula
ECHO Org;>> Movementlinema.nebula
ECHO Created Timestamp;>> Movementlinema.nebula
ECHO Createdby Date;>> Movementlinema.nebula
ECHO Isactive YesNo;>> Movementlinema.nebula
ECHO !Attributesetinstance;>> Movementlinema.nebula
ECHO !Movementline;>> Movementlinema.nebula
ECHO Movementqty Quantity;>> Movementlinema.nebula
ECHO Updated Timestamp;>> Movementlinema.nebula
ECHO Updatedby Date;>> Movementlinema.nebula
ECHO };>> Movementlinema.nebula 
 ECHO @Package("Material Management") type  Operationresource{ >> Operationresource.nebula
ECHO Org;>> Operationresource.nebula
ECHO Asset;>> Operationresource.nebula
ECHO Job;>> Operationresource.nebula
ECHO Created Timestamp;>> Operationresource.nebula
ECHO Createdby Date;>> Operationresource.nebula
ECHO Description;>> Operationresource.nebula
ECHO Help;>> Operationresource.nebula
ECHO Isactive YesNo;>> Operationresource.nebula
ECHO !ID;>> Operationresource.nebula
ECHO Productoperation;>> Operationresource.nebula
ECHO !Name;>> Operationresource.nebula
ECHO Setuptime Long;>> Operationresource.nebula
ECHO Teardowntime Long;>> Operationresource.nebula
ECHO Unitruntime Long;>> Operationresource.nebula
ECHO Updated Timestamp;>> Operationresource.nebula
ECHO Updatedby Date;>> Operationresource.nebula
ECHO };>> Operationresource.nebula 
 ECHO @Package("Material Management") type  Package{ >> Package.nebula
ECHO Org;>> Package.nebula
ECHO Created Timestamp;>> Package.nebula
ECHO Createdby Date;>> Package.nebula
ECHO Datereceived Date;>> Package.nebula
ECHO Description;>> Package.nebula
ECHO Documentno SeqNo;>> Package.nebula
ECHO Isactive YesNo;>> Package.nebula
ECHO Inout;>> Package.nebula
ECHO !ID;>> Package.nebula
ECHO Shipper;>> Package.nebula
ECHO Processed YesNo;>> Package.nebula
ECHO Receivedinfo String;>> Package.nebula
ECHO Shipdate Date;>> Package.nebula
ECHO Trackinginfo String;>> Package.nebula
ECHO Updated Timestamp;>> Package.nebula
ECHO Updatedby Date;>> Package.nebula
ECHO };>> Package.nebula 
 ECHO @Package("Material Management") type  Packageline{ >> Packageline.nebula
ECHO Org;>> Packageline.nebula
ECHO Created Timestamp;>> Packageline.nebula
ECHO Createdby Date;>> Packageline.nebula
ECHO Description;>> Packageline.nebula
ECHO Isactive YesNo;>> Packageline.nebula
ECHO Inoutline;>> Packageline.nebula
ECHO !ID;>> Packageline.nebula
ECHO Package;>> Packageline.nebula
ECHO Qty Quantity;>> Packageline.nebula
ECHO Updated Timestamp;>> Packageline.nebula
ECHO Updatedby Date;>> Packageline.nebula
ECHO };>> Packageline.nebula 
 ECHO @Package("Material Management") type  Perpetualinv{ >> Perpetualinv.nebula
ECHO Org;>> Perpetualinv.nebula
ECHO Counthighmovement YesNo;>> Perpetualinv.nebula
ECHO Created Timestamp;>> Perpetualinv.nebula
ECHO Createdby Date;>> Perpetualinv.nebula
ECHO Datelastrun Date;>> Perpetualinv.nebula
ECHO Datenextrun Date;>> Perpetualinv.nebula
ECHO Description;>> Perpetualinv.nebula
ECHO Isactive YesNo;>> Perpetualinv.nebula
ECHO !ID;>> Perpetualinv.nebula
ECHO Product_Category;>> Perpetualinv.nebula
ECHO Warehouse;>> Perpetualinv.nebula
ECHO !Name;>> Perpetualinv.nebula
ECHO Noinventorycount Count;>> Perpetualinv.nebula
ECHO Noproductcount Count;>> Perpetualinv.nebula
ECHO Numberofruns Number;>> Perpetualinv.nebula
ECHO Processing YesNo;>> Perpetualinv.nebula
ECHO Updated Timestamp;>> Perpetualinv.nebula
ECHO Updatedby Date;>> Perpetualinv.nebula
ECHO };>> Perpetualinv.nebula 
 ECHO @Package("Material Management") type  Pricelist{ >> Pricelist.nebula
ECHO Org;>> Pricelist.nebula
ECHO Basepricelist Pricelist;>> Pricelist.nebula
ECHO Currency;>> Pricelist.nebula
ECHO Created Timestamp;>> Pricelist.nebula
ECHO Createdby Date;>> Pricelist.nebula
ECHO Description;>> Pricelist.nebula
ECHO Enforcepricelimit YesNo;>> Pricelist.nebula
ECHO Isactive YesNo;>> Pricelist.nebula
ECHO Isdefault YesNo;>> Pricelist.nebula
ECHO Issopricelist YesNo;>> Pricelist.nebula
ECHO Istaxincluded YesNo;>> Pricelist.nebula
ECHO !ID;>> Pricelist.nebula
ECHO !Name;>> Pricelist.nebula
ECHO Priceprecision Precision;>> Pricelist.nebula
ECHO Updated Timestamp;>> Pricelist.nebula
ECHO Updatedby Date;>> Pricelist.nebula
ECHO };>> Pricelist.nebula 
 ECHO @Package("Material Management") type  Pricelist_Version{ >> Pricelist_Version.nebula
ECHO Org;>> Pricelist_Version.nebula
ECHO Created Timestamp;>> Pricelist_Version.nebula
ECHO Createdby Date;>> Pricelist_Version.nebula
ECHO Description;>> Pricelist_Version.nebula
ECHO Isactive YesNo;>> Pricelist_Version.nebula
ECHO Discountschema;>> Pricelist_Version.nebula
ECHO Pricelist;>> Pricelist_Version.nebula
ECHO !ID;>> Pricelist_Version.nebula
ECHO Pricelist_Version_base Pricelist_Version;>> Pricelist_Version.nebula
ECHO !Name;>> Pricelist_Version.nebula
ECHO Proccreate YesNo;>> Pricelist_Version.nebula
ECHO Updated Timestamp;>> Pricelist_Version.nebula
ECHO Updatedby Date;>> Pricelist_Version.nebula
ECHO Validfrom Date;>> Pricelist_Version.nebula
ECHO };>> Pricelist_Version.nebula 
 ECHO @Package("Material Management") type  Product{ >> Product.nebula
ECHO Org;>> Product.nebula
ECHO Subscriptiontype;>> Product.nebula
ECHO Taxcategory;>> Product.nebula
ECHO Uom;>> Product.nebula
ECHO Classification Attr;>> Product.nebula
ECHO Created Timestamp;>> Product.nebula
ECHO Createdby Date;>> Product.nebula
ECHO Description;>> Product.nebula
ECHO Descriptionurl URL;>> Product.nebula
ECHO Discontinued YesNo;>> Product.nebula
ECHO Discontinuedby Date;>> Product.nebula
ECHO Documentnote Note;>> Product.nebula
ECHO Downloadurl URL;>> Product.nebula
ECHO Guaranteedays Count;>> Product.nebula
ECHO Guaranteedaysmin Count;>> Product.nebula
ECHO Help;>> Product.nebula
ECHO Imageurl URL;>> Product.nebula
ECHO Isactive YesNo;>> Product.nebula
ECHO Isbom YesNo;>> Product.nebula
ECHO Isdropship YesNo;>> Product.nebula
ECHO Isexcludeautodelivery YesNo;>> Product.nebula
ECHO Isinvoiceprintdetails YesNo;>> Product.nebula
ECHO Ispicklistprintdetails YesNo;>> Product.nebula
ECHO Ispurchased YesNo;>> Product.nebula
ECHO Ispurchasedtoorder YesNo;>> Product.nebula
ECHO Isselfservice YesNo;>> Product.nebula
ECHO Issold YesNo;>> Product.nebula
ECHO Isstocked YesNo;>> Product.nebula
ECHO Issummary YesNo;>> Product.nebula
ECHO Isverified YesNo;>> Product.nebula
ECHO Iswebstorefeatured YesNo;>> Product.nebula
ECHO Licenseinfo String;>> Product.nebula
ECHO Attributesetinstance;>> Product.nebula
ECHO Attributeset;>> Product.nebula
ECHO Freightcategory;>> Product.nebula
ECHO Locator;>> Product.nebula
ECHO Product_Category;>> Product.nebula
ECHO !ID;>> Product.nebula
ECHO !Name;>> Product.nebula
ECHO Processing YesNo;>> Product.nebula
ECHO Producttype YesNo;>> Product.nebula
ECHO Mailtext;>> Product.nebula
ECHO Source;>> Product.nebula
ECHO Sku String;>> Product.nebula
ECHO Expensetype;>> Product.nebula
ECHO Resource;>> Product.nebula
ECHO Salesrep User;>> Product.nebula
ECHO Shelfdepth Depth;>> Product.nebula
ECHO Shelfheight Height;>> Product.nebula
ECHO Shelfwidth Width;>> Product.nebula
ECHO Supportunits Number;>> Product.nebula
ECHO Trialphasedays Count;>> Product.nebula
ECHO UPC;>> Product.nebula
ECHO Unitsperpallet Number;>> Product.nebula
ECHO Updated Timestamp;>> Product.nebula
ECHO Updatedby Date;>> Product.nebula
ECHO Value String;>> Product.nebula
ECHO Versionno Code;>> Product.nebula
ECHO Volume Long;>> Product.nebula
ECHO Weight;>> Product.nebula
ECHO };>> Product.nebula 
 ECHO @Package("Material Management") type  Product_Acct{ >> Product_Acct.nebula
ECHO Org;>> Product_Acct.nebula
ECHO !Acctschema;>> Product_Acct.nebula
ECHO Created Timestamp;>> Product_Acct.nebula
ECHO Createdby Date;>> Product_Acct.nebula
ECHO Isactive YesNo;>> Product_Acct.nebula
ECHO !Product;>> Product_Acct.nebula
ECHO P_Asset_acct Account;>> Product_Acct.nebula
ECHO P_Cogs_acct Account;>> Product_Acct.nebula
ECHO P_Costadjustment_acct Account;>> Product_Acct.nebula
ECHO P_Expense_acct Account;>> Product_Acct.nebula
ECHO P_Inventoryclearing_acct Account;>> Product_Acct.nebula
ECHO P_Invoicepricevariance_acct Account;>> Product_Acct.nebula
ECHO P_Purchasepricevariance_acct Account;>> Product_Acct.nebula
ECHO P_Revenue_acct Account;>> Product_Acct.nebula
ECHO P_Tradediscountgrant_acct Account;>> Product_Acct.nebula
ECHO P_Tradediscountrec_acct Account;>> Product_Acct.nebula
ECHO Updated Timestamp;>> Product_Acct.nebula
ECHO Updatedby Date;>> Product_Acct.nebula
ECHO };>> Product_Acct.nebula 
 ECHO @Package("Material Management") type  Product_Bom{ >> Product_Bom.nebula
ECHO Org;>> Product_Bom.nebula
ECHO Bomqty Quantity;>> Product_Bom.nebula
ECHO Bomtype YesNo;>> Product_Bom.nebula
ECHO Created Timestamp;>> Product_Bom.nebula
ECHO Createdby Date;>> Product_Bom.nebula
ECHO Description;>> Product_Bom.nebula
ECHO Isactive YesNo;>> Product_Bom.nebula
ECHO Line Number;>> Product_Bom.nebula
ECHO Productbom Product;>> Product_Bom.nebula
ECHO !ID;>> Product_Bom.nebula
ECHO Product;>> Product_Bom.nebula
ECHO Updated Timestamp;>> Product_Bom.nebula
ECHO Updatedby Date;>> Product_Bom.nebula
ECHO };>> Product_Bom.nebula 
 ECHO @Package("Material Management") type  Product_Category{ >> Product_Category.nebula
ECHO Org;>> Product_Category.nebula
ECHO Printcolor;>> Product_Category.nebula
ECHO Asset_Group;>> Product_Category.nebula
ECHO Created Timestamp;>> Product_Category.nebula
ECHO Createdby Date;>> Product_Category.nebula
ECHO Description;>> Product_Category.nebula
ECHO Isactive YesNo;>> Product_Category.nebula
ECHO Isdefault YesNo;>> Product_Category.nebula
ECHO Ispurchasedtoorder YesNo;>> Product_Category.nebula
ECHO Isselfservice YesNo;>> Product_Category.nebula
ECHO Mmpolicy YesNo;>> Product_Category.nebula
ECHO !ID;>> Product_Category.nebula
ECHO !Name;>> Product_Category.nebula
ECHO Plannedmargin Number;>> Product_Category.nebula
ECHO Updated Timestamp;>> Product_Category.nebula
ECHO Updatedby Date;>> Product_Category.nebula
ECHO Value String;>> Product_Category.nebula
ECHO };>> Product_Category.nebula 
 ECHO @Package("Material Management") type  Product_Category_acct{ >> Product_Category_acct.nebula
ECHO Org;>> Product_Category_acct.nebula
ECHO !Acctschema;>> Product_Category_acct.nebula
ECHO Costinglevel YesNo;>> Product_Category_acct.nebula
ECHO Costingmethod YesNo;>> Product_Category_acct.nebula
ECHO Created Timestamp;>> Product_Category_acct.nebula
ECHO Createdby Date;>> Product_Category_acct.nebula
ECHO Isactive YesNo;>> Product_Category_acct.nebula
ECHO !Product_Category;>> Product_Category_acct.nebula
ECHO P_Asset_acct Account;>> Product_Category_acct.nebula
ECHO P_Cogs_acct Account;>> Product_Category_acct.nebula
ECHO P_Costadjustment_acct Account;>> Product_Category_acct.nebula
ECHO P_Expense_acct Account;>> Product_Category_acct.nebula
ECHO P_Inventoryclearing_acct Account;>> Product_Category_acct.nebula
ECHO P_Invoicepricevariance_acct Account;>> Product_Category_acct.nebula
ECHO P_Purchasepricevariance_acct Account;>> Product_Category_acct.nebula
ECHO P_Revenue_acct Account;>> Product_Category_acct.nebula
ECHO P_Tradediscountgrant_acct Account;>> Product_Category_acct.nebula
ECHO P_Tradediscountrec_acct Account;>> Product_Category_acct.nebula
ECHO Processing YesNo;>> Product_Category_acct.nebula
ECHO Updated Timestamp;>> Product_Category_acct.nebula
ECHO Updatedby Date;>> Product_Category_acct.nebula
ECHO };>> Product_Category_acct.nebula 
 ECHO @Package("Material Management") type  Product_Costing{ >> Product_Costing.nebula
ECHO Org;>> Product_Costing.nebula
ECHO !Acctschema;>> Product_Costing.nebula
ECHO Costaverage Number;>> Product_Costing.nebula
ECHO Costaveragecumamt Amount;>> Product_Costing.nebula
ECHO Costaveragecumqty Quantity;>> Product_Costing.nebula
ECHO Coststandard Number;>> Product_Costing.nebula
ECHO Coststandardcumamt Amount;>> Product_Costing.nebula
ECHO Coststandardcumqty Quantity;>> Product_Costing.nebula
ECHO Coststandardpoamt Amount;>> Product_Costing.nebula
ECHO Coststandardpoqty Quantity;>> Product_Costing.nebula
ECHO Created Timestamp;>> Product_Costing.nebula
ECHO Createdby Date;>> Product_Costing.nebula
ECHO Currentcostprice Price;>> Product_Costing.nebula
ECHO Futurecostprice Price;>> Product_Costing.nebula
ECHO Isactive YesNo;>> Product_Costing.nebula
ECHO !Product;>> Product_Costing.nebula
ECHO Pricelastinv Price;>> Product_Costing.nebula
ECHO Pricelastpo Price;>> Product_Costing.nebula
ECHO Totalinvamt Amount;>> Product_Costing.nebula
ECHO Totalinvqty Quantity;>> Product_Costing.nebula
ECHO Updated Timestamp;>> Product_Costing.nebula
ECHO Updatedby Date;>> Product_Costing.nebula
ECHO };>> Product_Costing.nebula 
 ECHO @Package("Material Management") type  Product_Po{ >> Product_Po.nebula
ECHO Org;>> Product_Po.nebula
ECHO !Bpartner;>> Product_Po.nebula
ECHO Currency;>> Product_Po.nebula
ECHO Uom;>> Product_Po.nebula
ECHO Costperorder Number;>> Product_Po.nebula
ECHO Created Timestamp;>> Product_Po.nebula
ECHO Createdby Date;>> Product_Po.nebula
ECHO Deliverytime_Actual Number;>> Product_Po.nebula
ECHO Deliverytime_Promised Long;>> Product_Po.nebula
ECHO Discontinued YesNo;>> Product_Po.nebula
ECHO Discontinuedby Date;>> Product_Po.nebula
ECHO Isactive YesNo;>> Product_Po.nebula
ECHO Iscurrentvendor YesNo;>> Product_Po.nebula
ECHO !Product;>> Product_Po.nebula
ECHO Manufacturer String;>> Product_Po.nebula
ECHO Order_Min Number;>> Product_Po.nebula
ECHO Order_Pack Number;>> Product_Po.nebula
ECHO Priceeffective Date;>> Product_Po.nebula
ECHO Pricelastinv Price;>> Product_Po.nebula
ECHO Pricelastpo Price;>> Product_Po.nebula
ECHO Pricelist Price;>> Product_Po.nebula
ECHO Pricepo Price;>> Product_Po.nebula
ECHO Qualityrating Number;>> Product_Po.nebula
ECHO Royaltyamt Amount;>> Product_Po.nebula
ECHO UPC;>> Product_Po.nebula
ECHO Updated Timestamp;>> Product_Po.nebula
ECHO Updatedby Date;>> Product_Po.nebula
ECHO Vendorcategory String;>> Product_Po.nebula
ECHO Vendorproductno Code;>> Product_Po.nebula
ECHO };>> Product_Po.nebula 
 ECHO @Package("Material Management") type  Product_Trl{ >> Product_Trl.nebula
ECHO !Ad_Language Language;>> Product_Trl.nebula
ECHO Org;>> Product_Trl.nebula
ECHO Created Timestamp;>> Product_Trl.nebula
ECHO Createdby Date;>> Product_Trl.nebula
ECHO Description;>> Product_Trl.nebula
ECHO Documentnote Note;>> Product_Trl.nebula
ECHO Isactive YesNo;>> Product_Trl.nebula
ECHO Istranslated YesNo;>> Product_Trl.nebula
ECHO !Product;>> Product_Trl.nebula
ECHO !Name;>> Product_Trl.nebula
ECHO Updated Timestamp;>> Product_Trl.nebula
ECHO Updatedby Date;>> Product_Trl.nebula
ECHO };>> Product_Trl.nebula 
 ECHO @Package("Material Management") type  Productdownload{ >> Productdownload.nebula
ECHO Org;>> Productdownload.nebula
ECHO Created Timestamp;>> Productdownload.nebula
ECHO Createdby Date;>> Productdownload.nebula
ECHO Downloadurl URL;>> Productdownload.nebula
ECHO Isactive YesNo;>> Productdownload.nebula
ECHO Isleaddownload YesNo;>> Productdownload.nebula
ECHO !ID;>> Productdownload.nebula
ECHO Product;>> Productdownload.nebula
ECHO !Name;>> Productdownload.nebula
ECHO Updated Timestamp;>> Productdownload.nebula
ECHO Updatedby Date;>> Productdownload.nebula
ECHO };>> Productdownload.nebula 
 ECHO @Package("Material Management") type  Production{ >> Production.nebula
ECHO Orgtrx Org;>> Production.nebula
ECHO Org;>> Production.nebula
ECHO Activity;>> Production.nebula
ECHO Campaign;>> Production.nebula
ECHO Project;>> Production.nebula
ECHO Created Timestamp;>> Production.nebula
ECHO Createdby Date;>> Production.nebula
ECHO Description;>> Production.nebula
ECHO Isactive YesNo;>> Production.nebula
ECHO Iscreated YesNo;>> Production.nebula
ECHO !ID;>> Production.nebula
ECHO Movementdate Date;>> Production.nebula
ECHO !Name;>> Production.nebula
ECHO Posted YesNo;>> Production.nebula
ECHO Processed YesNo;>> Production.nebula
ECHO Processing YesNo;>> Production.nebula
ECHO Updated Timestamp;>> Production.nebula
ECHO Updatedby Date;>> Production.nebula
ECHO User1 Elementvalue;>> Production.nebula
ECHO User2 Elementvalue;>> Production.nebula
ECHO };>> Production.nebula 
 ECHO @Package("Material Management") type  Productionline{ >> Productionline.nebula
ECHO Org;>> Productionline.nebula
ECHO Created Timestamp;>> Productionline.nebula
ECHO Createdby Date;>> Productionline.nebula
ECHO Description;>> Productionline.nebula
ECHO Isactive YesNo;>> Productionline.nebula
ECHO Line Number;>> Productionline.nebula
ECHO Attributesetinstance;>> Productionline.nebula
ECHO Locator;>> Productionline.nebula
ECHO Product;>> Productionline.nebula
ECHO !ID;>> Productionline.nebula
ECHO Productionplan;>> Productionline.nebula
ECHO Movementqty Quantity;>> Productionline.nebula
ECHO Processed YesNo;>> Productionline.nebula
ECHO Updated Timestamp;>> Productionline.nebula
ECHO Updatedby Date;>> Productionline.nebula
ECHO };>> Productionline.nebula 
 ECHO @Package("Material Management") type  Productionlinema{ >> Productionlinema.nebula
ECHO Org;>> Productionlinema.nebula
ECHO Created Timestamp;>> Productionlinema.nebula
ECHO Createdby Date;>> Productionlinema.nebula
ECHO Isactive YesNo;>> Productionlinema.nebula
ECHO !Attributesetinstance;>> Productionlinema.nebula
ECHO !Productionline;>> Productionlinema.nebula
ECHO Movementqty Quantity;>> Productionlinema.nebula
ECHO Updated Timestamp;>> Productionlinema.nebula
ECHO Updatedby Date;>> Productionlinema.nebula
ECHO };>> Productionlinema.nebula 
 ECHO @Package("Material Management") type  Productionplan{ >> Productionplan.nebula
ECHO Org;>> Productionplan.nebula
ECHO Created Timestamp;>> Productionplan.nebula
ECHO Createdby Date;>> Productionplan.nebula
ECHO Description;>> Productionplan.nebula
ECHO Isactive YesNo;>> Productionplan.nebula
ECHO Line Number;>> Productionplan.nebula
ECHO Locator;>> Productionplan.nebula
ECHO Product;>> Productionplan.nebula
ECHO !ID;>> Productionplan.nebula
ECHO Production;>> Productionplan.nebula
ECHO Processed YesNo;>> Productionplan.nebula
ECHO Productionqty Quantity;>> Productionplan.nebula
ECHO Updated Timestamp;>> Productionplan.nebula
ECHO Updatedby Date;>> Productionplan.nebula
ECHO };>> Productionplan.nebula 
 ECHO @Package("Material Management") type  Productlocator{ >> Productlocator.nebula
ECHO Org;>> Productlocator.nebula
ECHO Created Timestamp;>> Productlocator.nebula
ECHO Createdby Date;>> Productlocator.nebula
ECHO Description;>> Productlocator.nebula
ECHO Isactive YesNo;>> Productlocator.nebula
ECHO Locator;>> Productlocator.nebula
ECHO !ID;>> Productlocator.nebula
ECHO Product;>> Productlocator.nebula
ECHO Maxquantity Quantity;>> Productlocator.nebula
ECHO Minquantity Quantity;>> Productlocator.nebula
ECHO Updated Timestamp;>> Productlocator.nebula
ECHO Updatedby Date;>> Productlocator.nebula
ECHO Volumelimit Number;>> Productlocator.nebula
ECHO Weightlimit Weight;>> Productlocator.nebula
ECHO };>> Productlocator.nebula 
 ECHO @Package("Material Management") type  Productoperation{ >> Productoperation.nebula
ECHO Org;>> Productoperation.nebula
ECHO Created Timestamp;>> Productoperation.nebula
ECHO Createdby Date;>> Productoperation.nebula
ECHO Description;>> Productoperation.nebula
ECHO Help;>> Productoperation.nebula
ECHO Isactive YesNo;>> Productoperation.nebula
ECHO !ID;>> Productoperation.nebula
ECHO Product;>> Productoperation.nebula
ECHO !Name;>> Productoperation.nebula
ECHO Setuptime Long;>> Productoperation.nebula
ECHO Teardowntime Long;>> Productoperation.nebula
ECHO Unitruntime Long;>> Productoperation.nebula
ECHO Updated Timestamp;>> Productoperation.nebula
ECHO Updatedby Date;>> Productoperation.nebula
ECHO };>> Productoperation.nebula 
 ECHO @Package("Material Management") type  Productprice{ >> Productprice.nebula
ECHO Org;>> Productprice.nebula
ECHO Created Timestamp;>> Productprice.nebula
ECHO Createdby Date;>> Productprice.nebula
ECHO Isactive YesNo;>> Productprice.nebula
ECHO !Pricelist_Version;>> Productprice.nebula
ECHO !Product;>> Productprice.nebula
ECHO Pricelimit Price;>> Productprice.nebula
ECHO Pricelist Price;>> Productprice.nebula
ECHO Pricestd Price;>> Productprice.nebula
ECHO Updated Timestamp;>> Productprice.nebula
ECHO Updatedby Date;>> Productprice.nebula
ECHO };>> Productprice.nebula 
 ECHO @Package("Material Management") type  Relatedproduct{ >> Relatedproduct.nebula
ECHO Org;>> Relatedproduct.nebula
ECHO Created Timestamp;>> Relatedproduct.nebula
ECHO Createdby Date;>> Relatedproduct.nebula
ECHO Description;>> Relatedproduct.nebula
ECHO Isactive YesNo;>> Relatedproduct.nebula
ECHO !Product;>> Relatedproduct.nebula
ECHO !Name;>> Relatedproduct.nebula
ECHO Relatedproducttype YesNo;>> Relatedproduct.nebula
ECHO ID;>> Relatedproduct.nebula
ECHO Updated Timestamp;>> Relatedproduct.nebula
ECHO Updatedby Date;>> Relatedproduct.nebula
ECHO };>> Relatedproduct.nebula 
 ECHO @Package("Material Management") type  Replenish{ >> Replenish.nebula
ECHO Org;>> Replenish.nebula
ECHO Created Timestamp;>> Replenish.nebula
ECHO Createdby Date;>> Replenish.nebula
ECHO Isactive YesNo;>> Replenish.nebula
ECHO Level_Max Long;>> Replenish.nebula
ECHO Level_Min Long;>> Replenish.nebula
ECHO !Product;>> Replenish.nebula
ECHO Warehousesource Warehouse;>> Replenish.nebula
ECHO !Warehouse;>> Replenish.nebula
ECHO Replenishtype YesNo;>> Replenish.nebula
ECHO Updated Timestamp;>> Replenish.nebula
ECHO Updatedby Date;>> Replenish.nebula
ECHO };>> Replenish.nebula 
 ECHO @Package("Material Management") type  Requisition{ >> Requisition.nebula
ECHO Org;>> Requisition.nebula
ECHO User;>> Requisition.nebula
ECHO Doctype;>> Requisition.nebula
ECHO Created Timestamp;>> Requisition.nebula
ECHO Createdby Date;>> Requisition.nebula
ECHO Datedoc Date;>> Requisition.nebula
ECHO Daterequired Date;>> Requisition.nebula
ECHO Description;>> Requisition.nebula
ECHO Docaction Attr;>> Requisition.nebula
ECHO Docstatus Attr;>> Requisition.nebula
ECHO Documentno SeqNo;>> Requisition.nebula
ECHO Help;>> Requisition.nebula
ECHO Isactive YesNo;>> Requisition.nebula
ECHO Isapproved YesNo;>> Requisition.nebula
ECHO Pricelist;>> Requisition.nebula
ECHO !ID;>> Requisition.nebula
ECHO Warehouse;>> Requisition.nebula
ECHO Posted YesNo;>> Requisition.nebula
ECHO Priorityrule YesNo;>> Requisition.nebula
ECHO Processed YesNo;>> Requisition.nebula
ECHO Processing YesNo;>> Requisition.nebula
ECHO Totallines Number;>> Requisition.nebula
ECHO Updated Timestamp;>> Requisition.nebula
ECHO Updatedby Date;>> Requisition.nebula
ECHO };>> Requisition.nebula 
 ECHO @Package("Material Management") type  Requisitionline{ >> Requisitionline.nebula
ECHO Org;>> Requisitionline.nebula
ECHO Charge;>> Requisitionline.nebula
ECHO Orderline;>> Requisitionline.nebula
ECHO Created Timestamp;>> Requisitionline.nebula
ECHO Createdby Date;>> Requisitionline.nebula
ECHO Description;>> Requisitionline.nebula
ECHO Isactive YesNo;>> Requisitionline.nebula
ECHO Line Number;>> Requisitionline.nebula
ECHO Linenetamt Amount;>> Requisitionline.nebula
ECHO Attributesetinstance;>> Requisitionline.nebula
ECHO Product;>> Requisitionline.nebula
ECHO !ID;>> Requisitionline.nebula
ECHO Requisition;>> Requisitionline.nebula
ECHO Priceactual Price;>> Requisitionline.nebula
ECHO Qty Quantity;>> Requisitionline.nebula
ECHO Updated Timestamp;>> Requisitionline.nebula
ECHO Updatedby Date;>> Requisitionline.nebula
ECHO };>> Requisitionline.nebula 
 ECHO @Package("Material Management") type  Returnpolicy{ >> Returnpolicy.nebula
ECHO Org;>> Returnpolicy.nebula
ECHO Created Timestamp;>> Returnpolicy.nebula
ECHO Createdby Date;>> Returnpolicy.nebula
ECHO Description;>> Returnpolicy.nebula
ECHO Isactive YesNo;>> Returnpolicy.nebula
ECHO Isdefault YesNo;>> Returnpolicy.nebula
ECHO !ID;>> Returnpolicy.nebula
ECHO !Name;>> Returnpolicy.nebula
ECHO Timeframe Long;>> Returnpolicy.nebula
ECHO Updated Timestamp;>> Returnpolicy.nebula
ECHO Updatedby Date;>> Returnpolicy.nebula
ECHO };>> Returnpolicy.nebula 
 ECHO @Package("Material Management") type  Returnpolicyline{ >> Returnpolicyline.nebula
ECHO Org;>> Returnpolicyline.nebula
ECHO Created Timestamp;>> Returnpolicyline.nebula
ECHO Createdby Date;>> Returnpolicyline.nebula
ECHO Description;>> Returnpolicyline.nebula
ECHO Isactive YesNo;>> Returnpolicyline.nebula
ECHO Product_Category;>> Returnpolicyline.nebula
ECHO Product;>> Returnpolicyline.nebula
ECHO !ID;>> Returnpolicyline.nebula
ECHO Returnpolicy;>> Returnpolicyline.nebula
ECHO Timeframe Long;>> Returnpolicyline.nebula
ECHO Updated Timestamp;>> Returnpolicyline.nebula
ECHO Updatedby Date;>> Returnpolicyline.nebula
ECHO };>> Returnpolicyline.nebula 
 ECHO @Package("Material Management") type  Rmacategory{ >> Rmacategory.nebula
ECHO Org;>> Rmacategory.nebula
ECHO Created Timestamp;>> Rmacategory.nebula
ECHO Createdby Date;>> Rmacategory.nebula
ECHO Description;>> Rmacategory.nebula
ECHO Help;>> Rmacategory.nebula
ECHO Isactive YesNo;>> Rmacategory.nebula
ECHO !ID;>> Rmacategory.nebula
ECHO !Name;>> Rmacategory.nebula
ECHO Updated Timestamp;>> Rmacategory.nebula
ECHO Updatedby Date;>> Rmacategory.nebula
ECHO };>> Rmacategory.nebula 
 ECHO @Package("Material Management") type  Routing{ >> Routing.nebula
ECHO Org;>> Routing.nebula
ECHO Created Timestamp;>> Routing.nebula
ECHO Createdby Date;>> Routing.nebula
ECHO Description;>> Routing.nebula
ECHO Help;>> Routing.nebula
ECHO Isactive YesNo;>> Routing.nebula
ECHO Product;>> Routing.nebula
ECHO !ID;>> Routing.nebula
ECHO !Name;>> Routing.nebula
ECHO Processed YesNo;>> Routing.nebula
ECHO Processing YesNo;>> Routing.nebula
ECHO Routingtype Attr;>> Routing.nebula
ECHO Routinguse YesNo;>> Routing.nebula
ECHO Updated Timestamp;>> Routing.nebula
ECHO Updatedby Date;>> Routing.nebula
ECHO };>> Routing.nebula 
 ECHO @Package("Material Management") type  Routingoperation{ >> Routingoperation.nebula
ECHO Orgtrx Org;>> Routingoperation.nebula
ECHO Org;>> Routingoperation.nebula
ECHO Activity;>> Routingoperation.nebula
ECHO Bpartner;>> Routingoperation.nebula
ECHO Bpartner_Location;>> Routingoperation.nebula
ECHO Campaign;>> Routingoperation.nebula
ECHO Projectphase;>> Routingoperation.nebula
ECHO Projecttask;>> Routingoperation.nebula
ECHO Project;>> Routingoperation.nebula
ECHO Created Timestamp;>> Routingoperation.nebula
ECHO Createdby Date;>> Routingoperation.nebula
ECHO Description;>> Routingoperation.nebula
ECHO Isactive YesNo;>> Routingoperation.nebula
ECHO Ishazmat YesNo;>> Routingoperation.nebula
ECHO Isoptional YesNo;>> Routingoperation.nebula
ECHO Ispermitrequired YesNo;>> Routingoperation.nebula

ECHO !ID;>> Routingoperation.nebula
ECHO Routing;>> Routingoperation.nebula
ECHO !Name;>> Routingoperation.nebula
ECHO Processingtime Long;>> Routingoperation.nebula
ECHO SeqNo;>> Routingoperation.nebula
ECHO Setuptime Long;>> Routingoperation.nebula
ECHO Updated Timestamp;>> Routingoperation.nebula
ECHO Updatedby Date;>> Routingoperation.nebula
ECHO };>> Routingoperation.nebula 
 ECHO @Package("Material Management") type  Sernoctl{ >> Sernoctl.nebula
ECHO Org;>> Sernoctl.nebula
ECHO Created Timestamp;>> Sernoctl.nebula
ECHO Createdby Date;>> Sernoctl.nebula
ECHO Currentnext Number;>> Sernoctl.nebula
ECHO Description;>> Sernoctl.nebula
ECHO Incrementno SeqNo;>> Sernoctl.nebula
ECHO Isactive YesNo;>> Sernoctl.nebula
ECHO !ID;>> Sernoctl.nebula
ECHO !Name;>> Sernoctl.nebula
ECHO Prefix String;>> Sernoctl.nebula
ECHO Startno SeqNo;>> Sernoctl.nebula
ECHO Suffix String;>> Sernoctl.nebula
ECHO Updated Timestamp;>> Sernoctl.nebula
ECHO Updatedby Date;>> Sernoctl.nebula
ECHO };>> Sernoctl.nebula 
 ECHO @Package("Material Management") type  Sernoctlexclude{ >> Sernoctlexclude.nebula
ECHO Org;>> Sernoctlexclude.nebula
ECHO Table;>> Sernoctlexclude.nebula
ECHO Created Timestamp;>> Sernoctlexclude.nebula
ECHO Createdby Date;>> Sernoctlexclude.nebula
ECHO Isactive YesNo;>> Sernoctlexclude.nebula
ECHO Issotrx YesNo;>> Sernoctlexclude.nebula
ECHO !ID;>> Sernoctlexclude.nebula
ECHO Sernoctl;>> Sernoctlexclude.nebula
ECHO Updated Timestamp;>> Sernoctlexclude.nebula
ECHO Updatedby Date;>> Sernoctlexclude.nebula
ECHO };>> Sernoctlexclude.nebula 
 ECHO @Package("Material Management") type  Shipper{ >> Shipper.nebula
ECHO Org;>> Shipper.nebula
ECHO Bpartner;>> Shipper.nebula
ECHO Created Timestamp;>> Shipper.nebula
ECHO Createdby Date;>> Shipper.nebula
ECHO Description;>> Shipper.nebula
ECHO Isactive YesNo;>> Shipper.nebula
ECHO !ID;>> Shipper.nebula
ECHO !Name;>> Shipper.nebula
ECHO Trackingurl URL;>> Shipper.nebula
ECHO Updated Timestamp;>> Shipper.nebula
ECHO Updatedby Date;>> Shipper.nebula
ECHO };>> Shipper.nebula 
 ECHO @Package("Material Management") type  Storage{ >> Storage.nebula
ECHO Org;>> Storage.nebula
ECHO Created Timestamp;>> Storage.nebula
ECHO Createdby Date;>> Storage.nebula
ECHO Datelastinventory Date;>> Storage.nebula
ECHO Isactive YesNo;>> Storage.nebula
ECHO !Attributesetinstance;>> Storage.nebula
ECHO !Locator;>> Storage.nebula
ECHO !Product;>> Storage.nebula
ECHO Qtyallocated Quantity;>> Storage.nebula
ECHO Qtydedicated Quantity;>> Storage.nebula
ECHO Qtyexpected Quantity;>> Storage.nebula
ECHO Qtyonhand Quantity;>> Storage.nebula
ECHO Qtyordered Quantity;>> Storage.nebula
ECHO Qtyreserved Quantity;>> Storage.nebula
ECHO Updated Timestamp;>> Storage.nebula
ECHO Updatedby Date;>> Storage.nebula
ECHO };>> Storage.nebula 
 ECHO @Package("Material Management") type  Substitute{ >> Substitute.nebula
ECHO Org;>> Substitute.nebula
ECHO Created Timestamp;>> Substitute.nebula
ECHO Createdby Date;>> Substitute.nebula
ECHO Description;>> Substitute.nebula
ECHO Isactive YesNo;>> Substitute.nebula
ECHO !Product;>> Substitute.nebula
ECHO !Name;>> Substitute.nebula
ECHO ID;>> Substitute.nebula
ECHO Updated Timestamp;>> Substitute.nebula
ECHO Updatedby Date;>> Substitute.nebula
ECHO };>> Substitute.nebula 
 ECHO @Package("Material Management") type  Tasklist{ >> Tasklist.nebula
ECHO Org;>> Tasklist.nebula
ECHO User;>> Tasklist.nebula
ECHO Doctype;>> Tasklist.nebula
ECHO Created Timestamp;>> Tasklist.nebula
ECHO Createdby Date;>> Tasklist.nebula
ECHO Description;>> Tasklist.nebula
ECHO Documentno SeqNo;>> Tasklist.nebula
ECHO Isactive YesNo;>> Tasklist.nebula
ECHO !ID;>> Tasklist.nebula
ECHO Warehouse;>> Tasklist.nebula
ECHO Pickmethod YesNo;>> Tasklist.nebula
ECHO Updated Timestamp;>> Tasklist.nebula
ECHO Updatedby Date;>> Tasklist.nebula
ECHO };>> Tasklist.nebula 
 ECHO @Package("Material Management") type  Tasklistline{ >> Tasklistline.nebula
ECHO Org;>> Tasklistline.nebula
ECHO Created Timestamp;>> Tasklistline.nebula
ECHO Createdby Date;>> Tasklistline.nebula
ECHO Description;>> Tasklistline.nebula
ECHO Isactive YesNo;>> Tasklistline.nebula
ECHO Line Number;>> Tasklistline.nebula
ECHO !ID;>> Tasklistline.nebula
ECHO Tasklist;>> Tasklistline.nebula
ECHO Warehousetask;>> Tasklistline.nebula
ECHO Slotno SeqNo;>> Tasklistline.nebula
ECHO Stopno SeqNo;>> Tasklistline.nebula
ECHO Updated Timestamp;>> Tasklistline.nebula
ECHO Updatedby Date;>> Tasklistline.nebula
ECHO };>> Tasklistline.nebula 
 ECHO @Package("Material Management") type  Transaction{ >> Transaction.nebula
ECHO Org;>> Transaction.nebula
ECHO Projectissue;>> Transaction.nebula
ECHO Created Timestamp;>> Transaction.nebula
ECHO Createdby Date;>> Transaction.nebula
ECHO Isactive YesNo;>> Transaction.nebula
ECHO Attributesetinstance;>> Transaction.nebula
ECHO Inoutline;>> Transaction.nebula
ECHO Inventoryline;>> Transaction.nebula
ECHO Locator;>> Transaction.nebula
ECHO Movementline;>> Transaction.nebula
ECHO Product;>> Transaction.nebula
ECHO Productionline;>> Transaction.nebula
ECHO !ID;>> Transaction.nebula
ECHO Warehousetask;>> Transaction.nebula
ECHO Workordertransactionline;>> Transaction.nebula
ECHO Movementdate Date;>> Transaction.nebula
ECHO Movementqty Quantity;>> Transaction.nebula
ECHO Movementtype Attr;>> Transaction.nebula
ECHO Updated Timestamp;>> Transaction.nebula
ECHO Updatedby Date;>> Transaction.nebula
ECHO };>> Transaction.nebula 
 ECHO @Package("Material Management") type  Transactionallocation{ >> Transactionallocation.nebula
ECHO Org;>> Transactionallocation.nebula
ECHO !Allocationstrategytype YesNo;>> Transactionallocation.nebula
ECHO Created Timestamp;>> Transactionallocation.nebula
ECHO Createdby Date;>> Transactionallocation.nebula
ECHO Isactive YesNo;>> Transactionallocation.nebula
ECHO Isallocated YesNo;>> Transactionallocation.nebula
ECHO Ismanual YesNo;>> Transactionallocation.nebula
ECHO Attributesetinstance;>> Transactionallocation.nebula
ECHO Inoutline;>> Transactionallocation.nebula
ECHO Inventoryline;>> Transactionallocation.nebula
ECHO Product;>> Transactionallocation.nebula
ECHO Productionline;>> Transactionallocation.nebula
ECHO !Transaction;>> Transactionallocation.nebula
ECHO Out_M_inoutline Inoutline;>> Transactionallocation.nebula
ECHO Out_M_inventoryline Inventoryline;>> Transactionallocation.nebula
ECHO Out_M_productionline Productionline;>> Transactionallocation.nebula
ECHO Out_M_transaction Transaction;>> Transactionallocation.nebula
ECHO Qty Quantity;>> Transactionallocation.nebula
ECHO Updated Timestamp;>> Transactionallocation.nebula
ECHO Updatedby Date;>> Transactionallocation.nebula
ECHO };>> Transactionallocation.nebula 
 ECHO @Package("Material Management") type  Warehouse{ >> Warehouse.nebula
ECHO Org;>> Warehouse.nebula
ECHO Location;>> Warehouse.nebula
ECHO Created Timestamp;>> Warehouse.nebula
ECHO Createdby Date;>> Warehouse.nebula
ECHO Description;>> Warehouse.nebula
ECHO Isactive YesNo;>> Warehouse.nebula
ECHO Isdisallownegativeinv YesNo;>> Warehouse.nebula
ECHO Iswmsenabled YesNo;>> Warehouse.nebula
ECHO Rcvlocator Locator;>> Warehouse.nebula
ECHO Stglocator Locator;>> Warehouse.nebula
ECHO Warehousesource Warehouse;>> Warehouse.nebula
ECHO !ID;>> Warehouse.nebula
ECHO !Name;>> Warehouse.nebula
ECHO Replenishmentclass Name;>> Warehouse.nebula
ECHO Separator String;>> Warehouse.nebula
ECHO Updated Timestamp;>> Warehouse.nebula
ECHO Updatedby Date;>> Warehouse.nebula
ECHO Value String;>> Warehouse.nebula
ECHO };>> Warehouse.nebula 
 ECHO @Package("Material Management") type  Warehouse_Acct{ >> Warehouse_Acct.nebula
ECHO Org;>> Warehouse_Acct.nebula
ECHO !Acctschema;>> Warehouse_Acct.nebula
ECHO Created Timestamp;>> Warehouse_Acct.nebula
ECHO Createdby Date;>> Warehouse_Acct.nebula
ECHO Isactive YesNo;>> Warehouse_Acct.nebula
ECHO !Warehouse;>> Warehouse_Acct.nebula
ECHO Updated Timestamp;>> Warehouse_Acct.nebula
ECHO Updatedby Date;>> Warehouse_Acct.nebula
ECHO W_Differences_acct Account;>> Warehouse_Acct.nebula
ECHO W_Invactualadjust_acct Account;>> Warehouse_Acct.nebula
ECHO W_Inventory_acct Account;>> Warehouse_Acct.nebula
ECHO W_Revaluation_acct Account;>> Warehouse_Acct.nebula
ECHO };>> Warehouse_Acct.nebula 
 ECHO @Package("Material Management") type  Warehousetask{ >> Warehousetask.nebula
ECHO Org;>> Warehousetask.nebula
ECHO User;>> Warehousetask.nebula
ECHO Approvalamt Amount;>> Warehousetask.nebula
ECHO Doctype;>> Warehousetask.nebula
ECHO Orderline;>> Warehousetask.nebula
ECHO Uom;>> Warehousetask.nebula
ECHO Waveline;>> Warehousetask.nebula
ECHO Created Timestamp;>> Warehousetask.nebula
ECHO Createdby Date;>> Warehousetask.nebula
ECHO Description;>> Warehousetask.nebula
ECHO Docaction Attr;>> Warehousetask.nebula
ECHO Docstatus Attr;>> Warehousetask.nebula
ECHO Documentno SeqNo;>> Warehousetask.nebula
ECHO Isactive YesNo;>> Warehousetask.nebula
ECHO Isapproved YesNo;>> Warehousetask.nebula

ECHO Actuallocatorto Locator;>> Warehousetask.nebula
ECHO Actuallocator Locator;>> Warehousetask.nebula
ECHO Attributesetinstance;>> Warehousetask.nebula
ECHO Inoutline;>> Warehousetask.nebula
ECHO Locatorto Locator;>> Warehousetask.nebula
ECHO Locator;>> Warehousetask.nebula
ECHO Product;>> Warehousetask.nebula
ECHO Splitwarehousetask Warehousetask;>> Warehousetask.nebula
ECHO !ID;>> Warehousetask.nebula
ECHO Warehouse;>> Warehousetask.nebula
ECHO Movementdate Date;>> Warehousetask.nebula
ECHO Movementqty Quantity;>> Warehousetask.nebula
ECHO Processed YesNo;>> Warehousetask.nebula
ECHO Processing YesNo;>> Warehousetask.nebula
ECHO Qtydedicated Quantity;>> Warehousetask.nebula
ECHO Qtyentered Quantity;>> Warehousetask.nebula
ECHO Qtysuggested Quantity;>> Warehousetask.nebula
ECHO Splittask YesNo;>> Warehousetask.nebula
ECHO Targetqty Quantity;>> Warehousetask.nebula
ECHO Updated Timestamp;>> Warehousetask.nebula
ECHO Updatedby Date;>> Warehousetask.nebula
ECHO };>> Warehousetask.nebula 
 ECHO @Package("Material Management") type  Warehousetaskma{ >> Warehousetaskma.nebula
ECHO Org;>> Warehousetaskma.nebula
ECHO Created Timestamp;>> Warehousetaskma.nebula
ECHO Createdby Date;>> Warehousetaskma.nebula
ECHO Isactive YesNo;>> Warehousetaskma.nebula
ECHO !Attributesetinstance;>> Warehousetaskma.nebula
ECHO !Warehousetask;>> Warehousetaskma.nebula
ECHO Movementqty Quantity;>> Warehousetaskma.nebula
ECHO Qtydedicated Quantity;>> Warehousetaskma.nebula
ECHO Updated Timestamp;>> Warehousetaskma.nebula
ECHO Updatedby Date;>> Warehousetaskma.nebula
ECHO };>> Warehousetaskma.nebula 
 ECHO @Package("Material Management") type  Workorder{ >> Workorder.nebula
ECHO Orgtrx Org;>> Workorder.nebula
ECHO Org;>> Workorder.nebula
ECHO User;>> Workorder.nebula
ECHO Activity;>> Workorder.nebula
ECHO Bpartner;>> Workorder.nebula
ECHO Bpartner_Location;>> Workorder.nebula
ECHO Campaign;>> Workorder.nebula
ECHO Doctype;>> Workorder.nebula
ECHO Orderline;>> Workorder.nebula
ECHO Order;>> Workorder.nebula
ECHO Project;>> Workorder.nebula
ECHO Uom;>> Workorder.nebula
ECHO Copyfrom YesNo;>> Workorder.nebula
ECHO Created Timestamp;>> Workorder.nebula
ECHO Createdby Date;>> Workorder.nebula
ECHO Dateacct Date;>> Workorder.nebula
ECHO Dateactualfrom Date;>> Workorder.nebula
ECHO Dateactualto Date;>> Workorder.nebula
ECHO Dateschedulefrom Date;>> Workorder.nebula
ECHO Datescheduleto Date;>> Workorder.nebula
ECHO Description;>> Workorder.nebula
ECHO Docaction Attr;>> Workorder.nebula
ECHO Docstatus Attr;>> Workorder.nebula
ECHO Documentno SeqNo;>> Workorder.nebula
ECHO Help;>> Workorder.nebula
ECHO Isactive YesNo;>> Workorder.nebula
ECHO Isapproved YesNo;>> Workorder.nebula
ECHO Issotrx YesNo;>> Workorder.nebula
ECHO Attributesetinstance;>> Workorder.nebula
ECHO Bom;>> Workorder.nebula
ECHO Locator;>> Workorder.nebula
ECHO Product;>> Workorder.nebula

ECHO Warehouse;>> Workorder.nebula
ECHO Workorderclass;>> Workorder.nebula
ECHO !ID;>> Workorder.nebula
ECHO Posted YesNo;>> Workorder.nebula
ECHO Priorityrule YesNo;>> Workorder.nebula
ECHO Processed YesNo;>> Workorder.nebula
ECHO Processing YesNo;>> Workorder.nebula
ECHO Qtyassembled Quantity;>> Workorder.nebula
ECHO Qtyavailable Quantity;>> Workorder.nebula
ECHO Qtyentered Quantity;>> Workorder.nebula
ECHO Salesrep User;>> Workorder.nebula
ECHO Sendemail YesNo;>> Workorder.nebula
ECHO Updated Timestamp;>> Workorder.nebula
ECHO Updatedby Date;>> Workorder.nebula
ECHO User1 Elementvalue;>> Workorder.nebula
ECHO User2 Elementvalue;>> Workorder.nebula
ECHO Wosource YesNo;>> Workorder.nebula
ECHO Wotype YesNo;>> Workorder.nebula
ECHO };>> Workorder.nebula 
 ECHO @Package("Material Management") type  Workorderclass{ >> Workorderclass.nebula
ECHO Org;>> Workorderclass.nebula
ECHO Created Timestamp;>> Workorderclass.nebula
ECHO Createdby Date;>> Workorderclass.nebula
ECHO Description;>> Workorderclass.nebula
ECHO Isactive YesNo;>> Workorderclass.nebula
ECHO Isdefault YesNo;>> Workorderclass.nebula
ECHO !ID;>> Workorderclass.nebula
ECHO !Name;>> Workorderclass.nebula
ECHO Updated Timestamp;>> Workorderclass.nebula
ECHO Updatedby Date;>> Workorderclass.nebula
ECHO Wot_Doctype Doctype;>> Workorderclass.nebula
ECHO Wotype YesNo;>> Workorderclass.nebula
ECHO Wo_Doctype Doctype;>> Workorderclass.nebula
ECHO };>> Workorderclass.nebula 
 ECHO @Package("Material Management") type  Workorderclass_Acct{ >> Workorderclass_Acct.nebula
ECHO Org;>> Workorderclass_Acct.nebula
ECHO !Acctschema;>> Workorderclass_Acct.nebula
ECHO Created Timestamp;>> Workorderclass_Acct.nebula
ECHO Createdby Date;>> Workorderclass_Acct.nebula
ECHO Isactive YesNo;>> Workorderclass_Acct.nebula
ECHO !Workorderclass;>> Workorderclass_Acct.nebula
ECHO Updated Timestamp;>> Workorderclass_Acct.nebula
ECHO Updatedby Date;>> Workorderclass_Acct.nebula
ECHO Wo_Closeexpense_acct Account;>> Workorderclass_Acct.nebula
ECHO Wo_Material_acct Account;>> Workorderclass_Acct.nebula
ECHO };>> Workorderclass_Acct.nebula 
 ECHO @Package("Material Management") type  Workordercomponent{ >> Workordercomponent.nebula
ECHO Orgtrx Org;>> Workordercomponent.nebula
ECHO Org;>> Workordercomponent.nebula
ECHO Activity;>> Workordercomponent.nebula
ECHO Bpartner;>> Workordercomponent.nebula
ECHO Bpartner_Location;>> Workordercomponent.nebula
ECHO Campaign;>> Workordercomponent.nebula
ECHO Project;>> Workordercomponent.nebula
ECHO Uom;>> Workordercomponent.nebula
ECHO Created Timestamp;>> Workordercomponent.nebula
ECHO Createdby Date;>> Workordercomponent.nebula
ECHO Description;>> Workordercomponent.nebula
ECHO Isactive YesNo;>> Workordercomponent.nebula
ECHO Line Number;>> Workordercomponent.nebula
ECHO Attributesetinstance;>> Workordercomponent.nebula
ECHO Bom;>> Workordercomponent.nebula
ECHO Locator;>> Workordercomponent.nebula
ECHO Product;>> Workordercomponent.nebula
ECHO !ID;>> Workordercomponent.nebula
ECHO Workorderoperation;>> Workordercomponent.nebula
ECHO Workorder;>> Workordercomponent.nebula
ECHO Processed YesNo;>> Workordercomponent.nebula
ECHO Qtyavailable Quantity;>> Workordercomponent.nebula
ECHO Qtyrequired Quantity;>> Workordercomponent.nebula
ECHO Qtyspent Quantity;>> Workordercomponent.nebula
ECHO Supplytype YesNo;>> Workordercomponent.nebula
ECHO Updated Timestamp;>> Workordercomponent.nebula
ECHO Updatedby Date;>> Workordercomponent.nebula
ECHO User1 Elementvalue;>> Workordercomponent.nebula
ECHO User2 Elementvalue;>> Workordercomponent.nebula
ECHO };>> Workordercomponent.nebula 
 ECHO @Package("Material Management") type  Workorderoperation{ >> Workorderoperation.nebula
ECHO Orgtrx Org;>> Workorderoperation.nebula
ECHO Org;>> Workorderoperation.nebula
ECHO Activity;>> Workorderoperation.nebula
ECHO Bpartner;>> Workorderoperation.nebula
ECHO Bpartner_Location;>> Workorderoperation.nebula
ECHO Campaign;>> Workorderoperation.nebula
ECHO Projectphase;>> Workorderoperation.nebula
ECHO Projecttask;>> Workorderoperation.nebula
ECHO Project;>> Workorderoperation.nebula
ECHO Created Timestamp;>> Workorderoperation.nebula
ECHO Createdby Date;>> Workorderoperation.nebula
ECHO Dateprocessed Date;>> Workorderoperation.nebula
ECHO Description;>> Workorderoperation.nebula
ECHO Isactive YesNo;>> Workorderoperation.nebula
ECHO Isexecuted YesNo;>> Workorderoperation.nebula

ECHO Routingoperation;>> Workorderoperation.nebula
ECHO Routing;>> Workorderoperation.nebula
ECHO !ID;>> Workorderoperation.nebula
ECHO Workorder;>> Workorderoperation.nebula
ECHO !Name;>> Workorderoperation.nebula
ECHO Processed YesNo;>> Workorderoperation.nebula
ECHO Qtyassembled Quantity;>> Workorderoperation.nebula
ECHO Qtyrejected Quantity;>> Workorderoperation.nebula
ECHO Qtyscrapped Quantity;>> Workorderoperation.nebula
ECHO SeqNo;>> Workorderoperation.nebula
ECHO Updated Timestamp;>> Workorderoperation.nebula
ECHO Updatedby Date;>> Workorderoperation.nebula
ECHO };>> Workorderoperation.nebula 
 ECHO @Package("Material Management") type  Workordertransaction{ >> Workordertransaction.nebula
ECHO Orgtrx Org;>> Workordertransaction.nebula
ECHO Org;>> Workordertransaction.nebula
ECHO User;>> Workordertransaction.nebula
ECHO Activity;>> Workordertransaction.nebula
ECHO Bpartner;>> Workordertransaction.nebula
ECHO Bpartner_Location;>> Workordertransaction.nebula
ECHO Campaign;>> Workordertransaction.nebula
ECHO Doctype;>> Workordertransaction.nebula
ECHO Project;>> Workordertransaction.nebula
ECHO Uom;>> Workordertransaction.nebula
ECHO Created Timestamp;>> Workordertransaction.nebula
ECHO Createdby Date;>> Workordertransaction.nebula
ECHO Dateacct Date;>> Workordertransaction.nebula
ECHO Datetrx Date;>> Workordertransaction.nebula
ECHO Description;>> Workordertransaction.nebula
ECHO Docaction Attr;>> Workordertransaction.nebula
ECHO Docstatus Attr;>> Workordertransaction.nebula
ECHO Documentno SeqNo;>> Workordertransaction.nebula
ECHO Generatelines YesNo;>> Workordertransaction.nebula
ECHO Help;>> Workordertransaction.nebula
ECHO Isactive YesNo;>> Workordertransaction.nebula
ECHO Isapproved YesNo;>> Workordertransaction.nebula
ECHO Iscomplete YesNo;>> Workordertransaction.nebula
ECHO Istransferred YesNo;>> Workordertransaction.nebula
ECHO Locator;>> Workordertransaction.nebula
ECHO Product;>> Workordertransaction.nebula
ECHO !ID;>> Workordertransaction.nebula
ECHO Workorder;>> Workordertransaction.nebula
ECHO Operationfrom Workorderoperation;>> Workordertransaction.nebula
ECHO Operationto Workorderoperation;>> Workordertransaction.nebula
ECHO Parentworkordertxn Workordertransaction;>> Workordertransaction.nebula
ECHO Posted YesNo;>> Workordertransaction.nebula
ECHO Processed YesNo;>> Workordertransaction.nebula
ECHO Processing YesNo;>> Workordertransaction.nebula
ECHO Qtyentered Quantity;>> Workordertransaction.nebula
ECHO Updated Timestamp;>> Workordertransaction.nebula
ECHO Updatedby Date;>> Workordertransaction.nebula
ECHO User1 Elementvalue;>> Workordertransaction.nebula
ECHO User2 Elementvalue;>> Workordertransaction.nebula
ECHO Wocomplete YesNo;>> Workordertransaction.nebula
ECHO Wotxnsource YesNo;>> Workordertransaction.nebula
ECHO Workordertxntype Attr;>> Workordertransaction.nebula
ECHO };>> Workordertransaction.nebula 
 ECHO @Package("Material Management") type  Workordertransactionline{ >> Workordertransactionline.nebula
ECHO Org;>> Workordertransactionline.nebula
ECHO User;>> Workordertransactionline.nebula
ECHO Uom;>> Workordertransactionline.nebula
ECHO Created Timestamp;>> Workordertransactionline.nebula
ECHO Createdby Date;>> Workordertransactionline.nebula
ECHO Description;>> Workordertransactionline.nebula
ECHO Help;>> Workordertransactionline.nebula
ECHO Isactive YesNo;>> Workordertransactionline.nebula
ECHO Line Number;>> Workordertransactionline.nebula
ECHO Attributesetinstance;>> Workordertransactionline.nebula
ECHO Locator;>> Workordertransactionline.nebula
ECHO Product;>> Workordertransactionline.nebula
ECHO !ID;>> Workordertransactionline.nebula
ECHO Workordertransaction;>> Workordertransactionline.nebula
ECHO Processed YesNo;>> Workordertransactionline.nebula
ECHO Qtyentered Quantity;>> Workordertransactionline.nebula
ECHO SeqNo;>> Workordertransactionline.nebula
ECHO Updated Timestamp;>> Workordertransactionline.nebula
ECHO Updatedby Date;>> Workordertransactionline.nebula
ECHO User1 Elementvalue;>> Workordertransactionline.nebula
ECHO User2 Elementvalue;>> Workordertransactionline.nebula
ECHO };>> Workordertransactionline.nebula 
 ECHO @Package("Material Management") type  Workordertransactionlinema{ >> Workordertransactionlinema.nebula
ECHO Org;>> Workordertransactionlinema.nebula
ECHO Created Timestamp;>> Workordertransactionlinema.nebula
ECHO Createdby Date;>> Workordertransactionlinema.nebula
ECHO Isactive YesNo;>> Workordertransactionlinema.nebula
ECHO !Attributesetinstance;>> Workordertransactionlinema.nebula
ECHO !Workordertransactionline;>> Workordertransactionlinema.nebula
ECHO Movementqty Quantity;>> Workordertransactionlinema.nebula
ECHO Updated Timestamp;>> Workordertransactionlinema.nebula
ECHO Updatedby Date;>> Workordertransactionlinema.nebula
ECHO };>> Workordertransactionlinema.nebula 
 ECHO @Package("Material Management") type  Zone{ >> Zone.nebula
ECHO Org;>> Zone.nebula
ECHO Addlocator YesNo;>> Zone.nebula
ECHO Created Timestamp;>> Zone.nebula
ECHO Createdby Date;>> Zone.nebula
ECHO Description;>> Zone.nebula
ECHO Isactive YesNo;>> Zone.nebula
ECHO Isavailableforallocation YesNo;>> Zone.nebula
ECHO Isavailabletopromise YesNo;>> Zone.nebula
ECHO Isstatic YesNo;>> Zone.nebula
ECHO Warehouse;>> Zone.nebula
ECHO !ID;>> Zone.nebula
ECHO !Name;>> Zone.nebula
ECHO Pickingseqno SeqNo;>> Zone.nebula
ECHO Putawayseqno SeqNo;>> Zone.nebula
ECHO Replenishmentseqno SeqNo;>> Zone.nebula
ECHO Synchronizedefaults YesNo;>> Zone.nebula
ECHO Updated Timestamp;>> Zone.nebula
ECHO Updatedby Date;>> Zone.nebula
ECHO };>> Zone.nebula 
 ECHO @Package("Material Management") type  Zonelocator{ >> Zonelocator.nebula
ECHO Org;>> Zonelocator.nebula
ECHO Created Timestamp;>> Zonelocator.nebula
ECHO Createdby Date;>> Zonelocator.nebula
ECHO Isactive YesNo;>> Zonelocator.nebula
ECHO Locator;>> Zonelocator.nebula
ECHO !ID;>> Zonelocator.nebula
ECHO Zone;>> Zonelocator.nebula
ECHO Updated Timestamp;>> Zonelocator.nebula
ECHO Updatedby Date;>> Zonelocator.nebula
ECHO };>> Zonelocator.nebula 
 ECHO @Package("Material Management") type  Zonerelationship{ >> Zonerelationship.nebula
ECHO Org;>> Zonerelationship.nebula
ECHO Created Timestamp;>> Zonerelationship.nebula
ECHO Createdby Date;>> Zonerelationship.nebula
ECHO Isactive YesNo;>> Zonerelationship.nebula
ECHO Sourcezone Zone;>> Zonerelationship.nebula
ECHO !ID;>> Zonerelationship.nebula
ECHO Zone;>> Zonerelationship.nebula
ECHO Replenishmentseqno SeqNo;>> Zonerelationship.nebula
ECHO Updated Timestamp;>> Zonerelationship.nebula
ECHO Updatedby Date;>> Zonerelationship.nebula
ECHO };>> Zonerelationship.nebula 
 ECHO @Package("Performance Analysis") type  Achievement{ >> Achievement.nebula
ECHO Org;>> Achievement.nebula
ECHO Created Timestamp;>> Achievement.nebula
ECHO Createdby Date;>> Achievement.nebula
ECHO Datedoc Date;>> Achievement.nebula
ECHO Description;>> Achievement.nebula
ECHO Isachieved YesNo;>> Achievement.nebula
ECHO Isactive YesNo;>> Achievement.nebula
ECHO Manualactual Number;>> Achievement.nebula
ECHO !Name;>> Achievement.nebula
ECHO Note;>> Achievement.nebula
ECHO !ID;>> Achievement.nebula
ECHO Measure;>> Achievement.nebula
ECHO SeqNo;>> Achievement.nebula
ECHO Updated Timestamp;>> Achievement.nebula
ECHO Updatedby Date;>> Achievement.nebula
ECHO };>> Achievement.nebula 
 ECHO @Package("Performance Analysis") type  Benchmark{ >> Benchmark.nebula
ECHO Org;>> Benchmark.nebula
ECHO Accumulationtype YesNo;>> Benchmark.nebula
ECHO Created Timestamp;>> Benchmark.nebula
ECHO Createdby Date;>> Benchmark.nebula
ECHO Description;>> Benchmark.nebula
ECHO Help;>> Benchmark.nebula
ECHO Isactive YesNo;>> Benchmark.nebula
ECHO !Name;>> Benchmark.nebula
ECHO !ID;>> Benchmark.nebula
ECHO Updated Timestamp;>> Benchmark.nebula
ECHO Updatedby Date;>> Benchmark.nebula
ECHO };>> Benchmark.nebula 
 ECHO @Package("Performance Analysis") type  Benchmarkdata{ >> Benchmarkdata.nebula
ECHO Org;>> Benchmarkdata.nebula
ECHO Benchmarkdate Date;>> Benchmarkdata.nebula
ECHO Benchmarkvalue Number;>> Benchmarkdata.nebula
ECHO Created Timestamp;>> Benchmarkdata.nebula
ECHO Createdby Date;>> Benchmarkdata.nebula
ECHO Description;>> Benchmarkdata.nebula
ECHO Isactive YesNo;>> Benchmarkdata.nebula
ECHO !Name;>> Benchmarkdata.nebula
ECHO !ID;>> Benchmarkdata.nebula
ECHO Benchmark;>> Benchmarkdata.nebula
ECHO Updated Timestamp;>> Benchmarkdata.nebula
ECHO Updatedby Date;>> Benchmarkdata.nebula
ECHO };>> Benchmarkdata.nebula 
 ECHO @Package("Performance Analysis") type  Colorschema{ >> Colorschema.nebula
ECHO Org;>> Colorschema.nebula
ECHO Printcolor1 Printcolor;>> Colorschema.nebula
ECHO Printcolor2 Printcolor;>> Colorschema.nebula
ECHO Printcolor3 Printcolor;>> Colorschema.nebula
ECHO Printcolor4 Printcolor;>> Colorschema.nebula
ECHO Created Timestamp;>> Colorschema.nebula
ECHO Createdby Date;>> Colorschema.nebula
ECHO Description;>> Colorschema.nebula
ECHO Entitytype Attr;>> Colorschema.nebula
ECHO Isactive YesNo;>> Colorschema.nebula
ECHO Mark1percent Percent;>> Colorschema.nebula
ECHO Mark2percent Percent;>> Colorschema.nebula
ECHO Mark3percent Percent;>> Colorschema.nebula
ECHO Mark4percent Percent;>> Colorschema.nebula
ECHO !Name;>> Colorschema.nebula
ECHO !ID;>> Colorschema.nebula
ECHO Updated Timestamp;>> Colorschema.nebula
ECHO Updatedby Date;>> Colorschema.nebula
ECHO };>> Colorschema.nebula 
 ECHO @Package("Performance Analysis") type  Goal{ >> Goal.nebula
ECHO Org;>> Goal.nebula
ECHO Role;>> Goal.nebula
ECHO User;>> Goal.nebula
ECHO Created Timestamp;>> Goal.nebula
ECHO Createdby Date;>> Goal.nebula
ECHO Datefrom Date;>> Goal.nebula
ECHO Datelastrun Date;>> Goal.nebula
ECHO Dateto Date;>> Goal.nebula
ECHO Description;>> Goal.nebula
ECHO Goalperformance Number;>> Goal.nebula
ECHO Isactive YesNo;>> Goal.nebula
ECHO Issummary YesNo;>> Goal.nebula
ECHO Measureactual Number;>> Goal.nebula
ECHO Measuredisplay YesNo;>> Goal.nebula
ECHO Measurescope YesNo;>> Goal.nebula
ECHO Measuretarget Number;>> Goal.nebula
ECHO !Name;>> Goal.nebula
ECHO Note;>> Goal.nebula
ECHO Colorschema;>> Goal.nebula
ECHO Goalparent Goal;>> Goal.nebula
ECHO !ID;>> Goal.nebula
ECHO Measure;>> Goal.nebula
ECHO Relativeweight Number;>> Goal.nebula
ECHO SeqNo;>> Goal.nebula
ECHO Updated Timestamp;>> Goal.nebula
ECHO Updatedby Date;>> Goal.nebula
ECHO };>> Goal.nebula 
 ECHO @Package("Performance Analysis") type  Goalrestriction{ >> Goalrestriction.nebula
ECHO Org;>> Goalrestriction.nebula
ECHO Bp_Group;>> Goalrestriction.nebula
ECHO Bpartner;>> Goalrestriction.nebula
ECHO Created Timestamp;>> Goalrestriction.nebula
ECHO Createdby Date;>> Goalrestriction.nebula
ECHO Goalrestrictiontype YesNo;>> Goalrestriction.nebula
ECHO Isactive YesNo;>> Goalrestriction.nebula
ECHO Product_Category;>> Goalrestriction.nebula
ECHO Product;>> Goalrestriction.nebula
ECHO !Name;>> Goalrestriction.nebula
ECHO Org;>> Goalrestriction.nebula
ECHO !ID;>> Goalrestriction.nebula
ECHO Goal;>> Goalrestriction.nebula
ECHO Updated Timestamp;>> Goalrestriction.nebula
ECHO Updatedby Date;>> Goalrestriction.nebula
ECHO };>> Goalrestriction.nebula 
 ECHO @Package("Performance Analysis") type  Hierarchy{ >> Hierarchy.nebula
ECHO Org;>> Hierarchy.nebula
ECHO Tree_Account Tree;>> Hierarchy.nebula
ECHO Tree_Activity Tree;>> Hierarchy.nebula
ECHO Tree_Bpartner Tree;>> Hierarchy.nebula
ECHO Tree_Campaign Tree;>> Hierarchy.nebula
ECHO Tree_Org Tree;>> Hierarchy.nebula
ECHO Tree_Product Tree;>> Hierarchy.nebula
ECHO Tree_Project Tree;>> Hierarchy.nebula
ECHO Tree_Salesregion Tree;>> Hierarchy.nebula
ECHO Created Timestamp;>> Hierarchy.nebula
ECHO Createdby Date;>> Hierarchy.nebula
ECHO Description;>> Hierarchy.nebula
ECHO Help;>> Hierarchy.nebula
ECHO Isactive YesNo;>> Hierarchy.nebula
ECHO !Name;>> Hierarchy.nebula
ECHO !ID;>> Hierarchy.nebula
ECHO Updated Timestamp;>> Hierarchy.nebula
ECHO Updatedby Date;>> Hierarchy.nebula
ECHO };>> Hierarchy.nebula 
 ECHO @Package("Performance Analysis") type  Measure{ >> Measure.nebula
ECHO Org;>> Measure.nebula
ECHO Projecttype;>> Measure.nebula
ECHO Calculationclass Name;>> Measure.nebula
ECHO Created Timestamp;>> Measure.nebula
ECHO Createdby Date;>> Measure.nebula
ECHO Description;>> Measure.nebula
ECHO Isactive YesNo;>> Measure.nebula
ECHO Manualactual Number;>> Measure.nebula
ECHO Manualnote Note;>> Measure.nebula
ECHO Measuredatatype YesNo;>> Measure.nebula
ECHO Measuretype YesNo;>> Measure.nebula
ECHO !Name;>> Measure.nebula
ECHO Benchmark;>> Measure.nebula
ECHO Hierarchy;>> Measure.nebula
ECHO Measurecalc;>> Measure.nebula
ECHO !ID;>> Measure.nebula
ECHO Ratio;>> Measure.nebula
ECHO Requesttype;>> Measure.nebula
ECHO Updated Timestamp;>> Measure.nebula
ECHO Updatedby Date;>> Measure.nebula
ECHO };>> Measure.nebula 
 ECHO @Package("Performance Analysis") type  Measurecalc{ >> Measurecalc.nebula
ECHO Org;>> Measurecalc.nebula
ECHO Table;>> Measurecalc.nebula
ECHO Bpartnercolumn Name;>> Measurecalc.nebula
ECHO Created Timestamp;>> Measurecalc.nebula
ECHO Createdby Date;>> Measurecalc.nebula
ECHO Datecolumn Name;>> Measurecalc.nebula
ECHO Description;>> Measurecalc.nebula
ECHO Entitytype Attr;>> Measurecalc.nebula
ECHO Isactive YesNo;>> Measurecalc.nebula
ECHO Keycolumn Name;>> Measurecalc.nebula
ECHO !Name;>> Measurecalc.nebula
ECHO Orgcolumn Name;>> Measurecalc.nebula
ECHO !ID;>> Measurecalc.nebula
ECHO Productcolumn Name;>> Measurecalc.nebula
ECHO Selectclause Note;>> Measurecalc.nebula
ECHO Updated Timestamp;>> Measurecalc.nebula
ECHO Updatedby Date;>> Measurecalc.nebula
ECHO Whereclause Note;>> Measurecalc.nebula
ECHO };>> Measurecalc.nebula 
 ECHO @Package("Performance Analysis") type  Ratio{ >> Ratio.nebula
ECHO Org;>> Ratio.nebula
ECHO Acctschema;>> Ratio.nebula
ECHO Created Timestamp;>> Ratio.nebula
ECHO Createdby Date;>> Ratio.nebula
ECHO Description;>> Ratio.nebula
ECHO Help;>> Ratio.nebula
ECHO Isactive YesNo;>> Ratio.nebula
ECHO !Name;>> Ratio.nebula
ECHO !ID;>> Ratio.nebula
ECHO Updated Timestamp;>> Ratio.nebula
ECHO Updatedby Date;>> Ratio.nebula
ECHO };>> Ratio.nebula 
 ECHO @Package("Performance Analysis") type  Ratioelement{ >> Ratioelement.nebula
ECHO Org;>> Ratioelement.nebula
ECHO Account Elementvalue;>> Ratioelement.nebula
ECHO Constantvalue String;>> Ratioelement.nebula
ECHO Created Timestamp;>> Ratioelement.nebula
ECHO Createdby Date;>> Ratioelement.nebula
ECHO Description;>> Ratioelement.nebula
ECHO Isactive YesNo;>> Ratioelement.nebula
ECHO !Name;>> Ratioelement.nebula
ECHO Measurecalc;>> Ratioelement.nebula
ECHO !ID;>> Ratioelement.nebula
ECHO Ratioused Ratio;>> Ratioelement.nebula
ECHO Ratio;>> Ratioelement.nebula
ECHO Postingtype YesNo;>> Ratioelement.nebula
ECHO Ratioelementtype YesNo;>> Ratioelement.nebula
ECHO Ratiooperand YesNo;>> Ratioelement.nebula
ECHO SeqNo;>> Ratioelement.nebula
ECHO Updated Timestamp;>> Ratioelement.nebula
ECHO Updatedby Date;>> Ratioelement.nebula
ECHO };>> Ratioelement.nebula 
 ECHO @Package("Performance Analysis") type  Report{ >> Report.nebula
ECHO Org;>> Report.nebula
ECHO Printformat;>> Report.nebula
ECHO Acctschema;>> Report.nebula
ECHO Calendar;>> Report.nebula
ECHO Created Timestamp;>> Report.nebula
ECHO Createdby Date;>> Report.nebula
ECHO Description;>> Report.nebula
ECHO Isactive YesNo;>> Report.nebula
ECHO Listsources YesNo;>> Report.nebula
ECHO Listtrx YesNo;>> Report.nebula
ECHO !Name;>> Report.nebula
ECHO Reportcolumnset;>> Report.nebula
ECHO Reportlineset;>> Report.nebula
ECHO !ID;>> Report.nebula
ECHO Processing YesNo;>> Report.nebula
ECHO Updated Timestamp;>> Report.nebula
ECHO Updatedby Date;>> Report.nebula
ECHO };>> Report.nebula 
 ECHO @Package("Performance Analysis") type  Reportcolumn{ >> Reportcolumn.nebula
ECHO Org;>> Reportcolumn.nebula
ECHO Amounttype Attr;>> Reportcolumn.nebula
ECHO Activity;>> Reportcolumn.nebula
ECHO Bpartner;>> Reportcolumn.nebula
ECHO Campaign;>> Reportcolumn.nebula
ECHO Currency;>> Reportcolumn.nebula
ECHO Elementvalue;>> Reportcolumn.nebula
ECHO Location;>> Reportcolumn.nebula
ECHO Project;>> Reportcolumn.nebula
ECHO Salesregion;>> Reportcolumn.nebula
ECHO Calculationtype YesNo;>> Reportcolumn.nebula
ECHO Columntype YesNo;>> Reportcolumn.nebula
ECHO Created Timestamp;>> Reportcolumn.nebula
ECHO Createdby Date;>> Reportcolumn.nebula
ECHO Currencytype YesNo;>> Reportcolumn.nebula
ECHO Description;>> Reportcolumn.nebula
ECHO Elementtype Attr;>> Reportcolumn.nebula
ECHO Budget;>> Reportcolumn.nebula
ECHO Isactive YesNo;>> Reportcolumn.nebula
ECHO Isadhocconversion YesNo;>> Reportcolumn.nebula
ECHO Isprinted YesNo;>> Reportcolumn.nebula
ECHO Product;>> Reportcolumn.nebula
ECHO !Name;>> Reportcolumn.nebula
ECHO Oper_1 Reportcolumn;>> Reportcolumn.nebula
ECHO Oper_2 Reportcolumn;>> Reportcolumn.nebula
ECHO Org;>> Reportcolumn.nebula
ECHO Reportcolumnset;>> Reportcolumn.nebula
ECHO !ID;>> Reportcolumn.nebula
ECHO Postingtype YesNo;>> Reportcolumn.nebula
ECHO Relativeperiod Number;>> Reportcolumn.nebula
ECHO SeqNo;>> Reportcolumn.nebula
ECHO Updated Timestamp;>> Reportcolumn.nebula
ECHO Updatedby Date;>> Reportcolumn.nebula
ECHO };>> Reportcolumn.nebula 
 ECHO @Package("Performance Analysis") type  Reportcolumnset{ >> Reportcolumnset.nebula
ECHO Org;>> Reportcolumnset.nebula
ECHO Created Timestamp;>> Reportcolumnset.nebula
ECHO Createdby Date;>> Reportcolumnset.nebula
ECHO Description;>> Reportcolumnset.nebula
ECHO Isactive YesNo;>> Reportcolumnset.nebula
ECHO !Name;>> Reportcolumnset.nebula
ECHO !ID;>> Reportcolumnset.nebula
ECHO Processing YesNo;>> Reportcolumnset.nebula
ECHO Updated Timestamp;>> Reportcolumnset.nebula
ECHO Updatedby Date;>> Reportcolumnset.nebula
ECHO };>> Reportcolumnset.nebula 
 ECHO @Package("Performance Analysis") type  Reportline{ >> Reportline.nebula
ECHO Org;>> Reportline.nebula
ECHO Amounttype Attr;>> Reportline.nebula
ECHO Calculationtype YesNo;>> Reportline.nebula
ECHO Created Timestamp;>> Reportline.nebula
ECHO Createdby Date;>> Reportline.nebula
ECHO Description;>> Reportline.nebula
ECHO Budget;>> Reportline.nebula
ECHO Isactive YesNo;>> Reportline.nebula
ECHO Isprinted YesNo;>> Reportline.nebula
ECHO Issummary YesNo;>> Reportline.nebula
ECHO Linetype YesNo;>> Reportline.nebula
ECHO !Name;>> Reportline.nebula
ECHO Oper_1 Reportline;>> Reportline.nebula
ECHO Oper_2 Reportline;>> Reportline.nebula
ECHO Reportlineset;>> Reportline.nebula
ECHO !ID;>> Reportline.nebula
ECHO Parent Reportline;>> Reportline.nebula
ECHO Postingtype YesNo;>> Reportline.nebula
ECHO SeqNo;>> Reportline.nebula
ECHO Updated Timestamp;>> Reportline.nebula
ECHO Updatedby Date;>> Reportline.nebula
ECHO };>> Reportline.nebula 
 ECHO @Package("Performance Analysis") type  Reportlineset{ >> Reportlineset.nebula
ECHO Org;>> Reportlineset.nebula
ECHO Created Timestamp;>> Reportlineset.nebula
ECHO Createdby Date;>> Reportlineset.nebula
ECHO Description;>> Reportlineset.nebula
ECHO Isactive YesNo;>> Reportlineset.nebula
ECHO !Name;>> Reportlineset.nebula
ECHO !ID;>> Reportlineset.nebula
ECHO Processing YesNo;>> Reportlineset.nebula
ECHO Updated Timestamp;>> Reportlineset.nebula
ECHO Updatedby Date;>> Reportlineset.nebula
ECHO };>> Reportlineset.nebula 
 ECHO @Package("Performance Analysis") type  Reportsource{ >> Reportsource.nebula
ECHO Org;>> Reportsource.nebula
ECHO Activity;>> Reportsource.nebula
ECHO Bpartner;>> Reportsource.nebula
ECHO Campaign;>> Reportsource.nebula
ECHO Elementvalue;>> Reportsource.nebula
ECHO Location;>> Reportsource.nebula
ECHO Project;>> Reportsource.nebula
ECHO Salesregion;>> Reportsource.nebula
ECHO Created Timestamp;>> Reportsource.nebula
ECHO Createdby Date;>> Reportsource.nebula
ECHO Description;>> Reportsource.nebula
ECHO Elementtype Attr;>> Reportsource.nebula
ECHO Isactive YesNo;>> Reportsource.nebula
ECHO Product;>> Reportsource.nebula
ECHO Org;>> Reportsource.nebula
ECHO Reportline;>> Reportsource.nebula
ECHO !ID;>> Reportsource.nebula
ECHO Updated Timestamp;>> Reportsource.nebula
ECHO Updatedby Date;>> Reportsource.nebula
ECHO };>> Reportsource.nebula 
 ECHO @Package("Performance Analysis") type  Sla_Criteria{ >> Sla_Criteria.nebula
ECHO Org;>> Sla_Criteria.nebula
ECHO Classname Name;>> Sla_Criteria.nebula
ECHO Created Timestamp;>> Sla_Criteria.nebula
ECHO Createdby Date;>> Sla_Criteria.nebula
ECHO Description;>> Sla_Criteria.nebula
ECHO Help;>> Sla_Criteria.nebula
ECHO Isactive YesNo;>> Sla_Criteria.nebula
ECHO Ismanual YesNo;>> Sla_Criteria.nebula
ECHO !Name;>> Sla_Criteria.nebula
ECHO !ID;>> Sla_Criteria.nebula
ECHO Updated Timestamp;>> Sla_Criteria.nebula
ECHO Updatedby Date;>> Sla_Criteria.nebula
ECHO };>> Sla_Criteria.nebula 
 ECHO @Package("Performance Analysis") type  Sla_Goal{ >> Sla_Goal.nebula
ECHO Org;>> Sla_Goal.nebula
ECHO Bpartner;>> Sla_Goal.nebula
ECHO Created Timestamp;>> Sla_Goal.nebula
ECHO Createdby Date;>> Sla_Goal.nebula
ECHO Datelastrun Date;>> Sla_Goal.nebula
ECHO Description;>> Sla_Goal.nebula
ECHO Help;>> Sla_Goal.nebula
ECHO Isactive YesNo;>> Sla_Goal.nebula
ECHO Measureactual Number;>> Sla_Goal.nebula
ECHO Measuretarget Number;>> Sla_Goal.nebula
ECHO !Name;>> Sla_Goal.nebula
ECHO Sla_Criteria;>> Sla_Goal.nebula
ECHO !ID;>> Sla_Goal.nebula
ECHO Processed YesNo;>> Sla_Goal.nebula
ECHO Processing YesNo;>> Sla_Goal.nebula
ECHO Updated Timestamp;>> Sla_Goal.nebula
ECHO Updatedby Date;>> Sla_Goal.nebula
ECHO Validfrom Date;>> Sla_Goal.nebula
ECHO Validto Date;>> Sla_Goal.nebula
ECHO };>> Sla_Goal.nebula 
 ECHO @Package("Performance Analysis") type  Sla_Measure{ >> Sla_Measure.nebula
ECHO Org;>> Sla_Measure.nebula
ECHO Table;>> Sla_Measure.nebula
ECHO Created Timestamp;>> Sla_Measure.nebula
ECHO Createdby Date;>> Sla_Measure.nebula
ECHO Datetrx Date;>> Sla_Measure.nebula
ECHO Description;>> Sla_Measure.nebula
ECHO Isactive YesNo;>> Sla_Measure.nebula
ECHO Measureactual Number;>> Sla_Measure.nebula
ECHO Sla_Goal;>> Sla_Measure.nebula
ECHO !ID;>> Sla_Measure.nebula
ECHO Processed YesNo;>> Sla_Measure.nebula
ECHO Processing YesNo;>> Sla_Measure.nebula

ECHO Updated Timestamp;>> Sla_Measure.nebula
ECHO Updatedby Date;>> Sla_Measure.nebula
ECHO };>> Sla_Measure.nebula 
 ECHO @Package("Requests") type  Category{ >> Category.nebula
ECHO Org;>> Category.nebula
ECHO Created Timestamp;>> Category.nebula
ECHO Createdby Date;>> Category.nebula
ECHO Description;>> Category.nebula
ECHO Help;>> Category.nebula
ECHO Isactive YesNo;>> Category.nebula
ECHO Product;>> Category.nebula
ECHO !Name;>> Category.nebula
ECHO !ID;>> Category.nebula
ECHO Updated Timestamp;>> Category.nebula
ECHO Updatedby Date;>> Category.nebula
ECHO };>> Category.nebula 
 ECHO @Package("Requests") type  Categoryupdates{ >> Categoryupdates.nebula
ECHO Org;>> Categoryupdates.nebula
ECHO !User;>> Categoryupdates.nebula
ECHO Created Timestamp;>> Categoryupdates.nebula
ECHO Createdby Date;>> Categoryupdates.nebula
ECHO Isactive YesNo;>> Categoryupdates.nebula
ECHO Isselfservice YesNo;>> Categoryupdates.nebula
ECHO !Category;>> Categoryupdates.nebula
ECHO Updated Timestamp;>> Categoryupdates.nebula
ECHO Updatedby Date;>> Categoryupdates.nebula
ECHO };>> Categoryupdates.nebula 
 ECHO @Package("Requests") type  Contactinterest{ >> Contactinterest.nebula
ECHO Org;>> Contactinterest.nebula
ECHO !User;>> Contactinterest.nebula
ECHO Created Timestamp;>> Contactinterest.nebula
ECHO Createdby Date;>> Contactinterest.nebula
ECHO Isactive YesNo;>> Contactinterest.nebula
ECHO Optoutdate Date;>> Contactinterest.nebula
ECHO !Interestarea;>> Contactinterest.nebula
ECHO Remote_Addr URL;>> Contactinterest.nebula
ECHO Remote_Host Host;>> Contactinterest.nebula
ECHO Subscribedate Date;>> Contactinterest.nebula
ECHO Updated Timestamp;>> Contactinterest.nebula
ECHO Updatedby Date;>> Contactinterest.nebula
ECHO };>> Contactinterest.nebula 
 ECHO @Package("Requests") type  Group{ >> Group.nebula
ECHO Org;>> Group.nebula
ECHO Created Timestamp;>> Group.nebula
ECHO Createdby Date;>> Group.nebula
ECHO Description;>> Group.nebula
ECHO Help;>> Group.nebula
ECHO Isactive YesNo;>> Group.nebula
ECHO Bom;>> Group.nebula
ECHO Changenotice;>> Group.nebula
ECHO !Name;>> Group.nebula
ECHO !ID;>> Group.nebula
ECHO Updated Timestamp;>> Group.nebula
ECHO Updatedby Date;>> Group.nebula
ECHO };>> Group.nebula 
 ECHO @Package("Requests") type  Groupupdates{ >> Groupupdates.nebula
ECHO Org;>> Groupupdates.nebula
ECHO !User;>> Groupupdates.nebula
ECHO Created Timestamp;>> Groupupdates.nebula
ECHO Createdby Date;>> Groupupdates.nebula
ECHO Isactive YesNo;>> Groupupdates.nebula
ECHO Isselfservice YesNo;>> Groupupdates.nebula
ECHO !Group;>> Groupupdates.nebula
ECHO Updated Timestamp;>> Groupupdates.nebula
ECHO Updatedby Date;>> Groupupdates.nebula
ECHO };>> Groupupdates.nebula 
 ECHO @Package("Requests") type  Interestarea{ >> Interestarea.nebula
ECHO Org;>> Interestarea.nebula
ECHO Created Timestamp;>> Interestarea.nebula
ECHO Createdby Date;>> Interestarea.nebula
ECHO Description;>> Interestarea.nebula
ECHO Isactive YesNo;>> Interestarea.nebula
ECHO Isselfservice YesNo;>> Interestarea.nebula
ECHO !Name;>> Interestarea.nebula
ECHO !ID;>> Interestarea.nebula
ECHO Source;>> Interestarea.nebula
ECHO Updated Timestamp;>> Interestarea.nebula
ECHO Updatedby Date;>> Interestarea.nebula
ECHO Value String;>> Interestarea.nebula
ECHO };>> Interestarea.nebula 
 ECHO @Package("Requests") type  Issueknown{ >> Issueknown.nebula
ECHO Org;>> Issueknown.nebula
ECHO Created Timestamp;>> Issueknown.nebula
ECHO Createdby Date;>> Issueknown.nebula
ECHO Description;>> Issueknown.nebula
ECHO Isactive YesNo;>> Issueknown.nebula
ECHO Issuestatus Note;>> Issueknown.nebula
ECHO Issuesummary Note;>> Issueknown.nebula
ECHO Lineno SeqNo;>> Issueknown.nebula
ECHO Loggername Name;>> Issueknown.nebula
ECHO Processing YesNo;>> Issueknown.nebula
ECHO !ID;>> Issueknown.nebula
ECHO Issuerecommendation;>> Issueknown.nebula
ECHO Issuestatus;>> Issueknown.nebula
ECHO Request;>> Issueknown.nebula
ECHO Releaseno Code;>> Issueknown.nebula
ECHO Sourceclassname Name;>> Issueknown.nebula
ECHO Sourcemethodname Name;>> Issueknown.nebula
ECHO Updated Timestamp;>> Issueknown.nebula
ECHO Updatedby Date;>> Issueknown.nebula
ECHO };>> Issueknown.nebula 
 ECHO @Package("Requests") type  Issueproject{ >> Issueproject.nebula
ECHO Org;>> Issueproject.nebula
ECHO Asset;>> Issueproject.nebula
ECHO Project;>> Issueproject.nebula
ECHO Created Timestamp;>> Issueproject.nebula
ECHO Createdby Date;>> Issueproject.nebula
ECHO Description;>> Issueproject.nebula
ECHO Isactive YesNo;>> Issueproject.nebula
ECHO !Name;>> Issueproject.nebula
ECHO Profileinfo Description;>> Issueproject.nebula
ECHO !ID;>> Issueproject.nebula
ECHO Statisticsinfo String;>> Issueproject.nebula
ECHO Systemstatus YesNo;>> Issueproject.nebula
ECHO Updated Timestamp;>> Issueproject.nebula
ECHO Updatedby Date;>> Issueproject.nebula
ECHO };>> Issueproject.nebula 
 ECHO @Package("Requests") type  Issuerecommendation{ >> Issuerecommendation.nebula
ECHO Org;>> Issuerecommendation.nebula
ECHO Created Timestamp;>> Issuerecommendation.nebula
ECHO Createdby Date;>> Issuerecommendation.nebula
ECHO Description;>> Issuerecommendation.nebula
ECHO Help;>> Issuerecommendation.nebula
ECHO Isactive YesNo;>> Issuerecommendation.nebula
ECHO !Name;>> Issuerecommendation.nebula
ECHO !ID;>> Issuerecommendation.nebula
ECHO Updated Timestamp;>> Issuerecommendation.nebula
ECHO Updatedby Date;>> Issuerecommendation.nebula
ECHO };>> Issuerecommendation.nebula 
 ECHO @Package("Requests") type  Issuestatus{ >> Issuestatus.nebula
ECHO Org;>> Issuestatus.nebula
ECHO Created Timestamp;>> Issuestatus.nebula
ECHO Createdby Date;>> Issuestatus.nebula
ECHO Description;>> Issuestatus.nebula
ECHO Isactive YesNo;>> Issuestatus.nebula
ECHO !Name;>> Issuestatus.nebula
ECHO !ID;>> Issuestatus.nebula
ECHO Updated Timestamp;>> Issuestatus.nebula
ECHO Updatedby Date;>> Issuestatus.nebula
ECHO };>> Issuestatus.nebula 
 ECHO @Package("Requests") type  Issuesystem{ >> Issuesystem.nebula
ECHO Org;>> Issuesystem.nebula
ECHO Asset;>> Issuesystem.nebula
ECHO Created Timestamp;>> Issuesystem.nebula
ECHO Createdby Date;>> Issuesystem.nebula
ECHO Dbaddress String;>> Issuesystem.nebula
ECHO Isactive YesNo;>> Issuesystem.nebula
ECHO Profileinfo Description;>> Issuesystem.nebula
ECHO !ID;>> Issuesystem.nebula
ECHO Statisticsinfo String;>> Issuesystem.nebula
ECHO Systemstatus YesNo;>> Issuesystem.nebula
ECHO Updated Timestamp;>> Issuesystem.nebula
ECHO Updatedby Date;>> Issuesystem.nebula
ECHO };>> Issuesystem.nebula 
 ECHO @Package("Requests") type  Issueuser{ >> Issueuser.nebula
ECHO Org;>> Issueuser.nebula
ECHO User;>> Issueuser.nebula
ECHO Created Timestamp;>> Issueuser.nebula
ECHO Createdby Date;>> Issueuser.nebula
ECHO Description;>> Issueuser.nebula
ECHO Isactive YesNo;>> Issueuser.nebula
ECHO !ID;>> Issueuser.nebula
ECHO Updated Timestamp;>> Issueuser.nebula
ECHO Updatedby Date;>> Issueuser.nebula
ECHO Username Name;>> Issueuser.nebula
ECHO };>> Issueuser.nebula 
 ECHO @Package("Requests") type  Mailtext{ >> Mailtext.nebula
ECHO Org;>> Mailtext.nebula
ECHO Created Timestamp;>> Mailtext.nebula
ECHO Createdby Date;>> Mailtext.nebula
ECHO Isactive YesNo;>> Mailtext.nebula
ECHO Ishtml YesNo;>> Mailtext.nebula
ECHO Mailheader Note;>> Mailtext.nebula
ECHO Mailtext Note;>> Mailtext.nebula
ECHO Mailtext2 Note;>> Mailtext.nebula
ECHO Mailtext3 Note;>> Mailtext.nebula
ECHO !Name;>> Mailtext.nebula
ECHO !ID;>> Mailtext.nebula
ECHO Updated Timestamp;>> Mailtext.nebula
ECHO Updatedby Date;>> Mailtext.nebula
ECHO };>> Mailtext.nebula 
 ECHO @Package("Requests") type  Mailtext_Trl{ >> Mailtext_Trl.nebula
ECHO !Ad_Language Language;>> Mailtext_Trl.nebula
ECHO Org;>> Mailtext_Trl.nebula
ECHO Created Timestamp;>> Mailtext_Trl.nebula
ECHO Createdby Date;>> Mailtext_Trl.nebula
ECHO Isactive YesNo;>> Mailtext_Trl.nebula
ECHO Istranslated YesNo;>> Mailtext_Trl.nebula
ECHO Mailheader Note;>> Mailtext_Trl.nebula
ECHO Mailtext Note;>> Mailtext_Trl.nebula
ECHO Mailtext2 Note;>> Mailtext_Trl.nebula
ECHO Mailtext3 Note;>> Mailtext_Trl.nebula
ECHO !Name;>> Mailtext_Trl.nebula
ECHO !Mailtext;>> Mailtext_Trl.nebula
ECHO Updated Timestamp;>> Mailtext_Trl.nebula
ECHO Updatedby Date;>> Mailtext_Trl.nebula
ECHO };>> Mailtext_Trl.nebula 
 ECHO @Package("Requests") type  Request{ >> Request.nebula
ECHO Org;>> Request.nebula
ECHO Role;>> Request.nebula
ECHO Table;>> Request.nebula
ECHO User;>> Request.nebula
ECHO Asset;>> Request.nebula
ECHO Activity;>> Request.nebula
ECHO Bpartnersr Bpartner;>> Request.nebula
ECHO Bpartner;>> Request.nebula
ECHO Campaign;>> Request.nebula
ECHO Invoicerequest Invoice;>> Request.nebula
ECHO Invoice;>> Request.nebula
ECHO Lead;>> Request.nebula
ECHO Order;>> Request.nebula
ECHO Payment;>> Request.nebula
ECHO Project;>> Request.nebula
ECHO Salesregion;>> Request.nebula
ECHO Closedate Date;>> Request.nebula
ECHO Confidentialtype YesNo;>> Request.nebula
ECHO Confidentialtypeentry YesNo;>> Request.nebula
ECHO Created Timestamp;>> Request.nebula
ECHO Createdby Date;>> Request.nebula
ECHO Datecompleteplan Date;>> Request.nebula
ECHO Datelastaction Date;>> Request.nebula
ECHO Datelastalert Date;>> Request.nebula
ECHO Datenextaction Date;>> Request.nebula
ECHO Datestartplan Date;>> Request.nebula
ECHO Documentno SeqNo;>> Request.nebula
ECHO Duetype YesNo;>> Request.nebula
ECHO Endtime Timestamp;>> Request.nebula
ECHO Isactive YesNo;>> Request.nebula
ECHO Isescalated YesNo;>> Request.nebula
ECHO Isinvoiced YesNo;>> Request.nebula
ECHO Isselfservice YesNo;>> Request.nebula
ECHO Lastresult String;>> Request.nebula
ECHO Changerequest;>> Request.nebula
ECHO Fixchangenotice Changenotice;>> Request.nebula
ECHO Inout;>> Request.nebula
ECHO Productspent Product;>> Request.nebula
ECHO Product;>> Request.nebula
ECHO Nextaction YesNo;>> Request.nebula
ECHO Priority;>> Request.nebula
ECHO Priorityuser YesNo;>> Request.nebula
ECHO Processed YesNo;>> Request.nebula
ECHO Qtyinvoiced Quantity;>> Request.nebula
ECHO Qtyplan Quantity;>> Request.nebula
ECHO Qtyspent Quantity;>> Request.nebula
ECHO Category;>> Request.nebula
ECHO Group;>> Request.nebula
ECHO Mailtext;>> Request.nebula
ECHO Requestrelated Request;>> Request.nebula
ECHO Requesttype;>> Request.nebula
ECHO !ID;>> Request.nebula
ECHO Resolution;>> Request.nebula
ECHO Source;>> Request.nebula
ECHO Standardresponse;>> Request.nebula
ECHO Status;>> Request.nebula

ECHO Requestamt Amount;>> Request.nebula
ECHO Result Number;>> Request.nebula
ECHO Salesrep User;>> Request.nebula
ECHO Startdate Date;>> Request.nebula
ECHO Starttime Timestamp;>> Request.nebula
ECHO Summary Note;>> Request.nebula
ECHO Taskstatus YesNo;>> Request.nebula
ECHO Updated Timestamp;>> Request.nebula
ECHO Updatedby Date;>> Request.nebula
ECHO };>> Request.nebula 
 ECHO @Package("Requests") type  Requestaction{ >> Requestaction.nebula
ECHO Org;>> Requestaction.nebula
ECHO Role;>> Requestaction.nebula
ECHO User;>> Requestaction.nebula
ECHO Asset;>> Requestaction.nebula
ECHO Activity;>> Requestaction.nebula
ECHO Bpartner;>> Requestaction.nebula
ECHO Invoice;>> Requestaction.nebula
ECHO Order;>> Requestaction.nebula
ECHO Payment;>> Requestaction.nebula
ECHO Project;>> Requestaction.nebula
ECHO Confidentialtype YesNo;>> Requestaction.nebula
ECHO Created Timestamp;>> Requestaction.nebula
ECHO Createdby Date;>> Requestaction.nebula
ECHO Datecompleteplan Date;>> Requestaction.nebula
ECHO Datenextaction Date;>> Requestaction.nebula
ECHO Datestartplan Date;>> Requestaction.nebula
ECHO Enddate Date;>> Requestaction.nebula
ECHO Isactive YesNo;>> Requestaction.nebula
ECHO Isescalated YesNo;>> Requestaction.nebula
ECHO Isinvoiced YesNo;>> Requestaction.nebula
ECHO Isselfservice YesNo;>> Requestaction.nebula
ECHO Inout;>> Requestaction.nebula
ECHO Productspent Product;>> Requestaction.nebula
ECHO Product;>> Requestaction.nebula
ECHO Nullcolumns String;>> Requestaction.nebula
ECHO Priority;>> Requestaction.nebula
ECHO Priorityuser YesNo;>> Requestaction.nebula
ECHO Qtyinvoiced Quantity;>> Requestaction.nebula
ECHO Qtyplan Quantity;>> Requestaction.nebula
ECHO Qtyspent Quantity;>> Requestaction.nebula
ECHO Category;>> Requestaction.nebula
ECHO Group;>> Requestaction.nebula
ECHO !ID;>> Requestaction.nebula
ECHO Requesttype;>> Requestaction.nebula
ECHO Request;>> Requestaction.nebula
ECHO Resolution;>> Requestaction.nebula
ECHO Status;>> Requestaction.nebula
ECHO Salesrep User;>> Requestaction.nebula
ECHO Startdate Date;>> Requestaction.nebula
ECHO Summary Note;>> Requestaction.nebula
ECHO Taskstatus YesNo;>> Requestaction.nebula
ECHO Updated Timestamp;>> Requestaction.nebula
ECHO Updatedby Date;>> Requestaction.nebula
ECHO };>> Requestaction.nebula 
 ECHO @Package("Requests") type  Requestprocessor{ >> Requestprocessor.nebula
ECHO Org;>> Requestprocessor.nebula
ECHO Schedule;>> Requestprocessor.nebula
ECHO Created Timestamp;>> Requestprocessor.nebula
ECHO Createdby Date;>> Requestprocessor.nebula
ECHO Datelastrun Date;>> Requestprocessor.nebula
ECHO Datenextrun Date;>> Requestprocessor.nebula
ECHO Description;>> Requestprocessor.nebula
ECHO Frequency;>> Requestprocessor.nebula
ECHO Frequencytype YesNo;>> Requestprocessor.nebula
ECHO Inactivityalertdays Count;>> Requestprocessor.nebula
ECHO Isactive YesNo;>> Requestprocessor.nebula
ECHO Keeplogdays Count;>> Requestprocessor.nebula
ECHO !Name;>> Requestprocessor.nebula
ECHO Overduealertdays Count;>> Requestprocessor.nebula
ECHO Overdueassigndays Count;>> Requestprocessor.nebula
ECHO Processing YesNo;>> Requestprocessor.nebula
ECHO !ID;>> Requestprocessor.nebula
ECHO Requesttype;>> Requestprocessor.nebula
ECHO Reminddays Count;>> Requestprocessor.nebula
ECHO Supervisor User;>> Requestprocessor.nebula
ECHO Updated Timestamp;>> Requestprocessor.nebula
ECHO Updatedby Date;>> Requestprocessor.nebula
ECHO };>> Requestprocessor.nebula 
 ECHO @Package("Requests") type  Requestprocessor_Route{ >> Requestprocessor_Route.nebula
ECHO Org;>> Requestprocessor_Route.nebula
ECHO User;>> Requestprocessor_Route.nebula
ECHO Created Timestamp;>> Requestprocessor_Route.nebula
ECHO Createdby Date;>> Requestprocessor_Route.nebula
ECHO Isactive YesNo;>> Requestprocessor_Route.nebula
ECHO Keyword String;>> Requestprocessor_Route.nebula
ECHO Requestprocessor;>> Requestprocessor_Route.nebula
ECHO !ID;>> Requestprocessor_Route.nebula
ECHO Requesttype;>> Requestprocessor_Route.nebula
ECHO SeqNo;>> Requestprocessor_Route.nebula
ECHO Updated Timestamp;>> Requestprocessor_Route.nebula
ECHO Updatedby Date;>> Requestprocessor_Route.nebula
ECHO };>> Requestprocessor_Route.nebula 
 ECHO @Package("Requests") type  Requestprocessorlog{ >> Requestprocessorlog.nebula
ECHO Org;>> Requestprocessorlog.nebula
ECHO Binarydata Note;>> Requestprocessorlog.nebula
ECHO Created Timestamp;>> Requestprocessorlog.nebula
ECHO Createdby Date;>> Requestprocessorlog.nebula
ECHO Description;>> Requestprocessorlog.nebula
ECHO Isactive YesNo;>> Requestprocessorlog.nebula
ECHO Iserror YesNo;>> Requestprocessorlog.nebula
ECHO !ID;>> Requestprocessorlog.nebula
ECHO Requestprocessor;>> Requestprocessorlog.nebula
ECHO Reference String;>> Requestprocessorlog.nebula
ECHO Summary Note;>> Requestprocessorlog.nebula
ECHO Textmsg MSG;>> Requestprocessorlog.nebula
ECHO Updated Timestamp;>> Requestprocessorlog.nebula
ECHO Updatedby Date;>> Requestprocessorlog.nebula
ECHO };>> Requestprocessorlog.nebula 
 ECHO @Package("Requests") type  Requesttype{ >> Requesttype.nebula
ECHO Org;>> Requesttype.nebula
ECHO Autoduedatedays Count;>> Requesttype.nebula
ECHO Confidentialtype YesNo;>> Requesttype.nebula
ECHO Created Timestamp;>> Requesttype.nebula
ECHO Createdby Date;>> Requesttype.nebula
ECHO Description;>> Requesttype.nebula
ECHO Duedatetolerance Number;>> Requesttype.nebula
ECHO Isactive YesNo;>> Requesttype.nebula
ECHO Isautochangerequest YesNo;>> Requesttype.nebula
ECHO Isconfidentialinfo YesNo;>> Requesttype.nebula
ECHO Isdefault YesNo;>> Requesttype.nebula
ECHO Isemailwhendue YesNo;>> Requesttype.nebula
ECHO Isemailwhenoverdue YesNo;>> Requesttype.nebula
ECHO Isindexed YesNo;>> Requesttype.nebula
ECHO Isinvoiced YesNo;>> Requesttype.nebula
ECHO Isselfservice YesNo;>> Requesttype.nebula
ECHO !Name;>> Requesttype.nebula
ECHO !ID;>> Requesttype.nebula
ECHO Statuscategory;>> Requesttype.nebula
ECHO Updated Timestamp;>> Requesttype.nebula
ECHO Updatedby Date;>> Requesttype.nebula
ECHO };>> Requesttype.nebula 
 ECHO @Package("Requests") type  Requesttypeupdates{ >> Requesttypeupdates.nebula
ECHO Org;>> Requesttypeupdates.nebula
ECHO !User;>> Requesttypeupdates.nebula
ECHO Created Timestamp;>> Requesttypeupdates.nebula
ECHO Createdby Date;>> Requesttypeupdates.nebula
ECHO Isactive YesNo;>> Requesttypeupdates.nebula
ECHO Isselfservice YesNo;>> Requesttypeupdates.nebula
ECHO !Requesttype;>> Requesttypeupdates.nebula
ECHO Updated Timestamp;>> Requesttypeupdates.nebula
ECHO Updatedby Date;>> Requesttypeupdates.nebula
ECHO };>> Requesttypeupdates.nebula 
 ECHO @Package("Requests") type  Requestupdate{ >> Requestupdate.nebula
ECHO Org;>> Requestupdate.nebula
ECHO Confidentialtypeentry YesNo;>> Requestupdate.nebula
ECHO Created Timestamp;>> Requestupdate.nebula
ECHO Createdby Date;>> Requestupdate.nebula
ECHO Endtime Timestamp;>> Requestupdate.nebula
ECHO Isactive YesNo;>> Requestupdate.nebula
ECHO Productspent Product;>> Requestupdate.nebula
ECHO Qtyinvoiced Quantity;>> Requestupdate.nebula
ECHO Qtyspent Quantity;>> Requestupdate.nebula
ECHO !ID;>> Requestupdate.nebula
ECHO Request;>> Requestupdate.nebula
ECHO Result Number;>> Requestupdate.nebula
ECHO Starttime Timestamp;>> Requestupdate.nebula
ECHO Updated Timestamp;>> Requestupdate.nebula
ECHO Updatedby Date;>> Requestupdate.nebula
ECHO };>> Requestupdate.nebula 
 ECHO @Package("Requests") type  Requestupdates{ >> Requestupdates.nebula
ECHO Org;>> Requestupdates.nebula
ECHO !User;>> Requestupdates.nebula
ECHO Created Timestamp;>> Requestupdates.nebula
ECHO Createdby Date;>> Requestupdates.nebula
ECHO Isactive YesNo;>> Requestupdates.nebula
ECHO Isselfservice YesNo;>> Requestupdates.nebula
ECHO !Request;>> Requestupdates.nebula
ECHO Updated Timestamp;>> Requestupdates.nebula
ECHO Updatedby Date;>> Requestupdates.nebula
ECHO };>> Requestupdates.nebula 
 ECHO @Package("Requests") type  Resolution{ >> Resolution.nebula
ECHO Org;>> Resolution.nebula
ECHO Created Timestamp;>> Resolution.nebula
ECHO Createdby Date;>> Resolution.nebula
ECHO Description;>> Resolution.nebula
ECHO Help;>> Resolution.nebula
ECHO Isactive YesNo;>> Resolution.nebula
ECHO !Name;>> Resolution.nebula
ECHO !ID;>> Resolution.nebula
ECHO Updated Timestamp;>> Resolution.nebula
ECHO Updatedby Date;>> Resolution.nebula
ECHO };>> Resolution.nebula 
 ECHO @Package("Requests") type  Source{ >> Source.nebula
ECHO Org;>> Source.nebula
ECHO Created Timestamp;>> Source.nebula
ECHO Createdby Date;>> Source.nebula
ECHO Description;>> Source.nebula
ECHO Help;>> Source.nebula
ECHO Isactive YesNo;>> Source.nebula
ECHO !Name;>> Source.nebula
ECHO !ID;>> Source.nebula
ECHO Sourcecreatetype YesNo;>> Source.nebula
ECHO Updated Timestamp;>> Source.nebula
ECHO Updatedby Date;>> Source.nebula
ECHO Value String;>> Source.nebula
ECHO };>> Source.nebula 
 ECHO @Package("Requests") type  Standardresponse{ >> Standardresponse.nebula
ECHO Org;>> Standardresponse.nebula
ECHO Created Timestamp;>> Standardresponse.nebula
ECHO Createdby Date;>> Standardresponse.nebula
ECHO Isactive YesNo;>> Standardresponse.nebula
ECHO !Name;>> Standardresponse.nebula
ECHO !ID;>> Standardresponse.nebula
ECHO Responsetext Description;>> Standardresponse.nebula
ECHO Updated Timestamp;>> Standardresponse.nebula
ECHO Updatedby Date;>> Standardresponse.nebula
ECHO };>> Standardresponse.nebula 
 ECHO @Package("Requests") type  Status{ >> Status.nebula
ECHO Org;>> Status.nebula
ECHO Created Timestamp;>> Status.nebula
ECHO Createdby Date;>> Status.nebula
ECHO Description;>> Status.nebula
ECHO Help;>> Status.nebula
ECHO Isactive YesNo;>> Status.nebula
ECHO Isclosed YesNo;>> Status.nebula
ECHO Isdefault YesNo;>> Status.nebula
ECHO Isfinalclose YesNo;>> Status.nebula
ECHO Isopen YesNo;>> Status.nebula
ECHO Iswebcanupdate YesNo;>> Status.nebula
ECHO !Name;>> Status.nebula
ECHO Next_Status Status;>> Status.nebula
ECHO Statuscategory;>> Status.nebula
ECHO !ID;>> Status.nebula
ECHO SeqNo;>> Status.nebula
ECHO Timeoutdays Count;>> Status.nebula
ECHO Update_Status Status;>> Status.nebula
ECHO Updated Timestamp;>> Status.nebula
ECHO Updatedby Date;>> Status.nebula
ECHO Value String;>> Status.nebula
ECHO };>> Status.nebula 
 ECHO @Package("Requests") type  Statuscategory{ >> Statuscategory.nebula
ECHO Org;>> Statuscategory.nebula
ECHO Created Timestamp;>> Statuscategory.nebula
ECHO Createdby Date;>> Statuscategory.nebula
ECHO Description;>> Statuscategory.nebula
ECHO Help;>> Statuscategory.nebula
ECHO Isactive YesNo;>> Statuscategory.nebula
ECHO Isdefault YesNo;>> Statuscategory.nebula
ECHO !Name;>> Statuscategory.nebula
ECHO !ID;>> Statuscategory.nebula
ECHO Updated Timestamp;>> Statuscategory.nebula
ECHO Updatedby Date;>> Statuscategory.nebula
ECHO };>> Statuscategory.nebula 
 ECHO @Package("Service") type  Expensetype{ >> Expensetype.nebula
ECHO Org;>> Expensetype.nebula
ECHO Taxcategory;>> Expensetype.nebula
ECHO Uom;>> Expensetype.nebula
ECHO Created Timestamp;>> Expensetype.nebula
ECHO Createdby Date;>> Expensetype.nebula
ECHO Description;>> Expensetype.nebula
ECHO Isactive YesNo;>> Expensetype.nebula
ECHO Isinvoiced YesNo;>> Expensetype.nebula
ECHO Product_Category;>> Expensetype.nebula
ECHO !Name;>> Expensetype.nebula
ECHO !ID;>> Expensetype.nebula
ECHO Updated Timestamp;>> Expensetype.nebula
ECHO Updatedby Date;>> Expensetype.nebula
ECHO Value String;>> Expensetype.nebula
ECHO };>> Expensetype.nebula 
 ECHO @Package("Service") type  Resource{ >> Resource.nebula
ECHO Org;>> Resource.nebula
ECHO User;>> Resource.nebula
ECHO Chargeableqty Quantity;>> Resource.nebula
ECHO Created Timestamp;>> Resource.nebula
ECHO Createdby Date;>> Resource.nebula
ECHO Description;>> Resource.nebula
ECHO Isactive YesNo;>> Resource.nebula
ECHO Isavailable YesNo;>> Resource.nebula
ECHO Warehouse;>> Resource.nebula
ECHO !Name;>> Resource.nebula
ECHO Resourcetype;>> Resource.nebula
ECHO !ID;>> Resource.nebula
ECHO Updated Timestamp;>> Resource.nebula
ECHO Updatedby Date;>> Resource.nebula
ECHO Value String;>> Resource.nebula
ECHO };>> Resource.nebula 
 ECHO @Package("Service") type  Resourceassignment{ >> Resourceassignment.nebula
ECHO Org;>> Resourceassignment.nebula
ECHO Assigndatefrom Date;>> Resourceassignment.nebula
ECHO Assigndateto Date;>> Resourceassignment.nebula
ECHO Created Timestamp;>> Resourceassignment.nebula
ECHO Createdby Date;>> Resourceassignment.nebula
ECHO Description;>> Resourceassignment.nebula
ECHO Isactive YesNo;>> Resourceassignment.nebula
ECHO Isconfirmed YesNo;>> Resourceassignment.nebula
ECHO !Name;>> Resourceassignment.nebula
ECHO Qty Quantity;>> Resourceassignment.nebula
ECHO !ID;>> Resourceassignment.nebula
ECHO Resource;>> Resourceassignment.nebula
ECHO Updated Timestamp;>> Resourceassignment.nebula
ECHO Updatedby Date;>> Resourceassignment.nebula
ECHO };>> Resourceassignment.nebula 
 ECHO @Package("Service") type  Resourcetype{ >> Resourcetype.nebula
ECHO Org;>> Resourcetype.nebula
ECHO Allowuomfractions YesNo;>> Resourcetype.nebula
ECHO Taxcategory;>> Resourcetype.nebula
ECHO Uom;>> Resourcetype.nebula
ECHO Chargeableqty Quantity;>> Resourcetype.nebula
ECHO Created Timestamp;>> Resourcetype.nebula
ECHO Createdby Date;>> Resourcetype.nebula
ECHO Description;>> Resourcetype.nebula
ECHO Isactive YesNo;>> Resourcetype.nebula
ECHO Isdateslot YesNo;>> Resourcetype.nebula
ECHO Issingleassignment YesNo;>> Resourcetype.nebula
ECHO Istimeslot YesNo;>> Resourcetype.nebula
ECHO Product_Category;>> Resourcetype.nebula
ECHO !Name;>> Resourcetype.nebula
ECHO Onfriday YesNo;>> Resourcetype.nebula
ECHO Onmonday YesNo;>> Resourcetype.nebula
ECHO Onsaturday YesNo;>> Resourcetype.nebula
ECHO Onsunday YesNo;>> Resourcetype.nebula
ECHO Onthursday YesNo;>> Resourcetype.nebula
ECHO Ontuesday YesNo;>> Resourcetype.nebula
ECHO Onwednesday YesNo;>> Resourcetype.nebula
ECHO !ID;>> Resourcetype.nebula
ECHO Timeslotend Date;>> Resourcetype.nebula
ECHO Timeslotstart Date;>> Resourcetype.nebula
ECHO Updated Timestamp;>> Resourcetype.nebula
ECHO Updatedby Date;>> Resourcetype.nebula
ECHO Value String;>> Resourcetype.nebula
ECHO };>> Resourcetype.nebula 
 ECHO @Package("Service") type  Resourceunavailable{ >> Resourceunavailable.nebula
ECHO Org;>> Resourceunavailable.nebula
ECHO Created Timestamp;>> Resourceunavailable.nebula
ECHO Createdby Date;>> Resourceunavailable.nebula
ECHO Datefrom Date;>> Resourceunavailable.nebula
ECHO Dateto Date;>> Resourceunavailable.nebula
ECHO Description;>> Resourceunavailable.nebula
ECHO Isactive YesNo;>> Resourceunavailable.nebula
ECHO !ID;>> Resourceunavailable.nebula
ECHO Resource;>> Resourceunavailable.nebula
ECHO Updated Timestamp;>> Resourceunavailable.nebula
ECHO Updatedby Date;>> Resourceunavailable.nebula
ECHO };>> Resourceunavailable.nebula 
 ECHO @Package("Service") type  Timeexpense{ >> Timeexpense.nebula
ECHO Org;>> Timeexpense.nebula
ECHO Approvalamt Amount;>> Timeexpense.nebula
ECHO Bpartner;>> Timeexpense.nebula
ECHO Created Timestamp;>> Timeexpense.nebula
ECHO Createdby Date;>> Timeexpense.nebula
ECHO Datereport Date;>> Timeexpense.nebula
ECHO Description;>> Timeexpense.nebula
ECHO Docaction Attr;>> Timeexpense.nebula
ECHO Docstatus Attr;>> Timeexpense.nebula
ECHO Documentno SeqNo;>> Timeexpense.nebula
ECHO Isactive YesNo;>> Timeexpense.nebula
ECHO Isapproved YesNo;>> Timeexpense.nebula
ECHO Pricelist;>> Timeexpense.nebula
ECHO Warehouse;>> Timeexpense.nebula
ECHO Processed YesNo;>> Timeexpense.nebula
ECHO Processing YesNo;>> Timeexpense.nebula
ECHO !ID;>> Timeexpense.nebula
ECHO Updated Timestamp;>> Timeexpense.nebula
ECHO Updatedby Date;>> Timeexpense.nebula
ECHO };>> Timeexpense.nebula 
 ECHO @Package("Service") type  Timeexpenseline{ >> Timeexpenseline.nebula
ECHO Org;>> Timeexpenseline.nebula
ECHO Activity;>> Timeexpenseline.nebula
ECHO Bpartner;>> Timeexpenseline.nebula
ECHO Campaign;>> Timeexpenseline.nebula
ECHO Currency;>> Timeexpenseline.nebula
ECHO Invoiceline;>> Timeexpenseline.nebula
ECHO Orderline;>> Timeexpenseline.nebula
ECHO Projectphase;>> Timeexpenseline.nebula
ECHO Projecttask;>> Timeexpenseline.nebula
ECHO Project;>> Timeexpenseline.nebula
ECHO Uom;>> Timeexpenseline.nebula
ECHO Convertedamt Amount;>> Timeexpenseline.nebula
ECHO Created Timestamp;>> Timeexpenseline.nebula
ECHO Createdby Date;>> Timeexpenseline.nebula
ECHO Dateexpense Date;>> Timeexpenseline.nebula
ECHO Description;>> Timeexpenseline.nebula
ECHO Expenseamt Amount;>> Timeexpenseline.nebula
ECHO Invoiceprice Price;>> Timeexpenseline.nebula
ECHO Isactive YesNo;>> Timeexpenseline.nebula
ECHO Isinvoiced YesNo;>> Timeexpenseline.nebula
ECHO Istimereport YesNo;>> Timeexpenseline.nebula
ECHO Line Number;>> Timeexpenseline.nebula
ECHO Product;>> Timeexpenseline.nebula
ECHO Note;>> Timeexpenseline.nebula
ECHO Priceinvoiced Price;>> Timeexpenseline.nebula
ECHO Pricereimbursed Price;>> Timeexpenseline.nebula
ECHO Processed YesNo;>> Timeexpenseline.nebula
ECHO Qty Quantity;>> Timeexpenseline.nebula
ECHO Qtyinvoiced Quantity;>> Timeexpenseline.nebula
ECHO Qtyreimbursed Quantity;>> Timeexpenseline.nebula
ECHO Resourceassignment;>> Timeexpenseline.nebula
ECHO !ID;>> Timeexpenseline.nebula
ECHO Timeexpense;>> Timeexpenseline.nebula
ECHO Timetype;>> Timeexpenseline.nebula
ECHO Updated Timestamp;>> Timeexpenseline.nebula
ECHO Updatedby Date;>> Timeexpenseline.nebula
ECHO };>> Timeexpenseline.nebula 
 ECHO @Package("Service") type  Timetype{ >> Timetype.nebula
ECHO Org;>> Timetype.nebula
ECHO Created Timestamp;>> Timetype.nebula
ECHO Createdby Date;>> Timetype.nebula
ECHO Description;>> Timetype.nebula
ECHO Help;>> Timetype.nebula
ECHO Isactive YesNo;>> Timetype.nebula
ECHO !Name;>> Timetype.nebula
ECHO !ID;>> Timetype.nebula
ECHO Updated Timestamp;>> Timetype.nebula
ECHO Updatedby Date;>> Timetype.nebula
ECHO };>> Timetype.nebula 
 ECHO @Package("Service") type  Training{ >> Training.nebula
ECHO Org;>> Training.nebula
ECHO Taxcategory;>> Training.nebula
ECHO Uom;>> Training.nebula
ECHO Created Timestamp;>> Training.nebula
ECHO Createdby Date;>> Training.nebula
ECHO Description;>> Training.nebula
ECHO Descriptionurl URL;>> Training.nebula
ECHO Documentnote Note;>> Training.nebula
ECHO Help;>> Training.nebula
ECHO Imageurl URL;>> Training.nebula
ECHO Isactive YesNo;>> Training.nebula
ECHO Product_Category;>> Training.nebula
ECHO !Name;>> Training.nebula
ECHO Processing YesNo;>> Training.nebula
ECHO !ID;>> Training.nebula
ECHO Updated Timestamp;>> Training.nebula
ECHO Updatedby Date;>> Training.nebula
ECHO };>> Training.nebula 
 ECHO @Package("Service") type  Training_Class{ >> Training_Class.nebula
ECHO Org;>> Training_Class.nebula
ECHO Created Timestamp;>> Training_Class.nebula
ECHO Createdby Date;>> Training_Class.nebula
ECHO Enddate Date;>> Training_Class.nebula
ECHO Isactive YesNo;>> Training_Class.nebula
ECHO Product;>> Training_Class.nebula
ECHO !ID;>> Training_Class.nebula
ECHO Training;>> Training_Class.nebula
ECHO Startdate Date;>> Training_Class.nebula
ECHO Updated Timestamp;>> Training_Class.nebula
ECHO Updatedby Date;>> Training_Class.nebula
ECHO };>> Training_Class.nebula 
 ECHO @Package("Temporary Tables") type  Aging{ >> Aging.nebula
ECHO Org;>> Aging.nebula
ECHO !Pinstance;>> Aging.nebula
ECHO Activity;>> Aging.nebula
ECHO Bp_Group;>> Aging.nebula
ECHO !Bpartner;>> Aging.nebula
ECHO Campaign;>> Aging.nebula
ECHO !Currency;>> Aging.nebula


ECHO Project;>> Aging.nebula
ECHO Created Timestamp;>> Aging.nebula
ECHO Createdby Date;>> Aging.nebula
ECHO Daysdue Count;>> Aging.nebula
ECHO Due0 Number;>> Aging.nebula
ECHO Due0_30 Number;>> Aging.nebula
ECHO Due0_7 Number;>> Aging.nebula
ECHO Due1_7 Number;>> Aging.nebula
ECHO Due31_60 Number;>> Aging.nebula
ECHO Due31_Plus Number;>> Aging.nebula
ECHO Due61_90 Number;>> Aging.nebula
ECHO Due61_Plus Number;>> Aging.nebula
ECHO Due8_30 Number;>> Aging.nebula
ECHO Due91_Plus Number;>> Aging.nebula
ECHO Dueamt Amount;>> Aging.nebula
ECHO Duedate Date;>> Aging.nebula
ECHO Invoicedamt Amount;>> Aging.nebula
ECHO Isactive YesNo;>> Aging.nebula
ECHO Islistinvoices YesNo;>> Aging.nebula
ECHO Issotrx YesNo;>> Aging.nebula
ECHO Openamt Amount;>> Aging.nebula
ECHO Pastdue1_30 Number;>> Aging.nebula
ECHO Pastdue1_7 Number;>> Aging.nebula
ECHO Pastdue31_60 Number;>> Aging.nebula
ECHO Pastdue31_Plus Number;>> Aging.nebula
ECHO Pastdue61_90 Number;>> Aging.nebula
ECHO Pastdue61_Plus Number;>> Aging.nebula
ECHO Pastdue8_30 Number;>> Aging.nebula
ECHO Pastdue91_Plus Number;>> Aging.nebula
ECHO Pastdueamt Amount;>> Aging.nebula
ECHO Statementdate Date;>> Aging.nebula
ECHO Updated Timestamp;>> Aging.nebula
ECHO Updatedby Date;>> Aging.nebula
ECHO };>> Aging.nebula 
 ECHO @Package("Temporary Tables") type  Distributionrundetail{ >> Distributionrundetail.nebula
ECHO Org;>> Distributionrundetail.nebula
ECHO Bpartner;>> Distributionrundetail.nebula
ECHO Bpartner_Location;>> Distributionrundetail.nebula
ECHO Created Timestamp;>> Distributionrundetail.nebula
ECHO Createdby Date;>> Distributionrundetail.nebula
ECHO Isactive YesNo;>> Distributionrundetail.nebula
ECHO !Distributionlistline;>> Distributionrundetail.nebula
ECHO !Distributionlist;>> Distributionrundetail.nebula
ECHO !Distributionrunline;>> Distributionrundetail.nebula
ECHO !Distributionrun;>> Distributionrundetail.nebula
ECHO Product;>> Distributionrundetail.nebula
ECHO Minqty Quantity;>> Distributionrundetail.nebula
ECHO Qty Quantity;>> Distributionrundetail.nebula
ECHO Ratio;>> Distributionrundetail.nebula
ECHO Updated Timestamp;>> Distributionrundetail.nebula
ECHO Updatedby Date;>> Distributionrundetail.nebula
ECHO };>> Distributionrundetail.nebula 
 ECHO @Package("Temporary Tables") type  Fact_Acct{ >> Fact_Acct.nebula
ECHO Org;>> Fact_Acct.nebula
ECHO Pinstance;>> Fact_Acct.nebula
ECHO Table;>> Fact_Acct.nebula
ECHO Accountname Name;>> Fact_Acct.nebula
ECHO Accounttype YesNo;>> Fact_Acct.nebula
ECHO Accountvalue String;>> Fact_Acct.nebula
ECHO Account Elementvalue;>> Fact_Acct.nebula
ECHO Amtacctcr Amount;>> Fact_Acct.nebula
ECHO Amtacctdr Amount;>> Fact_Acct.nebula
ECHO Bpartnername Name;>> Fact_Acct.nebula
ECHO Bpartnervalue String;>> Fact_Acct.nebula
ECHO Acctschema;>> Fact_Acct.nebula
ECHO Bpartner;>> Fact_Acct.nebula
ECHO Doctype;>> Fact_Acct.nebula
ECHO Period;>> Fact_Acct.nebula
ECHO Salesregion;>> Fact_Acct.nebula
ECHO Dateacct Date;>> Fact_Acct.nebula
ECHO Description;>> Fact_Acct.nebula
ECHO Documentno SeqNo;>> Fact_Acct.nebula
ECHO Acct;>> Fact_Acct.nebula
ECHO Product;>> Fact_Acct.nebula
ECHO !Name;>> Fact_Acct.nebula
ECHO Postingtype YesNo;>> Fact_Acct.nebula
ECHO Productname Name;>> Fact_Acct.nebula
ECHO Productvalue String;>> Fact_Acct.nebula
ECHO Qty Quantity;>> Fact_Acct.nebula

ECHO };>> Fact_Acct.nebula 
 ECHO @Package("Temporary Tables") type  Impformat{ >> Impformat.nebula
ECHO Org;>> Impformat.nebula
ECHO Binarydata Note;>> Impformat.nebula
ECHO Created Timestamp;>> Impformat.nebula
ECHO Createdby Date;>> Impformat.nebula
ECHO Isactive YesNo;>> Impformat.nebula
ECHO !Name;>> Impformat.nebula
ECHO !ID;>> Impformat.nebula
ECHO Updated Timestamp;>> Impformat.nebula
ECHO Updatedby Date;>> Impformat.nebula
ECHO Websession String;>> Impformat.nebula
ECHO };>> Impformat.nebula 
 ECHO @Package("Temporary Tables") type  Inventoryvalue{ >> Inventoryvalue.nebula
ECHO Org;>> Inventoryvalue.nebula
ECHO !Pinstance;>> Inventoryvalue.nebula
ECHO Currency;>> Inventoryvalue.nebula
ECHO Cost Number;>> Inventoryvalue.nebula
ECHO Costamt Amount;>> Inventoryvalue.nebula
ECHO Coststandard Number;>> Inventoryvalue.nebula
ECHO Coststandardamt Amount;>> Inventoryvalue.nebula
ECHO Datevalue Date;>> Inventoryvalue.nebula
ECHO !Attributesetinstance;>> Inventoryvalue.nebula
ECHO Costelement;>> Inventoryvalue.nebula
ECHO Pricelist_Version;>> Inventoryvalue.nebula
ECHO !Product;>> Inventoryvalue.nebula
ECHO !Warehouse;>> Inventoryvalue.nebula
ECHO Pricelimit Price;>> Inventoryvalue.nebula
ECHO Pricelimitamt Amount;>> Inventoryvalue.nebula
ECHO Pricelist Price;>> Inventoryvalue.nebula
ECHO Pricelistamt Amount;>> Inventoryvalue.nebula
ECHO Pricepo Price;>> Inventoryvalue.nebula
ECHO Pricepoamt Amount;>> Inventoryvalue.nebula
ECHO Pricestd Price;>> Inventoryvalue.nebula
ECHO Pricestdamt Amount;>> Inventoryvalue.nebula
ECHO Qtyonhand Quantity;>> Inventoryvalue.nebula
ECHO };>> Inventoryvalue.nebula 
 ECHO @Package("Temporary Tables") type  Invoicegl{ >> Invoicegl.nebula
ECHO Org;>> Invoicegl.nebula
ECHO !Pinstance;>> Invoicegl.nebula
ECHO Apar YesNo;>> Invoicegl.nebula
ECHO Amtacctbalance Amount;>> Invoicegl.nebula
ECHO Amtrevalcr Amount;>> Invoicegl.nebula
ECHO Amtrevalcrdiff Amount;>> Invoicegl.nebula
ECHO Amtrevaldr Amount;>> Invoicegl.nebula
ECHO Amtrevaldrdiff Amount;>> Invoicegl.nebula
ECHO Amtsourcebalance Amount;>> Invoicegl.nebula
ECHO Conversiontypereval Conversiontype;>> Invoicegl.nebula
ECHO Doctypereval Doctype;>> Invoicegl.nebula
ECHO !Invoice;>> Invoicegl.nebula
ECHO Created Timestamp;>> Invoicegl.nebula
ECHO Createdby Date;>> Invoicegl.nebula
ECHO Datereval Date;>> Invoicegl.nebula
ECHO !Acct;>> Invoicegl.nebula
ECHO Grandtotal Number;>> Invoicegl.nebula
ECHO Isactive YesNo;>> Invoicegl.nebula
ECHO Isallcurrencies YesNo;>> Invoicegl.nebula
ECHO Openamt Amount;>> Invoicegl.nebula
ECHO Percentgl Number;>> Invoicegl.nebula
ECHO Updated Timestamp;>> Invoicegl.nebula
ECHO Updatedby Date;>> Invoicegl.nebula
ECHO };>> Invoicegl.nebula 
 ECHO @Package("Temporary Tables") type  Locatorvisits{ >> Locatorvisits.nebula
ECHO Org;>> Locatorvisits.nebula
ECHO Pinstance;>> Locatorvisits.nebula
ECHO Locator;>> Locatorvisits.nebula
ECHO Product;>> Locatorvisits.nebula
ECHO Warehouse;>> Locatorvisits.nebula
ECHO Movementdate Date;>> Locatorvisits.nebula
ECHO Pickvisits Count;>> Locatorvisits.nebula
ECHO Putawayvisits Count;>> Locatorvisits.nebula
ECHO Replenishmentvisits Count;>> Locatorvisits.nebula
ECHO Totalvisits Count;>> Locatorvisits.nebula
ECHO };>> Locatorvisits.nebula 
 ECHO @Package("Temporary Tables") type  Replenish{ >> Replenish.nebula
ECHO Org;>> Replenish.nebula
ECHO !Pinstance;>> Replenish.nebula
ECHO Bpartner;>> Replenish.nebula
ECHO Doctype;>> Replenish.nebula
ECHO Level_Max Long;>> Replenish.nebula
ECHO Level_Min Long;>> Replenish.nebula
ECHO !Product;>> Replenish.nebula
ECHO Warehousesource Warehouse;>> Replenish.nebula
ECHO !Warehouse;>> Replenish.nebula
ECHO Order_Min Number;>> Replenish.nebula
ECHO Order_Pack Number;>> Replenish.nebula
ECHO Qtyonhand Quantity;>> Replenish.nebula
ECHO Qtyordered Quantity;>> Replenish.nebula
ECHO Qtyreserved Quantity;>> Replenish.nebula
ECHO Qtytoorder Quantity;>> Replenish.nebula
ECHO Replenishtype YesNo;>> Replenish.nebula
ECHO Replenishmentcreate Attr;>> Replenish.nebula
ECHO };>> Replenish.nebula 
 ECHO @Package("Temporary Tables") type  Report{ >> Report.nebula
ECHO Col_0 Number;>> Report.nebula
ECHO Col_1 Number;>> Report.nebula
ECHO Col_10 Number;>> Report.nebula
ECHO Col_11 Number;>> Report.nebula
ECHO Col_12 Number;>> Report.nebula
ECHO Col_13 Number;>> Report.nebula
ECHO Col_14 Number;>> Report.nebula
ECHO Col_15 Number;>> Report.nebula
ECHO Col_16 Number;>> Report.nebula
ECHO Col_17 Number;>> Report.nebula
ECHO Col_18 Number;>> Report.nebula
ECHO Col_19 Number;>> Report.nebula
ECHO Col_2 Number;>> Report.nebula
ECHO Col_20 Number;>> Report.nebula
ECHO Col_3 Number;>> Report.nebula
ECHO Col_4 Number;>> Report.nebula
ECHO Col_5 Number;>> Report.nebula
ECHO Col_6 Number;>> Report.nebula
ECHO Col_7 Number;>> Report.nebula
ECHO Col_8 Number;>> Report.nebula
ECHO Col_9 Number;>> Report.nebula
ECHO Description;>> Report.nebula

ECHO Levelno SeqNo;>> Report.nebula
ECHO !Name;>> Report.nebula
ECHO !Reportline;>> Report.nebula

ECHO SeqNo;>> Report.nebula
ECHO };>> Report.nebula 
 ECHO @Package("Temporary Tables") type  Reportstatement{ >> Reportstatement.nebula
ECHO Amtacctcr Amount;>> Reportstatement.nebula
ECHO Amtacctdr Amount;>> Reportstatement.nebula
ECHO Balance Number;>> Reportstatement.nebula
ECHO Dateacct Date;>> Reportstatement.nebula
ECHO Description;>> Reportstatement.nebula

ECHO Levelno SeqNo;>> Reportstatement.nebula
ECHO !Name;>> Reportstatement.nebula
ECHO Qty Quantity;>> Reportstatement.nebula
ECHO };>> Reportstatement.nebula 
 ECHO @Package("Temporary Tables") type  Selection{ >> Selection.nebula
ECHO };>> Selection.nebula 
 

ECHO };>> Selection2.nebula 
 ECHO @Package("Temporary Tables") type  Spool{ >> Spool.nebula
ECHO Msgtext Note;>> Spool.nebula
ECHO SeqNo;>> Spool.nebula
ECHO };>> Spool.nebula 
 ECHO @Package("Temporary Tables") type  Taskhandlerperf{ >> Taskhandlerperf.nebula
ECHO Org;>> Taskhandlerperf.nebula
ECHO Pinstance;>> Taskhandlerperf.nebula
ECHO User;>> Taskhandlerperf.nebula
ECHO Created Timestamp;>> Taskhandlerperf.nebula
ECHO Createdby Date;>> Taskhandlerperf.nebula
ECHO Isactive YesNo;>> Taskhandlerperf.nebula
ECHO Linesputaway Number;>> Taskhandlerperf.nebula
ECHO Linesreplenished Number;>> Taskhandlerperf.nebula
ECHO Warehouse;>> Taskhandlerperf.nebula
ECHO Movementdate Date;>> Taskhandlerperf.nebula
ECHO Orderlinespicked Count;>> Taskhandlerperf.nebula
ECHO Orderspicked Count;>> Taskhandlerperf.nebula
ECHO Trolleyspicked Number;>> Taskhandlerperf.nebula
ECHO Updated Timestamp;>> Taskhandlerperf.nebula
ECHO Updatedby Date;>> Taskhandlerperf.nebula
ECHO };>> Taskhandlerperf.nebula 
 ECHO @Package("Temporary Tables") type  Transaction{ >> Transaction.nebula
ECHO Org;>> Transaction.nebula
ECHO !Pinstance;>> Transaction.nebula
ECHO Projectissue;>> Transaction.nebula
ECHO Project;>> Transaction.nebula
ECHO Created Timestamp;>> Transaction.nebula
ECHO Createdby Date;>> Transaction.nebula
ECHO Isactive YesNo;>> Transaction.nebula
ECHO Attributesetinstance;>> Transaction.nebula
ECHO Inoutline;>> Transaction.nebula
ECHO Inout;>> Transaction.nebula
ECHO Inventoryline;>> Transaction.nebula
ECHO Inventory;>> Transaction.nebula
ECHO Locator;>> Transaction.nebula
ECHO Movementline;>> Transaction.nebula
ECHO Movement;>> Transaction.nebula
ECHO !Product;>> Transaction.nebula
ECHO Productionline;>> Transaction.nebula
ECHO Production;>> Transaction.nebula
ECHO !ID;>> Transaction.nebula
ECHO Movementdate Date;>> Transaction.nebula
ECHO Movementqty Quantity;>> Transaction.nebula
ECHO Movementtype Attr;>> Transaction.nebula
ECHO Search_Inout Inout;>> Transaction.nebula
ECHO Search_Invoice Invoice;>> Transaction.nebula
ECHO Search_Order Order;>> Transaction.nebula
ECHO Updated Timestamp;>> Transaction.nebula
ECHO Updatedby Date;>> Transaction.nebula
ECHO };>> Transaction.nebula 
 ECHO @Package("Temporary Tables") type  Trialbalance{ >> Trialbalance.nebula
ECHO Orgtrx Org;>> Trialbalance.nebula
ECHO Org;>> Trialbalance.nebula
ECHO Pinstance;>> Trialbalance.nebula
ECHO Table;>> Trialbalance.nebula
ECHO Asset;>> Trialbalance.nebula
ECHO Accountvalue String;>> Trialbalance.nebula
ECHO Account Elementvalue;>> Trialbalance.nebula
ECHO Amtacctbalance Amount;>> Trialbalance.nebula
ECHO Amtacctcr Amount;>> Trialbalance.nebula
ECHO Amtacctdr Amount;>> Trialbalance.nebula
ECHO Amtsourcebalance Amount;>> Trialbalance.nebula
ECHO Amtsourcecr Amount;>> Trialbalance.nebula
ECHO Amtsourcedr Amount;>> Trialbalance.nebula
ECHO Acctschema;>> Trialbalance.nebula
ECHO Activity;>> Trialbalance.nebula
ECHO Bpartner;>> Trialbalance.nebula
ECHO Campaign;>> Trialbalance.nebula
ECHO Currency;>> Trialbalance.nebula
ECHO Locfrom Location;>> Trialbalance.nebula
ECHO Locto Location;>> Trialbalance.nebula
ECHO Period;>> Trialbalance.nebula
ECHO Project;>> Trialbalance.nebula
ECHO Salesregion;>> Trialbalance.nebula
ECHO Tax;>> Trialbalance.nebula
ECHO Uom;>> Trialbalance.nebula
ECHO Created Timestamp;>> Trialbalance.nebula
ECHO Createdby Date;>> Trialbalance.nebula
ECHO Dateacct Date;>> Trialbalance.nebula
ECHO Datetrx Date;>> Trialbalance.nebula
ECHO Description;>> Trialbalance.nebula

ECHO Budget;>> Trialbalance.nebula
ECHO Category;>> Trialbalance.nebula

ECHO Locator;>> Trialbalance.nebula
ECHO Product;>> Trialbalance.nebula

ECHO Postingtype YesNo;>> Trialbalance.nebula
ECHO Qty Quantity;>> Trialbalance.nebula

ECHO Updated Timestamp;>> Trialbalance.nebula
ECHO Updatedby Date;>> Trialbalance.nebula
ECHO User1 Elementvalue;>> Trialbalance.nebula
ECHO User2 Elementvalue;>> Trialbalance.nebula
ECHO };>> Trialbalance.nebula 
 ECHO @Package("Temporary Tables") type  Warehousestock{ >> Warehousestock.nebula
ECHO Org;>> Warehousestock.nebula
ECHO Pinstance;>> Warehousestock.nebula
ECHO Uom;>> Warehousestock.nebula
ECHO Consumedpastsevendays Count;>> Warehousestock.nebula
ECHO Consumedpastthirtydays Count;>> Warehousestock.nebula
ECHO Created Timestamp;>> Warehousestock.nebula
ECHO Createdby Date;>> Warehousestock.nebula
ECHO Dayscover Count;>> Warehousestock.nebula
ECHO Isactive YesNo;>> Warehousestock.nebula
ECHO Product;>> Warehousestock.nebula
ECHO Warehouse;>> Warehousestock.nebula
ECHO Qtyallocated Quantity;>> Warehousestock.nebula
ECHO Qtyavailable Quantity;>> Warehousestock.nebula
ECHO Qtyonhand Quantity;>> Warehousestock.nebula
ECHO Updated Timestamp;>> Warehousestock.nebula
ECHO Updatedby Date;>> Warehousestock.nebula
ECHO };>> Warehousestock.nebula 
 ECHO @Package("Temporary Tables") type  Wave{ >> Wave.nebula
ECHO Waveline;>> Wave.nebula
ECHO SeqNo;>> Wave.nebula
ECHO ID;>> Wave.nebula
ECHO };>> Wave.nebula 
 
























































ECHO @Package("Web") type  Advertisement{ >> Advertisement.nebula
ECHO Org;>> Advertisement.nebula
ECHO User;>> Advertisement.nebula
ECHO Adtext Note;>> Advertisement.nebula
ECHO Bpartner;>> Advertisement.nebula
ECHO Created Timestamp;>> Advertisement.nebula
ECHO Createdby Date;>> Advertisement.nebula
ECHO Description;>> Advertisement.nebula
ECHO Help;>> Advertisement.nebula
ECHO Imageurl URL;>> Advertisement.nebula
ECHO Isactive YesNo;>> Advertisement.nebula
ECHO Isselfservice YesNo;>> Advertisement.nebula
ECHO Name;>> Advertisement.nebula
ECHO Processing YesNo;>> Advertisement.nebula
ECHO Publishstatus YesNo;>> Advertisement.nebula
ECHO !Updated Timestamp;>> Advertisement.nebula
ECHO Updatedby Date;>> Advertisement.nebula
ECHO Validfrom Date;>> Advertisement.nebula
ECHO Validto Date;>> Advertisement.nebula
ECHO Version Number;>> Advertisement.nebula
ECHO ID;>> Advertisement.nebula
ECHO Clickcount;>> Advertisement.nebula
ECHO Countercount;>> Advertisement.nebula
ECHO !Webparam1 Note;>> Advertisement.nebula
ECHO Webparam2 Note;>> Advertisement.nebula
ECHO Webparam3 Note;>> Advertisement.nebula
ECHO Webparam4 Note;>> Advertisement.nebula
ECHO };>> Advertisement.nebula 
 ECHO @Package("Web") type  Basket{ >> Basket.nebula
ECHO Org;>> Basket.nebula
ECHO User;>> Basket.nebula
ECHO Bpartner;>> Basket.nebula
ECHO Created Timestamp;>> Basket.nebula
ECHO Createdby Date;>> Basket.nebula
ECHO Email;>> Basket.nebula
ECHO Isactive YesNo;>> Basket.nebula
ECHO Pricelist;>> Basket.nebula

ECHO Updated Timestamp;>> Basket.nebula
ECHO Updatedby Date;>> Basket.nebula
ECHO ID;>> Basket.nebula
ECHO };>> Basket.nebula 
 ECHO @Package("Web") type  Basketline{ >> Basketline.nebula
ECHO Org;>> Basketline.nebula
ECHO Created Timestamp;>> Basketline.nebula
ECHO Createdby Date;>> Basketline.nebula
ECHO Description;>> Basketline.nebula
ECHO !Isactive YesNo;>> Basketline.nebula
ECHO Line Number;>> Basketline.nebula
ECHO Product;>> Basketline.nebula
ECHO Price;>> Basketline.nebula
ECHO Product String;>> Basketline.nebula
ECHO Qty Quantity;>> Basketline.nebula
ECHO Updated Timestamp;>> Basketline.nebula
ECHO Updatedby Date;>> Basketline.nebula
ECHO !ID;>> Basketline.nebula
ECHO Basket;>> Basketline.nebula
ECHO };>> Basketline.nebula 
 ECHO @Package("Web") type  Click{ >> Click.nebula
ECHO Org;>> Click.nebula
ECHO !User;>> Click.nebula
ECHO Acceptlanguage Language;>> Click.nebula
ECHO Created Timestamp;>> Click.nebula
ECHO Createdby Date;>> Click.nebula
ECHO Email;>> Click.nebula
ECHO Isactive YesNo;>> Click.nebula
ECHO Processed YesNo;>> Click.nebula
ECHO Referrer String;>> Click.nebula
ECHO Remote_Addr URL;>> Click.nebula
ECHO Remote_Host Host;>> Click.nebula
ECHO Targeturl URL;>> Click.nebula
ECHO !Updated Timestamp;>> Click.nebula
ECHO Updatedby Date;>> Click.nebula
ECHO Useragent String;>> Click.nebula
ECHO Clickcount;>> Click.nebula
ECHO !ID;>> Click.nebula
ECHO };>> Click.nebula 
 ECHO @Package("Web") type  Clickcount{ >> Clickcount.nebula
ECHO Org;>> Clickcount.nebula
ECHO !Bpartner;>> Clickcount.nebula
ECHO Created Timestamp;>> Clickcount.nebula
ECHO Createdby Date;>> Clickcount.nebula
ECHO Description;>> Clickcount.nebula
ECHO Isactive YesNo;>> Clickcount.nebula
ECHO Name;>> Clickcount.nebula
ECHO Targeturl URL;>> Clickcount.nebula
ECHO Updated Timestamp;>> Clickcount.nebula
ECHO Updatedby Date;>> Clickcount.nebula
ECHO ID;>> Clickcount.nebula
ECHO };>> Clickcount.nebula 
 ECHO @Package("Web") type  Counter{ >> Counter.nebula
ECHO Org;>> Counter.nebula
ECHO !User;>> Counter.nebula
ECHO Acceptlanguage Language;>> Counter.nebula
ECHO Created Timestamp;>> Counter.nebula
ECHO Createdby Date;>> Counter.nebula
ECHO Email;>> Counter.nebula
ECHO Isactive YesNo;>> Counter.nebula
ECHO Pageurl URL;>> Counter.nebula
ECHO Processed YesNo;>> Counter.nebula
ECHO Referrer String;>> Counter.nebula
ECHO Remote_Addr URL;>> Counter.nebula
ECHO Remote_Host Host;>> Counter.nebula
ECHO Updated Timestamp;>> Counter.nebula
ECHO Updatedby Date;>> Counter.nebula
ECHO Useragent String;>> Counter.nebula
ECHO Countercount;>> Counter.nebula
ECHO ID;>> Counter.nebula
ECHO };>> Counter.nebula 
 ECHO @Package("Web") type  Countercount{ >> Countercount.nebula
ECHO Org;>> Countercount.nebula
ECHO Bpartner;>> Countercount.nebula
ECHO Created Timestamp;>> Countercount.nebula
ECHO Createdby Date;>> Countercount.nebula
ECHO Description;>> Countercount.nebula
ECHO Isactive YesNo;>> Countercount.nebula
ECHO Name;>> Countercount.nebula
ECHO Pageurl URL;>> Countercount.nebula
ECHO !Updated Timestamp;>> Countercount.nebula
ECHO Updatedby Date;>> Countercount.nebula
ECHO ID;>> Countercount.nebula
ECHO };>> Countercount.nebula 
 ECHO @Package("Web") type  Mailmsg{ >> Mailmsg.nebula
ECHO Org;>> Mailmsg.nebula
ECHO Created Timestamp;>> Mailmsg.nebula
ECHO Createdby Date;>> Mailmsg.nebula
ECHO Description;>> Mailmsg.nebula
ECHO Isactive YesNo;>> Mailmsg.nebula
ECHO !Mailmsgtype Attr;>> Mailmsg.nebula
ECHO Message MSG;>> Mailmsg.nebula
ECHO Message2 MSG;>> Mailmsg.nebula
ECHO Message3 MSG;>> Mailmsg.nebula
ECHO Name;>> Mailmsg.nebula
ECHO Subject;>> Mailmsg.nebula
ECHO Updated Timestamp;>> Mailmsg.nebula
ECHO Updatedby Date;>> Mailmsg.nebula
ECHO ID;>> Mailmsg.nebula
ECHO Store;>> Mailmsg.nebula
ECHO };>> Mailmsg.nebula 
 ECHO @Package("Web") type  Mailmsg_Trl{ >> Mailmsg_Trl.nebula
ECHO !Ad_Language Language;>> Mailmsg_Trl.nebula
ECHO Org;>> Mailmsg_Trl.nebula
ECHO Created Timestamp;>> Mailmsg_Trl.nebula
ECHO Createdby Date;>> Mailmsg_Trl.nebula
ECHO Isactive YesNo;>> Mailmsg_Trl.nebula
ECHO Istranslated YesNo;>> Mailmsg_Trl.nebula
ECHO Message MSG;>> Mailmsg_Trl.nebula
ECHO Message2 MSG;>> Mailmsg_Trl.nebula
ECHO Message3 MSG;>> Mailmsg_Trl.nebula
ECHO Subject;>> Mailmsg_Trl.nebula
ECHO !Updated Timestamp;>> Mailmsg_Trl.nebula
ECHO Updatedby Date;>> Mailmsg_Trl.nebula
ECHO Mailmsg;>> Mailmsg_Trl.nebula
ECHO };>> Mailmsg_Trl.nebula 
 ECHO @Package("Web") type  Store{ >> Store.nebula
ECHO Org;>> Store.nebula
ECHO Role;>> Store.nebula
ECHO Paymentterm;>> Store.nebula
ECHO Created Timestamp;>> Store.nebula
ECHO Createdby Date;>> Store.nebula
ECHO Description;>> Store.nebula
ECHO Emailfooter Note;>> Store.nebula
ECHO Emailheader Note;>> Store.nebula
ECHO Help;>> Store.nebula
ECHO Isactive YesNo;>> Store.nebula
ECHO Isdefault YesNo;>> Store.nebula
ECHO Ismenuassets YesNo;>> Store.nebula
ECHO Ismenucontact YesNo;>> Store.nebula
ECHO Ismenuinterests YesNo;>> Store.nebula
ECHO Ismenuinvoices YesNo;>> Store.nebula
ECHO Ismenuorders YesNo;>> Store.nebula
ECHO Ismenupayments YesNo;>> Store.nebula
ECHO Ismenuregistrations YesNo;>> Store.nebula
ECHO Ismenurequests YesNo;>> Store.nebula
ECHO Ismenurfqs YesNo;>> Store.nebula
ECHO Ismenushipments YesNo;>> Store.nebula
ECHO Pricelist;>> Store.nebula
ECHO Warehouse;>> Store.nebula
ECHO Name;>> Store.nebula
ECHO Salesrep User;>> Store.nebula
ECHO Stylesheet String;>> Store.nebula
ECHO URL;>> Store.nebula
ECHO Updated Timestamp;>> Store.nebula
ECHO Updatedby Date;>> Store.nebula
ECHO Wstoreemail Email;>> Store.nebula
ECHO Wstoreuser Name;>> Store.nebula
ECHO Wstoreuserpw String;>> Store.nebula
ECHO ID;>> Store.nebula
ECHO Webcontext Description;>> Store.nebula
ECHO Webinfo Note;>> Store.nebula
ECHO Weborderemail Email;>> Store.nebula
ECHO Webparam1 Note;>> Store.nebula
ECHO Webparam2 Note;>> Store.nebula
ECHO Webparam3 Note;>> Store.nebula
ECHO Webparam4 Note;>> Store.nebula
ECHO Webparam5 Note;>> Store.nebula
ECHO Webparam6 Note;>> Store.nebula
ECHO };>> Store.nebula 
 ECHO @Package("Web") type  Store_Trl{ >> Store_Trl.nebula
ECHO Ad_Language Language;>> Store_Trl.nebula
ECHO Org;>> Store_Trl.nebula
ECHO Created Timestamp;>> Store_Trl.nebula
ECHO Createdby Date;>> Store_Trl.nebula
ECHO Emailfooter Note;>> Store_Trl.nebula
ECHO Emailheader Note;>> Store_Trl.nebula
ECHO Isactive YesNo;>> Store_Trl.nebula
ECHO Istranslated YesNo;>> Store_Trl.nebula
ECHO Updated Timestamp;>> Store_Trl.nebula
ECHO Updatedby Date;>> Store_Trl.nebula
ECHO Store;>> Store_Trl.nebula
ECHO Webinfo Note;>> Store_Trl.nebula
ECHO Webparam1 Note;>> Store_Trl.nebula
ECHO Webparam2 Note;>> Store_Trl.nebula
ECHO Webparam3 Note;>> Store_Trl.nebula
ECHO Webparam4 Note;>> Store_Trl.nebula
ECHO Webparam5 Note;>> Store_Trl.nebula
ECHO Webparam6 Note;>> Store_Trl.nebula
ECHO };>> Store_Trl.nebula 
 

