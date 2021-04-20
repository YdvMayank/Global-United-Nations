package dev.rao.globalunitednations.mun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import dev.rao.globalunitednations.R;
import dev.rao.globalunitednations.data.DataActivity;
import dev.rao.globalunitednations.internship.Internship;

import dev.rao.globalunitednations.model.Mun_Rules;
import dev.rao.globalunitednations.news.NewsActivity;


public class MunActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Mun_Rules> rulesList;
    Button _btnUSA, _btnUN4Mun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mun);

        _btnUN4Mun = (Button) findViewById(R.id.btn_UN4MUN);
        _btnUN4Mun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MunActivity.this, PdfOpenner.class);
                i.putExtra("pdf", "MUN");
                startActivity(i);
            }
        });
        _btnUSA = (Button) findViewById(R.id.btn_USA);
        _btnUSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MunActivity.this, PdfOpenner.class);
                i.putExtra("pdf", "USA");
                startActivity(i);
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_barM);
        bottomNavigationView.setSelectedItemId(R.id.nav_mun);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_news:
                        startActivity(new Intent(getApplicationContext(), NewsActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.nav_Internship:
                        startActivity(new Intent(getApplicationContext(), Internship.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.nav_mun:
                        return true;

                    case R.id.nav_unData:
                        startActivity(new Intent(getApplicationContext(), DataActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });

        recyclerView = findViewById(R.id.mun_RecyclerView);
        initRecyclerView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initRecyclerView() {
        rulesList = new ArrayList<>();

        rulesList.add(new Mun_Rules("CHAIRS", "1. The Chairs shall act as the Directors of the Committee and shall preside over its Sessions. They shall declare the opening and closure of each Session, open and close Floor to the Points and Motions and ensure the observance of the RoP.\n" +
                "2. The Chairs shall have the authority in regards to the proceedings of the Committee; they shall have the right to rule out the Points and Motions they deem to be not constructive. They shall keep the Committee in order and direct them towards consensus, which may be done by their advice on both Substantial and Procedural Matters of the Committee.\n" +
                "3. The Chairpersons are in all time responsible to the Secretary-General."));

        rulesList.add(new Mun_Rules("ATTENDANCE (MOTION TO VERIFY THE QUORUM)", "1. At the beginning of each Session and upon the Motion to verify the Quorum, Chairpersons shall call in an alphabetical order on all Member States in order to state their status of attendance. Member States may reply “present” or “present and voting”. Representatives stating just “present and voting” shall have no right to abstain from any vote on the substantial matter.\n" +
                "2. If any of the Representatives were not present during the Roll Call, upon their arrival they shall send a note to the Chairpersons clarifying their status of presence"));

        rulesList.add(new Mun_Rules("QUORUM", "1. The quorum signifies the minimum number of delegates who need to be present in order to open Session for the debate.\n" +
                "2. The quorum is met when at least one-third of all delegates registered are present at the committee session. The quorum shall be verified at the beginning of each session by the Roll Call."));

        rulesList.add(new Mun_Rules("SETTING THE AGENDA", "1. Setting the agenda is the first step to be considered by the committee.\n\t" +
                "a. The motion in order to put a topic area on the agenda should be made first. This motion requires a second.\n" +
                "\tb. Delegates can propose only topics as put forward by the Secretariat in the provisional agenda, i.e. the topics which were set by the Secretariat prior the conference.\n" +
                "\tc. In case there is no opposition to the motion, the motion is considered to be adopted.\n" +
                "\td. In case of the opposition against the proposed order of the agenda, a Speaker ́s List of either two or four delegates “for” and “against” the order will be established- the number of speakers should be equal for both cases.\n" +
                "\te. After the exhaustion of the Speaker ́s List, the Committee will immediately vote on the motion. For this vote, which is procedural, the simple majority is required for the motion to pass. In the case that the motion fails to pass, the Committee will automatically adopt the second topic, i.e. the inversed order of the topics will be introduced."));

        rulesList.add(new Mun_Rules("DEBATE", "The three modes of Debate can be introduced during ModelUN Conferences:\n" +
                "\t1. Formal Debate (General Speaker ́s List)\n" +
                "\t2. Moderated Caucus.\n" +
                "\t3. Un-Moderated CaucusUpon setting the agenda, the Speaker ́s List is open- either in the discretion of Chairs or by delegates proposing a Motion to Open Speaker ́s List"));

        rulesList.add(new Mun_Rules("SPEAKER ́S LIST", "1. The General Speaker ́s List shows the order of speakers for the topic being on agenda and is open for the whole duration of the discussion.\n" +
                "2. Once the Speaker ́s List is open, any delegate can be added to the Speaker ́s List by:\n" +
                "\ta. Being recognized by raising their placard during the call made by Chairs.\n" +
                "\tb. By sending the official note to the Chairpersons.\n" +
                "3. The delegate already added to the Speaker ́s List cannot be added for the second time. Once the delegate makes his speech, he/she can submit a request to be put on the Speaker ́s List again. The delegate can also withdraw himself from the Speaker ́s List by sending a note to the Chairs.\n" +
                "4. Speaking time for the General Speaker ́s List is set to 90 seconds. The time limit can be altered by a Motion to Change the Speaker ́s Time.\n" +
                "5. If there is a motion adopted altering the mode of the debate, the general Speaker ́s List shall be suspended for the duration of the motion and shall be resumed afterward.\n" +
                "6. Once the Speaker ́s List is exhausted, the debate is considered closed and the Committee automatically moves into the voting procedure on the document which is currently being discussed within the set topic.\n" +
                "7. The Speaker ́s List is open just for the topic currently being on the agenda"));

        rulesList.add(new Mun_Rules("MODERATED CAUCUS", "1. The moderated caucus is aimed to facilitate and accelerate the discussion on the issues deemed as essential and critical for the topic on agenda. Instead of being added to the general Speaker ́s List, delegates wishing to speak shall raise their placards and be called upon at the discretion of the Chairs.\n" +
                "2. The motion for the moderated caucus can be introduced by any of the delegates once the Floor is open for Points and Motions. The delegate shall specify the total time of the Motion (not exceeding 20 minutes), individual speaker ́s time for each of the delegates (not exceeding the speaker ́s time set for the general Speaker ́s List) and the purpose of the Motion. The purpose, i.e. the topic of the Motion shall be connected to the issue currently being discussed onthe agenda and shall be more particular that the general topic of thediscussion.\n" +
                "3. A Simple Majority of the quorum is required for the motion to pass. In case that there are being more Moderated Caucuses proposed, the Committee will vote upon them in descending order according to the total time of the Caucus (i.e. from the longest to theshortest) as set by the Chairs.\n" +
                "4. In case that the delegate will not take advantage of whole allottedtime assigned to him/her, the remaining time and the floor is being yielded to the Chairpersons automatically. During Moderated Caucus delegate cannot yield his time to other delegations or inquiries.\n" +
                "5. If no delegates are wishing to speak, the Moderated Caucus may be closed at the discretion of Chairpersons and the Committee will return back to the general Speaker ́s List.\n" +
                "6. A Moderated Caucus can be extended twice as long as the total time of the Caucus does not exceed the timeframe of the previous one.\n" +
                "7. The Motion can be ruled out of order at the discretion of the Chairs- such decision is not a subject to appeal"));

        rulesList.add(new Mun_Rules("UNMODERATED CAUCUS (INFORMAL DEBATE)", "1. Unmoderated Caucus is the most informal out of all forms of debate, during which delegates are able to discuss freely all issues with other delegations, lobby for their interests, resolve difficult questions about the topic on the agenda and create working papers \n" +
                "and resolutions.\n" +
                "2. The motion for Un-moderated caucus can be introduced by any of the delegates once the Floor is open for Points and Motions. The delegate shall specify the purpose of the motion and shall state the total time of the motion (not exceeding 30 minutes).\n" +
                "3. A Simple Majority of the quorum is required for the motion to pass. In case that there are being more Unmoderated Caucuses proposed, the committee will vote upon them in descending order according to the total time of the Caucus (i.e. from the longest to theshortest) as set by the Chairs. An Unmoderated Caucus can be extended twice as long as the total time of the Caucus does not exceed the timeframe of the previous one.\n" +
                "4. The Motion can be ruled out of order at the discretion of the Chairs- such decision is not a subject to appeal."));

        rulesList.add(new Mun_Rules("CLOSURE OF DEBATE", "1. Any delegate may at any time when the Floor is open move the Closure of the Debate regarding the current topic on the agenda which is being discussed. Such Motion for the Closure of the Debate can be introduced without the exhaustion of general Speaker ́s List and no matter whether any other delegation is wishing to speak. Motion for Closure of the Debate means the immediate termination of all discussion regarding the Topic Area and bringing all Draft Resolutions and Amendments to vote.\n" +
                "2. After the introduction of the motion, two delegates shall have the right to speak “for” and “against” the Motion proposed.\n" +
                "3. Upon their speeches, the committee proceeds with the voting procedure regarding the motion. A Qualified Majority of the established quorum is required for the motion to pass.\n" +
                "4. If more than a two-thirds majority is in favor of the closure of the debate, the committee automatically moves into the voting procedure on all draft resolutions and amendments as proposed \n" +
                "during the debate.\n" +
                "5. The Closure of Debate and Move to the Voting Procedure can be introduced together in one motion or can be separated, i.e. first the Motion for the Closure of the Debate to be proposed and after its approval, the delegate can introduce the second one. Latter can be applied in the case that the delegation is wishing to alter the type of voting, Divide the Question etc. as this cannot be done in the former.\n" +
                "6. The motion can be overruled by the Chairpersons. Such decision is subject to appeal."));

        rulesList.add(new Mun_Rules("MOTION TO TABLE THE DEBATE", "1. Any delegate may at any time when the Floor is open introduce the Motion to Table the Debate which temporarily suspends any discussions regarding the Topic being currently on agenda.\n" +
                "2. The Motion is debatable. Two or four Representatives can be selected to speak “for” and “against” the Motion. The Motion requires a Qualified Majority to pass.\n" +
                "3. The Motion to Resume the Debate shall automatically cancel the Motion to Table the Debate on a given Topic. The motion is non-debatable and requires a Simple Majority to Pass.\n" +
                "4. For both- Motion to Table, the Debate and Motion to Resume the Debate- the Chairpersons may rule the Motions out of order. Such decision is not subject to appeal."));

        rulesList.add(new Mun_Rules("SUSPENSION AND ADJOURNMENT OF THE MEETING", "1. The Suspension of the Meeting is the postponement of all functions of the Committee till the next session.\n" +
                "2. The Adjournment of the Meeting is the postponement of all functions of the Committee till the next edition of ModelUN Conference. Such Motion postpones all its work for the rest of the Sessions and conference overall.\n" +
                "3. Both Motions can be raised by a delegate any time when the Floor is open and requires a second. The house is required to vote on such motion immediately; a simple majority of the quorum is needed for the any of these two motions to pass.\n" +
                "4. The motions can be overruled by the Chairpersons. Such decision is not subject to appeal."));

        rulesList.add(new Mun_Rules("YIELDS", "A delegate who was granted the permission to speak by the Chairpersons shall have the right to yield his time- if remaining- to:\n" +
                "1. Yield to another delegate- the remaining time will be offered to another delegate as allotted by the former speaker. The delegate, if accepting the yield, cannot yield the floor to any other person with the exception of Chairpersons.\n" +
                "2. Yield to inquiries- If the delegate is open to questions, it is at the discretion of the Chairpersons to grant this right to any delegate willing to pose an inquiry to the delegate within the remaining time allocated to the delegate. Inquiries are not counted into the remaining speaker ́s time, unlike the answers provided by the delegate. The delegate who yielded his time to questions can refuseto answer any of them at his discretion. Also, Chairpersons shall call to order any delegate whose inquiry by its character does not comply with the standards.3. Yield back to the Chairs- if there is remaining time left, but the delegate is not wishing to answer any questions or give his allocated time to another delegate, he /she can yield his time back to the Chairpersons, who will proceed with another delegate on the Speaker ́s List/ wishing to speak afterwards."));

        rulesList.add(new Mun_Rules("POINTS", "1. Point of Personal Privilege- A delegate may raise the Point of Personal Privilege in case of whichever kind of personal discomfort which prevents him from full participation in the debate. Such thing can be for example audibility of other speakers, switching of air-\n" +
                "conditioning etc. A Point of Personal Privilege can interrupt speaker only in the case of bad audibility.\n" +
                "2. Point of Parliamentary Inquiry- A delegate may raise the Point of Parliamentary Inquiry in order to clarify certain aspects of the Rules of Procedure by the Chairpersons. Such Point may not interrupt speakers and can be introduced only when the Floor is open for Points and Motions.\n" +
                "3. Point of Order- A delegate may raise the Point of Order if there is a discrepancy or any improperness in the application of the Rules ofProcedure by the delegates or Chairpersons. It is in the discretion ofChairs to decide whether their point is valid and to clarify any irregularities. The Point of Order may not interrupt a speech"));

        rulesList.add(new Mun_Rules("RIGHT OF REPLY", "1. Delegate, whose country ́s national integrity or sovereignty has been contested, may require Right of Reply. The Chairpersons may decide to give a certain time limit to the Delegate to respond and to rule whether the Right of Reply is in order. The decision of the Chairs is not subject to appeal.\n" +
                "2. The right of Reply does not concern any case of personal insults or challenges. Such issues are to be dealt with individually according to the code of conduct of the ModelUN Conferences conference"));

        rulesList.add(new Mun_Rules("WORKING PAPERS", "1. Working papers are intended to aid with the work of the Committee and especially the Draft Resolution, as well as to present the viewpoints of the delegates and the potential solutions to the topic.\n" +
                "2. Any delegate can introduce Working Paper for the consideration of the Committee. The Working Paper has to be approved by the Chairpersons before its distribution.\n" +
                "3. Working paper does not require any Signatories or Sponsors. However, it should bear the name of the delegate or delegates who proposed it.\n" +
                "4. There is no set format for the Working Paper, i.e. the working paper does not have to be introduced in the resolution format. The Working Paper is referred to by its designated number.\n" +
                "5. Any document of the United Nations or other organizations, as well as charts and tables, can be introduced as a Working Paper as long as they are relevant to the topic discussed. It is at the discretion of the Secretary-General to withdraw a Working Paper which is deemed inappropriate or not contributing to the discussion."));

        rulesList.add(new Mun_Rules("DRAFT RESOLUTIONS", "1. Draft Resolution means a document drafted in the official format of the resolution.\n" +
                "2. No Draft Resolution shall be circulated without the previous approval of its required format and number of Sponsors and Signatories by the Chairpersons.\n" +
                "3. The ones recognized as the writers of the Draft Resolution are called “Sponsors”. Chairpersons will set the required minimum number of Sponsors according to each Committee.4. “Signatories” are the ones supporting the discussion regarding the Draft Resolution on the Floor and bear no further obligation. Chairpersons will set the required minimum number of Signatories according to each Committee.5. One Member State cannot be “Sponsor” and “Signatory” at the same time.6. Once the Draft Resolution has been introduced delegates cannot add themselves to the list of Sponsors anymore. However, they can be removed from the list by passing a request in written form to the Chairs. If the Draft Resolution does not have the number of Sponsors required, the document will be removed from the Floor immediately.\n" +
                "7. Delegates wishing to be added or removed from the list of Signatories can do so at any time. The request to do so should be passed to the Chairs in written form.8. More than one Draft Resolution can be on the Floor at once."));

        rulesList.add(new Mun_Rules("INTRODUCING A DRAFT RESOLUTION", "1. After the approval of the Draft Resolution by Chairpersons and the Secretary-General, the Draft Resolution will be assigned a number and distributed between the delegates.\n" +
                "2. A Sponsor of the Draft Resolution shall introduce it upon passing of the Motion for a Moderated Caucus with the purpose of the Introduction of the Draft Resolution. Afterwards, the Sponsor has the Floor to introduce the Draft Resolution, while the Introduction should be limited to the reading of the Operative Clauses.\n" +
                "3. Subsequently, the Sponsor shall respond to inquiries regarding the clarification of the Draft Resolution. The Session for the inquiries shall not exceed five minutes. Any substantive statements regarding the Draft are not in order during the Inquiries Session.\t" +
                "4. It is at the discretion of the Secretary-General to provide any comments, objections or suggestions for improvement to the Draft Resolution."));

        rulesList.add(new Mun_Rules("WITHDRAWAL OF A DRAFT RESOLUTION", "1. A Draft Resolution may be withdrawn at any time from the Floor by its Sponsors before the voting upon the document has started. Incase that the Sponsor wishes to do so, he shall send a written request to the Chairpersons.\n" +
                "2. The same rules apply to the withdrawal of Amendments.3. A Draft Resolution cannot be withdrawn if there is an Unfriendly Amendment on the Floor."));

        rulesList.add(new Mun_Rules("AMENDMENTS", "1. An amendment is a document which modifies, deletes, adds or revises one or more parts of the Draft Resolution.\n" +
                "2. Amendment can be proposed by any delegate on any part or Clause of the Draft Resolution. All amendments shall be submitted in the written form to the Chairpersons.3. Amendments to Preambulatory Clauses are out of order.4. Any grammatical, spelling or formatting mistakes in the Draft Resolution shall be corrected without a vote. The final corrections are at the discretion of Chairpersons.5. There are two types of Amendments:a. Friendly Amendment- Amendments accepted and approved by allSponsors of the Draft Resolution shall be considered Friendly. They shall be implemented in the Draft Resolution without the need to be voted upon.b. Unfriendly Amendment- Amendments not approved by all the Sponsors shall be considered Unfriendly. The required number of Sponsors needed for Unfriendly Amendment shall be set by the Chairpersons according to each Committee. Any Amendments to Unfriendly Amendment are out of order. Unfriendly Amendment can be withdrawn from the Floor by all its Sponsors before being voted upon; such withdrawal shall be submitted in the written form to the Chairpersons."));

        rulesList.add(new Mun_Rules("PROCEDURAL AND SUBSTANTIVE VOTES", "1. Substantive votes shall be considered those referring to resolutions, amendments or their parts. During substantive votes, delegates can vote either in favor, against or can abstain. Delegatesstating “Present and voting” during the Roll Call cannot abstain. Substantive voting is being done bthe Member Stateses only, i.e. observers have no right to vote.\n." +
                "2. Procedural vote shall be considered those referring to other voting procedures with the exception of those mentioned above. During procedural vote, no abstentions are allowed. During procedural voting, all observes have the right to vote."));

        rulesList.add(new Mun_Rules("MAJORITIES IN VOTING", "1. A Simple Majority requires a majority of ½ + 1 of all delegates “Present and voting” to be in favor.\n" +
                "2. A Qualified Majority requires 2/3 of all delegates “Present and voting” to be in favor.\n" +
                "3. All procedural and substantive matters shall be passed by a Simple Majority unless stated otherwise."));

        rulesList.add(new Mun_Rules("REQUIRED MAJORITIES IN VOTING", "1. Motions requiring a Simple Majority:\n" +
                "\ta. Motion to Verify the Quorumb. Motion to Set the Agenda.\n" +
                "\tc. Motion to Open Speaker ́s List.\n" +
                "\td. Motion for the Moderated Caucus (and its extension).\n" +
                "\te. Motion for the Un-Moderated Caucus (and its extension)\n" +
                "\tf. Motion to Resume Debateg. Motion for Adjournment of the Meeting\n" +
                "2. Motions requiring a Qualified Majoritya. Motion to Table the Debateb. Motion for the Closure of the Debate"));

        rulesList.add(new Mun_Rules("VOTING PROCEDURE", "PROCEDURE1. If the Speaker ́s List is exhausted or the Motion for the Closure ofthe Debate and Moving into Voting Procedure shall be accepted, all the Draft Resolutions and Amendments on the Floor shall be put to vote.\n" +
                "2. During the Voting Procedure no Representative aside from the Secretary-General and authorized persons by the Secretary-General may enter or leave the room. The Chairpersons shall secure the doors so that the Voting Procedure is not interrupted. Delegates shall refrain from any communication within the room and shall not speak unless requested by the Chairpersons, while raising Points or when the Floor is open to the Points and Motions.\n" +
                "3. Each Representative has one vote. Voting shall be done by Delegates raising their placards, unless stated otherwise. Each Delegate may vote “In Favor”, “Against” or “Abstain”.\n" +
                "4. The Representative may decide to vote “In favor with rights” or “Against with rights” in order to be granted the right to explain the vote during the Roll Call Vote. The Chairpersons shall set the speaking time for the explanation of the vote.ROLL "));

        rulesList.add(new Mun_Rules("ROLL CALL VOTE", "1. During Roll Call Vote, each Representative shall vote in the alphabetical order, beginning with the Member State drawn randomly by the Chairpersons.\n" +
                "2. During such Vote, Chairpersons shall call upon each Representative separately who verbally announces his/her vote to the Committee. The Delegate shall state “In Favor”, “Against”, “Abstain” or “Pass”.\n" +
                "3. During the Roll Call Vote, a Representative may decide to “Pass” once, i.e. for one round of voting he/she does not have to state his opinion. However, subsequently the Delegate must vote either “In Favor” or “Against” when being called upon the second time."));

        rulesList.add(new Mun_Rules("VOTING ON AMENDMENTS", "1. Voting on Amendments to the Draft Resolutions on the Floor shallhave precedence to Draft Resolutions.\n" +
                "2. In case of two or more Amendments being proposed on the Floor, the Chairpersons shall determine the order in which they shallbe voted upon. The most disruptive Amendments shall be put to vote first.\n" +
                "3. Amendments that pass shall be incorporated into the Draft Resolution immediately"));

        rulesList.add(new Mun_Rules("ORDER OF VOTING", "1. In case of two or more Draft Resolutions being on the Floor, the Representatives shall vote upon them in the order they were submitted.\n" +
                "2. The order of the Draft Resolutions being voted upon can be altered by the “Motion to Reorder Draft Resolution” which can propose different order than the one being currently in place. The Motion requires a Simple Majority to pass. The Motion can be proposed only after the Closure of the Debate.\n" +
                "3. The Draft Resolution shall be voted upon as whole unless the Motion to Divide the Question may be introduced. By this Motion theDelegate suggests each Clause to be voted upon separately. Preambulatory clauses and sub-operative clauses may not be separated and voted upon independently. If there is an opposition against the Motion, two Speakers “for” and “against” can be selected- the Motion requires a Simple Majority to pass and can be proposed only after the Closure of the Debate.\n" +
                "4. In case of two or more Draft Resolutions being on the Floor, the Representatives shall first vote upon all Amendments to all Draft Resolutions and at the end upon the Draft Resolutions themselves.\n" +
                "5. If one of two or more Draft Resolutions passes, the other(s) automatically fail without being voted upon"));

        rulesList.add(new Mun_Rules("Appendix 1.1: THE PRECEDENCE OF POINTS AND MOTIONS", "Representatives shall vote upon them in the order they were submitted.\n" +
                "2. The order of the Draft Resolutions being voted upon can be altered by the “Motion to Reorder Draft Resolution” which can propose different order than the one being currently in place. The Motion requires a Simple Majority to pass. The Motion can be proposed only after the Closure of the Debate.\n" +
                "3. The Draft Resolution shall be voted upon as whole unless the Motion to Divide the Question may be introduced. By this Motion theDelegate suggests each Clause to be voted upon separately. Preambulatory clauses and sub-operative clauses may not be separated and voted upon independently. If there is an opposition against the Motion, two Speakers “for” and “against” can be selected- the Motion requires a Simple Majority to pass and can be proposed only after the Closure of the Debate.4. In case of two or more Draft Resolutions being on the Floor, the Representatives shall first vote upon all Amendments to all Draft Resolutions and at the end upon the Draft Resolutions themselves.5. If one of two or more Draft Resolutions passes, the other(s) automatically fail without being voted upon.SPECIAL RULESSECURITY COUNCILVOTING AND MAJORITIES1. For voting on Substantive Matters an affirmative vote of nine Members of the Security Council including the votes of the five Permanent Members shall be needed.2. For voting on Procedural Matters the general rules of other committees apply.Appendix 1.1: THE PRECEDENCE OF POINTS AND MOTIONSAs for the precedence of motions, the most disruptive one shall be voted upon as the first one. In case that a Motion with the higher \n" +
                "precedence passes, the rest of the Motions are automatically considered to be ruled out and the committee will not vote upon them anymore.In order at any time, including speeches and Voting Procedure1. Point of Personal Privilege2. Point of Order3. Point of Parliamentary Inquiry (not in order during speeches)In order when the Floor is open:1. Motion for Closure of the Debate2. Motion to Table the Debate3. Motion for Adjournment of the Meeting4. Motion for Suspension of the Meeting5. Motion to Resume Debate6. Motion to Introduce an Amendment7. Motion to Introduce a Working Paper8. Motion for Un-moderated Caucus (its Extension has precedence)9. Motion for Moderated Caucus (its Extension has precedence)10. Motion to Change the Speaking Time11. Motion to Open the Speaker ́s ListIn order after the Closure of the Debate:1. Motion to Reorder Draft Resolutions2. Motion to Divide the Question3. Motion for the Roll Call"));

        MunAdapter adapter = new MunAdapter(rulesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

}
