// to let the operation on dataBase executed in background of the activity

package com.example.roombasic;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private LiveData<List<Word>>allWordsLive;
    private WordDao wordDao;

    public WordRepository(Context context) {
        WordDatabase wordDatabase = WordDatabase.getDatabase(context.getApplicationContext());
        wordDao =  wordDatabase.getWordDao();
        allWordsLive = wordDao.getAllWordLive();
    }

    public LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }

    void insertWords(Word... words) {
        new InsertAsyncTask(wordDao).execute(words);
    }

    void updateWords(Word... words) {
        new UpdateAsyncTask(wordDao).execute(words);
    }

    void deleteWords(Word... words) {
        new DeleteAsyncTask(wordDao).execute(words);
    }

    void deleteAllWords(Word... words) {
        new DeleteAllAsyncTask(wordDao).execute();
    }

    static class InsertAsyncTask extends AsyncTask<Word,Void,Void> {
        private WordDao wordDao;
        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Word,Void,Void> {
        private WordDao wordDao;
        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWords(words);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Word,Void,Void> {
        private WordDao wordDao;
        public DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }
    }

    static class DeleteAllAsyncTask extends AsyncTask<Word,Void,Void> {
        private WordDao wordDao;
        public DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteAllWords();
            return null;
        }
    }
}
