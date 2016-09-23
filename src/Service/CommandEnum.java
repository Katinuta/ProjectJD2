package Service;

/**
 * class contains enumeration of commands and creating object of command and
 * method for getting command
 */

public enum CommandEnum {
	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	SUBJECT {
		{
			this.command = new SubjectCommand();
		}
	},
	TEST {
		{
			this.command = new TestCommand();
		}
	},
	ADDTEST {
		{
			this.command = new AddTestCommand();
		}
	},
	ADDQUESTION {
		{
			this.command = new AddQuestionCommand();
		}
	},
	ADDANSWERS {
		{
			this.command = new AddAnswersCommand();
		}
	},
	SAVETEST {
		{
			this.command = new SaveTestCommand();
		}
	},
	CHANGETEST {
		{
			this.command = new ChangeTestCommand();
		}
	},
	DELETEQUESTION {
		{
			this.command = new DeleteQuestionCommand();
		}
	},
	DELETETEST {
		{
			this.command = new DeleteTestCommand();
		}
	},
	CHOOSEPASSTEST {
		{
			this.command = new ChoosePassTestCommand();
		}
	},
	CHECKTEST {
		{
			this.command = new CheckTestCommand();
		}
	},
	SAVERESULT {
		{
			this.command = new SaveResultCommand();
		}
	},
	LOOKRESULT {
		{
			this.command = new LookResultCommand();
		}
	},
	REGISTRATION {
		{
			this.command = new RegistrationCommand();
		}
	},
	OPENFORM {
		{
			this.command = new OpenFormCommand();
		}
	},
	RETURNMAIN {
		{
			this.command = new ReturnMainCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogOutCommand();
		}
	},
	ADDSUBJECT {
		{
			this.command = new AddSubjectCommand();
		}
	},
	SAVESUBJECT {
		{
			this.command = new SaveSubjectCommand();
		}
	};
	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
}
