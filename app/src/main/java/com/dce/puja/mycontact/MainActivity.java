package com.dce.puja.mycontact;

import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
 import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.intent;
import android.Content.OperationApplicationException;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    OnClickListener addClicklistener=new OnClickListener()
        @Override
        public void onClick(View v) {
            EditText etName=(EditText) findViewById(R.id.et_name);
            EditText etMobile=(EditText)findViewById(R.id.et_mobile_phone);
            EditText etHomePhone=(EditText)  findViewById(R.id.et_home_phone)  ;
            EditText etHomeEmail=(EditText) findViewById(R.id.et.home_email);
            EditText etWorkEmail(EditText)findViewById(R.id.et.work_email);
            ArrayList<ContentProviderOperation> ops=new ArrayList<ContentProviderOperation>();
            int rawContactID=ops.size();
            ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.Data.RAW_CONTACT_ID,rawContactID)
                    .withValue(RawContacts.ACCOUNT_NAME,null)
                    .build());
            // Adding insert operation to operations list
            // to insert display name in the table ContactsContract.Data
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                    .withValue(ContactsContract.Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(StructuredName.DISPLAY_NAME, etName.getText().toString())
                    .build());

            // Adding
            in the table ContactsContract.Data
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                    .withValue(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
                    .withValue(Phone.NUMBER, etMobile.getText().toString())
                    .withValue(Phone.TYPE, CommonDataKinds.Phone.TYPE_MOBILE)
                    to operations list
                    // to  insert Home Phone Number in the table ContactsContract.Data
                    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                            .withValue(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
                            .withValue(Phone.NUMBER, etHomePhone.getText().toString())
                            .withValue(Phone.TYPE, Phone.TYPE_HOME)
                            .build());
            // to insert Home Email in the table ContactsContract.Data
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                    .withValue(ContactsContract.Data.MIMETYPE, Email.CONTENT_ITEM_TYPE)
                    .withValue(Email.ADDRESS, etHomeEmail.getText().toString())
                    .withValue(Email.TYPE, Email.TYPE_HOME)
                    .build());
            // to insert Work Email in the table ContactsContract.Data
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                    .withValue(ContactsContract.Data.MIMETYPE, Email.CONTENT_ITEM_TYPE)
                    .withValue(Email.ADDRESS, etWorkEmail.getText().toString())
                    .withValue(Email.TYPE, Email.TYPE_WORK)
                    .build());
            try{
                // Executing all the insert operations as a single database transaction
                getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
                Toast.makeText(getBaseContext(), "Contact is successfully added", Toast.LENGTH_SHORT).show();
            }catch (RemoteException e) {
                e.printStackTrace();
            }catch (OperationApplicationException e) {
                e.printStackTrace();
            }
        } };

// Creating a button click listener for the "Add Contact" button
OnClickListener contactsClickListener = new OnClickListener() {
@override
public void onClick(View v) {
        // Creating an intent to open Android's Contacts List
        Intent contacts = new Intent(Intent.ACTION_VIEW,ContactsContract.Contacts.CONTENT_URI);

        // Starting the activity
        startActivity(contacts);
        }
        };

        // Getting reference to "Add Contact" button
        Button btnAdd = (Button) findViewById(R.id.btn_add);

        // Getting reference to "Contacts List" button
        Button btnContacts = (Button) findViewById(R.id.btn_contacts);

        // Setting click listener for the "Add Contact" button
        btnAdd.setOnClickListener(addClickListener);

        // Setting click listener for the "List Contacts" button
        btnContacts.setOnClickListener(contactsClickListener);
        }

@Override
public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_main,menu);
        return true;
        }







